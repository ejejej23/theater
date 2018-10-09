package source;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.util.DBConn;

public class PaymentFrame extends JFrame implements ActionListener {
   private static final long serialVersionUID = 1L;
   private Connection conn = DBConn.getConnection();
   private PayDTO dto = new PayDTO();

   private JPanel contentPane;
   private JTextField useMileage;

   // // 받아와야하는 정보 : 현재 마일리지, 핸드폰번호,티켓가격
   // // 테스트로 미리 값줘놓음 -> 나중에 바꿔야함

   private JButton btn1;// 마일리지적용버튼
   private JButton btn2;// 결제버튼
   private JButton submitbtn1;// 승인정보 전송버튼
   private JButton submitbtn2;// 이메일 정보 전송버튼

   // 카드결제용 라디오버튼
   JRadioButton cardrad1;
   JRadioButton cardrad2;
   JRadioButton cardrad3;
   JRadioButton cardrad4;
   JRadioButton cardrad5;
   JRadioButton cardrad6;

   // 무통장입금용 라디오버튼
   JRadioButton bankrad1;
   JRadioButton bankrad2;
   JRadioButton bankrad3;
   JRadioButton bankrad4;
   JRadioButton bankrad5;
   JRadioButton bankrad6;

   private JLabel lbPrice; // 티켓금액
   private JLabel lbTotPrice;// 총 결제금액
   private JLabel label_11; // 포인트 적용메시지
   private JLabel lblNewLabel_2;// 승인번호 전송메시지
   private JLabel lblNewLabel_3;// 이메일 전송

   private JPanel panelt1; // 탭1 : cardPay
   private JPanel panelt2; // 탭1 : phonePay
   private JPanel panelt3; // 탭1 : cashPay
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;

   JTabbedPane tabbedPane;
   private JTextField payerName;

