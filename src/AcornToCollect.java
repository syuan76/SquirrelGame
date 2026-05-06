import javax.swing.*;
import java.awt.*;

public class AcornToCollect {
    private int x, y, dx;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private int viewWidth;
    private int viewHeight;
    private Image image;
    private int speed;
    private boolean isCollected;
    private GameView view;

    public AcornToCollect(GameView view){
        this.view = view;
        this.image = new ImageIcon("Resources/Acorn.png").getImage();
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
        this.speed = 5;
        isCollected = false;
        this.x = viewWidth;
        this.y = view.getPLATFORMER_HEIGHT() - HEIGHT;
        this.dx = speed;
    }

    public void move(){
        x -= dx;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void hit() {
        isCollected = true;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, view);
    }

    public boolean isOffScreen() {
        return x + WIDTH < 0;
    }
}
