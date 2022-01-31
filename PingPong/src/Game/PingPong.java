package Game;

import javax.swing.*;
import java.awt.*;

public class PingPong  {
    JFrame frame;
    GamePanel gamePanel;

    public final int WIDTH_WINDOW = 800;
    public final int HEIGHT_WINDOW = 600;

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
