package Game.builder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa <code>Paddle</code> reprezentuje paletkę do gry w tenisa stołowego. Obiekt rozszerza klase
 * <code>Rectangle</code> oraz implementuje interfejs <code>KeyListener</code>
 */
public class Paddle extends Rectangle implements KeyListener {

    /**
     * Zmienna lokalna odpowiedzialna za id paletki. Id = 1 jest zarezerwowane dla gracza nr 1 natomiast id = 2
     * zarezerwowane jest dla gracza nr 2
     */
    private int id;
    /**
     * Zmienna lokalna odpowiedzialna za szybkość przesuwania paletek
     */
    private int speed = 10;
    /**
     * Zmienna lokalna odpowiedzialna za trzymanie aktualnej prędkości paletki
     */
    private int ySpeed;

    /**
     * Konstruktor odpowiedzialny za utworzenie obiektu na wzór prostokąta
     *
     * @param x      lewy górny wierzchołek prostokąta dla wartości x
     * @param y      lewy górny wierzchołek prostokąta dla wartości y
     * @param width  szerokość prostokąta od puntku (x,y)
     * @param height wysokość prostokąta od puntku (x,y)
     * @param id     numer paletki
     */
    public Paddle(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }

    /**
     * Metoda <code>move</code> odpowiedzialna za poruszanie się paletki
     */
    public void move() {
        y = y + ySpeed;
    }

    /**
     * Metoda <code>setySpeed</code> odpowiedzialna za zmianę prędkości paletki
     *
     * @param y wartość na jaką zmieniamy prędkość
     */
    public void setySpeed(int y) {
        ySpeed = y;
    }

    /**
     * Metoda <code>keyTyped</code> odpowiedzialna jest za przechwycenie wydarzenia związanego z typem naciśniętego
     * klawisza
     *
     * @param e obiekt reprezentujący wydarzenie naciśniętego klawisza
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda <code>keyPressed</code> odpowiedzialna jest za przechwycenie wydarzenia związanego z naciśnięciem klawisza
     * oraz wywołanie funkcji odpowiedzialnej za poruszanie się paletki
     *
     * @param e obiekt reprezentujący wydarzenie naciśniętego klawisza
     */
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


    /**
     * Metoda <code>keyReleased</code> odpowiedzialna jest za przechwycenie wydarzenia związanego z trzymaniem
     * naciśniętego klawisza
     *
     * @param e obiekt reprezentujący wydarzenie naciśniętego klawisza
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (id == 1) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                setySpeed(0);
                move();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                setySpeed(0);
                move();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setySpeed(0);
                move();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setySpeed(0);
                move();
            }
        }
    }

    /**
     * Metoda <code>draw</code> odpowiedzialna za rysowanie paletek na ekranie
     *
     * @param g Obiekt odpowiedzialny za wyrysowanie elementu na ekranie
     */
    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.BLUE);
        } else if (id == 2) {
            g.setColor(Color.GREEN);
        }
        g.fillRect(x, y, width, height);
    }
}
