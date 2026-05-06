import javax.swing.*;
import java.awt.*;

public class ObstacleOwl {
    private int x, y, dx, dy;
    private final int WIDTH = 100;
    private final int HEIGHT = 75;
    private int viewWidth;
    private int viewHeight;
    private Image image;
    private int speed;
    private boolean isActive;
    private boolean isDead;

    private GameView view;

    public ObstacleOwl(GameView view) {
        // TODO: complete constructor
        this.image = new ImageIcon("Resources/Owl.png").getImage();
        this.view = view;
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
        this.speed = 5;

        this.x = viewWidth;
        this.y = view.getPLATFORMER_HEIGHT() - HEIGHT * 2;
        this.dx = speed;
        this.dy = 0;
        isDead = false;
    }

    public void move() {
        // TODO
        x -= dx;
        y += dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public boolean collides() {
        // TODO
        return false;
    }

    public void hit() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void takeDamage() {
        // TODO
        return;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, view);
    }

    public boolean isOffScreen() {
        return x + WIDTH < 0;
    }
}
