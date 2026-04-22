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
    private int dY = 0;
    private int terminalVelocity = 10;


    public Player() {
        // TODO: complete constructor
    }

    public void move() {
        // TODO
    }

    public void jump() {
        // TODO
        isJumping = false;
        if (!isJumping) {
            dY = -10;
            isJumping = true;
        }

        dY += gravity;
        // Cap the falling speed
        if (dY > terminalVelocity){
            dY = terminalVelocity;
        }

        this.y += dY;
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
        g.drawOval(600, 400, 50, 50 );
    }
    public void isOnGround() {
        // TODO
    }
}
