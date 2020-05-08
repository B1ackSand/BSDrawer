package shape;

import java.awt.*;

/**
 * @author BlackSand
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    public int x1, y1, x2, y2;
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

    public void drawShape(Graphics g) {

    }
}


/*
    public boolean containsPoint(double x, double y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }


    public void draw(Graphics g) {
        switch (shapeType) {
            // 处理框
            case SymbolType.ROUNDRECT:
                g.fillRoundRect(x, y, width, height, height / 2, height / 2);
                g.drawRoundRect(x, y, width, height, height / 2, height / 2);
                g.drawString(text, x + width / 2, y + height / 2);
                break;
            // 开始结束框
            case SymbolType.NORMALRECT:
                g.fillRect(x, y, width, height);
                g.drawRect(x, y, width, height);
                g.drawString(text, x + width / 2, y + height / 2);
                break;
            // 条件框
            case SymbolType.DIAMOND:
                int[] xPoints = {x + width / 2, x + width, x + width / 2, x,};
                int[] yPoints = {y, y + height / 2, y + height, y + height / 2,};
                g.fillPolygon(xPoints, yPoints, 4);
                g.drawPolygon(xPoints, yPoints, 4);
                g.drawString(text, x + width / 2, y + height / 2);
                break;
            default:
                ;
        }

    }

*/

/**
 * setter and getter
 */
/*
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDxToPointer(double dxToPointer) {
        this.dxToPointer = dxToPointer;
    }

    public void setDyToPointer(double dyToPointer) {
        this.dyToPointer = dyToPointer;
    }

    public int getShapeType() {
        return shapeType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public double getDxToPointer() {
        return dxToPointer;
    }

    public double getDyToPointer() {
        return dyToPointer;
    }
}
*/