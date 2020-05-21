package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author BlackSand
 */
public class Parallelogram extends Shapes {
    public Parallelogram() {
    }

    ;

    public Parallelogram(int[] xPoint, int[] yPoint, int n) {
        super(xPoint, yPoint, n);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawPolygon(xPoint, yPoint, 4);

    }
}