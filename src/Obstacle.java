import javax.swing.*;
import java.awt.*;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private int speed;
    private boolean isActive;

    private GameView view;

    public Obstacle(GameView view) {
        // TODO: complete constructor
        this.image = new ImageIcon("Resources/Snake.png").getImage();
        this.view = view;
    }

    public boolean collides() {
        // TODO
        return false;
    }

    public void hit() {
        // TODO
        return;
    }

    public void move() {
        // TODO
        return;
    }

    public void takeDamage() {
        // TODO
        return;
    }

    public void draw(Graphics g) {
        // TODO
        g.drawImage(image, 550, 540, 150, 150, view);
        return;
    }

    public boolean isOffScreen() {
        // TODO
        return false;
    }
}
