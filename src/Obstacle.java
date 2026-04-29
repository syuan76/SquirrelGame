import javax.swing.*;
import java.awt.*;

public class Obstacle {
    private int x, y, dx, dy;
    private final int WIDTH = 75;
    private final int HEIGHT = 100;
    private int viewWidth;
    private int viewHeight;
    private Image image;
    private int speed;
    private boolean isActive;

    private GameView view;

    public Obstacle(GameView view) {
        // TODO: complete constructor
        this.image = new ImageIcon("Resources/Snake.png").getImage();
        this.view = view;
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
        this.speed = 5;

        this.x = viewWidth;
        this.y = view.getPLATFORMER_HEIGHT() - HEIGHT;
        this.dx = speed;
        this.dy = 0;
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
        // TODO
        return;
    }

    public void takeDamage() {
        // TODO
        return;
    }

    public void draw(Graphics g) {
        // TODO
        g.drawImage(image, x, y, WIDTH, HEIGHT, view);
    }

    public boolean isOffScreen() {
        return x + WIDTH < 0;
    }
}
