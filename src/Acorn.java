import java.awt.*;

public class Acorn {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Image image;
    private boolean hasHit;

    public Acorn(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
        this.speed = 8;
        this.hasHit = false;
    }

    public boolean isOffScreen(int windowWidth){
        return x > windowWidth;
    }

    public boolean hasHit(){
        return hasHit;
    }
    public void fire() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x, y, width, height);
    }
}
