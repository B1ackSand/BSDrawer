package shape;

/**
 * @author BlackSand
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shapes {
    public int x1, y1, x2, y2;
    public int[] xPoint;
    public int[] yPoint;
    public int Height, Width;
    public int n;
    public Graphics g;
    int arcWidth;
    int arcHeight;
    public Color color;

    public Shapes() {
    }


    public Shapes(int x1, int y1, int Width, int Height, int arcWidth, int arcHeight) {
        this.x1=x1;
        this.y1=y1;
        this.Width=Width;
        this.Height=Height;
        this.arcWidth=arcWidth;
        this.arcHeight=arcHeight;
    }

    public Shapes(int[] xPoint, int[] yPoint, int n) {
        this.xPoint=xPoint;
        this.yPoint=yPoint;
        this.n=n;
    }

    public Shapes(int x1, int y1, int x2, int y2) {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }

    public void drawShape(Graphics g) {
        this.g=g;
    }
}