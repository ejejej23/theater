package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

///quest
public class YemaeFrame extends JFrame implements ActionListener, ChangeListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private YemaeDAO dao = new YemaeDAO();
	private JPanel contentPane;
	JCheckBox[] cb;
	JSpinner spinner, spinner_1;
	final String[] mem = new String[] { "0", "1", "2" };
	List<String> seat = new ArrayList<>();
	JLabel lblNewLabel_3;
	JLabel label_6;

	int adult = 0;
	int teenage = 0;
	int num = 0;
	int count = 0;
	private String memcode;
	private String screenplaycode;

	public YemaeFrame(String memcode, String screenplaycode) {
		this.memcode = memcode;
		this.screenplaycode = screenplaycode;
		setTitle("RESERVATION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		layeredPane.setBounds(0, 10, 657, 133);
		contentPane.add(layeredPane);

		/// quest : ¿µÈ­Á¦¸ñ ¹Þ¾Æ¿Í¼­ º¸¿©ÁÖ±â // movieDTO, movieDAO ÀÌ¿ë.
		JLabel lblTitle = new JLabel("\uC601\uD654\uC81C\uBAA9");
		lblTitle.setBounds(12, 10, 630, 31);
		layeredPane.add(lblTitle);
		lblTitle.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setOpaque(true);
		lblTitle.setOpaque(true);

		JLabel lblNewLabel = new JLabel("\uC131\uC778");
		lblNewLabel.setBounds(22, 54, 57, 23);
		layeredPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 14));

		SpinnerListModel colorModel = new SpinnerListModel(mem);
		spinner = new JSpinner(colorModel);
		spinner.setBounds(79, 51, 148, 30);
		layeredPane.add(spinner);
		spinner.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		spinner.addChangeListener(this);

		JLabel lblNewLabel_1 = new JLabel("\uCCAD\uC18C\uB144");
		lblNewLabel_1.setBounds(263, 54, 57, 23);
		layeredPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 14));

		SpinnerListModel colorModel2 = new SpinnerListModel(mem);
		spinner_1 = new JSpinner(colorModel2);
		spinner_1.setBounds(332, 51, 148, 30);
		layeredPane.add(spinner_1);
		spinner_1.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		spinner_1.addChangeListener(this);

		JLabel lblNewLabel_2 = new JLabel("\uCD1D \uAE08\uC561");
		lblNewLabel_2.setBounds(332, 90, 71, 31);
		layeredPane.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.BOLD, 13));

		JButton btnNewButton = new JButton("\uC608\uB9E4\uD558\uAE30");
		btnNewButton.setBounds(511, 90, 119, 31);
		layeredPane.add(btnNewButton);
		btnNewButton.setActionCommand("yemae");
		btnNewButton.addActionListener(this);

		lblNewLabel_3 = new JLabel("¼ºÀÎ: 0¸í, Ã»¼Ò³â: 0¸í");
		lblNewLabel_3.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(503, 51, 136, 26);
		layeredPane.add(lblNewLabel_3);

		label_6 = new JLabel("");
		label_6.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(396, 90, 84, 30);
		layeredPane.add(label_6);

		JLabel testlbl = new JLabel(screenplaycode);
		testlbl.setBounds(55, 98, 112, 15);
		layeredPane.add(testlbl);

		JLabel label = new JLabel("\uC88C\uC11D \uC120\uD0DD");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("±¼¸²", Font.BOLD, 15));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(10, 153, 638, 31);
		contentPane.add(label);

		cb = new JCheckBox[20];
		char c = 'A';
		int num = 1;
		int x = 162;
		int y = 270;
		for (int i = 0; i < cb.length; i++) {
			cb[i] = new JCheckBox("" + c + num);
			cb[i].setBounds(x, y, 53, 31);
			num++;
			x += 85;
			if (num % 6 == 0) {
				num = 1;
				c++;
				x = 162;
				y += 60;
			}
			cb[i].setFont(new Font("±¼¸²", Font.BOLD, 16));
			cb[i].setEnabled(false);
			cb[i].addItemListener(this);
			contentPane.add(cb[i]);

		}

		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setOpaque(true);
		layeredPane_1.setBackground(Color.LIGHT_GRAY);
		layeredPane_1.setBounds(148, 233, 424, 31);
		contentPane.add(layeredPane_1);

		JLabel label_1 = new JLabel("1");
		label_1.setBounds(12, 10, 57, 15);
		layeredPane_1.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel label_2 = new JLabel("2");
		label_2.setBounds(94, 10, 57, 15);
		layeredPane_1.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel label_3 = new JLabel("3");
		label_3.setBounds(178, 10, 57, 15);
		layeredPane_1.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel label_4 = new JLabel("4");
		label_4.setBounds(270, 10, 57, 15);
		layeredPane_1.add(label_4);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel label_5 = new JLabel("5");
		label_5.setBounds(355, 10, 57, 15);
		layeredPane_1.add(label_5);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setOpaque(true);
		layeredPane_2.setBackground(Color.LIGHT_GRAY);
		layeredPane_2.setBounds(75, 270, 56, 210);
		contentPane.add(layeredPane_2);

		JLabel lblA = new JLabel("A");
		lblA.setBounds(0, 10, 57, 15);
		layeredPane_2.add(lblA);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel lblB = new JLabel("B");
		lblB.setBounds(0, 70, 57, 15);
		layeredPane_2.add(lblB);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel lblC = new JLabel("C");
		lblC.setBounds(0, 129, 57, 15);
		layeredPane_2.add(lblC);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel lblD = new JLabel("D");
		lblD.setBounds(0, 185, 57, 15);
		layeredPane_2.add(lblD);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setFont(new Font("±¼¸²", Font.BOLD, 15));

		JLabel lblScreen = new JLabel("SCREEN");
		lblScreen.setOpaque(true);
		lblScreen.setForeground(Color.WHITE);
		lblScreen.setBackground(Color.DARK_GRAY);
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblScreen.setFont(new Font("±¼¸²", Font.BOLD, 13));
		lblScreen.setBounds(69, 194, 511, 15);
		contentPane.add(lblScreen);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("yemae")) {
			YemaeDTO dto = new YemaeDTO();

			dto.setAdult(adult);
			dto.setTeenager(teenage);
			dto.setPrice(adult * 7000 + teenage * 6000);
			dto.setMemcode(memcode);
			dto.setSccreenplayCode(screenplaycode);

			if (adult < 1 && teenage < 1) {
				JOptionPane.showMessageDialog(this, "ÀÎ¿øÀ» ¼±ÅÃÇØ ÁÖ¼¼¿ä.");
				return;
			} else if (seat.size() < 1) {
				JOptionPane.showMessageDialog(this, "ÁÂ¼®À» ¼±ÅÃÇØ ÁÖ¼¼¿ä.");
				return;
			}

			PaymentFrame f = new PaymentFrame(dto, seat);
			f.setVisible(true);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		if (e.getSource() == spinner || e.getSource() == spinner_1) {
			if (spinner.getValue().toString().equals("0") && spinner_1.getValue().toString().equals("0")) {
				for (int i = 0; i < cb.length; i++) {
					cb[i].setEnabled(false);

				}
			} else if (!spinner.getValue().toString().equals("0") || !spinner_1.getValue().toString().equals("0")) {
				for (int i = 0; i < cb.length; i++) {
					cb[i].setEnabled(true);

				}
			}

			for (int i = 0; i < cb.length; i++) {
				cb[i].setSelected(false);

				List<YemaeSeatDTO> list = dao.listYemeaSeat(screenplaycode);
				for (YemaeSeatDTO dto : list) {
					if (dto.getSeatinfo().equals(cb[i].getText())) {
						cb[i].setEnabled(false);
					}

				}

			}

			lblNewLabel_3.setText(
					"¼ºÀÎ : " + spinner.getValue().toString() + "¸í, Ã»¼Ò³â: " + spinner_1.getValue().toString() + "¸í");
			adult = Integer.parseInt(spinner.getValue().toString());
			teenage = Integer.parseInt(spinner_1.getValue().toString());
			count = adult + teenage;
			label_6.setText("" + (adult * 7000 + teenage * 6000));
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox c = (JCheckBox) e.getSource();
		if (c.isSelected()) {
			num++;
			seat.add(c.getText());
		} else if (!c.isSelected()) {
			num--;
			seat.remove(c.getText());
		}
		for (int i = 0; i < seat.size(); i++)
			System.out.println(seat.get(i));

		if (num > count) {
			c.setSelected(false);
			JOptionPane.showMessageDialog(this, "ÀÎ¿ø¼ö¸¦ ÃÊ°úÇÏ¿´½À´Ï´Ù.");
		}
	}
}