package source;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MyInfoFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelt1;
	// private JScrollPane panelt2;
	// private JScrollPane panelt3;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtTel;
	private JTextField txtBirth;
	private JButton submitbtn;

	MemberDTO dto = new MemberDTO();
	MemberDAO dao = new MemberDAO();
	MileageDTO dtoM = new MileageDTO();
	MileageDAO daoM = new MileageDAO();
	YemaeDAO daoY = new YemaeDAO();

	private JLabel idlbl;
	private JTextField txtNowpw;
	private JLabel label_4;
	private JTextField txtNewpw;
	private JLabel label_5;
	private JLabel pwmsg;
	private JTable tableY;
	private JTable tableM;
	private JLabel lblNewLabel_2;
	private JButton outbutton;

	public MyInfoFrame(String memcode) {
		// 일단 생성자에 아이디는 주고 시작해줘
		dto = dao.readMemberMemcode(memcode);

		setTitle("내 정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 610, 340);
		contentPane.add(tabbedPane);

		panelt1 = new JPanel();
		panelt1.setBackground(Color.WHITE);
		panelt1.setLayout(null);

		tabbedPane.add("내 정보", panelt1);

		JLabel lblNewLabel = new JLabel("\u25B6 \uC544\uC774\uB514");
		lblNewLabel.setBounds(64, 36, 190, 15);
		panelt1.add(lblNewLabel);

		JLabel label = new JLabel("\u25B6 \uC774\uB984");
		label.setBounds(64, 127, 190, 15);
		panelt1.add(label);

		JLabel label_1 = new JLabel("\u25B6 \uC774\uBA54\uC77C");
		label_1.setBounds(64, 161, 190, 15);
		panelt1.add(label_1);

		JLabel label_2 = new JLabel("\u25B6 \uC804\uD654\uBC88\uD638");
		label_2.setBounds(64, 195, 190, 15);
		panelt1.add(label_2);

		JLabel label_3 = new JLabel("\u25B6 \uC0DD\uB144\uC6D4\uC77C");
		label_3.setBounds(64, 228, 190, 15);
		panelt1.add(label_3);

		txtName = new JTextField(dto.getName());
		txtName.setColumns(10);
		txtName.setBounds(286, 124, 256, 21);
		panelt1.add(txtName);

		txtEmail = new JTextField(dto.getEmail());
		txtEmail.setColumns(10);
		txtEmail.setBounds(286, 158, 256, 21);
		panelt1.add(txtEmail);

		txtTel = new JTextField(dto.getTel());
		txtTel.setColumns(10);
		txtTel.setBounds(286, 192, 256, 21);
		panelt1.add(txtTel);

		txtBirth = new JTextField(dto.getBirth());
		txtBirth.setColumns(10);
		txtBirth.setBounds(286, 225, 256, 21);
		panelt1.add(txtBirth);

		submitbtn = new JButton("\uC218\uC815\uD558\uAE30");
		submitbtn.addActionListener(this);
		submitbtn.setBounds(445, 271, 97, 23);
		panelt1.add(submitbtn);

		idlbl = new JLabel(dto.getId());
		idlbl.setBounds(286, 36, 256, 15);
		panelt1.add(idlbl);

		txtNowpw = new JTextField((String) null);
		txtNowpw.setColumns(10);
		txtNowpw.setBounds(286, 61, 256, 21);
		panelt1.add(txtNowpw);

		label_4 = new JLabel("\u25B6 \uD604\uC7AC \uBE44\uBC00\uBC88\uD638");
		label_4.setBounds(64, 64, 190, 15);
		panelt1.add(label_4);

		txtNewpw = new JTextField((String) null);
		txtNewpw.setColumns(10);
		txtNewpw.setBounds(286, 92, 256, 21);
		panelt1.add(txtNewpw);

		label_5 = new JLabel("\u25B6 \uBCC0\uACBD \uBE44\uBC00\uBC88\uD638");
		label_5.setBounds(64, 95, 190, 15);
		panelt1.add(label_5);

		pwmsg = new JLabel("");
		pwmsg.setBounds(64, 271, 221, 23);
		panelt1.add(pwmsg);

		outbutton = new JButton("\uD0C8\uD1F4\uD558\uAE30");
		outbutton.setBounds(323, 271, 97, 23);
		outbutton.addActionListener(this);
		panelt1.add(outbutton);

		/*
		 * panelt2 = new JScrollPane(); panelt2.setBackground(Color.WHITE);
		 * panelt2.setLayout(null);
		 */
		String[] title = { "예매코드", "제목", "상영관", "상영날짜", "시작시간", "인원", "가격", "사용마일리지", "예매날짜" };
		MyTableModel mt = new MyTableModel(title);

		tableY = new JTable(mt);
		tableY.setBounds(12, 42, 581, 259);
		JScrollPane sp = new JScrollPane(tableY);

		tabbedPane.add("내 예매", sp);// 예매내역

		List<PayDTO> list1 = daoY.listYemeahis(dto.getMemcode());
		for (PayDTO dto : list1) {
			// 테이블에 자료 추가
			String[] items = new String[9];

			items[0] = dto.getMyYemaeCode();
			items[1] = dto.getmTitle();
			items[2] = dto.getScreenName();
			items[3] = dto.getPlaydate();
			items[4] = dto.getMstartTime();
			items[5] = dto.getPerN() + "";
			items[6] = dto.getTotalPrice() + "";
			items[7] = dto.getUseM() + "";
			items[8] = dto.getTicketingdate();
			// System.out.println(items[0] + " " + items[1] + " " + items[2] + " " +
			// items[3] + " " + items[4] + " "
			// + items[5] + " " + items[6] + " " + items[7] + " " + items[8]);
			((DefaultTableModel) tableY.getModel()).addRow(items);
		}

		String[] title2 = { "마일리지코드", "현재마일리지", "내역", "날짜" };
		MyTableModel mt2 = new MyTableModel(title2);

		lblNewLabel_2 = new JLabel(
				"\uC608\uB9E4\uCF54\uB4DC    \uC601\uD654\uC81C\uBAA9    \uC0C1\uC601\uAD00\uC774\uB984    \uC0C1\uC601\uC77C\uC790    \uC0C1\uC601\uC2DC\uAC04    \uC778\uC6D0\uC218    \uAC00\uACA9    \uC0AC\uC6A9\uB9C8\uC77C\uB9AC\uC9C0    \uC608\uB9E4\uC77C\uC790");
		lblNewLabel_2.setBounds(12, 10, 581, 15);

		tableM = new JTable(mt2);
		tableM.setBounds(12, 39, 581, 262);
		JScrollPane sp2 = new JScrollPane(tableM);
		tabbedPane.add("내 마일리지", sp2);// 마일리지 내역

		List<MileageDTO> list2 = daoM.listMileage(dto.getMemcode());
		for (MileageDTO dto : list2) {
			// 테이블에 자료 추가
			String[] items = new String[4];

			items[0] = dto.getMilecode();
			items[1] = dto.getNowmile() + "";
			items[2] = dto.getNeayeong();
			items[3] = dto.getHistorydate();
			((DefaultTableModel) tableM.getModel()).addRow(items);
		}

		// panelt3.add(tableM);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == submitbtn) {

			// 회원정보 업데이트
			if (!txtNowpw.getText().equals(dto.getPwd())) {
				System.out.println("비밀번호 불일치");
				pwmsg.setText("비밀번호 불일치");
				return;
			}

			// 수정하기
			MemberDTO updto = new MemberDTO();
			updto.setId(dto.getId());
			updto.setMemcode(dto.getMemcode());
			updto.setName(txtName.getText());
			updto.setPwd(txtNewpw.getText());
			updto.setEmail(txtEmail.getText());
			updto.setTel(txtTel.getText());
			updto.setBirth(txtBirth.getText());

			int r = dao.updateMember(updto);

			if (r == 1) {
				System.out.println("수정완료");
				pwmsg.setText("수정 완료");
			}
		} else if (e.getSource() == outbutton) {
			// 탈퇴
			int r = dao.outMember(dto);

			if (r == 1) {
				System.out.println("탈퇴완료");
				
				Frame[] frames = Frame.getFrames();
				for (Frame frame : frames)
					frame.dispose();
//				this.dispose();

				LoginFrame frameN = new LoginFrame();
				frameN.setVisible(true);
			}
		}

	}

}