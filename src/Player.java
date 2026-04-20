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
    private double gravity;
    private boolean isJumping;
    private boolean isCollided;
    private boolean isAlive;

    public Player() {
        // TODO: complete constructor
    }

    public void move() {
        // TODO
    }

    public void jump() {
        // TODO
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
    }
    public void isOnGround() {
        // TODO
    }
}
