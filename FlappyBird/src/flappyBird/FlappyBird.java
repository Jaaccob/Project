package flappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {
    public static FlappyBird flappyBird;

    public final int WIDTH = 800;
    public final int HEIGHT = 800;
    public Renderer renderer;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public Random random;
    public int tick, yMotion, score;
    public boolean gameOver, started;

    public FlappyBird() {
        //Window
        JFrame jFrame = new JFrame();
        Timer timer = new Timer(20, this);
        renderer = new Renderer();

        jFrame.add(renderer);
        jFrame.addMouseListener(this);
        jFrame.addKeyListener(this);
        jFrame.setTitle("Flappy Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setResizable(true);
        jFrame.setVisible(true);

        //Game
        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        columns = new ArrayList<>();
        random = new Random();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick++;
        int speed = 5;

        if (started) {


            //Funkcja odpowiedzialna za przesuwanie się kolumn (przeszkód)
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }
            //Funkcja odpowiedzialna za podskakiwanie {@code bird}
            if (tick % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }
            //Funkcja odpowiedzialna za tworzenie nowych przeszkód
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0) {
                    columns.remove(column);
                    if (column.y == 0)
                        addColumn(false);
                }
            }
            //Dodawanie wysokości {@code bird}
            bird.y += yMotion;
            for (Rectangle column : columns) {
                //Dodawanie punktów za poprawne przeskoczenie przeszkody
                if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 5 && bird.x + bird.width / 2 < column.x + column.width / 2 + 5) {
                    score++;
                }
                //Sprawdzanie czy {@code bird} trafił w przeszkodę
                if (column.intersects(bird)) {
                    gameOver = true;
                    //Zabezpieczenie przed przenikaniem ściany bocznej kolumny przez {@code bird}
                    if (bird.x < column.x) {
                        bird.x = column.x - bird.width;

                    } else {
                        //Zabezpieczenie przez wpadnieńciem do środka dolnej kolumny przez {@code bird}
                        if (column.y != 0) {
                            bird.y = column.y - bird.height;
                        }
                        //Zabzpieczenie przez wpadnieńciem do środka górnej kolumny przez {@code bird}
                        else if (bird.y < column.height) {
                            bird.y = column.height;
                        }
                    }
                }
            }
            //Zabezpieczenie przez wypadnięciem poza plansze {@code bird}
            if (bird.y > HEIGHT - 123 || bird.y < 0) {
                gameOver = true;
            }
            //Zabezpieczenie aby {@code bird} nie wypadł poza dolną część planszy
            if (bird.y + yMotion >= HEIGHT - 120) {
                bird.y = HEIGHT - 120 - bird.height;
            }
        }
        renderer.repaint();
    }

    public void addColumn(boolean start) {
        int space = 300;
        int width = 100;
        int height = 50 + random.nextInt(300);

        if (start) {
            //Dodanie nowego kwadratu jako przeszkody
            //Dolna kolumna
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            //Górna kolumna
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        //Dodawanie nowych przeszkód po śmierci {@code bird}
        else {
            //Dolna kolumna
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
            //Górna kolumna
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);

    }

    public void repaint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 150, WIDTH, 150);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 150, WIDTH, 20);

        g.setColor(Color.red);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));
        if (!started) {
            g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
        }
        if (gameOver) {
            g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
        }
        if (!gameOver && started) {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
        }
    }

    public void jump() {
        if (gameOver) {
            gameOver = false;
            yMotion = 0;
            score = 0;
            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            columns.clear();

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);
        }
        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yMotion > 0) {
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    public static void main(String[] args) {
        flappyBird = new FlappyBird();
    }
}

