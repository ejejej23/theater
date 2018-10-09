package source;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JoinFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MemberDAO mdao = new MemberDAO();
	private JPanel contentPane;
	private JTextField[] textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel label;
	private JLabel label_1;
	private int idcheck;
	private int pwcheck;

	public JoinFrame() {
		setTitle("JOIN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(35, 25, 109, 15);
		contentPane.add(lblId);

		textField = new JTextField[5];
		int x = 19;
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField();
			textField[i].setBounds(127, x, 116, 21);
			textField[i].setColumns(10);
			if (i == 1) {
				x += 93;
			} else {
				x += 30;
			}

			textField[i].setFocusTraversalKeysEnabled(false);
			textField[i].addKeyListener(new KeyHandler());
			contentPane.add(textField[i]);
		}

		textField[0].getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				System.out.println("test");
				idcheck = 0;
			}
		});

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(35, 53, 109, 15);
		contentPane.add(lblName);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(35, 84, 109, 15);
		contentPane.add(lblPassword);

		JLabel lblRepassword = new JLabel("REPASSWORD");
		lblRepassword.setBounds(35, 115, 109, 15);
		contentPane.add(lblRepassword);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(35, 146, 109, 15);
		contentPane.add(lblEmail);

		JLabel lblTel = new JLabel("TEL");
		lblTel.setBounds(35, 181, 109, 15);
		contentPane.add(lblTel);

		JLabel lblBirth = new JLabel("BIRTH");
		lblBirth.setBounds(35, 209, 109, 15);
		contentPane.add(lblBirth);

		JButton btnIdCheck = new JButton("ID CHECK");
		btnIdCheck.setBounds(292, 21, 97, 26);
		btnIdCheck.setActionCommand("idcheck");
		btnIdCheck.addActionListener(this);
		contentPane.add(btnIdCheck);

		JButton btnInit = new JButton("INIT");
		btnInit.setBounds(292, 129, 97, 26);
		btnInit.setActionCommand("init");
		btnInit.addActionListener(this);
		contentPane.add(btnInit);

		JButton btnJoin = new JButton("JOIN");
		btnJoin.setBounds(292, 165, 97, 26);
		btnJoin.setActionCommand("join");
		btnJoin.addActionListener(this);
		contentPane.add(btnJoin);

		label = new JLabel("");
		label.setFont(new Font("굴림", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(255, 60, 167, 26);
		contentPane.add(label);

		passwordField = new JPasswordField();
		passwordField.setBounds(127, 78, 116, 21);
		contentPane.add(passwordField);
		passwordField.setFocusTraversalKeysEnabled(false);
		passwordField.addKeyListener(new KeyHandler());

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(127, 109, 116, 21);
		contentPane.add(passwordField_1);
		passwordField_1.setFocusTraversalKeysEnabled(false);
		passwordField_1.addKeyListener(new KeyHandler());
		passwordField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String pwd = new String(passwordField.getPassword());
				String pwd2 = new String(passwordField_1.getPassword());
				if (pwd.equals(pwd2)) {
					label_1.setText("");
					pwcheck = 1;
				} else {
					label_1.setText("비밀번호가 다릅니다.");
					pwcheck = 0;
				}
			}
		});

		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.PLAIN, 11));
		label_1.setBounds(255, 96, 167, 26);
		contentPane.add(label_1);

		JButton btnClose = new JButton("CLOSE");
		btnClose.setActionCommand("close");
		btnClose.addActionListener(this);
		btnClose.setBounds(292, 203, 97, 26);
		contentPane.add(btnClose);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		MemberDTO dto = new MemberDTO();

		if (cmd.equals("idcheck")) {
			String id = textField[0].getText();
			if (textField[0].getText().isEmpty()) {
				label.setText("ID를 입력해주세요.");
				return;
			}
			dto = mdao.readMember(id);
			if (dto != null) {
				label.setText("이미 존재하는 ID 입니다.");
				idcheck = 0;
			} else {
				label.setText("사용 가능한 ID 입니다.");
				idcheck = 1;
			}
		} else if (cmd.equals("join")) {
			if (idcheck == 0) {
				JOptionPane.showMessageDialog(this, "ID 중복 체크를 해주세요..");
			} else if (pwcheck == 0) {
				JOptionPane.showMessageDialog(this, "PW를 체크해 주세요..");
			} else {
				fieldcheck();
			}

		} else if (cmd.equals("init")) {
			init();

		} else if (cmd.equals("close")) {
			this.dispose();
		}
	}

	private void init() {
		for (int i = 0; i < textField.length; i++) {
			textField[i].setText("");
		}
		passwordField.setText("");
		passwordField_1.setText("");
	}

	private void fieldcheck() {
		if (textField[1].getText().isEmpty()) {
			label_1.setText("이름을 입력해주세요.");
			return;
		} else if (textField[2].getText().isEmpty()) {
			label_1.setText("메일을 입력해주세요.");
			return;
		} else if (textField[3].getText().isEmpty()) {
			label_1.setText("번호을 입력해주세요.");
			return;
		} else if (textField[4].getText().isEmpty()) {
			label_1.setText("생일을 입력해주세요.");
			return;
		} else if (new String(passwordField.getPassword()).isEmpty()) {
			label_1.setText("패스워드를 입력해주세요.");
			return;
		} else {
			MemberDTO dto = new MemberDTO();
			dto.setName(textField[1].getText());
			dto.setId(new String(textField[0].getText()));
			dto.setPwd(new String(passwordField.getPassword()));
			dto.setEmail(textField[2].getText());
			dto.setTel(textField[3].getText());
			dto.setBirth(textField[4].getText());
			int result = mdao.insertMember(dto);

			if (result == 1) {
				JOptionPane.showMessageDialog(this, "회원가입되었습니다.");
				init();
			}
		}
	}

	class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();

			if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_TAB) {
				if (e.getSource() == textField[0]) {
					textField[1].requestFocus();
				} else if (e.getSource() == textField[1]) {
					passwordField.requestFocus();
				} else if (e.getSource() == passwordField) {
					passwordField_1.requestFocus();
				} else if (e.getSource() == passwordField_1) {
					textField[2].requestFocus();
				} else if (e.getSource() == textField[2]) {
					textField[3].requestFocus();
				} else if (e.getSource() == textField[3]) {
					textField[4].requestFocus();
				}
			}
		}

	}
}
