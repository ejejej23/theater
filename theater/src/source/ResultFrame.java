package source;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ResultFrame extends JFrame {
	private JPanel contentPane;
	private String myYemaeCode;
	private String myPayCode;
	private String playdate;
	private String myseats[];
	private String mTitle;
	private String mstartTime;
	private String screenName;
	private int mileage;
	private int useM;
	private String mycode;

	public ResultFrame(PayDTO dto) {
		this.mycode=dto.getMyCode();//test
		System.out.println(mycode);
		this.myYemaeCode = dto.getMyYemaeCode();
		this.myPayCode = dto.getMyPayCode();
		this.playdate = dto.getPlaydate();
		this.myseats = dto.getMyseats();
		this.mTitle = dto.getmTitle();
		this.mstartTime = dto.getMstartTime();
		this.screenName = dto.getScreenName();
		this.mileage = dto.getMileage();
		this.useM = dto.getUseM();

		setTitle("예매완료-예매정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("\u25B6 \uACB0\uC81C\uCF54\uB4DC");
		lblNewLabel.setBounds(12, 55, 107, 15);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\u25B6 \uC601\uD654\uC81C\uBAA9");
		label.setBounds(12, 80, 107, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u25B6 \uC0C1\uC601\uAD00");
		label_1.setBounds(12, 105, 107, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u25B6 \uC0C1\uC601\uC2DC\uAC04");
		label_2.setBounds(12, 130, 107, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u25B6 \uC608\uB9E4\uC88C\uC11D");
		label_3.setBounds(12, 162, 107, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u25B6 \uC0AC\uC6A9 \uB9C8\uC77C\uB9AC\uC9C0");
		label_4.setBounds(12, 219, 107, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\u25B6 \uB0A8\uC740 \uB9C8\uC77C\uB9AC\uC9C0");
		label_5.setBounds(12, 243, 107, 15);
		contentPane.add(label_5);

		JLabel pcodelbl = new JLabel(myPayCode);
		pcodelbl.setBounds(153, 55, 107, 15);
		contentPane.add(pcodelbl);

		JLabel titlelbl = new JLabel(mTitle);
		titlelbl.setBounds(153, 80, 223, 15);
		contentPane.add(titlelbl);

		JLabel screenlbl = new JLabel(screenName);
		screenlbl.setBounds(153, 105, 107, 15);
		contentPane.add(screenlbl);

		JLabel playdatelbl = new JLabel(playdate);
		playdatelbl.setBounds(153, 130, 94, 22);
		contentPane.add(playdatelbl);

		JLabel seatNum = new JLabel(myseats.length + "");
		seatNum.setBounds(153, 162, 37, 15);
		contentPane.add(seatNum);

		JLabel useMlbl = new JLabel(useM + "");
		useMlbl.setBounds(153, 219, 107, 15);
		contentPane.add(useMlbl);

		JLabel leftMlbl = new JLabel(mileage + "");
		leftMlbl.setBounds(153, 243, 107, 15);
		contentPane.add(leftMlbl);

		// 좌석 보여주기
		String s = "";
		for (int i = 0; i < myseats.length; i++) {
			s = s + myseats[i] + " ";
		}

		JLabel seats = new JLabel(s);
		seats.setBounds(153, 187, 191, 22);
		contentPane.add(seats);

		JLabel label_6 = new JLabel("\u25B6 \uC608\uB9E4\uCF54\uB4DC");
		label_6.setBounds(12, 30, 107, 15);
		contentPane.add(label_6);

		JLabel ycodelbl = new JLabel(myYemaeCode);
		ycodelbl.setBounds(153, 30, 107, 15);
		contentPane.add(ycodelbl);

		JLabel playtimelbl = new JLabel(mstartTime);
		playtimelbl.setBounds(259, 130, 85, 22);
		contentPane.add(playtimelbl);

		JLabel lblNewLabel_1 = new JLabel("point");
		lblNewLabel_1.setBounds(272, 219, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel label_7 = new JLabel("point");
		label_7.setBounds(272, 243, 57, 15);
		contentPane.add(label_7);

		JLabel lblNewLabel_2 = new JLabel("\uC11D");
		lblNewLabel_2.setBounds(202, 162, 57, 15);
		contentPane.add(lblNewLabel_2);

		JButton lastbtn = new JButton("\uD655\uC778");
		lastbtn.setBounds(133, 279, 97, 28);
		lastbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// dispose();

				Frame[] frames = Frame.getFrames();
				for (Frame frame : frames) {
					frame.dispose();
				}
				
				Main_up mainframe = new Main_up(mycode);
				mainframe.setVisible(true);
				//this.dispose();
				
//					if (!frame.getName().equals("frame1")) {
//						frame.dispose();
//					}else{
//						frame.revalidate();;
//						frame.repaint();;
//					}
			}

		});
		contentPane.add(lastbtn);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
