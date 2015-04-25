package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game {
	// image resources
	public static BufferedImage imgBall;
	public static BufferedImage imgBackground;
	public static BufferedImage imgSlime1, imgSlime2;
	
	// game objects
	public static Slime slime1, slime2;
	public static Ball ball;
	public static Goal goal1, goal2;
	static public int groundLevel = 400; // y value of where ground level is in game
	static public int leftBoundary = 0, rightBoundary = 600; // y value of where ground level is in game
	
	// game stats
	public static String background;
	public static String player1_slimeType, player2_slimeType;
	public static String player1_username, player2_username;
	public static double player1_manaCurrent, player2_manaCurrent; // live data
	public static int player1_manaMax, player2_manaMax;
	public static Integer player1_score, player2_score; // live data
	public static boolean player1scored, player2scored;
	public static int manaRegenerationRate;
	public static String specialMode;
	
	public Game(String background, String player1_slimeType, String player2_slimeType, String player1_username, String player2_username, int player1_manaMax, int player2_manaMax, int manaRegenerationRate, String specialMode) {
		// load resources
		try {
			// load soccer ball image
			imgBall = ImageIO.read(new File("./resources/game/slimes/SoccerBall.png"));
			
			// load background image
			if (background.equals("soccerfield")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/soccerfield.png"));
			} else if (background.equals("desk")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/desk.jpg"));
			} else if (background.equals("laptop")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/laptop.png"));
			} else if (background.equals("outerspace")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/outerspace.jpg"));
			}
			// load slime images
			if (player1_slimeType.equals("SlimeBomb")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/BombSlime.png"));
			} else if (player1_slimeType.equals("SlimeBowAndArrow")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/BowAndArrowSlime.png"));
			} else if (player1_slimeType.equals("SlimeClone")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/CloneSlime.png"));
			} else if (player1_slimeType.equals("SlimeCosmic")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/CosmicSlime.png"));
			} else if (player1_slimeType.equals("SlimeFireball")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/FireballSlime.png"));
			} else if (player1_slimeType.equals("SlimeFisher")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/FisherSlime.png"));
			} else if (player1_slimeType.equals("SlimeGeyser")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/GeyserSlime.png"));
			} else if (player1_slimeType.equals("SlimeMagnet")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/MagnetSlime.png"));
			} else if (player1_slimeType.equals("SlimeSuperSize")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/SuperSizeSlime.png"));
			} else if (player1_slimeType.equals("SlimeSuper")) {
				imgSlime1 = ImageIO.read(new File("./resources/game/slimes/SuperSlime.png"));
			}
			if (player2_slimeType.equals("SlimeBomb")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/BombSlime.png"));
			} else if (player2_slimeType.equals("SlimeBowAndArrow")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/BowAndArrowSlime.png"));
			} else if (player2_slimeType.equals("SlimeClone")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/CloneSlime.png"));
			} else if (player2_slimeType.equals("SlimeCosmic")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/CosmicSlime.png"));
			} else if (player2_slimeType.equals("SlimeFireball")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/FireballSlime.png"));
			} else if (player2_slimeType.equals("SlimeFisher")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/FisherSlime.png"));
			} else if (player2_slimeType.equals("SlimeGeyser")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/GeyserSlime.png"));
			} else if (player2_slimeType.equals("SlimeMagnet")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/MagnetSlime.png"));
			} else if (player2_slimeType.equals("SlimeSuperSize")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/SuperSizeSlime.png"));
			} else if (player2_slimeType.equals("SlimeSuper")) {
				imgSlime2 = ImageIO.read(new File("./resources/game/slimes/SuperSlime.png"));
			}
		} catch (IOException e) {
			System.out.println("IOException in load resources (Frame): " + e.getMessage());
		}
		
		// set game stats
		Game.background = background;
		Game.player1_slimeType = player1_slimeType;
		Game.player2_slimeType = player2_slimeType;
		Game.player1_username = player1_username;
		Game.player2_username = player2_username;
		Game.player1_manaCurrent = player1_manaMax;
		Game.player2_manaCurrent = player2_manaMax;
		Game.player1_manaMax = player1_manaMax;
		Game.player2_manaMax = player2_manaMax;
		Game.player1_score = 0;
		Game.player2_score = 0;
		Game.manaRegenerationRate = manaRegenerationRate;
		Game.specialMode = specialMode;
		Game.player1scored = false;
		Game.player2scored = false;
		
		// initialize objects
		if (player1_slimeType.equals("SlimeBomb")) {
			slime1 = new SlimeBomb(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeBowAndArrow")) {
			slime1 = new SlimeBowAndArrow(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeClone")) {
			slime1 = new SlimeClone(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeCosmic")) {
			slime1 = new SlimeCosmic(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeFireball")) {
			slime1 = new SlimeFireball(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeFisher")) {
			slime1 = new SlimeFisher(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeGeyser")) {
			slime1 = new SlimeGeyser(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeMagnet")) {
			slime1 = new SlimeMagnet(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeSuperSize")) {
			slime1 = new SlimeSuperSize(leftBoundary + 50, groundLevel, 1, imgSlime1);
		} else if (player1_slimeType.equals("SlimeSuper")) {
			slime1 = new SlimeSuper(leftBoundary + 50, groundLevel, 1, imgSlime1);
		}
		if (player2_slimeType.equals("SlimeBomb")) {
			slime2 = new SlimeBomb(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeBowAndArrow")) {
			slime2 = new SlimeBowAndArrow(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeClone")) {
			slime2 = new SlimeClone(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeCosmic")) {
			slime2 = new SlimeCosmic(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeFireball")) {
			slime2 = new SlimeFireball(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeFisher")) {
			slime2 = new SlimeFisher(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeGeyser")) {
			slime2 = new SlimeGeyser(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeMagnet")) {
			slime2 = new SlimeMagnet(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeSuperSize")) {
			slime2 = new SlimeSuperSize(rightBoundary - 50, groundLevel, 2, imgSlime2);
		} else if (player2_slimeType.equals("SlimeSuper")) {
			slime2 = new SlimeSuper(rightBoundary - 50, groundLevel, 2, imgSlime2);
		}
		ball = new Ball((leftBoundary + rightBoundary)/2, groundLevel - 12 - 20);
		goal1 = new Goal(leftBoundary);
		goal2 = new Goal(rightBoundary);
	}
	
	public static void update() {
		// update positions
		slime1.update();
		slime2.update();
		ball.update();
		
		// check for ball collision with slimes
		Double [] slime1CollideNewVelocities = slime1.detectCollision(ball);
		Double [] slime2CollideNewVelocities = slime2.detectCollision(ball);
		if (slime1CollideNewVelocities[0] != null) {
			ball.velocityX = slime1CollideNewVelocities[0].intValue();
			ball.velocityY = slime1CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		if (slime2CollideNewVelocities[0] != null) {
			ball.velocityX = slime2CollideNewVelocities[0].intValue();
			ball.velocityY = slime2CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		// check for ball collision with top piece of goalpost
		Double [] goal1CollideNewVelocities = goal1.detectCollision(ball.x, ball.y, ball.velocityX, ball.velocityY);
		Double [] goal2CollideNewVelocities = goal2.detectCollision(ball.x, ball.y, ball.velocityX, ball.velocityY);
		if (goal1CollideNewVelocities != null) {
			ball.velocityX = goal1CollideNewVelocities[0].intValue();
			ball.velocityY = goal1CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		if (goal2CollideNewVelocities != null) {
			ball.velocityX = goal2CollideNewVelocities[0].intValue();
			ball.velocityY = goal2CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		
		
		// check for goals
		if (goal1.checkGoal(ball.x, ball.y)) {
			goalScored(2);
		}
		if (goal2.checkGoal(ball.x, ball.y)) {
			goalScored(1);
		}

	}
	
	private static void goalScored(int playerThatScored) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException in goalScored() function: " + e.getMessage());
		}
		if (playerThatScored == 1) {
			player1_score++;
			player1scored = true;
		}
		else if (playerThatScored == 2) {
			player2_score++;
			player2scored = true;
		}
		if (playerThatScored == 1 || playerThatScored == 2) { // then reset ball position, slime positions, etc
			ball.x = (leftBoundary + rightBoundary)/2;
			ball.y = groundLevel - 12 - 20;
			ball.velocityX = 0;
			ball.velocityY = 0;
			slime1.x = leftBoundary + 50;
			slime1.y = groundLevel;
			slime1.velocityX = 0;
			slime1.velocityY = 0;
			slime2.x = rightBoundary - 50;
			slime2.y = groundLevel;
			slime2.velocityX = 0;
			slime2.velocityY = 0;
		}
	}
	
}
