package Game;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    private int xVelocity;
    private int yVelocity;
    private int initialSpeed = 2;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        Random random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }

    public void setXDirection(int XDirection) {
        xVelocity = XDirection;
    }

    public void setYDirection(int YDirection) {
        yVelocity = YDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
