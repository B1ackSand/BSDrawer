package shape;

import java.awt.*;

/**
 * @author BlackSand
 */
public class RoundRect extends Shape {
    public RoundRect() {
    }

    ;

    public RoundRect(int x1, int y1, int x2, int y2, int arcWidth, int arcHeight, String name, Color color) {
        super(x1, y1, x2, y2, arcWidth, arcHeight, name, color);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), 30, 30);
    }
}
