package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author BlackSand
 */
public class BrokenLine extends Shapes {
    public BrokenLine() {
    }

    ;

    public BrokenLine(int[] xPoint, int[] yPoint, int n) {
        super(xPoint, yPoint, n);
    }


    public void drawshape(Graphics g) {
        g.setColor(color);
        g.drawPolyline(xPoint, yPoint, 3);

    }
}
