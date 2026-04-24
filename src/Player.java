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
    private double gravity = 0.25;
    private boolean isJumping;
    private boolean isCollided;
    private boolean isAlive;
    private boolean isDucking;

    // Variables for gravity
    private double dY = 0.0;
    private double terminalVelocity = 400.0;


    public Player() {
        // TODO: complete constructor
        x = 200;
        y= 600;
        width = 50;
        height = 50;
    }

    public void move() {
        // TODO
        dY += gravity;

        if (dY > terminalVelocity) {
            dY = terminalVelocity;
        }

        y += dY;

        // Bring player back down after jump
        if (y >= 600) {
            y = 600;
            dY = 0;
        }
    }

    public void jump() {
        // TODO
        // Jump up
        if (y >= 600) {
            dY = - 10;
        }
    }

    public void duck() {
        // TODO
        if (y <= 600){
            isDucking = true;
            height = 25;
            y = 625;
        }
    }

    public void stand(){
        isDucking = false;
        height = 50;
        y = 600;
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
