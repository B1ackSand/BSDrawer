package gui;


import shape.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;

public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {

    private int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
    private Boolean flag1 = true, flag2 = false;
    private String name;
    private Color color;
    private Graphics g;
    private Shape[] shapeArray;
    private int index = 0;

    // 初始化画笔

    public void setGr(Graphics g) {
        this.g = g;
    }

    // 初始化图形数组

    public void setSp(Shape[] shapeArray) {
        this.shapeArray = shapeArray;
    }

    //鼠标点击

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        flag2 = true;
        if (!flag1) {
            x5 = e.getX();
            y5 = e.getY();
            g.drawLine(x4, y4, x5, y5);
            //将直线存入数组
            Shape line = new Line(x4, y4, x5, y5, name, color);
            shapeArray[index++] = line;

            x4 = x5;
            y4 = y5;
        }
        //双击自动完成多边形闭合
        if (e.getClickCount() == 2) {
            g.drawLine(x5, y5, x3, y3);

            Shape line = new Line(x5, y5, x3, y3, name, color);
            shapeArray[index++] = line;
            flag1 = true;
        }

    }

    //鼠标按下

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        {
            x1 = e.getX();
            y1 = e.getY();
        }
    }

    //鼠标释放

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

        {
            x2 = e.getX();
            y2 = e.getY();
            // 绘制直线
            if ("ROUNDRECT".equals(name)) {
                g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), 30, 30);
                Shape roundRect = new RoundRect(x1, y1, x2, y2, 30, 30, name, color);
                shapeArray[index++] = roundRect;
            }
            if ("NORMALRECT".equals(name)) {
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shape rect = new NormalRect(x1, y1, x2, y2, name, color);
                shapeArray[index++] = rect;
            }
            if ("DIAMOND".equals(name)) {
                g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shape diamond = new Diamond(x1, y1, x2, y2, name, color);
                shapeArray[index++] = diamond;
            }

            if ("PARALLELOGRAM".equals(name) && flag1) {
                g.drawLine(x1, y1, x2, y2);

                Shape line = new Line(x1, y1, x2, y2, name, color);
                shapeArray[index++] = line;

                x3 = x1;
                y3 = y1;
                x4 = x2;
                y4 = y2;

                flag1 = false;
            }

        }

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    //鼠标拖动

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // 画笔重载需注意内存
        if ("画笔".equals(name)) {
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1, y1, x2, y2);
            Shape line = new Line(x1, y1, x2, y2, name, color);
            shapeArray[index++] = line;
            x1 = x2;
            y1 = y2;
        }
        if ("橡皮檫".equals(name)) {
            color = Color.white;
            g.setColor(color);
            //设置线宽
            ((Graphics2D) g).setStroke(new BasicStroke(20));
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1, y1, x2, y2);
            Shape eraser = new Eraser(x1, y1, x2, y2, name, color);
            shapeArray[index++] = eraser;
            x1 = x2;
            y1 = y2;
            color = Color.black;
            g.setColor(color);
            ((Graphics2D) g).setStroke(new BasicStroke(1));
        }
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("".equals(e.getActionCommand())) {
            // 获取当前事件源，并强制转换
            JButton jb = (JButton) e.getSource();
            // 将按钮背景色赋值给color
            color = jb.getBackground();
            // 设置画笔背景色
            // 注意：不能直接写成g.setColor(jb.getBackground());后面重绘时需用到color参数；
            g.setColor(color);
        } else {
            name = e.getActionCommand();

            if ("清屏".equals(name)) {
                color = Color.white;
                g.setColor(color);
                x1 = 0;
                y1 = 0;
                x2 = 900;
                y2 = 700;
                g.fillRect(x1, y1, x2, y2);

                // 重置多边形最后一条线段数据
                x3 = 0;
                y3 = 0;
                x5 = 0;
                y5 = 0;
            }
        }

        // 多边形切换设置
        flag1 = true;
        // 点击非清屏按钮，先完成多边形绘制
        if (!"".equals(e.getActionCommand()) && flag2) {
            g.drawLine(x5, y5, x3, y3);

            Shape line = new Line(x5, y5, x3, y3, name, color);
            shapeArray[index++] = line;

            flag2 = false;
        }
        // 点击颜色按钮继续画图
        if ("".equals(e.getActionCommand()) && flag2) {
            flag1 = false;
        }
    }
}