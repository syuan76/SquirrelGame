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
        y = view.getPLATFORMER_HEIGHT() - height;
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
            y = view.getPLATFORMER_HEIGHT() - height;
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
        if (isOnGround() && !isJumping){
            isDucking = true;
            height = 25;
            y = view.getPLATFORMER_HEIGHT() - height;
        }
    }

    public void stand(){
        if(isDucking){
            isDucking = false;
            height = 50;
            y = view.getPLATFORMER_HEIGHT() - height;
        }
        isJumping = false;
    }

    public Acorn fireAcorn() {
        return new Acorn(x + width, y + height / 2);
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
        return y + height >= view.getPLATFORMER_HEIGHT();

    }
}
