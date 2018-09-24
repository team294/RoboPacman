package pacman.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pacman.base.DriveTrain;
import pacman.base.RobotBase;
import pacman.base.Util;
import pacman.robot.Robot;


public class PacmanGraphics extends Canvas{
	
	private static final long serialVersionUID = -7506350633593738441L;
	
	public static final int HEIGHT = 600;
	public static final int WIDTH = 600;
	public static final double TICK_TIME_MS = 50;

	private int score = 0;
	private int ticks = 0;

	private BufferStrategy strategy;
	private BufferedImage robotImage;
	private BufferedImage dotImage;
	private List<Dot> dotList = new ArrayList<Dot>();

	private DriveTrain driveTrain = Robot.driveTrain;
	//private Arm arm = Arm.getInstance();
	
	private BufferedImage getImage(String filename) {
		BufferedImage sourceImage = null;
		
		try {
			URL url = this.getClass().getClassLoader().getResource(filename);
			if (url == null) {
				throw new RuntimeException("Can't find filename: "+filename);
			}
			sourceImage = ImageIO.read(url);
			//System.out.println("read image "+filename+" width:"+sourceImage.getWidth()+" height:"+sourceImage.getHeight());
		} catch (IOException e) {
			throw new RuntimeException("Failed to load: "+filename);
		}
		
		return sourceImage;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTime() {
		double time = ticks * (TICK_TIME_MS / 1000);
		return (int) time;
	}

	
	public void setup() {
		ticks = 0;
		robotImage = getImage("robot.png");
		dotImage = getImage("dot.png");
		
		JFrame container = new JFrame("Robo Pacman");
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		panel.setLayout(null);
		
		setBounds(0,0,WIDTH,HEIGHT);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		// create the dots
		int x = PacmanGraphics.WIDTH / 2;
		int y = PacmanGraphics.HEIGHT - 100;
		
		for (int i=0;i<4;i++) {
			y -= 50;
			dotList.add(new Dot(x,y));	
		}
		
		for (int i=0;i<4;i++) {
			x += 50;
			dotList.add(new Dot(x,y));	
		}
		
		for (int i=0;i<4;i++) {
			y -= 50;
			dotList.add(new Dot(x,y));	
			//dotList.add(new Dot(x-50,y));	
		}
		
		for (int i=0;i<8;i++) {
			x -= 50;
			dotList.add(new Dot(x,y));	
		}
		
		for (int i=0;i<6;i++) {
			y += 50;
			dotList.add(new Dot(x,y));	
		}
		
		y += 50;
		x += 50;
		dotList.add(new Dot(x,y));	
		
		y += 50;
		x += 50;
		dotList.add(new Dot(x,y));	
		
		y += 50;
		x += 50;
		dotList.add(new Dot(x,y));	

		for (int i=0;i<5;i++) {
			x += 50;
			dotList.add(new Dot(x,y));	
		}


		
	}
	
	
	private boolean checkForCollision(Dot dot, int x, int y) {
		boolean collision = false;
		
		if (dot.getX() == x && dot.getY() == y) {
			collision = true;
			score++;
			Util.log("score: "+score);
		} else {
			//System.out.printf("no collision dot %s,%s pacman %s,%s\n",dot.getX(), dot.getY(), x,y);
		}
		
		return collision;
	}
	


	public void drawField(RobotBase robot) {
		// keep track of time in ticks so game can run at different speeds
		ticks++;

		// init the graphics system to redraw the map
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,HEIGHT,WIDTH,HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		// draw the dots and check for collisions
		for (Iterator<Dot> iterator = dotList.iterator(); iterator.hasNext();) {
		    Dot d = iterator.next();
			if (checkForCollision(d,driveTrain.getPositionX(), driveTrain.getPositionY()))
			{
				iterator.remove();
			} else {
				g.drawImage(dotImage, d.getX(), d.getY(), null);
			}
		}		

		// draw pacman
		// rotate as needed around the center of the image
		double rotationRequired = Math.toRadians (driveTrain.getAngle());
		double centerX = robotImage.getWidth() / 2;
		double centerY = robotImage.getHeight() / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(op.filter(robotImage, null), driveTrain.getPositionX(), driveTrain.getPositionY(), null);
		
		g.setColor(Color.white);
		g.drawString("Time "+getTime()+" Score "+score,25,25);
		
		// show the graphics
		g.dispose();
		strategy.show();

	}


}
