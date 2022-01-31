package Game;

import Game.builder.Ball;
import Game.builder.Paddle;
import Game.builder.Score;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

/**
 * Klasa <code>GamePanel</code> odpowiedzialna jest za logiczną część gry oraz za utworzenie poszczególnych elementów
 * gry. Jest to tzw. dyrektor, który tworzy części w zależności jakie potrzebuje. Również klasa ta odpowiada za logiczną
 * część aplikacji tzn. zasady jakie panują w grze. Klasa ta rozszerza klasę <code>JPanle</code> oraz implementuje 2
 * interfejsy <code>Runnable</code> odpowiedzialną za uruchomienie gry w kilku wątkach i <code>KeyListener</code>
 * odpowiedzialnej za nasłuch klawiatury. Obiekt posiada wiele zmiennych lokalnych potrzebych do prawidłowego działania.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    /**
     * Zmienna lokalna odpowiedzialna za szerokośc faktycznej aplikacji
     */
    private static final int WIDTH = 800;
    /**
     * Zmienna lokalna odpowiedzialna za wysokośc faktycznej aplikacji
     */
    private static final int HEIGHT = 600;
    /**
     * Zmienna lokalna odpowiedzialna za szerokość paletek do gry
     */
    private int paddleWidth = 25;
    /**
     * Zmienna lokalna odpowiedzialna za wysokość paletek do gry
     */
    private int paddleHeight = 100;
    /**
     * Zmienna lokalna odpowiedzialna za szerokość piłeczki pingpongowej
     */
    private int ballWidth = 25;
    /**
     * Zmienna lokalna odpowiedzialna za wysokośc piłeczki pingpongpowej
     */
    private int ballHeight = 25;
    /**
     * Zmienna lokalna odpowiedzialna za
     */
    private Image image;
    /**
     * Zmienna lokalna odpowiedzialna za tworzenie graficznego interfejsu
     */
    private Graphics graphics;
    /**
     * Zmienna lokalna odpowiedzialna za paletkę pierwszego gracza
     */
    private Paddle paddleOne;
    /**
     * Zmienna lokalna odpowiedzialna za paletkę drugiego gracza
     */
    private Paddle paddleTwo;
    /**
     * Zmienna lokalna odpowiedzialna za piłeczkę pingpongową
     */
    private Ball ball;
    /**
     * Zmienna lokalna odpowiedzialna za punkty dla graczy
     */
    private Score score;
    /**
     * Zmienna lokalna odpowiedzialna za wysokość i szerokośc okna
     */
    private Dimension screenSize;


    /**
     * Konstruktor bezarumentowy odpowiedzialny za utworzenie całej aplikajci
     */
    public GamePanel() {
        screenSize = new Dimension(WIDTH, HEIGHT);
        Thread thread = new Thread(this);
        score = new Score(WIDTH, HEIGHT);

        newBall();
        newPaddles();
        playMusic("/home/jacob/Programer/Project/PingPong/src/Music/Music.wav", true);

        this.setFocusable(true);
        this.addKeyListener(this);
        this.setPreferredSize(screenSize);

        thread.start();
    }

    /**
     * Metoda <code>newBall</code> odpowiedzialna jest za utworzenie piłeczki pingpongowej według podanego wzorca
     */
    public void newBall() {
        ball = new Ball((WIDTH / 2) - (ballWidth / 2), (HEIGHT / 2) - (ballHeight / 2), 25, 25);
    }

    /**
     * Metoda <code>newPaddles</code> odpowiedzialna jest za utworzenie paletki pingpongowej według podanego wzorca
     */
    public void newPaddles() {
        paddleOne = new Paddle(0, (HEIGHT / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 1);
        paddleTwo = new Paddle(WIDTH - paddleWidth, (HEIGHT / 2) - (paddleHeight / 2), paddleWidth, paddleHeight, 2);
    }

    /**
     * Metoda <code>playMusic</code> odpowiedzialna jest za uruchomienie dzwieku aplikacji posiada dwie zmienne
     *
     * @param path ścieżka dostępu do pliku dzwiękowego
     * @param loop true jesli dzwiek ma się powtarzać, false jesli dzwiek ma być wykonany jeden raz
     */
    public void playMusic(String path, boolean loop) {
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if (loop)
                clip.loop(1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda <code>move</code> odpowiedzialna jest za ruch poszczególnych elementów na planszy
     */
    private void move() {
        paddleOne.move();
        paddleTwo.move();
        ball.move();
    }

    /**
     * Metoda <code>paint</code> odpowiedzialna jest za utworzenie poszczególnych elementów na planszy
     */
    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Metoda <code>draw</code> odpowiedzialna jest za wyrysowanie elementów znajdujacych się na planszy
     *
     * @param g obiekt który jest odpowiedzialny za rysowanie obiektów
     */
    public void draw(Graphics g) {
        try {
            Image img = ImageIO.read(new File("/home/jacob/Programer/Project/PingPong/src/Texture/texture.jpg"));
            g.drawImage(img, 0, 0, WIDTH, HEIGHT + 70, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        paddleOne.draw(g);
        paddleTwo.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Metoda <code>checkCollision</code> odpowiedzialna jest za sprawdzanie czy powstała kolizja któregoś elementu z
     * innym. Jeśli, któryś element znalazł się w kolizji to są wywoływane poszczególne procedury aby usunąć kolizje
     */
    public void checkCollision() {
        if (ball.intersects(paddleOne)) {
            ball.setxVelocity(Math.abs(ball.getxVelocity()));
            ball.setxVelocity(ball.getxVelocity() + 1);
            if (ball.getyVelocity() > 0)
                ball.setyVelocity(ball.getyVelocity() + 1);
            else
                ball.setyVelocity(ball.getyVelocity() - 1);
            ball.setxVelocity(ball.getxVelocity());
            ball.setyVelocity(ball.getyVelocity());
            playMusic("/home/jacob/Programer/Project/PingPong/src/Music/Odbij.wav", false);
        }
        if (ball.intersects(paddleTwo)) {
            ball.setxVelocity(Math.abs(ball.getxVelocity()));
            ball.setxVelocity(ball.getxVelocity() - 1);
            if (ball.getyVelocity() > 0)
                ball.setyVelocity(ball.getyVelocity() - 1);
            else
                ball.setyVelocity(ball.getyVelocity() - 1);
            ball.setxVelocity(ball.getxVelocity());
            ball.setyVelocity(ball.getyVelocity());
            playMusic("/home/jacob/Programer/Project/PingPong/src/Music/Odbij.wav", false);
        }

        if (ball.y < 0) {
            ball.setyVelocity(-ball.getyVelocity());
            playMusic("/home/jacob/Programer/Project/PingPong/src/Music/P.wav", false);
        }
        if (ball.y > HEIGHT - ballHeight) {
            ball.setyVelocity(-ball.getyVelocity());
            playMusic("/home/jacob/Programer/Project/PingPong/src/Music/P.wav", false);
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
            playMusic("/home/jacob/Programer/Project/PingPong/src/Music/Punkt.wav", false);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.getPlayerOne());

        }

        if (ball.x <= 0) {
            score.setPlayerTwo(score.getPlayerTwo() + 1);
            playMusic("/home/jacob/Programer/Project/PingPong/src/Music/Punkt.wav", false);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.getPlayerTwo());
        }


    }

    /**
     * Metoda <code>keyTyped</code> odpowiedzialna jest za przechwycenie wydarzenia związanego z typem naciśniętego
     * klawisza
     *
     * @param e obiekt reprezentujący wydarzenie naciśniętego klawisza
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //KeyTyped not used
    }

    /**
     * Metoda <code>keyPressed</code> odpowiedzialna jest za przechwycenie wydarzenia związanego z naciśnięciem klawisza
     *
     * @param e obiekt reprezentujący wydarzenie naciśniętego klawisza
     */
    @Override
    public void keyPressed(KeyEvent e) {
        paddleOne.keyPressed(e);
        paddleTwo.keyPressed(e);
    }

    /**
     * Metoda <code>keyReleased</code> odpowiedzialna jest za przechwycenie wydarzenia związanego z trzymaniem
     * naciśniętego klawisza
     *
     * @param e obiekt reprezentujący wydarzenie naciśniętego klawisza
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //KeyReleased not used
    }

    /**
     * Metoda <code>run</code> odpowiedzialna jest za ciągłe działanie aplikacji
     */
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
