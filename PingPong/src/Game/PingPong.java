package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa <code>PingPong</code> odpowiedzialna jest za reprezentacje okna wyświetlanego klientowi oraz wstępną jego
 * konfigurację. Klasa ta również spaja wszystkie elementy jakie aplikacja posiada. Jest pierwszym elementem jaki
 * użytkownik widzi po uruchomieniu aplikacji. Klasa posiada 4 zmienne lokalne.
 */
public class PingPong {
    /**
     * Zmiennna odpowiedzialna za utworzenie okna graficznego oraz przechowywanie wszystkich elementów znajdujących
     * się w oknie
     */
    private JFrame frame;
    /**
     * Zmienna odpowiedzialna za utworzenie logicznej częsci aplikacji oraz zasad panujących w grze
     */
    private GamePanel gamePanel;

    /**
     * Zmienna odpowiedzialna za szerokość okna
     */
    public final int WIDTH_WINDOW = 800;
    /**
     * Zmienna odpowiedzialna za wysokość okna
     */
    public final int HEIGHT_WINDOW = 600;

    /**
     * Konstruktor bezargumetrowy (bezparametrowy) odpowiedzialny za utworzenie i wstepne skonfigurowanie aplikacji.
     */
    public PingPong() {
        frame = new JFrame();
        gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.setTitle("Ping Pong");
        frame.setResizable(true);
        frame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
