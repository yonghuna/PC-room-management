package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.util.Date;

public class ManageView extends JFrame {

	public ManageView() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(1600, 900);
		setTitle("ManageView");
		setLayout(null);
		
		// my window 
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);
		
		//background
		JPanel myPanel = new MyPanel();
		myPanel.setLayout(null);
		myPanel.setBounds(0, -30, 1600, 900);
		
		//watch 
		ImgClock imgClock = new ImgClock();
		imgClock.setLayout(null);
		imgClock.setBounds(15, 20, 179, 149);
		imgClock.setOpaque(false); // background invisible
		new Thread(imgClock).start();
		//watch word 
		
		//light source processing
		
		//finally insert 
		layeredPane.add(myPanel, new Integer(0));
		layeredPane.add(imgClock, new Integer(1));
		add(layeredPane);
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
	class ImgClock extends JPanel implements Runnable{
		
		Image img[] = new Image[4];
		int i = 2;
		public ImgClock() {
			img[1] = Toolkit.getDefaultToolkit().createImage("C:\\Users\\kooaa\\Desktop\\img\\cl1.png");
			img[2] = Toolkit.getDefaultToolkit().createImage("C:\\Users\\kooaa\\Desktop\\img\\cl2.png");
			img[3] = Toolkit.getDefaultToolkit().createImage("C:\\Users\\kooaa\\Desktop\\img\\cl3.png");
			img[0] = img[1];
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img[0], 0, 0, this);
		}
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
					switch (i) {
					case 1: img[0] = img[i]; i++; repaint(); break;
					case 2: img[0] = img[i]; i++; repaint(); break;
					case 3: img[0] = img[i]; i=1; repaint(); break;
					
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	// 시계 글씨
	class ClockMessage extends JPanel implements Runnable {
		int i = Calendar.getInstance().get(Calendar.AM_PM);
		String[] ampm = {"AM", "PM"};
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(new Date());
		JLabel timeLabel, ampmLabel;
		
		public ClockMessage() {
			this.setLayout(null);
			timeLabel = new JLabel(time);
			timeLabel.setBounds(0, 0, 100, 20);
			timeLabel.setForeground(new Color(36, 205, 198));
			timeLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 12));
			
			ampmLabel= new JLabel(ampm[i]);
			ampmLabel.setBounds(15, 20, 100, 30);
			ampmLabel.setForeground(new Color(36, 205, 198));
			ampmLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 12));
			
			add(timeLabel, BorderLayout.NORTH);
			add(timeLabel, BorderLayout.CENTER);
		}
		
		@Override
		public void run() {
			do {
				try {
					Thread.sleep(1000);
					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				timeLabel.setText(sdf.format(new Date()));
			} while(true);
		}
		
		
	}
}
