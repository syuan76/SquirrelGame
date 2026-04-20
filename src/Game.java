import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Acorn> projectiles;
    private int gameState;
    private int score;
    private static final int SLEEP_TIME = 50;
    private int background;
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
        window.addKeyListener(this);

        player = new Player();
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
        player.move();

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

    // Return player
    public Player getPlayer(){
        return this.player;
    }
    // Jump button
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                player.jump();
                window.repaint();
                break;

            case KeyEvent.VK_DOWN:
                player.duck();
                window.repaint();
                break;
        }
        window.repaint();
    }

    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                player.stand();
                window.repaint();
                break;
        }
    }

    public void keyTyped(KeyEvent e){

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
