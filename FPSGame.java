import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FPSGame extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private boolean[] keys = new boolean[256]; // Track key presses

    public FPSGame() {
        setFocusable(true);
        addKeyListener(this);
        player = new Player(50, 300); // Player starts near left side
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        spawnEnemies();

        // Game loop timer (runs at ~60 FPS)
        timer = new Timer(16, this);
        timer.start();
    }

    private void spawnEnemies() {
        // Spawn 5 enemies on the right side
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(800, 100 + i * 100));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Bullet bullet : bullets) bullet.draw(g);
        for (Enemy enemy : enemies) enemy.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    private void updateGame() {
        // Player movement
        if (keys[KeyEvent.VK_W]) player.move(0, -5);
        if (keys[KeyEvent.VK_S]) player.move(0, 5);
        if (keys[KeyEvent.VK_A]) player.move(-5, 0);
        if (keys[KeyEvent.VK_D]) player.move(5, 0);
        if (keys[KeyEvent.VK_SPACE]) shoot();

        // Update bullets
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.update();
            if (b.x > 800) bullets.remove(i); // Remove off-screen bullets
        }

        // Update enemies
        for (Enemy enemy : enemies) enemy.update();

        // Check collisions
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy e = enemies.get(j);
                if (b.collidesWith(e)) {
                    bullets.remove(i);
                    enemies.remove(j);
                    break;
                }
            }
        }
    }

    private void shoot() {
        bullets.add(new Bullet(player.x + 20, player.y));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple FPS Game");
        FPSGame game = new FPSGame();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}