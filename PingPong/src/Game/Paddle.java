package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends Rectangle implements KeyListener {

    private int id;
    private int speed = 10;
    private int ySpeed;

    public Paddle(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public void move() {
        y = y + ySpeed;
    }

    public void setySpeed(int y) {
        ySpeed = y;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (id == 1) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                setySpeed(-speed);
                move();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                setySpeed(speed);
                move();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setySpeed(-speed);
                move();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setySpeed(speed);
                move();
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.RED);
        } else if (id == 2) {
            g.setColor(Color.GREEN);
        }
        g.fillRect(x, y, width, height);
    }
}
