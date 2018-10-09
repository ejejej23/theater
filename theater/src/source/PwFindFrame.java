package source;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PwFindFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MemberDAO mdao = new MemberDAO();
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField emailField;
	private JButton closeButton;


	public PwFindFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setTitle("비밀번호 찾기");

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("굴림", Font.BOLD, 13));
		lblId.setBounds(28, 28, 57, 15);
		contentPane.add(lblId);

		idField = new JTextField();
		idField.setBounds(102, 25, 116, 21);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setBounds(28, 72, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("EMAIL");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel_1.setBounds(28, 116, 57, 15);
		contentPane.add(lblNewLabel_1);

		nameField = new JTextField();
		nameField.setBounds(102, 69, 116, 21);
		contentPane.add(nameField);
		nameField.setColumns(10);

		emailField = new JTextField();
		emailField.setBounds(102, 113, 116, 21);
		contentPane.add(emailField);
		emailField.setColumns(10);

		JButton findButton = new JButton("FIND");
		findButton.setBounds(293, 28, 97, 68);
		findButton.setActionCommand("find");
		findButton.addActionListener(this);
		contentPane.add(findButton);

		closeButton = new JButton("CLOSE");
		closeButton.setFont(new Font("굴림", Font.PLAIN, 12));
		closeButton.setBounds(293, 110, 97, 26);
		closeButton.setActionCommand("close");
		closeButton.addActionListener(this);
		contentPane.add(closeButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		MemberDTO dto = new MemberDTO();
		if (cmd.equals("find")) {
			dto = mdao.findMember(idField.getText(), nameField.getText(), emailField.getText());
			if (dto == null) {
				System.out.println("등록되지 않은 회원입니다.");
				init();
				return;
			} else {
				String randpw = getRamdomPassword();
				System.out.println(dto.getBirth());
				dto.setBirth(dto.getBirth().substring(0, 10));
				dto.setPwd(randpw);
				mdao.updateMember(dto);

				init();
				JOptionPane.showMessageDialog(this, "임시 비밀번호가 메일로 발송되었습니다.");

			}
		} else if (cmd.equals("close")) {
			this.dispose();
		}

	}

	private String getRamdomPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'f', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			idx = (int) (charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	private void init() {
		idField.setText("");
		nameField.setText("");
		emailField.setText("");
	}

}
