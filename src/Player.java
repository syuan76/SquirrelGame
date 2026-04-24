import java.awt.*;

public class Player {
    private int x;
    private int y;
    private int width;
    private int height;
    private int score;
    private int highScore;
    private boolean isFlying;
    private Image image;
    private double gravity = 0.5;
    private boolean isJumping;
    private boolean isCollided;
    private boolean isAlive;

    // Variables for gravity
    private double dY = 0.0;
    private double terminalVelocity = 10.0;


    public Player() {
        // TODO: complete constructor
        x = 200;
        y= 600;
        width = 50;
        height = 50;
    }

    public void move() {
        // TODO
    }

    public void jump() {
        // TODO
        // Jump up instantly
        y -= 100;

//        // Bring player back down after jump
//        if (y < 600) {
//            y = 600;
//        }
    }

    public void duck() {
        // TODO
    }

    public void fireAcorn() {
        // TODO
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
        g.setColor(Color.black);
        g.drawOval(x, y, width, height);
    }
    public void isOnGround() {
        // TODO

    }
}
