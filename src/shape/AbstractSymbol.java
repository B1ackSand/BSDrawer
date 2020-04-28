package shape;

import java.awt.*;

/**
 * 所有图形符号的抽象父类
 * @author BlackSand
 */
public abstract class AbstractSymbol implements java.io.Serializable {
    /**
     * 绘制图形的抽象方法
     *
     * @param g
     */
    public abstract void draw(Graphics g);

    public abstract boolean containsPoint(double x, double y);

    public abstract boolean isSelected();
    public abstract void setSelected(boolean b);
}
