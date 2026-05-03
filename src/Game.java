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
    private int scoreTimer;
    private int highScore;
    private static final int SLEEP_TIME = 10;
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

        player = new Player(window);
        obstacles = new ArrayList<Obstacle>();

        projectiles = new ArrayList<Acorn>();
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

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<Acorn> getAcorns() {
        return projectiles;
    }

    public int getRandomSpawnTime() {
        // TODO: have spawn time gradually increase as the game goes on to increase difficulty
        // Obstacle spawns every 1 to 2 seconds randomly
        return 100 + (int)(Math.random() * 101);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameState == STATE_MAIN_GROUND) {
            actionPerformedGround();
        } else if (gameState == STATE_MAIN_FLYING) {
            // TODO: Implement flying state
        }
        window.repaint();
    }

    public void actionPerformedGround() {
        player.move();
        scoreTimer++;
        // Increment score every 0.1 seconds
        if (scoreTimer >= 10) {
            score++;
            scoreTimer = 0;
        }
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

        for (int i = 0; i < projectiles.size(); i++){
            Acorn a = projectiles.get(i);
            a.fire();
            if (a.isOffScreen(window.getWidth())){
                projectiles.remove(i);
                i--;
            }
        }
        checkGameOver();
    }

    // Return player
    public Player getPlayer(){
        return this.player;
    }
    // Jump button
    public void keyPressed(KeyEvent e){
        if (gameState == STATE_INSTR) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gameState = STATE_MAIN_GROUND;
            }
        } else if (gameState == STATE_MAIN_GROUND) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    player.jump();
                    window.repaint();
                    break;

                case KeyEvent.VK_DOWN:
                    player.duck();
                    window.repaint();
                    break;

                case KeyEvent.VK_SPACE:
                    projectiles.add(player.fireAcorn());
                    window.repaint();
                    break;
            }
        } else if (gameState == STATE_END) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                // NOTE: restartGame() isn't completely done yet
                restartGame();
            }
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
        // TODO: Complete once highScore and score logic implemented
        player = new Player(window);
        obstacles.clear();
        projectiles.clear();
        score = 0;
        scoreTimer = 0;
        obstacleSpawnTimer = 0;
        nextObstacleSpawnTime = getRandomSpawnTime();
        gameState = STATE_MAIN_GROUND;
    }

    public boolean checkCollisions() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle o = obstacles.get(i);
            if (player.getBounds().intersects(o.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void play() {
        gameState = STATE_INSTR;
        window.repaint();
    }

    public void spawnObstacle() {
        obstacles.add(new Obstacle(window));
    }

    public void checkGameOver() {
        if (checkCollisions()) {
            if (score > highScore) {
                highScore = score;
            }
            gameState = STATE_END;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
