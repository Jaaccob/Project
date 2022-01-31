package Game.builder;

import java.awt.*;

/**
 * Klasa <code>Score</code> odpowiedzialna za trzymanie wyników graczy. Rozszerza ona klase <code>Rectangle</code>.
 */
public class Score extends Rectangle {

    /**
     * Zmienna lokalna odpowiedzialna za trzymanie informacji id grasza nr 1
     */
    private int playerOne;
    /**
     * Zmienna lokalna odpowiedzialna za trzymanie informacji id grasza nr 2
     */
    private int playerTwo;

    /**
     * Konstruktor odpowiedzialny za utworzenie obiektu o podajanej wysokości i szerokości
     *
     * @param width  szerokość prostokąta wyświetlającego wynik
     * @param height wysokość prostokąta wyświetlającego wynik
     */
    public Score(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Metoda <code>draw</code> odpowiedzialna jest za wyrysowanie wyniku.
     *
     * @param g obiekt który jest odpowiedzialny za rysowanie obiektów
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawString(String.valueOf(playerOne / 10) + String.valueOf(playerOne % 10), (width / 2) - 85, 50);
        g.drawString(String.valueOf(playerTwo / 10) + String.valueOf(playerTwo % 10), (width / 2) + 20, 50);
    }

    /**
     * Metoda <code>getPlayerOne</code> odpowiedzialna za zwrócenie id gracza
     *
     * @return id gracza nr 1
     */
    public int getPlayerOne() {
        return playerOne;
    }


    /**
     * Metoda <code>setPlayerOne</code> odpowiedzialny za ustawienie id gracza
     *
     * @param playerOne id gracza nr 1
     */
    public void setPlayerOne(int playerOne) {
        this.playerOne = playerOne;
    }

    /**
     * Metoda <code>getPlayerTwo</code> odpowiedzialna za zwrócenie id gracza
     *
     * @return id gracza nr 2
     */
    public int getPlayerTwo() {
        return playerTwo;
    }

    /**
     * Metoda <code>setPlayerTwo</code> odpowiedzialny za ustawienie id gracza
     *
     * @param playerTwo id gracza nr 2
     */
    public void setPlayerTwo(int playerTwo) {
        this.playerTwo = playerTwo;
    }
}
