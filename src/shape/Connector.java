package shape;

import java.awt.*;

public class Connector extends Shape {
    public Connector() {
    }

    ;

    public Connector(int x1, int y1, int x2, int y2, int Width, int Height, String name, Color color) {
        super(x1, y1, x2, y2, Width, Height, name, color);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawArc(Math.abs((x2 + x1) / 2), Math.abs((y2 + y1) / 2), 10, 10, 0, 360);
    }
}
