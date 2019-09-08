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

import pacman.base.RobotBase;
import pacman.base.Util;


public class PacmanGraphics extends Canvas{
	
	private static final long serialVersionUID = -7506350633593738441L;
	
	public static final int HEIGHT = 450;
	public static final int WIDTH = 600;
	public static final double TICK_TIME_MS = 50;

	private int score = 0;
	private int ticks = 0;
	private int level = 1;
	private boolean caught = false;

	private BufferStrategy strategy;
	private BufferedImage robotImage;
	private BufferedImage dotImage;
	private BufferedImage ghostImage;
	private List<Dot> dotList = new ArrayList<Dot>();
	private List<Ghost> ghostList = new ArrayList<Ghost>();

	
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

	public boolean getCaught() {
		return caught;
	}
	
	public int getTime() {
		double time = ticks * (TICK_TIME_MS / 1000);
		return (int) time;
	}

	public List<Ghost> getGhostList() {
		return ghostList;
	}

	
	public void setup(int level) {
		ticks = 0;
		this.level = level;
		robotImage = getImage("robot.png");
		dotImage = getImage("dot.png");
		ghostImage = getImage("ghost.png");
		
		JFrame container = new JFrame("294 Robo Pacman "+RobotBase.VERSION);
		
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

		// static dot layout
		if (level == 1 || level == 2 || level == 3) {
			int x = 50;
			for (int i=0;i<11;i++) {
				dotList.add(new Dot(x,100));	
				dotList.add(new Dot(x,250));	
				dotList.add(new Dot(x,400));	
				x += 50;
			}

			dotList.add(new Dot(50,150));
			dotList.add(new Dot(50,200));	
			dotList.add(new Dot(50,300));
			dotList.add(new Dot(50,350));	
			
			dotList.add(new Dot(300,150));
			dotList.add(new Dot(300,200));	
			dotList.add(new Dot(300,300));
			dotList.add(new Dot(300,350));	

			dotList.add(new Dot(550,150));
			dotList.add(new Dot(550,200));	
			dotList.add(new Dot(550,300));
			dotList.add(new Dot(550,350));	

			// create the ghosts
			if (level == 2 || level == 3) {
				ghostList.add(new Ghost(250,100));
				ghostList.add(new Ghost(250,250));
				ghostList.add(new Ghost(250,400));
			}

		}
		
		// simple horizonal row of dots with a few ghosts that only move vertically
		if (level == 4) {
			int x = 50;
			for (int i=0;i<10;i++) {
				dotList.add(new Dot(x,400));	
				x += 50;
			}
			ghostList.add(new GhostVertical(150,400));
			ghostList.add(new GhostVertical(250,400));
			ghostList.add(new GhostVertical(350,400));
			ghostList.add(new GhostVertical(450,400));
		} 

		// add in ghosts to create obstacles
		if (level == 6) {
			int y = 50;
			for (int i=1;i<=7;i++) {
				ghostList.add(new GhostNoMove(150,y));
				y += 50;
			}
			y = 100;
			for (int i=1;i<=7;i++) {
				ghostList.add(new GhostNoMove(450,y));
				y += 50;
			}
		}

		// randomized dot layout
		if (level == 5 || level == 6) {
			for (int i=1;i<12;i++) {
				double rand = Math.random()*6 + 1;
				int y = Double.valueOf(rand).intValue()*50;
				if (i != 3 && i != 9) {
					dotList.add(new Dot(i*50, y));
					System.out.println(i*50+" "+y);
				}	
			}	
		}
		
	}
	
	private int[][] getDotLocations() {
		int[][] locations = new int[dotList.size()][2];
		int i = 0;
		for (Dot d:dotList) {
			locations[i][0] = d.getX();
			locations[i][1] = d.getY();
			i++;
		}
		
		return locations;	
	}

	public int[][] getGhostLocations() {
		int[][] locations = new int[ghostList.size()][2];
		int i = 0;
		for (Ghost g:ghostList) {
			locations[i][0] = g.getX();
			locations[i][1] = g.getY();
			i++;
		}
		
		return locations;	
	}
	
	
	private boolean checkForCollision(Dot dot, int x, int y) {
		boolean collision = false;
		
		if (dot.getX() == x && dot.getY() == y) {
			collision = true;
			score++;
			Util.log("score: "+score+" at time "+getTime());
		} else {
			//System.out.printf("no collision dot %s,%s pacman %s,%s\n",dot.getX(), dot.getY(), x,y);
		}
		
		return collision;
	}
	
	public static boolean checkForCollision(Ghost ghost, int x, int y) {
		boolean collision = false;
		int dist = Math.abs(Util.getDistance(ghost.getX(), ghost.getY(), x, y));

		if (dist < 25) {
			System.out.printf("collision dot %s,%s pacman %s,%s dist:%s %n",ghost.getX(), ghost.getY(), x,y,dist);
			collision = true;
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
			if (checkForCollision(d,RobotBase.driveTrain.getPositionX(), RobotBase.driveTrain.getPositionY()))
			{
				iterator.remove();
			} else {
				g.drawImage(dotImage, d.getX(), d.getY(), null);
			}
		}	
		
		// update the dot sensor
		RobotBase.dotSensor.setDotLocations(getDotLocations());
		
		// draw the ghosts and check for collisions
		for (Iterator<Ghost> iterator = ghostList.iterator(); iterator.hasNext();) {
			Ghost ghost = iterator.next();
			ghost.move(RobotBase.driveTrain.getPositionX(), RobotBase.driveTrain.getPositionY());

			if (checkForCollision(ghost,RobotBase.driveTrain.getPositionX(), RobotBase.driveTrain.getPositionY()))
			{
				caught = true;
				Util.log("Collision with ghost!");
				iterator.remove();
			} else {
				g.drawImage(ghostImage, ghost.getX(), ghost.getY(), null);
			}
		}			

		// draw pacman
		// rotate as needed around the center of the image
		if (caught == false) {
			double rotationRequired = Math.toRadians (RobotBase.driveTrain.getAngle());
			double centerX = robotImage.getWidth() / 2;
			double centerY = robotImage.getHeight() / 2;
			
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, centerX, centerY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

			// Drawing the rotated image at the required drawing locations
			g.drawImage(op.filter(robotImage, null), RobotBase.driveTrain.getPositionX(), RobotBase.driveTrain.getPositionY(), null);

		}
		
		g.setColor(Color.white);
		g.drawString("Time "+getTime()+" Level "+level+" Score "+score,25,25);
		
		// show the graphics
		g.dispose();
		strategy.show();

	}


}
