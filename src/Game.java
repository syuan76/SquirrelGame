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
    private int gameState;
    private int background;
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
        window = new GameView(this);
        window.addKeyListener(this);

        player = new Player();

        Timer clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    // Return player
    public Player getPlayer(){
        return this.player;
    }
    // Jump button
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                player.jump();
                window.repaint();
                System.out.println("SPACE");
        }
        window.repaint();
    }

    public void keyReleased(KeyEvent e){

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
    }

    public void spawnObstacle() {
        // TODO
    }

    public void checkGameOver() {
        // TODO
    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}
