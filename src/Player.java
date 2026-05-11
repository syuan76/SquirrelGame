import javax.swing.*;
import java.awt.*;

public class Player {
    private int x;
    private int y;
    private final int WIDTH = 100;
    private int height;
    private final int STANDING_HEIGHT = 123;
    private int viewWidth;
    private int viewHeight;
    private Image image;
    private final double GRAVITY = 0.25;
    private boolean isJumping;
    private boolean isDucking;
    private int numAcorns;

    private GameView view;

    // Variables for gravity
    private double dY;
    private final double TERMINAL_VELOCITY = 400.0;


    public Player(GameView view) {
        this.view = view;
        height = STANDING_HEIGHT;
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
        this.x = viewWidth / 2;
        this.y = view.getPLATFORMER_HEIGHT() - height;
        image = new ImageIcon("Resources/Squirrel.png").getImage();
        numAcorns = 0;
        dY = 0.0;
    }

    public void move() {
        // Apply gravity by increasing downward velocity
        dY += GRAVITY;

        // Cap the falling speed so the player doesn't keep falling
        if (dY > TERMINAL_VELOCITY) {
            dY = TERMINAL_VELOCITY;
        }

        // Move the player vertically based on current velocity
        y += dY;

        // Bring player back down after jump
        if (y + height >= view.getPLATFORMER_HEIGHT()) {
            y = view.getPLATFORMER_HEIGHT() - height;
            dY = 0;
            isJumping = false;
        }
    }

    public void jump() {
        // Jump up
        // Only allow the player to jump if they are still on the ground
        if (isOnGround()) {
            // Apply upward velocity to launch player
            dY = - 10;
            isJumping = true;
        }
    }

    public void duck() {
        // Only allow player to duck when they're on the ground and aren't jumping or already ducking
        if (isOnGround() && !isJumping && !isDucking){
            isDucking = true;
            // Halve the player's height to make it seem like the player is crouching
            height = height / 2;
            // Reposition player so their feet are on the ground again
            y = view.getPLATFORMER_HEIGHT() - height;
        }
    }

    public void stand(){
        // Only restore standing state if player is ducking
        if (isDucking) {
            isDucking = false;
            // Restore the player's full height, making them stand
            height = STANDING_HEIGHT;
            // Keep players feet on the ground
            y = view.getPLATFORMER_HEIGHT()  - height;
        }
        // Ensure the jumping is cleared when standing
        isJumping = false;
    }

    public Acorn fireAcorn() {
        return new Acorn(x + WIDTH, y + height / 2);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, height);
    }

    public void addAcorn(){
        numAcorns++;
    }
    public void subtractAcorn(){
        numAcorns--;
    }

    public int getAcornAmount(){
        return numAcorns;
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

    public int getHeight() {
        return height;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, height, view);
    }
    public boolean isOnGround() {
        return y + height >= view.getPLATFORMER_HEIGHT();

    }
}
