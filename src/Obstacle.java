import javax.swing.*;
import java.awt.*;

public class Obstacle {
    private int x, y, dx, dy;
    private static final int WIDTH = 150;
    private static final int HEIGHT = 150;
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
        this.speed = 10;

        this.x = viewWidth;
        this.y = view.getPLATFORMER_HEIGHT();
        this.dx = speed;
        this.dy = 0;
    }

    public void move() {
        // TODO
        x -= dx;
        y += dy;
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
