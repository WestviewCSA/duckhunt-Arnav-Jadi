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
	Font medFont = new Font("Serif", Font.BOLD,50);
	
	Shark shark = new Shark();
	Salmon salmon = new Salmon();
	Salmon salmon1 = new Salmon();

	Foreground foreground = new Foreground();
	GameBackground ground = new GameBackground("backround.png");
	
	int roundTimer;
	int score;
	long time;
	int currRound = 1;
	
	public void init() {
		
		roundTimer = 30;
		score = 0;
		time = 0;

		ground.setScale(8 ,8);
		salmon1.setScale(1, 1);
		salmon.setWidthHeight(90,90);
		salmon.setScale(1,1);
		
		
		salmon.setVx((int) Math.random()* 10 + 5);
		salmon.setVy((int) Math.random()* 10 + 5);

		
		
		foreground.setXY(0,500);
		foreground.setScale(8,9);
		
		shark.setWidthHeight(200, 200);
		shark.setScale(5, 5);
		shark.setXY(0, 800);
	}
	
	
	public void reset() {
		
		
	}

	public void nextRound() {

		roundTimer = 30;
		currRound++;

		salmon.setXY(250,250);

		int randV = (int)(Math.random()*4) + 1;

		salmon.setVx(randV + currRound);
		salmon.setVy(randV + currRound);
	}

	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		time += 20;
		
		if (time%1000 == 0) {
			
			roundTimer -= 1;
			if (roundTimer == 0 ) {
				nextRound();
				g.drawString("Press the space bar for the next round", 0,  0);
				t.stop();
				g.drawString("Press the space bar for the next round", 0,  0);

			}
		}
		
//		if(!t.isRunning()) {
//			g.drawString("Press the space bar for the next round", 50, 0);
//		}
		
		
		ground.paint(g);
		
		//salmon1.paint(g);
		
		salmon.paint(g);
		shark.paint(g);
		foreground.paint(g);
		
		g.setFont(medFont);
		g.drawString("" + this.roundTimer, 200, 100);

		g.setFont(medFont);
		g.drawString("Round " + this.currRound, 50, 700);
		
		
//		if(salmon.getY() > 560) {
//			salmon.setX(200);
//			salmon.setY(150);
//			salmon.setVx(3);
//			salmon.setVy(0);
		

		if(salmon.getX() > 700) {
			salmon.setVx(salmon.getVx()*-1);
		}

		if (salmon.getY() < 0) {
			salmon.setVy(salmon.getVy()*-1);
		}
		if (salmon.getX() < 0) {
			salmon.setVx(salmon.getVx()*-1);
		}
		if (salmon.getY() > 650) {
			salmon.setVy(salmon.getVx()*-1);
		}
		
		
		
		
		salmon1.setVy(0);
		
		salmon1.setVy(0);
		
		
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

		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t = new Timer(16, this);

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
			
		shark.setX(salmon1.getX()); //may need to add some offset to center	
		shark.setY(500);  
		shark.setVy(-3);
		
		
		
			
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

		if(arg0.getKeyCode() == 32) {
			if (!t.isRunning()) {
				t.start();
			}
		}
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