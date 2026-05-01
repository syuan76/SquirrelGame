import javax.swing.*;
import java.awt.*;

public class Player {
    private int x;
    private int y;
    private int defaultY;
    private final int WIDTH = 100;
    private final int HEIGHT = 123;
    private int viewWidth;
    private int viewHeight;
    private int height;
    private int score;
    private int highScore;
    private boolean isFlying;
    private Image image;
    private double gravity = 0.25;
    private boolean isJumping;
    private boolean isCollided;
    private boolean isAlive;
    private boolean isDucking;

    private GameView view;

    // Variables for gravity
    private double dY = 0.0;
    private double terminalVelocity = 400.0;


    public Player(GameView view) {
        // TODO: complete constructor

        this.view = view;
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
        defaultY = view.getPLATFORMER_HEIGHT() - HEIGHT;
        this.x = viewWidth / 2;
        // TODO: take care of magic numbers
        this.y = defaultY;
        image = new ImageIcon("Resources/Squirrel.png").getImage();
    }

    public void move() {
        // TODO
        dY += gravity;

        if (dY > terminalVelocity) {
            dY = terminalVelocity;
        }

        y += dY;

        // Bring player back down after jump
        if (y >= defaultY) {
            y = defaultY;
            dY = 0;
        }
    }

    public void jump() {
        // TODO
        // Jump up
        if (y >= defaultY) {
            dY = - 10;
            isJumping = true;
        }
    }

    public void duck() {
        // TODO
        if (y <= defaultY){
            isDucking = true;
            height = 25;
            y = defaultY + 25;
        }
    }

    public void stand(){
        if(isDucking){
            isDucking = false;
            height = 50;
            y = defaultY;
        }
        isJumping = false;
    }

    public Acorn fireAcorn() {
        return new Acorn(x + WIDTH, y + height / 2);
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

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void updateHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void draw(Graphics g) {
        // TODO
        g.drawImage(image, x, y, WIDTH, HEIGHT, view);
    }
    public boolean isOnGround() {
        return y + height >= view.getPLATFORMER_HEIGHT();

    }
}