   // 마일리지 가져오기 : 디비연결 됨
   public void getMyMileage() {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql;

      try {
         sql = "SELECT NOWMILE FROM MILEAGE WHERE MEMCODE=? ORDER BY HISTORYDATE DESC";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyCode());
         rs = pstmt.executeQuery();

         if (rs.next()) {
            dto.setMileage(rs.getInt("NOWMILE"));
         } else {
            dto.setMileage(0);
         }

      } catch (Exception e) {
         System.out.println(e.toString());
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

   // 예매관련 전부~~~~
   public void insertYemaeInfo() {

      System.out.println("==========DB연결==========");
      // 예매테이블 입력
      yemaeTable();

      // 예매좌석 입력
      int seats = dto.getMyseats().length;
      System.out.println("총 좌석 수 : " + seats);
      for (int i = 0; i < seats; i++) {
         System.out.println(dto.getMyseats(i));
         yemaeSeatTable(i);
      }

      // 예매할인 입력 : 마일리지 사용시에만
      if (dto.getUseM() > 0) {
         // 마일리지 할인내역 추가
         saleTable();
         // 현 마일리지에서 사용한 포인트 빼기
         mileageTable2();
      }

      // 마일리지 입력 1 : 예매포인트 적립
      mileageTable1();

      // 결제테이블
      payTable();

      System.out.println("==========예매완료==========");

      // 예매정보 보여주기
      showInfo();
   }

   // 예매코드 가져오기: 예매수행된 후 불러야함
   public int getSeqNum() {
      PreparedStatement pstmt = null;
      String sql;
      ResultSet rs = null;
      int seqNum = 0;

      try {
         sql = "SELECT SEQMOVIE.CURRVAL FROM DUAL";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            seqNum = rs.getInt(1);
         }

      } catch (Exception e) {
         System.out.println(e.toString());
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
      return seqNum;
   }

   // 예매테이블 입력
   public void yemaeTable() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO YEMAE(YEMAECODE,ADULT,TEENAGER,PRICE,TICKETINGDATE,MEMCODE,SCREENPLAYCODE) "
               + "VALUES('예매'||SEQMOVIE.NEXTVAL,?,?,?,SYSDATE,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, dto.getAdultN());// 성인수
         pstmt.setInt(2, dto.getTeenagerN());// 청소년수
         pstmt.setInt(3, dto.getTotalPrice());// 가격 :최종결제금액
         pstmt.setString(4, dto.getMyCode());// 회원코드
         pstmt.setString(5, dto.getMyScreenCode());// 상영코드
         // pstmt.executeUpdate();

         int result = pstmt.executeUpdate();
         System.out.println(result + "행이 예매테이블에 추가됨");

         // 현재 예매코드 저장
         dto.setMyYemaeCode("예매" + getSeqNum());

      } catch (SQLException e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

   // 예매좌석 입력
   public void yemaeSeatTable(int num) {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO YEMAESEAT(SEATCODE,SEATINFO,YEMAECODE,SCREENPLAYCODE) VALUES('좌석'||SEQMOVIE.NEXTVAL,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyseats(num));
         pstmt.setString(2, dto.getMyYemaeCode());
         pstmt.setString(3, dto.getMyScreenCode());
         int r = pstmt.executeUpdate();

         if (r == 1)
            System.out.println("예매좌석이 추가됨 : 1석");
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
               e.printStackTrace();
            }
         }
      }
   }

   // 예매할인 입력 : 마일리지 사용시에만
   public void saleTable() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO YEMAESALE(YEMAECODE,SALECODE,MILEUSEJPRICE) VALUES(?,'할인'||SEQMOVIE.NEXTVAL,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyYemaeCode());
         pstmt.setInt(2, dto.getUseM());
         int r = pstmt.executeUpdate();
         if (r == 1)
            System.out.println("예매할인테이블에 정보저장 : 마일리지사용함");
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
               e.printStackTrace();
            }
         }
      }
   }

   // 마일리지 입력 1 : 예매포인트 적립
   public void mileageTable1() {
      PreparedStatement pstmt = null;
      String sql;

      getMyMileage();

      try {
         sql = "INSERT INTO MILEAGE(MILECODE,MEMCODE,NOWMILE,HISTORYDATE,NEAYEONG) VALUES('마일리지'||SEQMOVIE.NEXTVAL,?,?,SYSDATE,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyCode());
         pstmt.setInt(2, dto.getMileage() + 1000);
         pstmt.setString(3, 1000 + "포인트 : 예매포인트 적립");
         int r = pstmt.executeUpdate();

         if (r == 1) {
            System.out.println("mileage 1000포인트 적립됨");
            getMyMileage();
         }
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
               e.printStackTrace();
            }
         }
      }
   }

   // 마일리지 입력 2 : 마일리지 사용시에만 사용마일리지 계산해주기
   public void mileageTable2() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO MILEAGE(MILECODE,MEMCODE,NOWMILE,HISTORYDATE,NEAYEONG) VALUES('마일리지'||SEQMOVIE.NEXTVAL,?,?,SYSDATE,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyCode());
         pstmt.setInt(2, dto.getMileage() - dto.getUseM());
         pstmt.setString(3, dto.getUseM() + "포인트 : 예매할인");
         int r = pstmt.executeUpdate();
         if (r == 1)
            System.out.println("마일리지 할인내용 저장됨");
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
               e.printStackTrace();
            }
         }
      }
   }

   // 결제테이블
   public void payTable() {
      PreparedStatement pstmt = null;
      String sql;

      // 결제방법에 따라 승인정보 입력
      int tabN = tabbedPane.getSelectedIndex();
      String paypay = "";

      switch (tabN) {
      case 0:
         paypay = "카드결제";
         break;
      case 1:
         paypay = "휴대폰결제";
         break;
      case 2:
         paypay = "무통장입금 결제";
         break;
      }

      try {
         sql = "INSERT INTO PAY(PAYCODE,PAYWAY,YEMAECODE) VALUES('결제'||SEQMOVIE.NEXTVAL,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, paypay);// 결제방법
         pstmt.setString(2, dto.getMyYemaeCode());// 예매코드
         // pstmt.executeUpdate();

         int result = pstmt.executeUpdate();
         System.out.println(result + "행이 결제테이블에 추가됨");

         // 현재 결제코드 저장
         dto.setMyPayCode("결제" + getSeqNum());

         if (tabN == 0) {
            payWay0();
         } else if (tabN == 1) {
            payWay1();
         } else if (tabN == 2) {
            payWay2();
         }

      } catch (SQLException e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

   // 결제0 : 카드결제
   public void payWay0() {
      PreparedStatement pstmt = null;
      String sql;

      if (cardrad1.isSelected()) {
         dto.setCardCom("우리카드");
      } else if (cardrad2.isSelected()) {
         dto.setCardCom("비씨카드");
      } else if (cardrad3.isSelected()) {
         dto.setCardCom("농협카드");
      } else if (cardrad4.isSelected()) {
         dto.setCardCom("국민카드");
      } else if (cardrad5.isSelected()) {
         dto.setCardCom("신한카드");
      } else if (cardrad6.isSelected()) {
         dto.setCardCom("카카오카드");
      }

      try {
         sql = "INSERT INTO CARDPAY(CARDPAYCODE,PAYCODE,PAYINFO) VALUES('카드결제'||SEQMOVIE.NEXTVAL,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyPayCode());
         pstmt.setString(2, dto.getCardCom());
         int r = pstmt.executeUpdate();
         if (r == 1)
            System.out.println("카드결제 내용저장");
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
               e.printStackTrace();
            }
         }
      }
   }

   // 결제1 : 폰 소액결제
   public void payWay1() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO PHONEPAY(PHONEPAYCODE,PAYCODE,PAYINFO) VALUES('폰결제'||SEQMOVIE.NEXTVAL,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyPayCode());// 결제코드 시퀀스
         pstmt.setString(2, dto.getPhone()); // 승인정보 : 폰번호 보내주기
         int r = pstmt.executeUpdate();

         if (r == 1)
            System.out.println("폰 결제내역 저장됨");
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
               e.printStackTrace();
            }
         }
      }
   }

   // 결제2 : 현금 무통장입금
   public void payWay2() {
      PreparedStatement pstmt = null;
      String sql;

      if (bankrad1.isSelected()) {
         dto.setBankCom("우리은행");
      } else if (bankrad2.isSelected()) {
         dto.setBankCom("비씨은행");
      } else if (bankrad3.isSelected()) {
         dto.setBankCom("농협은행");
      } else if (bankrad4.isSelected()) {
         dto.setBankCom("국민은행");
      } else if (bankrad5.isSelected()) {
         dto.setBankCom("신한은행");
      } else if (bankrad6.isSelected()) {
         dto.setBankCom("카카오은행");
      }

      try {
         sql = "INSERT INTO CASHPAY(CASHPAYCODE,PAYCODE,PAYNAME,PAYDATE,PRICE,BANKNAME) VALUES('현금결제'||SEQMOVIE.NEXTVAL,?,?,SYSDATE,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyPayCode());// 결제코드 시퀀스
         pstmt.setString(2, payerName.getText());
         pstmt.setInt(3, dto.getTotalPrice());
         pstmt.setString(4, dto.getBankCom());
         int r = pstmt.executeUpdate();

         if (r == 1)
            System.out.println("무통장입금내역 저장됨");
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

   public void thisYemea(String yemaecode) {
      StringBuffer sb = new StringBuffer();
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      // 필요한거 : 예매내역에 보여줄 정보
      // 예매코드
      try {
         sb.append("SELECT Y.YEMAECODE myYemaeCode, TO_CHAR(TICKETINGDATE,'YYYYMMDD') TICKETINGDATE,  ");
         sb.append("TITLE mTitle, STARTTIME mstartTime,SCREENNAME,TO_CHAR(SCREENDATE,'YYYYMMDD') PLAYDATE, ");
         sb.append("NVL(MILEUSEJPRICE,0) USEM, ADULT+TEENAGER PERN,PRICE totalprice ");
         sb.append("FROM YEMAE Y ");
         sb.append(" LEFT OUTER JOIN YEMAESALE YS2 ON Y.YEMAECODE=YS2.YEMAECODE ");
         sb.append(" LEFT OUTER JOIN SCREENPLAY SP ON Y.SCREENPLAYCODE= SP.SCREENPLAYCODE ");
         sb.append(" LEFT OUTER JOIN SCREEN S ON SP.SCREENCODE=S.SCREENCODE ");
         sb.append(" LEFT OUTER JOIN MOVIE M ON M.MOVIECODE=SP.MOVIECODE ");
         sb.append("WHERE Y.YEMAECODE =?");

         pstmt = conn.prepareStatement(sb.toString());
         pstmt.setString(1, dto.getMyYemaeCode());
         rs = pstmt.executeQuery();

         if (rs.next()) {
            dto.setTicketingdate(rs.getString("ticketingdate"));
            dto.setmTitle(rs.getString("mTitle"));
            dto.setMstartTime(rs.getString("mstartTime"));
            dto.setScreenName(rs.getString("screenname"));
            dto.setPlaydate(rs.getString("playdate"));

            // System.out.println("티켓팅날짜 :" + dto.getTicketingdate() + " 제목 :" +
            // dto.getmTitle() + " 상영시작시간 : "
            // + dto.getMstartTime() + " 상영관이름: " + dto.getScreenName() + " 상영날짜 :" +
            // dto.getPlaydate());
         }

      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
         } catch (Exception e) {

         }

         try {
            if (pstmt != null)
               pstmt.close();
         } catch (Exception e2) {

         }
      }
   }

   public void showInfo() {
      thisYemea(dto.getMyCode());

      System.out.println("==========예매정보==========");
      // 예매한 회원코드
      System.out.println("회원코드 : " + dto.getMyCode());
      // 예매코드
      System.out.println("예매코드 : " + dto.getMyYemaeCode());

      // 영화관련 정보는 조인해서 가져와야함
      // 영화제목
      System.out.println("영화제목 : " + dto.getmTitle());
      // 예매한 상영관
      System.out.println("상영관코드 : " + dto.getScreenName());
      // 상영시간
      System.out.println("상영시간 : " + dto.getMstartTime());

      // 예매한 좌석정보
      System.out.print("예매 좌석 : ");
      for (int i = 0; i < dto.getMyseats().length; i++)
         System.out.print(dto.getMyseats(i) + " ");
      System.out.println();
      // 사용 마일리지
      System.out.println("사용 마일리지 : " + dto.getUseM());
      // 할인 및 적립 후 마일리지
      getMyMileage();
      System.out.println("남은 마일리지 :" + dto.getMileage());
      System.out.println("========================");

      ResultFrame f = new ResultFrame(dto);
      f.setVisible(true);
   }

   /**
    * Create the frame.
    */
   // 받아와야하는 정보 payment 생성자 매개변수 꼭 써주기
   public PaymentFrame(YemaeDTO dtoY, List<String> seats) {

      // System.out.println(dtoY.getMemcode()+"/"+dtoY.getSccreenplayCode()+"/"+dtoY.getPrice()+"/"+dtoY.getAdult()+"/"+dtoY.getTeenager());
      // System.out.println(dtoY.getMemcode()+"memcode");

      dto.setMyCode(dtoY.getMemcode());
      dto.setMyScreenCode(dtoY.getSccreenplayCode());
      dto.setTicketPrice(dtoY.getPrice());
      dto.setAdultN(dtoY.getAdult());
      dto.setTeenagerN(dtoY.getTeenager());

      List<String> list = seats;
      String[] mySeats = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
         mySeats[i] = list.get(i);
      }

      dto.setMyseats(mySeats);

      setTitle("결제창");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 450, 535);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(null);
      setContentPane(contentPane);

      JLabel lblTicketPrice = new JLabel("\u25B6 Ticket price :");
      lblTicketPrice.setBounds(12, 10, 91, 25);
      contentPane.add(lblTicketPrice);

      JLabel label = new JLabel("\\");
      label.setBackground(Color.WHITE);
      label.setBounds(111, 10, 11, 25);
      contentPane.add(label);

      btn2 = new JButton("\uACB0\uC81C\uD558\uAE30");
      btn2.addActionListener(this);
      btn2.setBounds(238, 463, 172, 23);
      contentPane.add(btn2);

      JLabel lblTotalPrice = new JLabel("total price : ");
      lblTotalPrice.setBounds(190, 433, 67, 15);
      contentPane.add(lblTotalPrice);

      JLabel lblMyMileage = new JLabel("\u25B6 My mileage :");
      lblMyMileage.setBounds(12, 41, 91, 25);
      contentPane.add(lblMyMileage);

      // 현재 마일리지 조회
      getMyMileage();
      lbPrice = new JLabel(dto.getMileage() + "");
      lbPrice.setOpaque(true);
      lbPrice.setBackground(Color.WHITE);
      lbPrice.setBounds(111, 41, 67, 25);
      contentPane.add(lbPrice);

      JLabel lblPoint = new JLabel("point ");
      lblPoint.setBackground(Color.WHITE);
      lblPoint.setBounds(190, 41, 66, 25);
      contentPane.add(lblPoint);

      // 티켓가격 표시라벨

      JLabel label_1 = new JLabel(dto.getTicketPrice() + "");
      label_1.setBackground(Color.WHITE);
      label_1.setBounds(128, 10, 111, 25);
      contentPane.add(label_1);

      JLabel label_2 = new JLabel("\\");
      label_2.setBackground(Color.WHITE);
      label_2.setBounds(264, 428, 11, 25);
      contentPane.add(label_2);

      lbTotPrice = new JLabel(dto.getTicketPrice() + "");
      lbTotPrice.setOpaque(true);
      lbTotPrice.setBackground(Color.WHITE);
      lbTotPrice.setBounds(281, 428, 111, 25);
      contentPane.add(lbTotPrice);

      // useMileage 입력창
      useMileage = new JTextField();
      useMileage.setBounds(111, 76, 67, 21);
      contentPane.add(useMileage);
      useMileage.setColumns(10);

      btn1 = new JButton("\uB9C8\uC77C\uB9AC\uC9C0 \uC801\uC6A9");
      btn1.addActionListener(this);
      btn1.setBounds(238, 75, 172, 23);
      contentPane.add(btn1);

      tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(12, 125, 410, 264);
      contentPane.add(tabbedPane);

      // cardPay
      panelt1 = new JPanel();
      panelt1.setBackground(Color.WHITE);
      JLabel labelt1 = new JLabel("\u25B6 \uCE74\uB4DC\uC0AC \uC120\uD0DD");
      panelt1.setLayout(null);
      panelt1.add(labelt1);

      tabbedPane.add("cardPay", panelt1); // 카드페이 탭

      JLabel lblNewLabel = new JLabel("\u25B6 \uCE74\uB4DC\uC0AC \uC120\uD0DD");
      lblNewLabel.setBounds(28, 22, 94, 15);
      panelt1.add(lblNewLabel);

      cardrad1 = new JRadioButton("\uC6B0\uB9AC\uCE74\uB4DC");
      cardrad1.setBackground(Color.WHITE);
      cardrad1.setBounds(38, 43, 121, 23);
      panelt1.add(cardrad1);

      cardrad2 = new JRadioButton("\uBE44\uC528\uCE74\uB4DC");
      cardrad2.setBackground(Color.WHITE);
      cardrad2.setBounds(38, 68, 121, 23);
      panelt1.add(cardrad2);

      cardrad3 = new JRadioButton("\uB18D\uD611\uCE74\uB4DC");
      cardrad3.setBackground(Color.WHITE);
      cardrad3.setBounds(38, 93, 121, 23);
      panelt1.add(cardrad3);

      cardrad4 = new JRadioButton("\uAD6D\uBBFC\uCE74\uB4DC");
      cardrad4.setBackground(Color.WHITE);
      cardrad4.setBounds(239, 43, 121, 23);
      panelt1.add(cardrad4);

      cardrad5 = new JRadioButton("\uC2E0\uD55C\uCE74\uB4DC");
      cardrad5.setBackground(Color.WHITE);
      cardrad5.setBounds(239, 68, 121, 23);
      panelt1.add(cardrad5);

      cardrad6 = new JRadioButton("\uCE74\uCE74\uC624\uCE74\uB4DC");
      cardrad6.setBackground(Color.WHITE);
      cardrad6.setBounds(239, 93, 121, 23);
      panelt1.add(cardrad6);

      // 라디오버튼 그룹화 : cardPay
      ButtonGroup group1 = new ButtonGroup();
      group1.add(cardrad1);
      group1.add(cardrad2);
      group1.add(cardrad3);
      group1.add(cardrad4);
      group1.add(cardrad5);
      group1.add(cardrad6);

      // phonePay
      panelt2 = new JPanel();
      panelt2.setBackground(Color.WHITE);
      JLabel labelt2 = new JLabel("phonePay");
      panelt2.add(labelt2);
      tabbedPane.add("phonePay", panelt2);
      panelt2.setLayout(null);

      JLabel label_3 = new JLabel("\u25B6 \uD734\uB300\uD3F0 \uC18C\uC561\uACB0\uC81C");
      label_3.setBounds(12, 38, 123, 15);
      panelt2.add(label_3);

      JLabel label_4 = new JLabel("");
      label_4.setOpaque(true);
      label_4.setBackground(Color.WHITE);
      label_4.setBounds(52, 45, 83, 22);
      panelt2.add(label_4);

      JLabel label_5 = new JLabel(dto.getPhone());
      label_5.setOpaque(true);
      label_5.setBackground(Color.WHITE);
      label_5.setBounds(159, 45, 146, 22);
      panelt2.add(label_5);

      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(159, 77, 123, 21);
      panelt2.add(textField_1);

      submitbtn1 = new JButton("\uC804\uC1A1");
      submitbtn1.addActionListener(this);
      submitbtn1.setBounds(288, 76, 105, 23);
      panelt2.add(submitbtn1);

      JLabel label_6 = new JLabel(": \uC2B9\uC778\uBC88\uD638");
      label_6.setOpaque(true);
      label_6.setBackground(Color.WHITE);
      label_6.setBounds(52, 77, 83, 22);
      panelt2.add(label_6);

      JLabel label_7 = new JLabel(": \uC774\uBA54\uC77C\uC8FC\uC18C");
      label_7.setOpaque(true);
      label_7.setBackground(Color.WHITE);
      label_7.setBounds(52, 127, 83, 22);
      panelt2.add(label_7);

      textField_2 = new JTextField();
      textField_2.setColumns(10);
      textField_2.setBounds(52, 161, 83, 21);
      panelt2.add(textField_2);

      submitbtn2 = new JButton("\uACB0\uC81C\uC815\uBCF4\uC804\uC1A1");
      submitbtn2.addActionListener(this);
      submitbtn2.setBounds(288, 160, 105, 23);
      panelt2.add(submitbtn2);

      textField_3 = new JTextField();
      textField_3.setColumns(10);
      textField_3.setBounds(159, 161, 123, 21);
      panelt2.add(textField_3);

      JLabel lblNewLabel_1 = new JLabel("@");
      lblNewLabel_1.setBounds(140, 164, 18, 15);
      panelt2.add(lblNewLabel_1);

      lblNewLabel_2 = new JLabel("");
      lblNewLabel_2.setBounds(159, 103, 234, 22);
      panelt2.add(lblNewLabel_2);

      lblNewLabel_3 = new JLabel("");
      lblNewLabel_3.setBounds(159, 193, 234, 22);
      panelt2.add(lblNewLabel_3);

      // cashPay
      panelt3 = new JPanel();
      panelt3.setBackground(Color.WHITE);
      JLabel labelt3 = new JLabel("cashPay");
      panelt3.setLayout(null);
      panelt3.add(labelt3);
      tabbedPane.add("cashPay", panelt3);

      JLabel label_8 = new JLabel("\u25B6 \uC740\uD589 \uC120\uD0DD");
      label_8.setBounds(28, 59, 94, 15);
      panelt3.add(label_8);

      bankrad1 = new JRadioButton("\uC6B0\uB9AC\uC740\uD589");
      bankrad1.setBackground(Color.WHITE);
      bankrad1.setBounds(38, 80, 121, 23);
      panelt3.add(bankrad1);

      bankrad2 = new JRadioButton("\uBE44\uC528\uC740\uD589");
      bankrad2.setBackground(Color.WHITE);
      bankrad2.setBounds(38, 105, 121, 23);
      panelt3.add(bankrad2);

      bankrad3 = new JRadioButton("\uB18D\uD611\uC740\uD589");
      bankrad3.setBackground(Color.WHITE);
      bankrad3.setBounds(38, 130, 121, 23);
      panelt3.add(bankrad3);

      bankrad4 = new JRadioButton("\uAD6D\uBBFC\uC740\uD589");
      bankrad4.setBackground(Color.WHITE);
      bankrad4.setBounds(239, 80, 121, 23);
      panelt3.add(bankrad4);

      bankrad5 = new JRadioButton("\uC2E0\uD55C\uC740\uD589");
      bankrad5.setBackground(Color.WHITE);
      bankrad5.setBounds(239, 105, 121, 23);
      panelt3.add(bankrad5);

      bankrad6 = new JRadioButton("\uCE74\uCE74\uC624\uC740\uD589");
      bankrad6.setBackground(Color.WHITE);
      bankrad6.setBounds(239, 130, 121, 23);
      panelt3.add(bankrad6);

      JCheckBox chckbxNewCheckBox = new JCheckBox("\uD604\uAE08\uC601\uC218\uC99D \uBC1C\uD589");
      chckbxNewCheckBox.setBackground(Color.WHITE);
      chckbxNewCheckBox.setBounds(28, 163, 115, 23);
      panelt3.add(chckbxNewCheckBox);

      JRadioButton cardrad14 = new JRadioButton("\uC9C0\uCD9C\uC99D\uBE59\uC6A9");
      cardrad14.setBackground(Color.WHITE);
      cardrad14.setBounds(239, 188, 121, 23);
      panelt3.add(cardrad14);

      JRadioButton cardrad13 = new JRadioButton("\uC18C\uB4DD\uACF5\uC81C\uC6A9");
      cardrad13.setBackground(Color.WHITE);
      cardrad13.setBounds(38, 188, 121, 23);
      panelt3.add(cardrad13);

      // 라디오버튼 그룹화 : cashPay
      ButtonGroup group2 = new ButtonGroup();
      group2.add(bankrad1);
      group2.add(bankrad2);
      group2.add(bankrad3);
      group2.add(bankrad4);
      group2.add(bankrad5);
      group2.add(bankrad6);

      ButtonGroup group3 = new ButtonGroup();
      group3.add(cardrad13);
      group3.add(cardrad14);

      JLabel label_9 = new JLabel("\u25B6 \uC785\uAE08\uC790\uBA85");
      label_9.setBounds(28, 27, 106, 15);
      panelt3.add(label_9);

      payerName = new JTextField();
      payerName.setColumns(10);
      payerName.setBounds(137, 23, 223, 21);
      panelt3.add(payerName);

      label_11 = new JLabel("");
      label_11.setBackground(Color.WHITE);
      label_11.setBounds(36, 399, 346, 25);
      contentPane.add(label_11);

      JLabel label_12 = new JLabel("\u25B6 \uC0AC\uC6A9\uD558\uAE30");
      label_12.setBounds(12, 76, 91, 25);
      contentPane.add(label_12);

      JLabel lblPoint_1 = new JLabel("point ");
      lblPoint_1.setBackground(Color.WHITE);
      lblPoint_1.setBounds(190, 76, 66, 25);
      contentPane.add(lblPoint_1);

   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getSource() == btn1) {
         // 입력없이 버튼 누를때 예외처리
         if (useMileage.getText().length() == 0) {
            dto.setUseM(0);
            return;
         } else {
            dto.setUseM(Integer.parseInt(useMileage.getText()));
         }

         // 마일리지 적용버튼
         if (dto.getUseM() >= dto.getTicketPrice()) {
            // 결제금액보다 큰 마일리지 입력시
            label_11.setText("최대 티켓값만큼만 사용가능합니다");
         } else if (dto.getUseM() >= dto.getUseM()) {
            // 적용가능한 마일리지 크기일 때
            dto.setTotalPrice(dto.getTicketPrice() - dto.getUseM());
            lbTotPrice.setText(dto.getTotalPrice() + "");
            label_11.setText("");

         } else {
            // 마일리지가 쓰려는 마일리지보다 적을때
            label_11.setText("보유 마일리지만큼만 사용가능합니다");
         }

      } else if (e.getSource() == btn2) {
         // 결제버튼
         // DB에 insert : 예매정보 등
         insertYemaeInfo();
      } else if (e.getSource() == submitbtn1) {
         // 승인정보 전송메시지 표시
         lblNewLabel_2.setText("승인번호를 전송했습니다");

      } else if (e.getSource() == submitbtn2) {

         // 이메일주소 다 쳤으면
         if (!textField_2.getText().isEmpty() && !textField_3.getText().isEmpty()) {
            // 승인정보 이메일전송 표시
            lblNewLabel_3.setText("결제후 정보를 메일로 전송합니다");
         }
      }

   }
}