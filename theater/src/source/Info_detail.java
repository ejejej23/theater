package source;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Info_detail {
	private JFrame frame;
	MovieDAO dao = new MovieDAO();
	MovieDTO dto = new MovieDTO();

	GenreDTO gdto = new GenreDTO();
	GenreDAO gdao = new GenreDAO();

	GradeDTO rdto = new GradeDTO();
	GradeDAO rdao = new GradeDAO();
	
	ReviewDTO vdto = new ReviewDTO();
	ReviewDAO vdao = new ReviewDAO();

	//jwyang ���� �߰�
	private String code;
	private String poster_path;

	private final JLabel lbl_rank_Image = new JLabel();

	/** Launch the application. */
	/*
	 * public static void djInfo() { EventQueue.invokeLater(new Runnable() { public
	 * void run() { try { Info_Dj window = new Info_Dj();
	 * window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	 * } }); }
	 */
	/** Create the application. */

	public Info_detail(String code, String poster_path) {
		//jwyang �ʱ�ȭ
		this.code = code;
		this.poster_path = poster_path;
		initialize();
		
	}

	/** Initialize the contents of the frame. */

	private void initialize() {
		
		dto = dao.readMovie(code);
		gdto = gdao.readGenre(dto.getGenreCode());
		rdto = rdao.readGrade(dto.getGradeCode());
		vdto = vdao.readReview(dto.getReviewCode());
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("��ȭ��");
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JPanel panel_Title = new JPanel();
		panel_Title.setBounds(0, 0, 572, 63);
		frame.getContentPane().add(panel_Title);
		panel_Title.setLayout(null);

		/* ���� */
		JLabel lblTitle = new JLabel();
		lblTitle.setBounds(12, 10, 188, 21);
		lblTitle.setFont(new Font("����", Font.BOLD, 20));

		if (dto == null) { // null�̸� ������ �� �������� ����
			System.out.println("DB�� ������ �����ϴ�.");
			return;
		} // ���� ������ �Ʒ��� ����
		lblTitle.setText(dto.getTitle());
		panel_Title.add(lblTitle);

		/* ����(��)���� */
		JLabel lbl_Eng_title = new JLabel();
		lbl_Eng_title.setFont(new Font("����", Font.PLAIN, 15));
		lbl_Eng_title.setBounds(12, 41, 491, 15);
		lbl_Eng_title.setText(dto.getEng_title());
		panel_Title.add(lbl_Eng_title);

		/* �ٰŸ� �� */
		JLabel lbl_story = new JLabel("\uC904\uAC70\uB9AC");
		lbl_story.setBounds(10, 214, 42, 16);
		frame.getContentPane().add(lbl_story);
		lbl_story.setFont(new Font("����", Font.BOLD, 13));
		/* �ٰŸ� �ǳ� */
		JPanel pnl_story = new JPanel();
		pnl_story.setBounds(0, 241, 373, 210);
		frame.getContentPane().add(pnl_story);
		pnl_story.setLayout(null);
		/* �ٰŸ� ��ũ�� */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 364, 208);
		pnl_story.add(scrollPane);
		/* �ٰŸ� ���� */
		JTextArea textArea_story = new JTextArea();
		textArea_story.setEditable(false);
		textArea_story.setLineWrap(true);
		scrollPane.setViewportView(textArea_story);
		textArea_story.append(dto.getStory());
		textArea_story.setCaretPosition(0);

		/* ������ */
		JLabel lbl_Image = new JLabel("");
		lbl_Image.setIcon(new ImageIcon(Info_detail.class.getResource(poster_path)));
		lbl_Image.setBounds(383, 72, 188, 268);
		frame.getContentPane().add(lbl_Image);

		/* Info */
		JPanel pnl_Info = new JPanel();
		pnl_Info.setBounds(0, 73, 373, 131);
		frame.getContentPane().add(pnl_Info);
		pnl_Info.setLayout(null);
		/* �帣 */
		JLabel lbl_genre = new JLabel("\uC7A5\uB974");
		lbl_genre.setBounds(10, 9, 38, 15);
		lbl_genre.setFont(new Font("����", Font.BOLD, 14));
		pnl_Info.add(lbl_genre);
		// ����
		JLabel lbl_genre2 = new JLabel();
		lbl_genre2.setText(gdto.getGenreName());
		lbl_genre2.setBounds(90, 10, 113, 15);
		pnl_Info.add(lbl_genre2);

		/* ���� */
		JLabel lbl_director = new JLabel();
		lbl_director.setText("����");
		lbl_director.setFont(new Font("����", Font.BOLD, 14));
		lbl_director.setBounds(10, 35, 57, 15);
		pnl_Info.add(lbl_director);
		// ����
		JLabel lbl_director2 = new JLabel();
		lbl_director2.setText(dto.getDirector());
		lbl_director2.setBounds(90, 35, 200, 15);
		pnl_Info.add(lbl_director2);

		/* ��� */
		JLabel lbl_actor = new JLabel("\uCD9C\uC5F0");
		lbl_actor.setFont(new Font("����", Font.BOLD, 14));
		lbl_actor.setBounds(10, 60, 57, 15);
		pnl_Info.add(lbl_actor);
		// ����
		JLabel lbl_actor2 = new JLabel();
		lbl_actor2.setText(dto.getActor());
		lbl_actor2.setBounds(90, 61, 265, 15);
		pnl_Info.add(lbl_actor2);

		/* �� ��� */
		JLabel lblgrade = new JLabel("\uB4F1\uAE09");
		lblgrade.setFont(new Font("����", Font.BOLD, 14));
		lblgrade.setBounds(10, 85, 57, 15);
		pnl_Info.add(lblgrade);
		// ����
		JLabel lbl_grade2 = new JLabel();
		lbl_grade2.setText(rdto.getGradeName());
		lbl_grade2.setBounds(90, 86, 83, 15);
		pnl_Info.add(lbl_grade2);

		/* �󿵽ð� */
		JLabel lbl_playtime = new JLabel("\uC0C1\uC601\uC2DC\uAC04");
		lbl_playtime.setFont(new Font("����", Font.BOLD, 14));
		lbl_playtime.setBounds(10, 110, 73, 15);
		pnl_Info.add(lbl_playtime);
		// ����
		JLabel lbl_playtime2 = new JLabel();
		lbl_playtime2.setText(Integer.toString(dto.getPlaytime()));
		lbl_playtime2.setBounds(90, 110, 57, 15);
		pnl_Info.add(lbl_playtime2);

		/* ���� �ǳ� */
		JPanel panel_rank = new JPanel();
		panel_rank.setBounds(384, 370, 186, 81);
		frame.getContentPane().add(panel_rank);
		panel_rank.setLayout(null);
		/* ���� �� */
		JLabel lbl_rank = new JLabel("\uD3C9\uC810");
		lbl_rank.setBounds(76, 0, 30, 17);
		panel_rank.add(lbl_rank);
		lbl_rank.setFont(new Font("����", Font.BOLD, 14));
		/* ���� �̹��� */
		lbl_rank_Image.setBounds(25, 14, 135, 50);
		panel_rank.add(lbl_rank_Image);
		/* ���� ����ġ�� */
		vdto = vdao.readReview(vdto.getReviewCode());
		int vnum = vdto.getReview();
			
		String path = null;
		switch (vnum) {
		case 1:
			path = "/source/st_1.png";

			break;
		case 2:
			path ="/source/st_2.png";

			break;
		case 3:
			path ="/source/st_3.png";

			break;
		case 4:
			path ="/source/st_4.png";

			break;
		case 5:
			path ="/source/st_5.png";

			break;
			
		default:
			path ="/mainmenu_up/st_0.png";

			break;
		}
		ImageIcon icon = new ImageIcon(Info_Detective.class.getResource(path));
		Image origin = icon.getImage();
		if (vnum == 5) {
			origin = origin.getScaledInstance(135, 50, Image.SCALE_SMOOTH);
		} else {

			origin = origin.getScaledInstance(135, 30, Image.SCALE_SMOOTH);
		}
		ImageIcon icon2 = new ImageIcon(origin);
		lbl_rank_Image.setIcon(icon2);
	}

}
