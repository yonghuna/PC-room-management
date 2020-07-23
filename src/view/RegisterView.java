package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import DAO.*;

public class RegisterView extends JFrame implements ActionListener {
	JPanel information = new JPanel(new GridLayout(2, 4, 5, 5));
	JLabel age = new JLabel("Age");
	JLabel id = new JLabel("ID");
	JLabel number = new JLabel("Number");
	JLabel pw = new JLabel("Password");
	JButton enter = new JButton("Enter");
	JTextField typeAge = new JTextField();
	JTextField typeID = new JTextField();
	JTextField typeNumber = new JTextField();
	JPasswordField typePW = new JPasswordField();
	DBConnection db = new DBConnection();

	public RegisterView() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		information.add(id);
		information.add(typeID);
		information.add(pw);
		information.add(typePW);
		information.add(age);
		information.add(typeAge);
		information.add(number);
		information.add(typeNumber);

		setLayout(new BorderLayout());
		add(information, BorderLayout.NORTH); // NORTH ID NORTH에 추가
		add(enter, BorderLayout.SOUTH);

		information.setPreferredSize(new Dimension(200, 120));
		enter.addActionListener(this);

		setSize(400, 200);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("회원가입");
		if (e.getSource().equals(enter)) {
			String id = (typeID.getText());
			char[] pw = (typePW.getPassword());
			String password = new String(pw);
			String age = (typeAge.getText());
			String phoneNumber = (typeNumber.getText());
			int existLogin = db.register(0, id, password, age, phoneNumber, "0");
			if (existLogin == 1) {
				// 로그인 성공
				JOptionPane.showMessageDialog(null, "성공");
				dispose();
			} else {
				// 로그인 실패
				JOptionPane.showMessageDialog(null, "실패");
			}
		}
		System.out.println("회윈가입");
	}
}
