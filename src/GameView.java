import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameView extends JFrame{
    private Game backend;
    private Image background;
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;
    private final int PLATFORMER_HEIGHT = 540;

    public GameView(Game backend) {
        // TODO: complete constructor
        this.backend = backend;

        this.background = new ImageIcon("Resources/PlatformerBackground.jpg").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SQUIRREL GAME");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public int getPLATFORMER_HEIGHT() {
        return PLATFORMER_HEIGHT;
    }

    public void drawInstructions(Graphics g) {
        // TODO
    }

    public void drawBackground(Graphics g) {
        // TODO
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void drawScore(Graphics g) {
        // TODO
    }

    public void keyTyped(KeyEvent e) {
        // TODO
    }

    public void keyPressed(KeyEvent e) {
        // TODO
    }

    public void KeyReleased(KeyEvent e) {
        // TODO
    }

    public void drawGameOver(Graphics g) {
        // TODO
    }

    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        // TODO


        if (backend.getGameState() == Game.STATE_MAIN_GROUND) {
            drawBackground(g);
            // Draw Obstacles
            for (int i = 0; i < backend.getObstacles().size(); i++) {
                backend.getObstacles().get(i).draw(g);
            }

            // Draw Player
            g.setColor(Color.WHITE);
//            g.fillRect(0,  0, WINDOW_WIDTH, WINDOW_HEIGHT);
            backend.getPlayer().draw(g);
        }

    }
}
