package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ManageView extends JFrame {

	public ManageView() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(1600, 900);
		setTitle("ManageView");
		
		// my window 
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

		JPanel myPanel = new MyPanel();
		add(myPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new ManageView();
	}

	// main
	class MyPanel extends JPanel {
		Image image;

		public MyPanel() {
			image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\kooaa\\Desktop\\img\\mainHud_back.png");
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, this);
		}

		public void update(Graphics g) {
			super.update(g);
		}
	}

}
