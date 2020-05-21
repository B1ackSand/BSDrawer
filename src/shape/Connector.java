package shape;

import java.awt.*;

/**
 * @author BlackSand
 */
public class Connector extends Shapes {
    public Connector() {
    }

    ;

    public Connector(int x1, int y1, int x2, int y2, int Width, int Height) {
        super(x1, y1, x2, y2, Width, Height);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawArc(Math.abs((x2 + x1) / 2), Math.abs((y2 + y1) / 2), 10, 10, 0, 360);
    }
}
