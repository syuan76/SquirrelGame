import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame{
    private Game backend;
    private Image background;
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;
    private final int PLATFORMER_HEIGHT = 670;

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
        // TODO: If time permits, replace instructions window with one designed on Canva
        g.setColor(Color.WHITE);
        g.fillRect(0, 0 , WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("SQUIRREL GAME", 400, 250);
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString("Press ENTER to start", 450, 350);
        g.drawString("UP = jump", 500, 400);
        g.drawString("DOWN = duck", 500, 440);
        g.drawString("SPACE = fire acorn", 500, 480);
    }

    public void drawBackgroundPlatform(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Score: " + backend.getScore(), 30, 55);
        g.drawString("High Score: " + backend.getHighScore(), 30, 85);
    }

    public void drawGameOver(Graphics g) {
        // TODO: If time permits, replace instructions window with one designed on Canv
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 52));
        g.drawString("GAME OVER", 430, 320);
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString("Press R to restart", 470, 390);
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
        if (backend.getGameState() == Game.STATE_INSTR) {
            drawInstructions(g);
        } else if (backend.getGameState() == Game.STATE_MAIN_GROUND) {
            drawBackgroundPlatform(g);
            drawScore(g);
            // Draw Snake Obstacles
            for (int i = 0; i < backend.getObstacleSnakes().size(); i++) {
                backend.getObstacleSnakes().get(i).draw(g);
            }
            // Draw Owl Obstacles
            for (int i = 0; i < backend.getObstacleOwls().size(); i++) {
                backend.getObstacleOwls().get(i).draw(g);
            }
            // Draw acorns
            for (int i = 0; i < backend.getAcorns().size(); i++){
                backend.getAcorns().get(i).draw(g);
            }
            // Draw Player
            backend.getPlayer().draw(g);
        } else if (backend.getGameState() == Game.STATE_END) {
            drawGameOver(g);
        }

    }
}
