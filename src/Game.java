import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Acorn> projectiles;
    private ArrayList<AcornToCollect> acorns;
    private int gameState;
    private int score;
    private int scoreTimer;
    private int highScore;
    private static final int SLEEP_TIME = 10;
    private int obstacleSpawnTimer;
    private int nextObstacleSpawnTime;
    private int acornSpawnTimer;
    private int nextAcornSpawnTime;

    private GameView window;

    public static final int STATE_COVER = 0;
    public static final int STATE_INSTR = 1;
    public static final int STATE_MAIN_GROUND = 2;
    public static final int STATE_END = 3;

    public Game() {
        // TODO: complete constructor
        window = new GameView(this);
        window.addKeyListener(this);

        player = new Player(window);
        obstacles = new ArrayList<Obstacle>();
        acorns = new ArrayList<AcornToCollect>();

        projectiles = new ArrayList<Acorn>();
        obstacleSpawnTimer = 0;
        nextObstacleSpawnTime = getRandomObstacleSpawnTime();

        acornSpawnTimer = 0;
        nextAcornSpawnTime = getRandomAcornSpawnTime();

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

    public ArrayList<AcornToCollect> getAcornsToCollect(){return acorns;}

    public int getRandomObstacleSpawnTime() {
        // Obstacle spawns every 1 to 2 seconds randomly
        return 100 + (int)(Math.random() * 101);
    }

    public int getRandomAcornSpawnTime() {
        // Acorn spawns every 4 to 7 seconds randomly. Less frequent than obstacles, since acorns shouldn't be so easy to collect.
        return 400 + (int)(Math.random() * 301);
    }

    public void spawnObstacles() {
        obstacleSpawnTimer++;

        if (obstacleSpawnTimer >= nextObstacleSpawnTime) {
            if (score < 50) {
                spawnSnake();
            } else if (score < 100) {
                spawnOwl();
            } else {
                int random = (int)(Math.random()*2);
                if (random == 0) {
                    spawnSnake();
                } else {
                    spawnOwl();
                }
            }
            obstacleSpawnTimer = 0;
            nextObstacleSpawnTime = getRandomObstacleSpawnTime();
        }
    }

    public void moveObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle o = obstacles.get(i);
            o.move();
            if (o.isOffScreen() || o.isDead()) {
                obstacles.remove(i);
                // Account for the fact that removing an element would skip over an index
                i--;
            }
        }
    }

    public void spawnCollectibleAcorns() {
        acornSpawnTimer++;
        // Spawn acorns to collect
        if (acornSpawnTimer >= nextAcornSpawnTime){
            spawnAcorn();
            acornSpawnTimer = 0;
            nextAcornSpawnTime = getRandomAcornSpawnTime();
        }

        // Move acorns
        for (int i = 0; i < acorns.size(); i++){
            AcornToCollect a = acorns.get(i);
            a.move();
            if(a.isOffScreen() || a.isCollected()){
                acorns.remove(i);
                i--;
            }
        }
    }

    public void moveProjectiles() {
        for (int i = 0; i < projectiles.size(); i++){
            Acorn a = projectiles.get(i);
            a.fire();
            if (a.isOffScreen(window.getWidth())){
                projectiles.remove(i);
                i--;
            }
        }
    }

    public void incrementScore() {
        scoreTimer++;
        // Increment score every 0.1 seconds
        if (scoreTimer >= 10) {
            score++;
            scoreTimer = 0;
        }
    }

    public void actionPerformed(ActionEvent e) {
        // This abstraction will allow us to easily implement other states, such as flying, in the future.
        if (gameState == STATE_MAIN_GROUND) {
            actionPerformedGround();
        }
        window.repaint();
    }

    public void actionPerformedGround() {
        player.move();

        incrementScore();

        spawnObstacles();
        moveObstacles();
        moveProjectiles();
        spawnCollectibleAcorns();

        checkGameOver();

        window.repaint();
    }

    // Return player
    public Player getPlayer(){
        return this.player;
    }
    // Jump button
    public void keyPressed(KeyEvent e){
        if (gameState == STATE_COVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gameState = STATE_INSTR;
                window.repaint();
            }
        } else if (gameState == STATE_INSTR) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gameState = STATE_MAIN_GROUND;
                window.repaint();
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
                    if (player.getAcornAmount() > 0) {
                        projectiles.add(player.fireAcorn());
                        player.subtractAcorn();
                    }
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
        // Unused, but required because Game implements KeyListener
    }
    public void restartGame() {
        player = new Player(window);
        obstacles.clear();
        projectiles.clear();
        acorns.clear();
        score = 0;
        scoreTimer = 0;
        obstacleSpawnTimer = 0;
        nextObstacleSpawnTime = getRandomObstacleSpawnTime();
        acornSpawnTimer = 0;
        nextAcornSpawnTime = getRandomAcornSpawnTime();
        gameState = STATE_MAIN_GROUND;
    }

    public boolean checkCollisions() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle o = obstacles.get(i);
            if (player.getBounds().intersects(o.getBounds())) {
                return true;
            }
        }

        // Check acorn collisions
        for (int i = 0; i < projectiles.size(); i++){
            Acorn a = projectiles.get(i);
            for (int j = 0; j < obstacles.size(); j++){
                Obstacle o = obstacles.get(j);
                if (a.getBounds().intersects(o.getBounds())){
                    o.hit();
                    projectiles.remove(i);
                    i--;
                    break;
                }
            }

        }

        // Check if Acorn is collected
        for (int i = 0; i < acorns.size(); i++){
            AcornToCollect a = acorns.get(i);
            if (player.getBounds().intersects(a.getBounds())){
                a.hit();
                acorns.remove(i);
                player.addAcorn();
                i--;
                break;
            }
        }
        return false;
    }

    public void play() {
        gameState = STATE_COVER;
        window.repaint();
    }

    public void spawnSnake() {
        obstacles.add(new Obstacle(window, "Resources/Snake.png", 75, 100, window.getPLATFORMER_HEIGHT() - 100));
    }

    public void spawnOwl() {
        obstacles.add(new Obstacle(window, "Resources/Owl.png", 100, 75, window.getPLATFORMER_HEIGHT() - 150));
    }

    public void spawnAcorn(){
        acorns.add(new AcornToCollect(window));
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
