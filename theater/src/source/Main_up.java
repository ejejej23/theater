package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class Main_up extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane_Main;

	// 날짜
	SimpleDateFormat sdf = new SimpleDateFormat("dd");
	Calendar cal = Calendar.getInstance();

	private TimeDAO daot = new TimeDAO();

	@SuppressWarnings("unused")
	private String id;

	private JPanel panel1; // 첫날
	private JPanel panel2; // 둘째날
	private JPanel panel3; // 셋째날
	private JPanel panel4; // 넷째날

	JButton time1_zu1;


	// jwyang 변수 추가
	private String poster_path;
	MemberDAO dao = new MemberDAO();

	/** Create the frame. */

	public Main_up(String id) {
		this.id=id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 891);
		contentPane_Main = new JPanel();
		contentPane_Main.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPane_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_Main.setLayout(null);
		setContentPane(contentPane_Main);
		setTitle("SS극장");

		JPanel panel_poster = new JPanel();
		panel_poster.setBounds(12, 49, 1110, 310);
		contentPane_Main.add(panel_poster);
		panel_poster.setLayout(null);
		/* 독전 포스터 */
		JButton btn_Dj = new JButton("");
		btn_Dj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// click the imageButton after that there is available
				// jwyang 각 버튼마다 포스터경로와 moviecode 같이 넘겨주기
				poster_path = "/source/dj.jpg";
				new Info_detail("영화2", poster_path);
			}
		});
		btn_Dj.setBounds(230, 10, 188, 268);
		panel_poster.add(btn_Dj);
		btn_Dj.setIcon(new ImageIcon(Main_up.class.getResource("/source/dj.jpg")));
		/* 독전 라벨 */
		JLabel lbl_Dj = new JLabel();
		lbl_Dj.setText("\uB3C5\uC804");
		lbl_Dj.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_Dj.setBounds(309, 290, 36, 15);
		// lbl_Dj.setText(dto.getTitle());
		panel_poster.add(lbl_Dj);
		/* 탐정:리턴즈 포스터 */
		JButton btn_Detective = new JButton("");
		btn_Detective.setBounds(12, 10, 188, 268);
		panel_poster.add(btn_Detective);
		btn_Detective.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// click the imageButton after that there is available
				poster_path = "/source/detective_re.jpg";
				new Info_detail("영화1", poster_path);
			}
		});
		btn_Detective.setIcon(new ImageIcon(Main_up.class.getResource("/source/detective_re.jpg")));
		/* 탐정:리턴즈 라벨 */
		JLabel lbl_Detective = new JLabel("\uD0D0\uC815: \uB9AC\uD134\uC988");
		lbl_Detective.setBounds(65, 290, 90, 15);
		panel_poster.add(lbl_Detective);
		lbl_Detective.setFont(new Font("굴림", Font.PLAIN, 15));

		/* 쥬라기 월드 포스터 */
		JButton btn_Jpark = new JButton("");
		btn_Jpark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// click the imageButton after that there is available
				poster_path = "/source/jpark.jpg";
				new Info_detail("영화3", poster_path);
			}
		});
		btn_Jpark.setBounds(443, 10, 184, 268);
		panel_poster.add(btn_Jpark);
		btn_Jpark.setIcon(new ImageIcon(Main_up.class.getResource("/source/jpark.jpg")));

		JLabel lbl_Jpark = new JLabel("\uC96C\uB77C\uAE30 \uC6D4\uB4DC: \uD3F4\uB978 \uD0B9\uB364");
		lbl_Jpark.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_Jpark.setBounds(462, 290, 154, 15);
		panel_poster.add(lbl_Jpark);

		/* 친절한 금자씨 포스터 */
		JButton btn_Gja = new JButton("");
		btn_Gja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// click the imageButton after that there is available
				poster_path = "/source/guma.jpg";
				new Info_detail("영화4", poster_path);
			}
		});
		btn_Gja.setIcon(new ImageIcon(Main_up.class.getResource("/source/guma.jpg")));
		btn_Gja.setBounds(652, 10, 188, 268);
		panel_poster.add(btn_Gja);
		/* 친절한 금자씨 라벨 */
		JLabel lbl_Gja = new JLabel("\uCE5C\uC808\uD55C \uAE08\uC790\uC528");
		lbl_Gja.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_Gja.setBounds(700, 290, 97, 15);
		panel_poster.add(lbl_Gja);

		/* 오션스8 포스터 */
		JButton btn_Oceans8 = new JButton("");
		btn_Oceans8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// click the imageButton after that there is available
				poster_path = "/source/oceans8.jpg";
				new Info_detail("영화5", poster_path);
			}
		});
		btn_Oceans8.setIcon(new ImageIcon(Main_up.class.getResource("/source/oceans8.jpg")));
		btn_Oceans8.setBounds(870, 10, 188, 268);
		panel_poster.add(btn_Oceans8);
		/* 오션스8 라벨 */
		JLabel lbl_Oceans8 = new JLabel("\uC624\uC158\uC2A48");
		lbl_Oceans8.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_Oceans8.setBounds(936, 290, 57, 15);
		panel_poster.add(lbl_Oceans8);

		JLabel lblNewLabel_3 = new JLabel("\uC774\uBC88\uC8FC \uC0C1\uC601\uC791");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_3.setBounds(40, 17, 273, 20);

		contentPane_Main.add(lblNewLabel_3);

		// jwyang id로 이름 불러오기
		MemberDTO dto = dao.readMemberMemcode(id);
		System.out.println(id);
		JLabel lbl_login = new JLabel();
		lbl_login.setBounds(731, 17, 150, 20);
		lbl_login.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login.setVerticalAlignment(SwingConstants.CENTER);
		contentPane_Main.add(lbl_login);
		if (dto != null) {
			lbl_login.setText(dto.getName() + " 님, 안녕하세요^^.");
		}

		// 생성 및 초기화
		JTabbedPane tPane = new JTabbedPane(JTabbedPane.TOP);
		tPane.setBounds(247, 369, 602, 463);
		contentPane_Main.add(tPane);
		int d = cal.get(Calendar.DATE);

		panel1 = new JPanel();
		panel1.setBackground(SystemColor.window);
		panel1.setLayout(null);

		panel2 = new JPanel();
		panel2.setBackground(SystemColor.window);
		panel2.setLayout(null);

		panel3 = new JPanel();
		panel3.setBackground(SystemColor.window);
		panel3.setLayout(null);

		panel4 = new JPanel();
		panel4.setBackground(SystemColor.window);
		panel4.setLayout(null);

		// 첫째날
		tPane.add((d) + "일(오늘)", panel1);

		JLabel label1_zu = new JLabel("쥬라기월드 : 폴른 킹덤");
		label1_zu.setHorizontalAlignment(SwingConstants.CENTER);
		label1_zu.setBounds(0, 0, 162, 86);
		panel1.add(label1_zu);
		label1_zu.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));

		JLabel label1_o = new JLabel("오션스8");
		label1_o.setHorizontalAlignment(SwingConstants.CENTER);
		label1_o.setBounds(0, 86, 162, 86);
		panel1.add(label1_o);
		label1_o.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label1_chin = new JLabel("친절한 금자씨");
		label1_chin.setHorizontalAlignment(SwingConstants.CENTER);
		label1_chin.setBounds(0, 172, 162, 86);
		panel1.add(label1_chin);
		label1_chin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label1_tam = new JLabel("탐정:리턴즈");
		label1_tam.setHorizontalAlignment(SwingConstants.CENTER);
		label1_tam.setBounds(0, 259, 162, 86);
		panel1.add(label1_tam);
		label1_tam.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label1_dok = new JLabel("독전");
		label1_dok.setHorizontalAlignment(SwingConstants.CENTER);
		label1_dok.setBounds(0, 344, 162, 86);
		panel1.add(label1_dok);

		// 상영/시간(좌석/20석수)
		// 쥬라기
		time1_zu1 = new JButton();
		time1_zu1.setHorizontalAlignment(SwingConstants.CENTER);
		time1_zu1.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_zu1.setBounds(161, 0, 87, 86);
		panel1.add(time1_zu1);

		JButton time1_zu2 = new JButton();
		time1_zu2.setHorizontalAlignment(SwingConstants.CENTER);
		time1_zu2.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_zu2.setBounds(248, 0, 87, 86);
		panel1.add(time1_zu2);

		JButton time1_zu3 = new JButton();
		time1_zu3.setHorizontalAlignment(SwingConstants.CENTER);
		time1_zu3.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_zu3.setBounds(336, 0, 87, 86);
		panel1.add(time1_zu3);

		JButton time1_zu4 = new JButton();
		time1_zu4.setHorizontalAlignment(SwingConstants.CENTER);
		time1_zu4.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_zu4.setBounds(424, 0, 87, 86);
		panel1.add(time1_zu4);

		JButton time1_zu5 = new JButton();
		time1_zu5.setHorizontalAlignment(SwingConstants.CENTER);
		time1_zu5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time1_zu5.setBounds(510, 0, 87, 86);
		panel1.add(time1_zu5);

		// 정보가져오기
		String s1 = "2018/06/" + d;
		List<TimeDTO> list1_zu = daot.listTime1("쥬라기 월드: 폴른 킹덤", s1);
		JButton[] l1 = new JButton[5];
		l1[0] = time1_zu1;
		l1[1] = time1_zu2;
		l1[2] = time1_zu3;
		l1[3] = time1_zu4;
		l1[4] = time1_zu5;

		for (int i = 0; i < list1_zu.size(); i++) {
			l1[i].setText("<html><center>" + list1_zu.get(i).getScreenName() + " / " + list1_zu.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list1_zu.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l1[i].setName(list1_zu.get(i).getScreenplayCode());
		
			l1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);

				}
			});
		}

		// 오션스8
		JButton time1_o1 = new JButton();
		time1_o1.setHorizontalAlignment(SwingConstants.CENTER);
		time1_o1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_o1.setBounds(161, 86, 87, 86);
		panel1.add(time1_o1);

		JButton time1_o2 = new JButton();
		time1_o2.setHorizontalAlignment(SwingConstants.CENTER);
		time1_o2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_o2.setBounds(248, 86, 87, 86);
		panel1.add(time1_o2);

		JButton time1_o3 = new JButton();
		time1_o3.setHorizontalAlignment(SwingConstants.CENTER);
		time1_o3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_o3.setBounds(336, 86, 87, 86);
		panel1.add(time1_o3);

		JButton time1_o4 = new JButton();
		time1_o4.setHorizontalAlignment(SwingConstants.CENTER);
		time1_o4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_o4.setBounds(424, 86, 87, 86);
		panel1.add(time1_o4);

		JButton time1_o5 = new JButton();
		time1_o5.setHorizontalAlignment(SwingConstants.CENTER);
		time1_o5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time1_o5.setBounds(510, 86, 87, 86);
		panel1.add(time1_o5);

		// 정보가져오기
		List<TimeDTO> list1_o = daot.listTime1("오션스8", s1);
		JButton[] l2 = new JButton[5];
		l2[0] = time1_o1;
		l2[1] = time1_o2;
		l2[2] = time1_o3;
		l2[3] = time1_o4;
		l2[4] = time1_o5;

		for (int i = 0; i < list1_o.size(); i++) {
			l2[i].setText("<html><center>" + list1_o.get(i).getScreenName() + " / " + list1_o.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list1_o.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l2[i].setName(list1_o.get(i).getScreenplayCode());
			l2[i].addActionListener(this);

			 
			l2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);

				}
			});
		}

		// 친절한 금자씨
		JButton time1_chin1 = new JButton();
		time1_chin1.setHorizontalAlignment(SwingConstants.CENTER);
		time1_chin1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_chin1.setBounds(161, 172, 87, 86);
		panel1.add(time1_chin1);

		JButton time1_chin2 = new JButton();
		time1_chin2.setHorizontalAlignment(SwingConstants.CENTER);
		time1_chin2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_chin2.setBounds(248, 172, 87, 86);
		panel1.add(time1_chin2);

		JButton time1_chin3 = new JButton();
		time1_chin3.setHorizontalAlignment(SwingConstants.CENTER);
		time1_chin3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_chin3.setBounds(336, 172, 87, 86);
		panel1.add(time1_chin3);

		JButton time1_chin4 = new JButton();
		time1_chin4.setHorizontalAlignment(SwingConstants.CENTER);
		time1_chin4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_chin4.setBounds(424, 172, 87, 86);
		panel1.add(time1_chin4);

		JButton time1_chin5 = new JButton();
		time1_chin5.setHorizontalAlignment(SwingConstants.CENTER);
		time1_chin5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time1_chin5.setBounds(510, 172, 87, 86);
		panel1.add(time1_chin5);

		// 정보가져오기
		List<TimeDTO> list1_chin = daot.listTime1("친절한 금자씨", s1);
		JButton[] l3 = new JButton[5];
		l3[0] = time1_chin1;
		l3[1] = time1_chin2;
		l3[2] = time1_chin3;
		l3[3] = time1_chin4;
		l3[4] = time1_chin5;
		for (int i = 0; i < list1_chin.size(); i++) {
			l3[i].setText("<html><center>" + list1_chin.get(i).getScreenName() + " / " + list1_chin.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list1_chin.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l3[i].setName(list1_chin.get(i).getScreenplayCode());
			l3[i].addActionListener(this);

			 
			l3[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);

				}
			});
		}

		// 탐정
		JButton time1_tam1 = new JButton();
		time1_tam1.setHorizontalAlignment(SwingConstants.CENTER);
		time1_tam1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_tam1.setBounds(161, 259, 87, 86);
		panel1.add(time1_tam1);

		JButton time1_tam2 = new JButton();
		time1_tam2.setHorizontalAlignment(SwingConstants.CENTER);
		time1_tam2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_tam2.setBounds(248, 259, 87, 86);
		panel1.add(time1_tam2);

		JButton time1_tam3 = new JButton();
		time1_tam3.setHorizontalAlignment(SwingConstants.CENTER);
		time1_tam3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_tam3.setBounds(336, 259, 87, 86);
		panel1.add(time1_tam3);

		JButton time1_tam4 = new JButton();
		time1_tam4.setHorizontalAlignment(SwingConstants.CENTER);
		time1_tam4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_tam4.setBounds(424, 259, 87, 86);
		panel1.add(time1_tam4);

		JButton time1_tam5 = new JButton();
		time1_tam5.setHorizontalAlignment(SwingConstants.CENTER);
		time1_tam5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time1_tam5.setBounds(510, 259, 87, 86);
		panel1.add(time1_tam5);

		// 정보가져오기
		List<TimeDTO> list1_tam = daot.listTime1("탐정:리턴즈", s1);
		JButton[] l4 = new JButton[5];
		l4[0] = time1_tam1;
		l4[1] = time1_tam2;
		l4[2] = time1_tam3;
		l4[3] = time1_tam4;
		l4[4] = time1_tam5;
		for (int i = 0; i < list1_tam.size(); i++) {
			l4[i].setText("<html><center>" + list1_tam.get(i).getScreenName() + " / " + list1_tam.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list1_tam.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l4[i].setName(list1_tam.get(i).getScreenplayCode());
			l4[i].addActionListener(this);

			 
			l4[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);

				}
			});
		}

		// 독전
		JButton time1_dok1 = new JButton();
		time1_dok1.setHorizontalAlignment(SwingConstants.CENTER);
		time1_dok1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_dok1.setBounds(161, 344, 87, 86);
		panel1.add(time1_dok1);

		JButton time1_dok2 = new JButton();
		time1_dok2.setHorizontalAlignment(SwingConstants.CENTER);
		time1_dok2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_dok2.setBounds(248, 344, 87, 86);
		panel1.add(time1_dok2);

		JButton time1_dok3 = new JButton();
		time1_dok3.setHorizontalAlignment(SwingConstants.CENTER);
		time1_dok3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_dok3.setBounds(336, 344, 87, 86);
		panel1.add(time1_dok3);

		JButton time1_dok4 = new JButton();
		time1_dok4.setHorizontalAlignment(SwingConstants.CENTER);
		time1_dok4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time1_dok4.setBounds(424, 344, 87, 86);
		panel1.add(time1_dok4);

		JButton time1_dok5 = new JButton();
		time1_dok5.setHorizontalAlignment(SwingConstants.CENTER);
		time1_dok5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time1_dok5.setBounds(510, 344, 87, 86);
		panel1.add(time1_dok5);

		// 정보가져오기
		List<TimeDTO> list1_dok = daot.listTime1("독전", s1);
		JButton[] l5 = new JButton[5];
		l5[0] = time1_dok1;
		l5[1] = time1_dok2;
		l5[2] = time1_dok3;
		l5[3] = time1_dok4;
		l5[4] = time1_dok5;

		for (int i = 0; i < list1_dok.size(); i++) {
			l5[i].setText("<html><center>" + list1_dok.get(i).getScreenName() + " / " + list1_dok.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list1_dok.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l5[i].setName(list1_dok.get(i).getScreenplayCode());
			l5[i].addActionListener(this);

			 
			l5[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);

				}
			});
		}

		// 둘째날
		tPane.add((d + 1) + "일(내일)", panel2);

		JLabel label2_zu = new JLabel("쥬라기월드 : 폴른 킹덤");
		label2_zu.setHorizontalAlignment(SwingConstants.CENTER);
		label2_zu.setBounds(0, 0, 162, 86);
		panel2.add(label2_zu);
		label2_zu.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));

		JLabel label2_o = new JLabel("오션스8");
		label2_o.setHorizontalAlignment(SwingConstants.CENTER);
		label2_o.setBounds(0, 86, 162, 86);
		panel2.add(label2_o);
		label2_o.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label2_chin = new JLabel("친절한 금자씨");
		label2_chin.setHorizontalAlignment(SwingConstants.CENTER);
		label2_chin.setBounds(0, 172, 162, 86);
		panel2.add(label2_chin);
		label2_chin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label2_tam = new JLabel("탐정:리턴즈");
		label2_tam.setHorizontalAlignment(SwingConstants.CENTER);
		label2_tam.setBounds(0, 259, 162, 86);
		panel2.add(label2_tam);
		label2_tam.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label2_dok = new JLabel("독전");
		label2_dok.setHorizontalAlignment(SwingConstants.CENTER);
		label2_dok.setBounds(0, 344, 162, 86);
		panel2.add(label2_dok);

		// 상영/시간(좌석/20석수)
		// 쥬라기
		JButton time2_zu1 = new JButton();
		time2_zu1.setHorizontalAlignment(SwingConstants.CENTER);
		time2_zu1.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_zu1.setBounds(161, 0, 87, 86);
		panel2.add(time2_zu1);

		JButton time2_zu2 = new JButton();
		time2_zu2.setHorizontalAlignment(SwingConstants.CENTER);
		time2_zu2.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_zu2.setBounds(248, 0, 87, 86);
		panel2.add(time2_zu2);

		JButton time2_zu3 = new JButton();
		time2_zu3.setHorizontalAlignment(SwingConstants.CENTER);
		time2_zu3.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_zu3.setBounds(336, 0, 87, 86);
		panel2.add(time2_zu3);

		JButton time2_zu4 = new JButton();
		time2_zu4.setHorizontalAlignment(SwingConstants.CENTER);
		time2_zu4.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_zu4.setBounds(424, 0, 87, 86);
		panel2.add(time2_zu4);

		JButton time2_zu5 = new JButton();
		time2_zu5.setHorizontalAlignment(SwingConstants.CENTER);
		time2_zu5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time2_zu5.setBounds(510, 0, 87, 86);
		panel2.add(time2_zu5);

		// 정보가져오기
		String s2 = "2018/06/" + (int) (d + 1);
		List<TimeDTO> list2_zu = daot.listTime1("쥬라기 월드: 폴른 킹덤", s2);
		JButton[] l6 = new JButton[5];
		l6[0] = time2_zu1;
		l6[1] = time2_zu2;
		l6[2] = time2_zu3;
		l6[3] = time2_zu4;
		l6[4] = time2_zu5;
		for (int i = 0; i < list2_zu.size(); i++) {
			l6[i].setText("<html><center>" + list2_zu.get(i).getScreenName() + " / " + list2_zu.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list2_zu.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l6[i].setName(list2_zu.get(i).getScreenplayCode());
			l6[i].addActionListener(this);

			 
			l6[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 오션스8
		JButton time2_o1 = new JButton();
		time2_o1.setHorizontalAlignment(SwingConstants.CENTER);
		time2_o1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_o1.setBounds(161, 86, 87, 86);
		panel2.add(time2_o1);

		JButton time2_o2 = new JButton();
		time2_o2.setHorizontalAlignment(SwingConstants.CENTER);
		time2_o2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_o2.setBounds(248, 86, 87, 86);
		panel2.add(time2_o2);

		JButton time2_o3 = new JButton();
		time2_o3.setHorizontalAlignment(SwingConstants.CENTER);
		time2_o3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_o3.setBounds(336, 86, 87, 86);
		panel2.add(time2_o3);

		JButton time2_o4 = new JButton();
		time2_o4.setHorizontalAlignment(SwingConstants.CENTER);
		time2_o4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_o4.setBounds(424, 86, 87, 86);
		panel2.add(time2_o4);

		JButton time2_o5 = new JButton();
		time2_o5.setHorizontalAlignment(SwingConstants.CENTER);
		time2_o5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time2_o5.setBounds(510, 86, 87, 86);
		panel2.add(time2_o5);

		// 정보가져오기
		List<TimeDTO> list2_o = daot.listTime1("오션스8", s2);
		JButton[] l7 = new JButton[5];
		l7[0] = time2_o1;
		l7[1] = time2_o2;
		l7[2] = time2_o3;
		l7[3] = time2_o4;
		l7[4] = time2_o5;
		for (int i = 0; i < list2_o.size(); i++) {
			l7[i].setText("<html><center>" + list2_o.get(i).getScreenName() + " / " + list2_o.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list2_o.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l7[i].setName(list2_o.get(i).getScreenplayCode());
			l7[i].addActionListener(this);

			 
			l7[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 친절한 금자씨
		JButton time2_chin1 = new JButton();
		time2_chin1.setHorizontalAlignment(SwingConstants.CENTER);
		time2_chin1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_chin1.setBounds(161, 172, 87, 86);
		panel2.add(time2_chin1);

		JButton time2_chin2 = new JButton();
		time2_chin2.setHorizontalAlignment(SwingConstants.CENTER);
		time2_chin2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_chin2.setBounds(248, 172, 87, 86);
		panel2.add(time2_chin2);

		JButton time2_chin3 = new JButton();
		time2_chin3.setHorizontalAlignment(SwingConstants.CENTER);
		time2_chin3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_chin3.setBounds(336, 172, 87, 86);
		panel2.add(time2_chin3);

		JButton time2_chin4 = new JButton();
		time2_chin4.setHorizontalAlignment(SwingConstants.CENTER);
		time2_chin4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_chin4.setBounds(424, 172, 87, 86);
		panel2.add(time2_chin4);

		JButton time2_chin5 = new JButton();
		time2_chin5.setHorizontalAlignment(SwingConstants.CENTER);
		time2_chin5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time2_chin5.setBounds(510, 172, 87, 86);
		panel2.add(time2_chin5);

		// 정보가져오기
		List<TimeDTO> list2_chin = daot.listTime1("친절한 금자씨", s2);
		JButton[] l8 = new JButton[5];
		l8[0] = time2_chin1;
		l8[1] = time2_chin2;
		l8[2] = time2_chin3;
		l8[3] = time2_chin4;
		l8[4] = time2_chin5;
		for (int i = 0; i < list2_chin.size(); i++) {
			l8[i].setText("<html><center>" + list2_chin.get(i).getScreenName() + " / " + list2_chin.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list2_chin.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l8[i].setName(list2_chin.get(i).getScreenplayCode());
			l8[i].addActionListener(this);

			 
			l8[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 탐정
		JButton time2_tam1 = new JButton();
		time2_tam1.setHorizontalAlignment(SwingConstants.CENTER);
		time2_tam1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_tam1.setBounds(161, 259, 87, 86);
		panel2.add(time2_tam1);

		JButton time2_tam2 = new JButton();
		time2_tam2.setHorizontalAlignment(SwingConstants.CENTER);
		time2_tam2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_tam2.setBounds(248, 259, 87, 86);
		panel2.add(time2_tam2);

		JButton time2_tam3 = new JButton();
		time2_tam3.setHorizontalAlignment(SwingConstants.CENTER);
		time2_tam3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_tam3.setBounds(336, 259, 87, 86);
		panel2.add(time2_tam3);

		JButton time2_tam4 = new JButton();
		time2_tam4.setHorizontalAlignment(SwingConstants.CENTER);
		time2_tam4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_tam4.setBounds(424, 259, 87, 86);
		panel2.add(time2_tam4);

		JButton time2_tam5 = new JButton();
		time2_tam5.setHorizontalAlignment(SwingConstants.CENTER);
		time2_tam5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time2_tam5.setBounds(510, 259, 87, 86);
		panel2.add(time2_tam5);

		// 정보가져오기
		List<TimeDTO> list2_tam = daot.listTime1("탐정:리턴즈", s2);
		JButton[] l9 = new JButton[5];
		l9[0] = time2_tam1;
		l9[1] = time2_tam2;
		l9[2] = time2_tam3;
		l9[3] = time2_tam4;
		l9[4] = time2_tam5;
		for (int i = 0; i < list2_tam.size(); i++) {
			l9[i].setText("<html><center>" + list2_tam.get(i).getScreenName() + " / " + list2_tam.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list2_tam.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l9[i].setName(list2_tam.get(i).getScreenplayCode());
			l9[i].addActionListener(this);

			 
			l9[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 독전
		JButton time2_dok1 = new JButton();
		time2_dok1.setHorizontalAlignment(SwingConstants.CENTER);
		time2_dok1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_dok1.setBounds(161, 344, 87, 86);
		panel2.add(time2_dok1);

		JButton time2_dok2 = new JButton();
		time2_dok2.setHorizontalAlignment(SwingConstants.CENTER);
		time2_dok2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_dok2.setBounds(248, 344, 87, 86);
		panel2.add(time2_dok2);

		JButton time2_dok3 = new JButton();
		time2_dok3.setHorizontalAlignment(SwingConstants.CENTER);
		time2_dok3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_dok3.setBounds(336, 344, 87, 86);
		panel2.add(time2_dok3);

		JButton time2_dok4 = new JButton();
		time2_dok4.setHorizontalAlignment(SwingConstants.CENTER);
		time2_dok4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time2_dok4.setBounds(424, 344, 87, 86);
		panel2.add(time2_dok4);

		JButton time2_dok5 = new JButton();
		time2_dok5.setHorizontalAlignment(SwingConstants.CENTER);
		time2_dok5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time2_dok5.setBounds(510, 344, 87, 86);
		panel2.add(time2_dok5);

		// 정보가져오기
		List<TimeDTO> list2_dok = daot.listTime1("독전", s2);
		JButton[] l10 = new JButton[5];
		l10[0] = time2_dok1;
		l10[1] = time2_dok2;
		l10[2] = time2_dok3;
		l10[3] = time2_dok4;
		l10[4] = time2_dok5;
		for (int i = 0; i < list2_dok.size(); i++) {
			l10[i].setText("<html><center>" + list2_dok.get(i).getScreenName() + " / " + list2_dok.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list2_dok.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l10[i].setName(list2_dok.get(i).getScreenplayCode());
			l10[i].addActionListener(this);

			 
			l10[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 셋째날
		tPane.add((d + 2) + "일", panel3);

		JLabel label3_zu = new JLabel("쥬라기월드 : 폴른 킹덤");
		label3_zu.setHorizontalAlignment(SwingConstants.CENTER);
		label3_zu.setBounds(0, 0, 162, 86);
		panel3.add(label3_zu);
		label3_zu.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));

		JLabel label3_o = new JLabel("오션스8");
		label3_o.setHorizontalAlignment(SwingConstants.CENTER);
		label3_o.setBounds(0, 86, 162, 86);
		panel3.add(label3_o);
		label3_o.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label3_chin = new JLabel("친절한 금자씨");
		label3_chin.setHorizontalAlignment(SwingConstants.CENTER);
		label3_chin.setBounds(0, 172, 162, 86);
		panel3.add(label3_chin);
		label3_chin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label3_tam = new JLabel("탐정:리턴즈");
		label3_tam.setHorizontalAlignment(SwingConstants.CENTER);
		label3_tam.setBounds(0, 259, 162, 86);
		panel3.add(label3_tam);
		label3_tam.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label3_dok = new JLabel("독전");
		label3_dok.setHorizontalAlignment(SwingConstants.CENTER);
		label3_dok.setBounds(0, 344, 162, 86);
		panel3.add(label3_dok);

		// 상영/시간(좌석/20석수)
		// 쥬라기
		JButton time3_zu1 = new JButton();
		time3_zu1.setHorizontalAlignment(SwingConstants.CENTER);
		time3_zu1.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_zu1.setBounds(161, 0, 87, 86);
		panel3.add(time3_zu1);

		JButton time3_zu2 = new JButton();
		time3_zu2.setHorizontalAlignment(SwingConstants.CENTER);
		time3_zu2.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_zu2.setBounds(248, 0, 87, 86);
		panel3.add(time3_zu2);

		JButton time3_zu3 = new JButton();
		time3_zu3.setHorizontalAlignment(SwingConstants.CENTER);
		time3_zu3.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_zu3.setBounds(336, 0, 87, 86);
		panel3.add(time3_zu3);

		JButton time3_zu4 = new JButton();
		time3_zu4.setHorizontalAlignment(SwingConstants.CENTER);
		time3_zu4.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_zu4.setBounds(424, 0, 87, 86);
		panel3.add(time3_zu4);

		JButton time3_zu5 = new JButton();
		time3_zu5.setHorizontalAlignment(SwingConstants.CENTER);
		time3_zu5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time3_zu5.setBounds(510, 0, 87, 86);
		panel3.add(time3_zu5);

		// 정보가져오기
		String s3 = "2018/06/" + (int) (d + 2);
		List<TimeDTO> list3_zu = daot.listTime1("쥬라기 월드: 폴른 킹덤", s3);
		JButton[] l11 = new JButton[5];
		l11[0] = time3_zu1;
		l11[1] = time3_zu2;
		l11[2] = time3_zu3;
		l11[3] = time3_zu4;
		l11[4] = time3_zu5;
		for (int i = 0; i < list3_zu.size(); i++) {
			l11[i].setText("<html><center>" + list3_zu.get(i).getScreenName() + " / " + list3_zu.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list3_zu.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l11[i].setName(list3_zu.get(i).getScreenplayCode());
			l11[i].addActionListener(this);

			 
			l11[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 오션스8
		JButton time3_o1 = new JButton();
		time3_o1.setHorizontalAlignment(SwingConstants.CENTER);
		time3_o1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_o1.setBounds(161, 86, 87, 86);
		panel3.add(time3_o1);

		JButton time3_o2 = new JButton();
		time3_o2.setHorizontalAlignment(SwingConstants.CENTER);
		time3_o2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_o2.setBounds(248, 86, 87, 86);
		panel3.add(time3_o2);

		JButton time3_o3 = new JButton();
		time3_o3.setHorizontalAlignment(SwingConstants.CENTER);
		time3_o3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_o3.setBounds(336, 86, 87, 86);
		panel3.add(time3_o3);

		JButton time3_o4 = new JButton();
		time3_o4.setHorizontalAlignment(SwingConstants.CENTER);
		time3_o4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_o4.setBounds(424, 86, 87, 86);
		panel3.add(time3_o4);

		JButton time3_o5 = new JButton();
		time3_o5.setHorizontalAlignment(SwingConstants.CENTER);
		time3_o5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time3_o5.setBounds(510, 86, 87, 86);
		panel3.add(time3_o5);

		// 정보가져오기
		List<TimeDTO> list3_o = daot.listTime1("오션스8", s3);
		JButton[] l12 = new JButton[5];
		l12[0] = time3_o1;
		l12[1] = time3_o2;
		l12[2] = time3_o3;
		l12[3] = time3_o4;
		l12[4] = time3_o5;
		for (int i = 0; i < list3_o.size(); i++) {
			l12[i].setText("<html><center>" + list3_o.get(i).getScreenName() + " / " + list3_o.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list3_o.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l12[i].setName(list3_o.get(i).getScreenplayCode());
			l12[i].addActionListener(this);

			 
			l12[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 친절한 금자씨
		JButton time3_chin1 = new JButton();
		time3_chin1.setHorizontalAlignment(SwingConstants.CENTER);
		time3_chin1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_chin1.setBounds(161, 172, 87, 86);
		panel3.add(time3_chin1);

		JButton time3_chin2 = new JButton();
		time3_chin2.setHorizontalAlignment(SwingConstants.CENTER);
		time3_chin2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_chin2.setBounds(248, 172, 87, 86);
		panel3.add(time3_chin2);

		JButton time3_chin3 = new JButton();
		time3_chin3.setHorizontalAlignment(SwingConstants.CENTER);
		time3_chin3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_chin3.setBounds(336, 172, 87, 86);
		panel3.add(time3_chin3);

		JButton time3_chin4 = new JButton();
		time3_chin4.setHorizontalAlignment(SwingConstants.CENTER);
		time3_chin4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_chin4.setBounds(424, 172, 87, 86);
		panel3.add(time3_chin4);

		JButton time3_chin5 = new JButton();
		time3_chin5.setHorizontalAlignment(SwingConstants.CENTER);
		time3_chin5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time3_chin5.setBounds(510, 172, 87, 86);
		panel3.add(time3_chin5);

		// 정보가져오기
		List<TimeDTO> list3_chin = daot.listTime1("친절한 금자씨", s3);
		JButton[] l13 = new JButton[5];
		l13[0] = time3_chin1;
		l13[1] = time3_chin2;
		l13[2] = time3_chin3;
		l13[3] = time3_chin4;
		l13[4] = time3_chin5;
		for (int i = 0; i < list3_chin.size(); i++) {
			l13[i].setText("<html><center>" + list3_chin.get(i).getScreenName() + " / " + list3_chin.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list3_chin.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l13[i].setName(list3_chin.get(i).getScreenplayCode());
			l13[i].addActionListener(this);

			 
			l13[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 탐정
		JButton time3_tam1 = new JButton();
		time3_tam1.setHorizontalAlignment(SwingConstants.CENTER);
		time3_tam1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_tam1.setBounds(161, 259, 87, 86);
		panel3.add(time3_tam1);

		JButton time3_tam2 = new JButton();
		time3_tam2.setHorizontalAlignment(SwingConstants.CENTER);
		time3_tam2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_tam2.setBounds(248, 259, 87, 86);
		panel3.add(time3_tam2);

		JButton time3_tam3 = new JButton();
		time3_tam3.setHorizontalAlignment(SwingConstants.CENTER);
		time3_tam3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_tam3.setBounds(336, 259, 87, 86);
		panel3.add(time3_tam3);

		JButton time3_tam4 = new JButton();
		time3_tam4.setHorizontalAlignment(SwingConstants.CENTER);
		time3_tam4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_tam4.setBounds(424, 259, 87, 86);
		panel3.add(time3_tam4);

		JButton time3_tam5 = new JButton();
		time3_tam5.setHorizontalAlignment(SwingConstants.CENTER);
		time3_tam5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time3_tam5.setBounds(510, 259, 87, 86);
		panel3.add(time3_tam5);

		// 정보가져오기
		List<TimeDTO> list3_tam = daot.listTime1("탐정:리턴즈", s3);
		JButton[] l14 = new JButton[5];
		l14[0] = time3_tam1;
		l14[1] = time3_tam2;
		l14[2] = time3_tam3;
		l14[3] = time3_tam4;
		l14[4] = time3_tam5;
		for (int i = 0; i < list3_tam.size(); i++) {
			l14[i].setText("<html><center>" + list3_tam.get(i).getScreenName() + " / " + list3_tam.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list3_tam.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l14[i].setName(list3_tam.get(i).getScreenplayCode());
			l14[i].addActionListener(this);

			 
			l14[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}
		// 독전
		JButton time3_dok1 = new JButton();
		time3_dok1.setHorizontalAlignment(SwingConstants.CENTER);
		time3_dok1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_dok1.setBounds(161, 344, 87, 86);
		panel3.add(time3_dok1);

		JButton time3_dok2 = new JButton();
		time3_dok2.setHorizontalAlignment(SwingConstants.CENTER);
		time3_dok2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_dok2.setBounds(248, 344, 87, 86);
		panel3.add(time3_dok2);

		JButton time3_dok3 = new JButton();
		time3_dok3.setHorizontalAlignment(SwingConstants.CENTER);
		time3_dok3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_dok3.setBounds(336, 344, 87, 86);
		panel3.add(time3_dok3);

		JButton time3_dok4 = new JButton();
		time3_dok4.setHorizontalAlignment(SwingConstants.CENTER);
		time3_dok4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time3_dok4.setBounds(424, 344, 87, 86);
		panel3.add(time3_dok4);

		JButton time3_dok5 = new JButton();
		time3_dok5.setHorizontalAlignment(SwingConstants.CENTER);
		time3_dok5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time3_dok5.setBounds(510, 344, 87, 86);
		panel3.add(time3_dok5);

		// 정보가져오기
		List<TimeDTO> list3_dok = daot.listTime1("독전", s3);
		JButton[] l15 = new JButton[5];
		l15[0] = time3_dok1;
		l15[1] = time3_dok2;
		l15[2] = time3_dok3;
		l15[3] = time3_dok4;
		l15[4] = time3_dok5;
		for (int i = 0; i < list3_dok.size(); i++) {
			l15[i].setText("<html><center>" + list3_dok.get(i).getScreenName() + " / " + list3_dok.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list3_dok.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l15[i].setName(list3_dok.get(i).getScreenplayCode());
			l15[i].addActionListener(this);

			 
			l15[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 넷째날
		tPane.add((d + 3) + "일", panel4);

		JLabel label4_zu = new JLabel("쥬라기월드 : 폴른 킹덤");
		label4_zu.setHorizontalAlignment(SwingConstants.CENTER);
		label4_zu.setBounds(0, 0, 162, 86);
		panel4.add(label4_zu);
		label4_zu.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));

		JLabel label4_o = new JLabel("오션스8");
		label4_o.setHorizontalAlignment(SwingConstants.CENTER);
		label4_o.setBounds(0, 86, 162, 86);
		panel4.add(label4_o);
		label4_o.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label4_chin = new JLabel("친절한 금자씨");
		label4_chin.setHorizontalAlignment(SwingConstants.CENTER);
		label4_chin.setBounds(0, 172, 162, 86);
		panel4.add(label4_chin);
		label4_chin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label4_tam = new JLabel("탐정:리턴즈");
		label4_tam.setHorizontalAlignment(SwingConstants.CENTER);
		label4_tam.setBounds(0, 259, 162, 86);
		panel4.add(label4_tam);
		label4_tam.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.controlHighlight));

		JLabel label4_dok = new JLabel("독전");
		label4_dok.setHorizontalAlignment(SwingConstants.CENTER);
		label4_dok.setBounds(0, 344, 162, 86);
		panel4.add(label4_dok);

		// 상영/시간(좌석/20석수)
		// 쥬라기
		JButton time4_zu1 = new JButton();
		time4_zu1.setHorizontalAlignment(SwingConstants.CENTER);
		time4_zu1.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_zu1.setBounds(161, 0, 87, 86);
		panel4.add(time4_zu1);

		JButton time4_zu2 = new JButton();
		time4_zu2.setHorizontalAlignment(SwingConstants.CENTER);
		time4_zu2.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_zu2.setBounds(248, 0, 87, 86);
		panel4.add(time4_zu2);

		JButton time4_zu3 = new JButton();
		time4_zu3.setHorizontalAlignment(SwingConstants.CENTER);
		time4_zu3.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_zu3.setBounds(336, 0, 87, 86);
		panel4.add(time4_zu3);

		JButton time4_zu4 = new JButton();
		time4_zu4.setHorizontalAlignment(SwingConstants.CENTER);
		time4_zu4.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_zu4.setBounds(424, 0, 87, 86);
		panel4.add(time4_zu4);

		JButton time4_zu5 = new JButton();
		time4_zu5.setHorizontalAlignment(SwingConstants.CENTER);
		time4_zu5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time4_zu5.setBounds(510, 0, 87, 86);
		panel4.add(time4_zu5);

		// 정보가져오기
		String s4 = "2018/06/" + (int) (d + 3);
		List<TimeDTO> list4_zu = daot.listTime1("쥬라기 월드: 폴른 킹덤", s4);
		JButton[] l16 = new JButton[5];
		l16[0] = time4_zu1;
		l16[1] = time4_zu2;
		l16[2] = time4_zu3;
		l16[3] = time4_zu4;
		l16[4] = time4_zu5;
		for (int i = 0; i < list4_zu.size(); i++) {
			l16[i].setText("<html><center>" + list4_zu.get(i).getScreenName() + " / " + list4_zu.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list4_zu.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l16[i].setName(list4_zu.get(i).getScreenplayCode());
			l16[i].addActionListener(this);

			 
			l16[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 오션스8
		JButton time4_o1 = new JButton();
		time4_o1.setHorizontalAlignment(SwingConstants.CENTER);
		time4_o1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_o1.setBounds(161, 86, 87, 86);
		panel4.add(time4_o1);

		JButton time4_o2 = new JButton();
		time4_o2.setHorizontalAlignment(SwingConstants.CENTER);
		time4_o2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_o2.setBounds(248, 86, 87, 86);
		panel4.add(time4_o2);

		JButton time4_o3 = new JButton();
		time4_o3.setHorizontalAlignment(SwingConstants.CENTER);
		time4_o3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_o3.setBounds(336, 86, 87, 86);
		panel4.add(time4_o3);

		JButton time4_o4 = new JButton();
		time4_o4.setHorizontalAlignment(SwingConstants.CENTER);
		time4_o4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_o4.setBounds(424, 86, 87, 86);
		panel4.add(time4_o4);

		JButton time4_o5 = new JButton();
		time4_o5.setHorizontalAlignment(SwingConstants.CENTER);
		time4_o5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time4_o5.setBounds(510, 86, 87, 86);
		panel4.add(time4_o5);

		// 정보가져오기
		List<TimeDTO> list4_o = daot.listTime1("오션스8", s4);
		JButton[] l17 = new JButton[5];
		l17[0] = time4_o1;
		l17[1] = time4_o2;
		l17[2] = time4_o3;
		l17[3] = time4_o4;
		l17[4] = time4_o5;
		for (int i = 0; i < list4_o.size(); i++) {
			l17[i].setText("<html><center>" + list4_o.get(i).getScreenName() + " / " + list4_o.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list4_o.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l17[i].setName(list4_o.get(i).getScreenplayCode());
			l17[i].addActionListener(this);

			 
			l17[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 친절한 금자씨
		JButton time4_chin1 = new JButton();
		time4_chin1.setHorizontalAlignment(SwingConstants.CENTER);
		time4_chin1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_chin1.setBounds(161, 172, 87, 86);
		panel4.add(time4_chin1);

		JButton time4_chin2 = new JButton();
		time4_chin2.setHorizontalAlignment(SwingConstants.CENTER);
		time4_chin2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_chin2.setBounds(248, 172, 87, 86);
		panel4.add(time4_chin2);

		JButton time4_chin3 = new JButton();
		time4_chin3.setHorizontalAlignment(SwingConstants.CENTER);
		time4_chin3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_chin3.setBounds(336, 172, 87, 86);
		panel4.add(time4_chin3);

		JButton time4_chin4 = new JButton();
		time4_chin4.setHorizontalAlignment(SwingConstants.CENTER);
		time4_chin4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_chin4.setBounds(424, 172, 87, 86);
		panel4.add(time4_chin4);

		JButton time4_chin5 = new JButton();
		time4_chin5.setHorizontalAlignment(SwingConstants.CENTER);
		time4_chin5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time4_chin5.setBounds(510, 172, 87, 86);
		panel4.add(time4_chin5);

		// 정보가져오기
		List<TimeDTO> list4_chin = daot.listTime1("친절한 금자씨", s4);

		JButton[] l18 = new JButton[5];
		l18[0] = time4_chin1;
		l18[1] = time4_chin2;
		l18[2] = time4_chin3;
		l18[3] = time4_chin4;
		l18[4] = time4_chin5;
		for (int i = 0; i < list4_chin.size(); i++) {
			l18[i].setText("<html><center>" + list4_chin.get(i).getScreenName() + " / " + list4_chin.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list4_chin.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l18[i].setName(list4_chin.get(i).getScreenplayCode());
			l18[i].addActionListener(this);

			 
			l18[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 탐정
		JButton time4_tam1 = new JButton();
		time4_tam1.setHorizontalAlignment(SwingConstants.CENTER);
		time4_tam1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_tam1.setBounds(161, 259, 87, 86);
		panel4.add(time4_tam1);

		JButton time4_tam2 = new JButton();
		time4_tam2.setHorizontalAlignment(SwingConstants.CENTER);
		time4_tam2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_tam2.setBounds(248, 259, 87, 86);
		panel4.add(time4_tam2);

		JButton time4_tam3 = new JButton();
		time4_tam3.setHorizontalAlignment(SwingConstants.CENTER);
		time4_tam3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_tam3.setBounds(336, 259, 87, 86);
		panel4.add(time4_tam3);

		JButton time4_tam4 = new JButton();
		time4_tam4.setHorizontalAlignment(SwingConstants.CENTER);
		time4_tam4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_tam4.setBounds(424, 259, 87, 86);
		panel4.add(time4_tam4);

		JButton time4_tam5 = new JButton();
		time4_tam5.setHorizontalAlignment(SwingConstants.CENTER);
		time4_tam5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time4_tam5.setBounds(510, 259, 87, 86);
		panel4.add(time4_tam5);

		// 정보가져오기
		List<TimeDTO> list4_tam = daot.listTime1("탐정:리턴즈", s4);
		JButton[] l19 = new JButton[5];
		l19[0] = time4_tam1;
		l19[1] = time4_tam2;
		l19[2] = time4_tam3;
		l19[3] = time4_tam4;
		l19[4] = time4_tam5;
		for (int i = 0; i < list4_tam.size(); i++) {
			l19[i].setText("<html><center>" + list4_tam.get(i).getScreenName() + " / " + list4_tam.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list4_tam.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l19[i].setName(list4_tam.get(i).getScreenplayCode());
			l19[i].addActionListener(this);

			 
			l19[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

		// 독전
		JButton time4_dok1 = new JButton();
		time4_dok1.setHorizontalAlignment(SwingConstants.CENTER);
		time4_dok1.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_dok1.setBounds(161, 344, 87, 86);
		panel4.add(time4_dok1);

		JButton time4_dok2 = new JButton();
		time4_dok2.setHorizontalAlignment(SwingConstants.CENTER);
		time4_dok2.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_dok2.setBounds(248, 344, 87, 86);
		panel4.add(time4_dok2);

		JButton time4_dok3 = new JButton();
		time4_dok3.setHorizontalAlignment(SwingConstants.CENTER);
		time4_dok3.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_dok3.setBounds(336, 344, 87, 86);
		panel4.add(time4_dok3);

		JButton time4_dok4 = new JButton();
		time4_dok4.setHorizontalAlignment(SwingConstants.CENTER);
		time4_dok4.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(227, 227, 227)));
		time4_dok4.setBounds(424, 344, 87, 86);
		panel4.add(time4_dok4);

		JButton time4_dok5 = new JButton();
		time4_dok5.setHorizontalAlignment(SwingConstants.CENTER);
		time4_dok5.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(227, 227, 227)));
		time4_dok5.setBounds(510, 344, 87, 86);
		panel4.add(time4_dok5);

		// 정보가져오기
		List<TimeDTO> list4_dok = daot.listTime1("독전", s4);
		JButton[] l20 = new JButton[5];
		l20[0] = time4_dok1;
		l20[1] = time4_dok2;
		l20[2] = time4_dok3;
		l20[3] = time4_dok4;
		l20[4] = time4_dok5;
		
				JButton btn_memInfo = new JButton("내정보");
				btn_memInfo.setBounds(907, 16, 87, 23);
				contentPane_Main.add(btn_memInfo);
				
						JButton btn_logout = new JButton("로그아웃");
						btn_logout.setBounds(994, 16, 88, 23);
						contentPane_Main.add(btn_logout);
						
						JButton btnNewButton = new JButton("\uB9E4\uC810");
						btnNewButton.setBounds(952, 369, 130, 36);
						contentPane_Main.add(btnNewButton);
						btn_logout.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									// test
									Frame[] frames = Frame.getFrames();
									for (Frame frame : frames)
										frame.dispose();

									LoginFrame frameN = new LoginFrame();
									frameN.setVisible(true);

								} catch (Exception e2) {
									e2.printStackTrace();
								}

							}
						});
				btn_memInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						System.out.println(id);
						MyInfoFrame f = new MyInfoFrame(id);
						f.setVisible(true);
					}
				});

		for (int i = 0; i < list4_dok.size(); i++) {
			l20[i].setText("<html><center>" + list4_dok.get(i).getScreenName() + " / " + list4_dok.get(i).getStartTime()
					+ "<br>" + daot.leftSeat(list4_dok.get(i).getScreenplayCode()) + "석/20석</center></html>");

			l20[i].setName(list4_dok.get(i).getScreenplayCode());
			l20[i].addActionListener(this);

			 
			l20[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JButton b = (JButton) e.getSource();
					YemaeFrame f = new YemaeFrame(id, b.getName());
					f.setVisible(true);
				}
			});
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}