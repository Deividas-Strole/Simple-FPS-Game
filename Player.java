import java.awt.*;

public class Player {
    int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        // Keep player in bounds
        if (x < 0) x = 0;
        if (x > 780) x = 780;
        if (y < 0) y = 0;
        if (y > 560) y = 560;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);
    }
}