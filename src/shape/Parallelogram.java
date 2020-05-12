package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author BlackSand
 */
public class Parallelogram extends Shape {
    public Parallelogram() {
    }

    ;

    public Parallelogram(int[] xPoint, int[] yPoint, int n,String name, Color color) {
        super(xPoint, yPoint, n,name, color);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawPolygon(xPoint, yPoint, 4);

    }
}