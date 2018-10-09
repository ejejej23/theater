package source;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MemberDAO mdao = new MemberDAO();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel label;
	private JLabel label_1;
	private JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_2;

	public LoginFrame() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setBounds(36, 61, 85, 15);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(133, 55, 122, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyHandler());
		textField.setFocusTraversalKeysEnabled(false);
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
				if (textField.getText().isEmpty()) {
					label.setText("ID를 입력해주세요.");
				} else {
					label.setText("");
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_1.setBounds(36, 110, 85, 15);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(133, 104, 122, 21);
		passwordField.addKeyListener(new KeyHandler());
		contentPane.add(passwordField);
		passwordField.setFocusTraversalKeysEnabled(false);
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
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
				if (passwordField.getPassword().length < 1) {
					label_1.setText("PW를 입력해주세요.");
				} else {
					label_1.setText("");
				}
			}
		});

		btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(279, 55, 97, 68);
		contentPane.add(btnNewButton);
		btnNewButton.setActionCommand("login");
		btnNewButton.addActionListener(this);

		btnNewButton_1 = new JButton("JOIN");
		btnNewButton_1.setBounds(279, 155, 97, 34);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setActionCommand("join");
		btnNewButton_1.addActionListener(this);

		btnNewButton_2 = new JButton("PW FIND");
		btnNewButton_2.setBounds(170, 155, 97, 34);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setActionCommand("find");

		label = new JLabel("ID를 입력해주세요.");
		label.setFont(new Font("굴림", Font.PLAIN, 11));
		label.setBounds(133, 79, 122, 15);
		contentPane.add(label);

		label_1 = new JLabel("PW를 입력해주세요.");
		label_1.setFont(new Font("굴림", Font.PLAIN, 11));
		label_1.setBounds(133, 130, 122, 15);
		contentPane.add(label_1);
		btnNewButton_2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		String pwd = new String(passwordField.getPassword());

		if (cmd.equals("login")) {
			MemberDTO dto = new MemberDTO();
			dto = mdao.readMember(textField.getText());

			if (dto == null) {
				System.out.println("등록되지 않은 회원입니다.");
				return;
			} else if (!pwd.equals(dto.getPwd())) {
				System.out.println("아이디/패스워드를 잘못 입력하셨습니다.");
				return;
			}

			Main_up mainframe = new Main_up(dto.getMemcode());
			mainframe.setVisible(true);
			this.dispose();

		} else if (cmd.equals("find")) {
			PwFindFrame pframe = new PwFindFrame();
			pframe.setVisible(true);
		} else if (cmd.equals("join")) {
			JoinFrame frame = new JoinFrame();
			frame.setVisible(true);

		}
	}

	class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();

			if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_TAB) {
				if (e.getSource() == textField) {
					passwordField.requestFocus();
				} else if (e.getSource() == passwordField) {
					btnNewButton.requestFocus();
				}
			}
		}

	}

}
