package shape;

import java.awt.Color;
import java.awt.Graphics;

public class Diamond extends Shape {
    public Diamond() {
    }

    ;

    public Diamond(int x1, int y1, int x2, int y2, String name, Color color) {
        super(x1, y1, x2, y2, name, color);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));

    }
}
