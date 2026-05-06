import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {
    private Player player;
    private ArrayList<ObstacleSnake> obstacleSnakes;
    private ArrayList<ObstacleOwl> obstacleOwls;
    private ArrayList<Acorn> projectiles;
    private ArrayList<AcornToCollect> acorns;
    private int gameState;
    private int score;
    private int scoreTimer;
    private int highScore;
    private static final int SLEEP_TIME = 10;
    private boolean isGameOver;
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
        isGameOver = false;
        window = new GameView(this);
        window.addKeyListener(this);

        player = new Player(window);
        obstacleSnakes = new ArrayList<ObstacleSnake>();
        obstacleOwls = new ArrayList<ObstacleOwl>();
        acorns = new ArrayList<AcornToCollect>();

        projectiles = new ArrayList<Acorn>();
        obstacleSpawnTimer = 0;
        nextObstacleSpawnTime = getRandomSpawnTime();

        acornSpawnTimer = 0;
        nextAcornSpawnTime = getRandomSpawnTime();

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

    public ArrayList<ObstacleSnake> getObstacleSnakes() {
        return obstacleSnakes;
    }

    public ArrayList<ObstacleOwl> getObstacleOwls() {
        return obstacleOwls;
    }

    public ArrayList<Acorn> getAcorns() {
        return projectiles;
    }

    public ArrayList<AcornToCollect> getAcornsToCollect(){return acorns;}

    public int getRandomSpawnTime() {
        // TODO: have spawn time gradually increase as the game goes on to increase difficulty
        // Obstacle spawns every 1 to 2 seconds randomly
        return 100 + (int)(Math.random() * 101);
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
            nextObstacleSpawnTime = getRandomSpawnTime();
        }
    }

    public void moveSnakes() {
        for (int i = 0; i < obstacleSnakes.size(); i++) {
            ObstacleSnake o = obstacleSnakes.get(i);
            o.move();
            if (o.isOffScreen() || o.isDead()) {
                obstacleSnakes.remove(i);
                // Account for the fact that removing an element would skip over an index
                i--;
            }
        }
    }

    public void moveOwls() {
        for (int i = 0; i < obstacleOwls.size(); i++) {
            ObstacleOwl o = obstacleOwls.get(i);
            o.move();
            if (o.isOffScreen() || o.isDead()) {
                obstacleOwls.remove(i);
                // Account for the fact that removing an element would skip over an index
                i--;
            }
            checkGameOver();
        }
        acornSpawnTimer++;
        // Spawn acorns to collect
        if (acornSpawnTimer >= nextAcornSpawnTime){
            spawnAcorn();
            acornSpawnTimer = 0;
            nextAcornSpawnTime = getRandomSpawnTime();
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

    public void spawnProjectiles() {
        for (int i = 0; i < projectiles.size(); i++){
            Acorn a = projectiles.get(i);
            a.fire();
            if (a.isOffScreen(window.getWidth())){
                projectiles.remove(i);
                i--;
            }
            checkCollisions();
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
        moveSnakes();
        moveOwls();
        spawnProjectiles();

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
        // TODO: remove if unused
    }
    public void restartGame() {
        player = new Player(window);
        obstacleSnakes.clear();
        obstacleOwls.clear();
        projectiles.clear();
        score = 0;
        scoreTimer = 0;
        obstacleSpawnTimer = 0;
        nextObstacleSpawnTime = getRandomSpawnTime();
        acornSpawnTimer = 0;
        nextAcornSpawnTime = getRandomSpawnTime();
        gameState = STATE_MAIN_GROUND;
    }

    public boolean checkCollisions() {
        // Check snake collisions
        for (int i = 0; i < obstacleSnakes.size(); i++) {
            ObstacleSnake o = obstacleSnakes.get(i);
            if (player.getBounds().intersects(o.getBounds())) {
                return true;
            }
        }

        // Check owl collisions
        for (int i = 0; i < obstacleOwls.size(); i++) {
            ObstacleOwl o = obstacleOwls.get(i);
            if (player.getBounds().intersects(o.getBounds())) {
                return true;
            }
        }

        // Check acorn collisions
        for (int i = 0; i < projectiles.size(); i++){
            Acorn a = projectiles.get(i);
            for (int j = 0; j < obstacleSnakes.size(); j++){
                ObstacleSnake o = obstacleSnakes.get(j);
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
        obstacleSnakes.add(new ObstacleSnake(window));
    }

    public void spawnOwl() {
        obstacleOwls.add(new ObstacleOwl(window));
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
