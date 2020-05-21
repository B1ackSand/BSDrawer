package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author BlackSand
 */
public class Diamond extends Shapes {
    public Diamond() {
    }

    ;

    public Diamond(int[] xPoint, int[] yPoint, int n, String name, Color color) {
        super(xPoint, yPoint, n, name, color);
    }


    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawPolygon(xPoint, yPoint, 4);

    }
}
