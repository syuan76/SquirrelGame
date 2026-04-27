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
    private int score;
    private static final int SLEEP_TIME = 50;
    private boolean isGameOver;
    private int obstacleSpawnTimer;
    private int nextObstacleSpawnTime;

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

        obstacleSpawnTimer = 0;
        nextObstacleSpawnTime = getRandomSpawnTime();

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

    public int getRandomSpawnTime() {
        // Obstacle spawns every 1.5 to 4 seconds randomly
        return 30 + (int)(Math.random() * 31);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
        obstacleSpawnTimer++;
        if (obstacleSpawnTimer >= nextObstacleSpawnTime) {
            spawnObstacle();
            obstacleSpawnTimer = 0;
            nextObstacleSpawnTime = getRandomSpawnTime();
        }
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle o = obstacles.get(i);
            o.move();
            if (o.isOffScreen()) {
                obstacles.remove(i);
                // Account for the fact that removing an element would skip over an index
                i--;
            }
        }
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).isOffScreen()) {
                obstacles.remove(i);
                i--;
            }
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
        obstacles.add(new Obstacle(window));
    }

    public void checkGameOver() {
        // TODO
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
