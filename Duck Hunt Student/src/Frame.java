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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	Font  bigFont = new Font("Serif", Font.BOLD, 100);
	Font medFont = new Font("Serif", Font.BOLD,50);
	Font smallFont = new Font("Serif", Font.BOLD,25);

	Shark shark = new Shark();
	Salmon salmon = new Salmon();
	Salmon salmon1 = new Salmon();

	Foreground foreground = new Foreground();
	GameBackground ground = new GameBackground("backround.png");

	int roundTimer;
	int score;
	long time;
	int currRound;

	Timer t = new Timer(16, this);

	public void resetGame() {
		roundTimer = 30;
		score = 0;
		time = 0;
		currRound = 1;

		salmon.setXY(250,250);
		salmon.setVx((int) Math.random()* 10 + 5);
		salmon.setVy(-9);
	}

	public void init() {
		roundTimer = 30;
		score = 0;
		time = 0;
		currRound = 1;

		ground.setScale(8 ,8);

		salmon.setWidthHeight(90,90);
		salmon.setScale(1,1);
		salmon.setXY(250,550);
		salmon.setVx((int) Math.random()* 10 + 5);
		salmon.setVy(-9);

		foreground.setXY(-380,400);
		foreground.setScale(17,9);

		shark.setWidthHeight(200, 200);
		shark.setScale(5, 5);
		shark.setXY(0, 800);
	}


	public void reset() {
		resetGame();
		t.start();
	}

	public void nextRound() {
		StdAudio.play("nextround.wav");
		roundTimer = 30;
		currRound++;

		salmon.setXY((int)(Math.random()* 600) +50,500);

		int randV = (int)(Math.random()*4) + 1;

		salmon.setVx(randV + currRound);
		salmon.setVy(-1*randV - currRound);
		shark.setY(800);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		time += 20;
		if (time%600 == 0) {
			roundTimer -= 1;

			if (roundTimer == 0) {
				//game over
				t.stop();
			}

			if (shark.getY() == 501) {
				nextRound();
				g.drawString("Press the space bar for the next round", 400,  200);
				t.stop();
			}
		}



		//bouncing of the walls (800x760)
		if(salmon.getX() > 700) {
			salmon.setVx(salmon.getVx()*-1);
		}
		if (salmon.getY() < 0) {
			salmon.setVy(salmon.getVy()*-1);
		}
		if (salmon.getX() < 0) {
			salmon.setVx(salmon.getVx()*-1);
		}
		if (salmon.getY() > 680) {
			salmon.setVy(salmon.getVy()*-1);
		}

		ground.paint(g);
		salmon.paint(g);
		shark.paint(g);
		foreground.paint(g);

		g.setFont(smallFont);
		g.drawString("score: "+ score, 600,70);
		g.drawString("Press Space bar to pause/start new round", 300,  650);
		g.setFont(medFont);
		g.drawString("" + this.roundTimer, 200, 100);
		g.drawString("Round " + this.currRound, 50, 700);

		//salmon is behind grass and clicked on (stop movement)
		if(salmon.getY() > 500 && salmon.getVx() == 0) {
			//roundTimer = 30;
			salmon.setVy(0);
		}

		// make the shark arise
		if (salmon.getY() > 500 && salmon.getVx() == 0 && shark.getVy() == 0 && (shark.getY() != 501)) {
			shark.setY(500);
			shark.setX(salmon.getX());
			shark.setVy(-3);
		}

		//come down
		if(shark.getY() < 300) {
			shark.setVy(shark.getVy()*-1);
			StdAudio.play("ploop.wav");
		}

		//stop in grass
		if (shark.getY() > 500 && (shark.getVy() != 0)) {
			shark.setY(501);
			shark.setVy(0);
		}
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
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub

		Rectangle rSalmon = new Rectangle(salmon.getX(),salmon.getY(),90,90);
		Rectangle rMouse = new Rectangle(m.getX(),m.getY(), 90,90);

		if(rSalmon.intersects(rMouse)) {
			StdAudio.play("chomp.wav");
			salmon.setVy(10);
			salmon.setVx(0);
			score += 1;
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
			//pause/restart
			if (roundTimer != 0) {
				if (!t.isRunning()) {
					t.start();
				} else {
					t.stop();
				}
			}
			else {
				//restart
				reset();
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

