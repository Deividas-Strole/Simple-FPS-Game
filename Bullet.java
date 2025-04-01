import java.awt.*;

public class Bullet {
    int x, y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += 10; // Move right
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 10, 5);
    }

    public boolean collidesWith(Enemy e) {
        return x + 10 > e.x && x < e.x + 20 && y + 5 > e.y && y < e.y + 20;
    }
}