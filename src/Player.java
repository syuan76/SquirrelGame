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
        // TODO: take care of magic numbers
        this.y = view.getPLATFORMER_HEIGHT() - height;
        image = new ImageIcon("Resources/Squirrel.png").getImage();
        numAcorns = 0;
        dY = 0.0;
    }

    public void move() {
        dY += GRAVITY;

        if (dY > TERMINAL_VELOCITY) {
            dY = TERMINAL_VELOCITY;
        }

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
        if (isOnGround()) {
            dY = - 10;
            isJumping = true;
        }
    }

    public void duck() {
        if (isOnGround() && !isJumping && !isDucking){
            isDucking = true;
            height = height / 2;
            y = view.getPLATFORMER_HEIGHT() - height;
        }
    }

    public void stand(){
        if (isDucking) {
            isDucking = false;
            height = STANDING_HEIGHT;
            y = view.getPLATFORMER_HEIGHT()  - height;
        }
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
