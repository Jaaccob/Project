package Game;

import java.awt.*;

public class Score extends Rectangle {

    private int playerOne;
    private int playerTwo;


    public Score(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        g.drawLine(width/2, 0, width/2, height);
        g.drawString(String.valueOf(playerOne/10)+String.valueOf(playerOne%10), (width/2)-85, 50);
        g.drawString(String.valueOf(playerTwo/10)+String.valueOf(playerTwo%10), (width/2)+20, 50);
    }

    public int getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(int playerOne) {
        this.playerOne = playerOne;
    }

    public int getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(int playerTwo) {
        this.playerTwo = playerTwo;
    }
}
