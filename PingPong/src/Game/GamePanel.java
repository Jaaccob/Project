package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * The type Game panel.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private int paddleWidth = 25;
    private int paddleHeight = 100;
    private int ballWidth = 25;
    private int ballHeight = 25;
    /**
     * The Image.
     */
    private Image image;
    /**
     * The Graphics.
     */
    private Graphics graphics;
    /**
     * The Paddle one.
     */
    private Paddle paddleOne;
    /**
     * The Paddle two.
     */
    private Paddle paddleTwo;
    /**
     * The Ball.
     */
    private Ball ball;
    /**
     * The Score.
     */
    private Score score;
    private Dimension screenSize;


    /**
     * Instantiates a new Game panel.
     */
    public GamePanel() {
        screenSize = new Dimension(WIDTH, HEIGHT);
        Thread thread = new Thread(this);
        score = new Score(WIDTH, HEIGHT);

        newBall();
        newPaddles();

        this.setFocusable(true);
        this.addKeyListener(this);
        this.setPreferredSize(screenSize);

        thread.start();
    }

    /**
     * New ball.
     */
    public void newBall() {
        ball = new Ball((WIDTH / 2) - (ballWidth / 2), (HEIGHT / 2) - (ballHeight / 2), 25, 25);
    }

    /**
     * New paddles.
     */
    public void newPaddles() {
        paddleOne = new Paddle(0, (HEIGHT / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 1);
        paddleTwo = new Paddle(WIDTH - paddleWidth, (HEIGHT / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 2);
    }

    private void move() {
        paddleOne.move();
        paddleTwo.move();
        ball.move();
    }

    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddleOne.draw(g);
        paddleTwo.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void checkCollision() {
        if (ball.intersects(paddleOne)) {
            ball.setXDirection(Math.abs(ball.getxVelocity()));
            ball.setXDirection(ball.getxVelocity() + 1);
            if (ball.getyVelocity() > 0)
                ball.setYDirection(ball.getyVelocity() + 1);
            else
                ball.setYDirection(ball.getyVelocity() - 1);
            ball.setXDirection(ball.getxVelocity());
            ball.setYDirection(ball.getyVelocity());
        }
        if (ball.intersects(paddleTwo)) {
            ball.setXDirection(Math.abs(ball.getxVelocity()));
            ball.setXDirection(ball.getxVelocity() - 1);
            if (ball.getyVelocity() > 0)
                ball.setYDirection(ball.getyVelocity() - 1);
            else
                ball.setYDirection(ball.getyVelocity() - 1);
            ball.setXDirection(ball.getxVelocity());
            ball.setYDirection(ball.getyVelocity());
        }

        if (ball.y < 0) {
            ball.setYDirection(-ball.getyVelocity());
        }
        if (ball.y > HEIGHT - ballHeight) {
            ball.setYDirection(-ball.getyVelocity());
        }

        if (paddleOne.y <= 0)
            paddleOne.y = 0;

        if (paddleOne.y > HEIGHT - paddleHeight)
            paddleOne.y = HEIGHT - paddleHeight;

        if (paddleTwo.y <= 0)
            paddleTwo.y = 0;

        if (paddleTwo.y > HEIGHT - paddleHeight)
            paddleTwo.y = HEIGHT - paddleHeight;

        if (ball.x > WIDTH - ballWidth) {
            score.setPlayerOne(score.getPlayerOne() + 1);
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.getPlayerOne());

        }

        if (ball.x <= 0) {
            score.setPlayerTwo(score.getPlayerTwo() + 1);
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.getPlayerTwo());
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
        //KeyTyped not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        paddleOne.keyPressed(e);
        paddleTwo.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //KeyReleased not used
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }

    }


}
