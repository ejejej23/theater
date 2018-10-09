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

   // // �޾ƿ;��ϴ� ���� : ���� ���ϸ���, �ڵ�����ȣ,Ƽ�ϰ���
   // // �׽�Ʈ�� �̸� ������� -> ���߿� �ٲ����

   private JButton btn1;// ���ϸ��������ư
   private JButton btn2;// ������ư
   private JButton submitbtn1;// �������� ���۹�ư
   private JButton submitbtn2;// �̸��� ���� ���۹�ư

   // ī������� ������ư
   JRadioButton cardrad1;
   JRadioButton cardrad2;
   JRadioButton cardrad3;
   JRadioButton cardrad4;
   JRadioButton cardrad5;
   JRadioButton cardrad6;

   // �������Աݿ� ������ư
   JRadioButton bankrad1;
   JRadioButton bankrad2;
   JRadioButton bankrad3;
   JRadioButton bankrad4;
   JRadioButton bankrad5;
   JRadioButton bankrad6;

   private JLabel lbPrice; // Ƽ�ϱݾ�
   private JLabel lbTotPrice;// �� �����ݾ�
   private JLabel label_11; // ����Ʈ ����޽���
   private JLabel lblNewLabel_2;// ���ι�ȣ ���۸޽���
   private JLabel lblNewLabel_3;// �̸��� ����

   private JPanel panelt1; // ��1 : cardPay
   private JPanel panelt2; // ��1 : phonePay
   private JPanel panelt3; // ��1 : cashPay
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;

   JTabbedPane tabbedPane;
   private JTextField payerName;

   // ���ϸ��� �������� : ��񿬰� ��
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

   // ���Ű��� ����~~~~
   public void insertYemaeInfo() {

      System.out.println("==========DB����==========");
      // �������̺� �Է�
      yemaeTable();

      // �����¼� �Է�
      int seats = dto.getMyseats().length;
      System.out.println("�� �¼� �� : " + seats);
      for (int i = 0; i < seats; i++) {
         System.out.println(dto.getMyseats(i));
         yemaeSeatTable(i);
      }

      // �������� �Է� : ���ϸ��� ���ÿ���
      if (dto.getUseM() > 0) {
         // ���ϸ��� ���γ��� �߰�
         saleTable();
         // �� ���ϸ������� ����� ����Ʈ ����
         mileageTable2();
      }

      // ���ϸ��� �Է� 1 : ��������Ʈ ����
      mileageTable1();

      // �������̺�
      payTable();

      System.out.println("==========���ſϷ�==========");

      // �������� �����ֱ�
      showInfo();
   }

   // �����ڵ� ��������: ���ż���� �� �ҷ�����
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

   // �������̺� �Է�
   public void yemaeTable() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO YEMAE(YEMAECODE,ADULT,TEENAGER,PRICE,TICKETINGDATE,MEMCODE,SCREENPLAYCODE) "
               + "VALUES('����'||SEQMOVIE.NEXTVAL,?,?,?,SYSDATE,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, dto.getAdultN());// ���μ�
         pstmt.setInt(2, dto.getTeenagerN());// û�ҳ��
         pstmt.setInt(3, dto.getTotalPrice());// ���� :���������ݾ�
         pstmt.setString(4, dto.getMyCode());// ȸ���ڵ�
         pstmt.setString(5, dto.getMyScreenCode());// ���ڵ�
         // pstmt.executeUpdate();

         int result = pstmt.executeUpdate();
         System.out.println(result + "���� �������̺� �߰���");

         // ���� �����ڵ� ����
         dto.setMyYemaeCode("����" + getSeqNum());

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

   // �����¼� �Է�
   public void yemaeSeatTable(int num) {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO YEMAESEAT(SEATCODE,SEATINFO,YEMAECODE,SCREENPLAYCODE) VALUES('�¼�'||SEQMOVIE.NEXTVAL,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyseats(num));
         pstmt.setString(2, dto.getMyYemaeCode());
         pstmt.setString(3, dto.getMyScreenCode());
         int r = pstmt.executeUpdate();

         if (r == 1)
            System.out.println("�����¼��� �߰��� : 1��");
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

   // �������� �Է� : ���ϸ��� ���ÿ���
   public void saleTable() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO YEMAESALE(YEMAECODE,SALECODE,MILEUSEJPRICE) VALUES(?,'����'||SEQMOVIE.NEXTVAL,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyYemaeCode());
         pstmt.setInt(2, dto.getUseM());
         int r = pstmt.executeUpdate();
         if (r == 1)
            System.out.println("�����������̺� �������� : ���ϸ��������");
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

   // ���ϸ��� �Է� 1 : ��������Ʈ ����
   public void mileageTable1() {
      PreparedStatement pstmt = null;
      String sql;

      getMyMileage();

      try {
         sql = "INSERT INTO MILEAGE(MILECODE,MEMCODE,NOWMILE,HISTORYDATE,NEAYEONG) VALUES('���ϸ���'||SEQMOVIE.NEXTVAL,?,?,SYSDATE,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyCode());
         pstmt.setInt(2, dto.getMileage() + 1000);
         pstmt.setString(3, 1000 + "����Ʈ : ��������Ʈ ����");
         int r = pstmt.executeUpdate();

         if (r == 1) {
            System.out.println("mileage 1000����Ʈ ������");
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

   // ���ϸ��� �Է� 2 : ���ϸ��� ���ÿ��� ��븶�ϸ��� ������ֱ�
   public void mileageTable2() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO MILEAGE(MILECODE,MEMCODE,NOWMILE,HISTORYDATE,NEAYEONG) VALUES('���ϸ���'||SEQMOVIE.NEXTVAL,?,?,SYSDATE,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyCode());
         pstmt.setInt(2, dto.getMileage() - dto.getUseM());
         pstmt.setString(3, dto.getUseM() + "����Ʈ : ��������");
         int r = pstmt.executeUpdate();
         if (r == 1)
            System.out.println("���ϸ��� ���γ��� �����");
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

   // �������̺�
   public void payTable() {
      PreparedStatement pstmt = null;
      String sql;

      // ��������� ���� �������� �Է�
      int tabN = tabbedPane.getSelectedIndex();
      String paypay = "";

      switch (tabN) {
      case 0:
         paypay = "ī�����";
         break;
      case 1:
         paypay = "�޴�������";
         break;
      case 2:
         paypay = "�������Ա� ����";
         break;
      }

      try {
         sql = "INSERT INTO PAY(PAYCODE,PAYWAY,YEMAECODE) VALUES('����'||SEQMOVIE.NEXTVAL,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, paypay);// �������
         pstmt.setString(2, dto.getMyYemaeCode());// �����ڵ�
         // pstmt.executeUpdate();

         int result = pstmt.executeUpdate();
         System.out.println(result + "���� �������̺� �߰���");

         // ���� �����ڵ� ����
         dto.setMyPayCode("����" + getSeqNum());

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

   // ����0 : ī�����
   public void payWay0() {
      PreparedStatement pstmt = null;
      String sql;

      if (cardrad1.isSelected()) {
         dto.setCardCom("�츮ī��");
      } else if (cardrad2.isSelected()) {
         dto.setCardCom("��ī��");
      } else if (cardrad3.isSelected()) {
         dto.setCardCom("����ī��");
      } else if (cardrad4.isSelected()) {
         dto.setCardCom("����ī��");
      } else if (cardrad5.isSelected()) {
         dto.setCardCom("����ī��");
      } else if (cardrad6.isSelected()) {
         dto.setCardCom("īī��ī��");
      }

      try {
         sql = "INSERT INTO CARDPAY(CARDPAYCODE,PAYCODE,PAYINFO) VALUES('ī�����'||SEQMOVIE.NEXTVAL,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyPayCode());
         pstmt.setString(2, dto.getCardCom());
         int r = pstmt.executeUpdate();
         if (r == 1)
            System.out.println("ī����� ��������");
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

   // ����1 : �� �Ҿװ���
   public void payWay1() {
      PreparedStatement pstmt = null;
      String sql;

      try {
         sql = "INSERT INTO PHONEPAY(PHONEPAYCODE,PAYCODE,PAYINFO) VALUES('������'||SEQMOVIE.NEXTVAL,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyPayCode());// �����ڵ� ������
         pstmt.setString(2, dto.getPhone()); // �������� : ����ȣ �����ֱ�
         int r = pstmt.executeUpdate();

         if (r == 1)
            System.out.println("�� �������� �����");
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

   // ����2 : ���� �������Ա�
   public void payWay2() {
      PreparedStatement pstmt = null;
      String sql;

      if (bankrad1.isSelected()) {
         dto.setBankCom("�츮����");
      } else if (bankrad2.isSelected()) {
         dto.setBankCom("������");
      } else if (bankrad3.isSelected()) {
         dto.setBankCom("��������");
      } else if (bankrad4.isSelected()) {
         dto.setBankCom("��������");
      } else if (bankrad5.isSelected()) {
         dto.setBankCom("��������");
      } else if (bankrad6.isSelected()) {
         dto.setBankCom("īī������");
      }

      try {
         sql = "INSERT INTO CASHPAY(CASHPAYCODE,PAYCODE,PAYNAME,PAYDATE,PRICE,BANKNAME) VALUES('���ݰ���'||SEQMOVIE.NEXTVAL,?,?,SYSDATE,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getMyPayCode());// �����ڵ� ������
         pstmt.setString(2, payerName.getText());
         pstmt.setInt(3, dto.getTotalPrice());
         pstmt.setString(4, dto.getBankCom());
         int r = pstmt.executeUpdate();

         if (r == 1)
            System.out.println("�������Աݳ��� �����");
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

      // �ʿ��Ѱ� : ���ų����� ������ ����
      // �����ڵ�
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

            // System.out.println("Ƽ���ó�¥ :" + dto.getTicketingdate() + " ���� :" +
            // dto.getmTitle() + " �󿵽��۽ð� : "
            // + dto.getMstartTime() + " �󿵰��̸�: " + dto.getScreenName() + " �󿵳�¥ :" +
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

      System.out.println("==========��������==========");
      // ������ ȸ���ڵ�
      System.out.println("ȸ���ڵ� : " + dto.getMyCode());
      // �����ڵ�
      System.out.println("�����ڵ� : " + dto.getMyYemaeCode());

      // ��ȭ���� ������ �����ؼ� �����;���
      // ��ȭ����
      System.out.println("��ȭ���� : " + dto.getmTitle());
      // ������ �󿵰�
      System.out.println("�󿵰��ڵ� : " + dto.getScreenName());
      // �󿵽ð�
      System.out.println("�󿵽ð� : " + dto.getMstartTime());

      // ������ �¼�����
      System.out.print("���� �¼� : ");
      for (int i = 0; i < dto.getMyseats().length; i++)
         System.out.print(dto.getMyseats(i) + " ");
      System.out.println();
      // ��� ���ϸ���
      System.out.println("��� ���ϸ��� : " + dto.getUseM());
      // ���� �� ���� �� ���ϸ���
      getMyMileage();
      System.out.println("���� ���ϸ��� :" + dto.getMileage());
      System.out.println("========================");

      ResultFrame f = new ResultFrame(dto);
      f.setVisible(true);
   }

   /**
    * Create the frame.
    */
   // �޾ƿ;��ϴ� ���� payment ������ �Ű����� �� ���ֱ�
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

      setTitle("����â");
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

      // ���� ���ϸ��� ��ȸ
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

      // Ƽ�ϰ��� ǥ�ö�

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

      // useMileage �Է�â
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

      tabbedPane.add("cardPay", panelt1); // ī������ ��

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

      // ������ư �׷�ȭ : cardPay
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

      // ������ư �׷�ȭ : cashPay
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
         // �Է¾��� ��ư ������ ����ó��
         if (useMileage.getText().length() == 0) {
            dto.setUseM(0);
            return;
         } else {
            dto.setUseM(Integer.parseInt(useMileage.getText()));
         }

         // ���ϸ��� �����ư
         if (dto.getUseM() >= dto.getTicketPrice()) {
            // �����ݾ׺��� ū ���ϸ��� �Է½�
            label_11.setText("�ִ� Ƽ�ϰ���ŭ�� ��밡���մϴ�");
         } else if (dto.getUseM() >= dto.getUseM()) {
            // ���밡���� ���ϸ��� ũ���� ��
            dto.setTotalPrice(dto.getTicketPrice() - dto.getUseM());
            lbTotPrice.setText(dto.getTotalPrice() + "");
            label_11.setText("");

         } else {
            // ���ϸ����� ������ ���ϸ������� ������
            label_11.setText("���� ���ϸ�����ŭ�� ��밡���մϴ�");
         }

      } else if (e.getSource() == btn2) {
         // ������ư
         // DB�� insert : �������� ��
         insertYemaeInfo();
      } else if (e.getSource() == submitbtn1) {
         // �������� ���۸޽��� ǥ��
         lblNewLabel_2.setText("���ι�ȣ�� �����߽��ϴ�");

      } else if (e.getSource() == submitbtn2) {

         // �̸����ּ� �� ������
         if (!textField_2.getText().isEmpty() && !textField_3.getText().isEmpty()) {
            // �������� �̸������� ǥ��
            lblNewLabel_3.setText("������ ������ ���Ϸ� �����մϴ�");
         }
      }

   }
}