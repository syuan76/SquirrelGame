import javax.swing.*;
import java.awt.*;

public class Obstacle {
    private int x, y, dx, dy;
    private int width;
    private int height;
    private int viewWidth;
    private int viewHeight;
    private Image image;
    private boolean isDead;

    private GameView view;

    public Obstacle(GameView view, String imageName, int width, int height, int y) {
        this.image = new ImageIcon(imageName).getImage();
        this.view = view;
        this.width = width;
        this.height = height;
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();

        this.x = viewWidth;
        this.y = y;
        this.dx = 5;
        this.dy = 0;
        isDead = false;
    }

    public void move() {
        x -= dx;
        y += dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void hit() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }


    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, view);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }
}
