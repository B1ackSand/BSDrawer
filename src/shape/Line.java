package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author BlackSand
 */
public class Line extends Shapes {

    public Line() {
    }

    ;

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

}

