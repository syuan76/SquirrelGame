import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame{
    private Game backend;
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    public GameView(Game backend) {
        // TODO: complete constructor
        this.backend = backend;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SQUIRREL GAME");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public void drawInstructions(Graphics g) {
        // TODO
    }

    public void drawBackground(Graphics g) {
        // TODO
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
        g.setColor(Color.WHITE);
        g.fillRect(0,  0, WINDOW_WIDTH, WINDOW_HEIGHT);
        backend.getPlayer().draw(g);
    }
}
