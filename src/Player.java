import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int x;
    private int y;
    private int defaultY;
    private final int WIDTH = 100;
    private int HEIGHT = 123;
    private int viewWidth;
    private int viewHeight;
    private boolean isFlying;
    private Image image;
    private double gravity = 0.25;
    private boolean isJumping;
    private boolean isCollided;
    private boolean isAlive;
    private boolean isDucking;
    private int acorns;

    private GameView view;

    // Variables for gravity
    private double dY = 0.0;
    private double terminalVelocity = 400.0;


    public Player(GameView view) {
        this.view = view;
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
        defaultY = view.getPLATFORMER_HEIGHT() - HEIGHT;
        this.x = viewWidth / 2;
        // TODO: take care of magic numbers
        this.y = defaultY;
        image = new ImageIcon("Resources/Squirrel.png").getImage();
        acorns = 0;
    }

    public void move() {
        dY += gravity;

        if (dY > terminalVelocity) {
            dY = terminalVelocity;
        }

        y += dY;

        // Bring player back down after jump
        if (y + HEIGHT >= view.getPLATFORMER_HEIGHT()) {
            y = view.getPLATFORMER_HEIGHT() - HEIGHT;
            dY = 0;
            isJumping = false;
        }
    }

    public void jump() {
        // TODO
        // Jump up
        if (isOnGround()) {
            dY = - 10;
            isJumping = true;
        }
    }

    public void duck() {
        // TODO
        if (isOnGround() && !isJumping && !isDucking){
            isDucking = true;
            HEIGHT = HEIGHT / 2;
            y = view.getPLATFORMER_HEIGHT() - HEIGHT;
        }
    }

    public void stand(){
        if(isDucking){
            isDucking = false;
            HEIGHT = 123;
            y = view.getPLATFORMER_HEIGHT()  - HEIGHT;
        }
        isJumping = false;
    }

    public Acorn fireAcorn() {
        return new Acorn(x + WIDTH, y + HEIGHT / 2);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void addAcorn(){
        acorns++;
    }
    public void subtractAcorn(){
        acorns--;
    }

    public int getAcornAmount(){
        return acorns;
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

    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, view);
    }
    public boolean isOnGround() {
        return y + HEIGHT >= view.getPLATFORMER_HEIGHT();

    }
}
