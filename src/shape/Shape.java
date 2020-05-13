package shape;

/**
 * @author BlackSand
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    public int x1, y1, x2, y2;
    public int[] xPoint;
    public int[] yPoint;
    public int Height, Width;
    public int n;
    int arcWidth;
    int arcHeight;
    public String name;
    public Color color;

    public Shape() {
    }

    ;

    public Shape(int x1, int y1, int x2, int y2, String name, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.name = name;
        this.color = color;
    }

    public Shape(int x1, int y1, int x2, int y2, int Width, int Height, String name, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.Height = Height;
        this.Width = Width;
        this.name = name;
        this.color = color;
    }

    public Shape(int[] xPoint, int[] yPoint, int n, String name, Color color) {
        this.xPoint=xPoint;
        this.yPoint=yPoint;
        this.n=n;
        this.name=name;
        this.color=color;
    }

    public Shape(int x1, int y1, int Width,int Height,int arcWidth, int arcHeight) {
        this.x1=x1;
        this.y1=y1;
        this.Width=Width;
        this.Height=Height;
        this.arcWidth=arcWidth;
        this.arcHeight=arcHeight;
    }

    public void drawShape(Graphics g) {

    }
}