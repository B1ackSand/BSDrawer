package shape;

import java.awt.*;

/**
 * @author BlackSand
 */
public class RoundRect extends Shapes {
    public RoundRect() {
    }

    ;

    public RoundRect(int x1, int y1, int Width, int Height, int arcWidth, int arcHeight) {
        super(x1, y1, Width, Height, arcWidth, arcHeight);
    }


    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.drawRoundRect(x1, y1, Width, Height, 20, 20);
    }
}
