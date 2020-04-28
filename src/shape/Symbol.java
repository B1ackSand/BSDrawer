package shape;

import common.SymbolType;


import java.awt.*;

public class Symbol {
    private int shapeType;
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;

    /**
     * shape有两点坐标和名称
     * 存储开始拖动时，符号的x,y与鼠标位置的距离
     **/
    private boolean isSelected = false;
    private double dxToPointer, dyToPointer;


    public Symbol(int shapeType, int x, int y, int width, int height, String text) {
        this.shapeType = shapeType;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
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
}