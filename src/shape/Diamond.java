package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author BlackSand
 */
public class Diamond extends Shape {
    public Diamond() {
    }

    ;

    public Diamond(int[] xPoint, int[] yPoint, String name, Color color) {
        super(xPoint, yPoint, name, color);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawPolygon(xPoint, yPoint, 4);

    }
}