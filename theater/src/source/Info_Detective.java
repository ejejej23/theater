package source;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Info_Detective {
	private JFrame frame;
	MovieDAO dao = new MovieDAO();
	MovieDTO dto = new MovieDTO();
	
	GenreDTO gdto = new GenreDTO();
	GenreDAO gdao = new GenreDAO();
	
	GradeDTO rdto = new GradeDTO();
	GradeDAO rdao = new GradeDAO();
	private final JLabel lbl_rank_Image = new JLabel();

	/** Launch the application. */
	public static void detectiveInfo() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info_Detective window = new Info_Detective();
					window.frame.setVisible(true);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application. */
	public Info_Detective() {
		initialize();
	}

	/** Initialize the contents of the frame. */

	private void initialize() {
		dto = dao.readMovie("1001");
		gdto = gdao.readGenre("16");
		rdto = rdao.readGrade("gr15");

		frame = new JFrame();
		frame.setTitle("\uD0D0\uC815 \uC0C1\uC138\uC815\uBCF4");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_Title = new JPanel();
		panel_Title.setBounds(0, 0, 572, 63);
		frame.getContentPane().add(panel_Title);
		panel_Title.setLayout(null);

		/* 제목 */
		JLabel lblTitle = new JLabel();
		lblTitle.setBounds(12, 10, 188, 21);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));

		if (dto == null) { // null이면 밑으로 안 내려가게 막음
			System.out.println("DB에 정보가 없습니다.");
			return;
		} // 값이 있으면 아래로 진행
		lblTitle.setText(dto.getTitle());
		panel_Title.add(lblTitle);

		/* 영어(소)제목 */
		JLabel lbl_Eng_title = new JLabel();
		lbl_Eng_title.setFont(new Font("굴림", Font.PLAIN, 15));
		lbl_Eng_title.setBounds(12, 41, 491, 15);
		lbl_Eng_title.setText(dto.getEng_title());
		panel_Title.add(lbl_Eng_title);

		/* 줄거리 판넬 */
		JPanel pnl_story = new JPanel();
		pnl_story.setBounds(10, 241, 361, 210);
		frame.getContentPane().add(pnl_story);
		pnl_story.setLayout(null);
		/* 줄거리 스크롤*/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 360, 210);
		pnl_story.add(scrollPane);
		/* 줄거리 내용*/
		JTextArea textArea_story = new JTextArea();
		textArea_story.setEditable(false);
		textArea_story.setLineWrap(true);
		scrollPane.setViewportView(textArea_story);
		textArea_story.append(dto.getStory());
		textArea_story.setCaretPosition(0);


		/* 포스터 */
		JLabel lbl_Image = new JLabel("");
		lbl_Image.setIcon(new ImageIcon(Info_Detective.class.getResource("/mainmenu_up/detective_re.jpg")));
		lbl_Image.setBounds(384, 73, 188, 268);
		frame.getContentPane().add(lbl_Image);

		JPanel pnl_Info = new JPanel();
		pnl_Info.setBounds(0, 73, 371, 131);
		frame.getContentPane().add(pnl_Info);
		pnl_Info.setLayout(null);
		
		
		/* 장르 */
		JLabel lbl_genre = new JLabel("\uC7A5\uB974");
		lbl_genre.setBounds(10, 9, 38, 15);
		lbl_genre.setFont(new Font("굴림", Font.BOLD, 14));
		pnl_Info.add(lbl_genre);
		// 내용
		JLabel lbl_genre2 = new JLabel();
		lbl_genre2.setText(gdto.getGenreName());
		lbl_genre2.setBounds(90, 10, 113, 15);
		pnl_Info.add(lbl_genre2);
		

		/* 감독 */
		JLabel lbl_director = new JLabel();
		lbl_director.setText("감독");
		lbl_director.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_director.setBounds(10, 35, 57, 15);
		pnl_Info.add(lbl_director);
		// 내용
		JLabel lbl_director2 = new JLabel();
		lbl_director2.setText(dto.getDirector());
		lbl_director2.setBounds(90, 35, 57, 15);
		pnl_Info.add(lbl_director2);

		
		/* 배우 */
		JLabel lbl_actor = new JLabel("\uCD9C\uC5F0");
		lbl_actor.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_actor.setBounds(10, 60, 57, 15);
		pnl_Info.add(lbl_actor);
		// 내용
		JLabel lbl_actor2 = new JLabel();
		lbl_actor2.setText(dto.getActor());
		lbl_actor2.setBounds(90, 61, 265, 15);
		pnl_Info.add(lbl_actor2);
		
		
		/* 상영 등급 */
		JLabel lbl_grade = new JLabel();
		lbl_grade = new JLabel("\uB4F1\uAE09");
		lbl_grade.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_grade.setBounds(10, 85, 38, 15);
		pnl_Info.add(lbl_grade);
		// 내용
		JLabel lbl_grade2 = new JLabel();
		lbl_grade2.setBounds(90, 86, 83, 15);
		pnl_Info.add(lbl_grade2);
		lbl_grade2.setText(rdto.getGradeName());
		
		
		/* 상영시간 */
		JLabel lbl_playtime = new JLabel("\uC0C1\uC601\uC2DC\uAC04");
		lbl_playtime.setFont(new Font("굴림", Font.BOLD, 14));
		lbl_playtime.setBounds(10, 110, 73, 15);
		pnl_Info.add(lbl_playtime);
		// 내용
		JLabel lbl_playtime2 = new JLabel();
		lbl_playtime2.setText(Integer.toString(dto.getPlaytime()));
		lbl_playtime2.setBounds(90, 110, 57, 15);
		pnl_Info.add(lbl_playtime2);
		JLabel lbl_story = new JLabel("\uC904\uAC70\uB9AC");
		lbl_story.setBounds(10, 214, 42, 16);
		frame.getContentPane().add(lbl_story);
		lbl_story.setFont(new Font("굴림", Font.BOLD, 13));
		
		
		/* 평점 판넬*/
		JPanel panel_rank = new JPanel();
		panel_rank.setBounds(383, 370, 186, 81);
		frame.getContentPane().add(panel_rank);
		panel_rank.setLayout(null);
		/* 평점 라벨*/
		JLabel lbl_rank = new JLabel("\uD3C9\uC810");
		lbl_rank.setBounds(80, 0, 30, 17);
		panel_rank.add(lbl_rank);
		lbl_rank.setFont(new Font("굴림", Font.BOLD, 14));
		/* 평점 이미지*/
		lbl_rank_Image.setBounds(20, 17, 138, 50);
		panel_rank.add(lbl_rank_Image);
		lbl_rank_Image.setIcon(new ImageIcon(Info_Detective.class.getResource("/mainmenu_up/st.png")));
		ImageIcon icon = new ImageIcon(Info_Detective.class.getResource("/mainmenu_up/st.png"));
		Image origin = icon.getImage();
		origin = origin.getScaledInstance(150, 130, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(origin);
		lbl_rank_Image.setIcon(icon2);
	}
}
