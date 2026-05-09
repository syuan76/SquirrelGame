import java.awt.*;

public class Acorn {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;

    public Acorn(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
        this.speed = 8;
    }

    public boolean isOffScreen(int windowWidth){
        return x > windowWidth;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void fire() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x, y, width, height);
    }
}
