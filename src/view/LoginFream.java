package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.DBConnection;

public class LoginFream extends JFrame implements ActionListener {
	BufferedImage img = null;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton bt_login;
	JButton bt_register;
	DBConnection db;
	RegisterView register;

	// 메인
	public static void main(String[] args) {
		new LoginFream();
	}

	// 생성자
	public LoginFream() {
		setTitle("로그인");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);

		// 이미지 불러오기
		try {
			img = ImageIO.read(new File("C:\\Users\\kooaa\\Desktop\\img\\login.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1600, 900);

		// 로그인
		loginTextField = new JTextField(15);
		loginTextField.setBounds(731, 399, 280, 30);
		layeredPane.add(loginTextField);
		loginTextField.setOpaque(false);
		loginTextField.setForeground(Color.green);
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 비밀번호
		passwordField = new JPasswordField(15);
		passwordField.setBounds(731, 529, 280, 30);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.green);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		layeredPane.add(passwordField);

		// 로그인 버튼 추가
		bt_login = new JButton(new ImageIcon("C:\\Users\\kooaa\\Desktop\\img\\btLogin_hud.png"));
		bt_login.setBounds(755, 689, 104, 48);

		// register button
		bt_register = new JButton(new ImageIcon("C:\\Users\\kooaa\\Desktop\\img\\starDdong.png"));
		bt_register.setBounds(755, 689, 350, 48);

		// 버튼 투명처리
		bt_login.setBorderPainted(false);
		bt_login.setFocusPainted(false);
		bt_login.setContentAreaFilled(false);
		bt_login.addActionListener(this);

		bt_register.setBorderPainted(false);
		bt_register.setFocusPainted(false);
		bt_register.setContentAreaFilled(false);
		bt_register.addActionListener(this);

		layeredPane.add(bt_login);
		layeredPane.add(bt_register);

		// 마지막 추가들
		layeredPane.add(panel);
		add(layeredPane);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bt_login)) {
			String id = loginTextField.getText();
			char[] pw = passwordField.getPassword();
			String password = new String(pw);
			if (id.equals("") || password.equals(pw)) {
				//메세지 남기기
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다.");
			} else {

				// 로그인 참 거짓 여부
				boolean existLogin = db.login(id, password);

				if (existLogin) {
					// 로그인 성공
					JOptionPane.showMessageDialog(null, "성공");
				} else {
					// 로그인 실패
					JOptionPane.showMessageDialog(null, "실패");
				}
			}

		}
		else if(e.getSource().equals(bt_register)) {
			new RegisterView();
			
		}
	}
}
