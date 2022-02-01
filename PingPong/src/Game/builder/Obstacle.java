package Game.builder;

import java.awt.*;

public class Obstacle extends Rectangle {

    public Obstacle(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
