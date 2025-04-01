import java.awt.*;

public class Enemy {
    int x, y;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x -= 2; // Move left slowly
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 20, 20);
    }
}