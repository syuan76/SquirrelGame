import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame{
    private Game backend;
    private Image coverImage;
    private Image instructions;
    private Image background;
    private Image gameOver;
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;
    private final int PLATFORMER_HEIGHT = 670;

    public GameView(Game backend) {
        // TODO: complete constructor
        this.backend = backend;

        this.coverImage = new ImageIcon("Resources/CoverImage.png").getImage();
        this.instructions = new ImageIcon("Resources/Instructions.png").getImage();
        this.background = new ImageIcon("Resources/PlatformerBackground.jpg").getImage();
        this.gameOver = new ImageIcon("Resources/GameOver.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SQUIRREL RUN");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public int getPLATFORMER_HEIGHT() {
        return PLATFORMER_HEIGHT;
    }

    public void drawCoverImage(Graphics g) {
        g.drawImage(coverImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void drawInstructions(Graphics g) {
        // TODO: If time permits, replace instructions window with one designed on Canva

        g.drawImage(instructions, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
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

    public void drawAcorns(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Acorns: " + backend.getPlayer().getAcornAmount(), 1000, 55);
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
        g.drawImage(gameOver, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        // Draws the score in the "SCORE: " slot of the gameOver image
        g.drawString("" + backend.getScore(), 650, 372);
        // Draws the high score in the "HIGH SCORE: " slot of the gameOver image
        g.drawString("" + backend.getHighScore(), 650, 470);
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
        if (backend.getGameState() == Game.STATE_COVER) {
            drawCoverImage(g);
        } else if (backend.getGameState() == Game.STATE_INSTR) {
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
            drawAcorns(g);
            // Draw Obstacles
            for (int i = 0; i < backend.getObstacles().size(); i++) {
                backend.getObstacles().get(i).draw(g);
            }
            // Draw acorns
            for (int i = 0; i < backend.getAcorns().size(); i++){
                backend.getAcorns().get(i).draw(g);
            }
            // Draw acorns to collect
            for (int i = 0; i < backend.getAcornsToCollect().size(); i++){
                backend.getAcornsToCollect().get(i).draw(g);
            }
            // Draw Player
            backend.getPlayer().draw(g);
        } else if (backend.getGameState() == Game.STATE_END) {
            drawGameOver(g);
        }

    }
}
