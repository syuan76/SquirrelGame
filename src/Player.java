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
    private GameView view;

    // Variables for gravity
    private double dY = 0.0;
    private double terminalVelocity = 400.0;


    public Player(GameView view) {
        // TODO: complete constructor
        this.view = view;
        x = 200;
        y = view.getPLATFORMER_HEIGHT();
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
        if (isOnGround()) {
            y = view.getPLATFORMER_HEIGHT();
            dY = 0;
            isJumping = false;
        }
    }

    public void jump() {
        // TODO
        // Jump up
        if (isOnGround() && !isDucking) {
            dY = - 10;
            isJumping = true;
        }
    }

    public void duck() {
        // TODO
        if (y <= view.getPLATFORMER_HEIGHT() && !isJumping){
            isDucking = true;
            y += 25;
            height = 25;
        }
    }

    public void stand(){
        y -= 25;
        isDucking = false;
        isJumping = false;
        height = 50;
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
    public boolean isOnGround() {
        return y >= view.getPLATFORMER_HEIGHT();

    }
}
