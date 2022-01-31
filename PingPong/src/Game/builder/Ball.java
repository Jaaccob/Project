package Game.builder;

import java.awt.*;
import java.util.Random;

/**
 * Klasa <code>Ball</code> odpowiedzialna za obiekty reprezentujący piłeczkę pingpongowa. Klasa ta rozszerza klasę
 * <code>Rectangle</code>.
 */
public class Ball extends Rectangle {
    private int xVelocity;
    private int yVelocity;
    private int initialSpeed = 2;

    /**
     * Konstruktor odpowiedzialny za utworzenie obiektu. Piłeczka może poruszać się z różną prędkością, dlatego
     * zastosowano funkcję Random odpowiedzialna za różną prędkość piłeczki
     *
     * @param x      lewy górny wierzchołek piłeczki dla wartości x
     * @param y      lewy górny wierzchołek piłeczki dla wartości y
     * @param width  szerokość od punktu (x,y)
     * @param height wysokość od punktu (x,y)
     */
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        Random random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setxVelocity(randomXDirection * initialSpeed);
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setyVelocity(randomYDirection * initialSpeed);
    }

    /**
     * Metoda <code>setxVelocity</code> odpowiedzialna jest za zmianę aktualnej prędkości piłeczki według x wartości
     *
     * @param XDirection wartość prędkości piłeczki
     */
    public void setxVelocity(int XDirection) {
        xVelocity = XDirection;
    }

    /**
     * Metoda <code>setYDirecion</code> odpowiedzialna jest za zmianę aktualnej prędkości piłeczki według y wartości
     *
     * @param YDirection wartość prędkości piłeczki
     */
    public void setyVelocity(int YDirection) {
        yVelocity = YDirection;
    }

    /**
     * Metoda <code>move</code> odpowiedzialna za poruszanie się piłeczki
     */
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    /**
     * Metoda <code>getxVelocity</code> odpowiedzialna jest za zwrócenie zawrości lokalnej zmiennej
     *
     * @return zwraca wartość xVelocity
     */
    public int getxVelocity() {
        return xVelocity;
    }

    /**
     * Metoda <code>getyVelocity</code> odpowiedzialna jest za zwrócenie zawrości lokalnej zmiennej
     *
     * @return zwraca wartość yVelocity
     */
    public int getyVelocity() {
        return yVelocity;
    }

    /**
     * Metoda <code>draw</code> odpowiedzialna jest za wyrysowanie wyniku.
     *
     * @param g obiekt który jest odpowiedzialny za rysowanie obiektów
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
