import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Game implements ActionListener {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Acorn> projectiles;
    private int gameState;
    private int timer;
    private static final int SLEEP_TIME = 50;
    private boolean isGameOver;

    private GameView window;

    public static final int STATE_INSTR = 0;
    public static final int STATE_MAIN_GROUND = 1;
    public static final int STATE_MAIN_FLYING = 2;
    public static final int STATE_END = 3;

    public Game() {
        // TODO: complete constructor
        isGameOver = false;
        window = new GameView(this);
        obstacles = new ArrayList<Obstacle>();
        for (int i = 0; i < 50; i++) {
            obstacles.add(new Obstacle(window));
        }

        Timer clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).move();
        }
        window.repaint();
    }

    public void restartGame() {
        // TODO
    }

    public void checkCollisions() {
        // TODO
    }

    public void play() {
        // TODO
        gameState = STATE_MAIN_GROUND;
        window.repaint();
    }

    public void spawnObstacle() {
        // TODO
    }

    public void checkGameOver() {
        // TODO
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
