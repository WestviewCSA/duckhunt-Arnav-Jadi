import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Font  bigFont = new Font("Serif", Font.BOLD, 100);
	Salmon salmon = new Salmon();
	Salmon salmon1 = new Salmon();
	Salmon salmon2 = new Salmon();
	
	GameBackground ground = new GameBackground("backround.png");
	
	int roundTimer;
	int score;
	long time;
	
	
	public void init() {
		
		roundTimer = 0;
		score = 0;
		time = 0;
		
		
		ground.setScale(8 ,8);
		
		salmon1.setScale(0.5, 0.5); 
		
		salmon.setWidthHeight(90,90);
		salmon.setScale(3,3);
		salmon.setVx(1);
		
		
	}
	
	
	public void reset() {
		
		
	}
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		time += 20;
		
		if (time%1000 == 0) {
			
			roundTimer -= 1;
			if (roundTimer == 0 ) {
				reset();
				
			}
		}
		
	
		
		
		ground.paint(g);
		
		salmon1.paint(g);
		
		salmon.paint(g);
		
		
//		ground.setScale(8, 8);
//		salmon.setScale(4, 4);
//		salmon.setVx(1);
//		
//		ground.setScale(1.1,1.0);
//		ground.setXY(0,500);
		
		
		if(salmon.getY() > 560) {
			salmon.setX(200);
			salmon.setY(150);
			salmon.setVx(3);
			salmon.setVy(0);
		}
		
		g.setFont(bigFont);
		g.drawString("" + this.roundTimer, 200, 100);
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(800, 760));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);	
		
		init();
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent Salmon) {
		// TODO Auto-generated method stub
		
		Rectangle rSalmon = new Rectangle(salmon.getX(),salmon.getY(),25,25);
	
		
		Rectangle rMain = new Rectangle(salmon.getX(),salmon.getY(),salmon.getWidth(),salmon.getHeight());
		
		
		if(rSalmon.intersects(rMain)) {
			salmon.setVy(10);
			salmon.setVx(0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
