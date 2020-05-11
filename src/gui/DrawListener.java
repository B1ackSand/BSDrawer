package gui;


import shape.*;
import shape.Shape;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;

public class DrawListener extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

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

        /*
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
        */
    }

    //鼠标按下

    @Override
    public void mousePressed(MouseEvent e) {
        {
            x1 = e.getX();
            y1 = e.getY();
        }
    }

    //鼠标释放

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    /*
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
            if ("ARROWLINE".equals(name) && flag1) {
                g.drawLine(x1, y1, x2, y2);
                Shape line = new Line(x1, y1, x2, y2, name, color);
                shapeArray[index++] = line;
            }
            if ("CONNECTOR".equals(name) && flag1) {
                g.drawArc(Math.abs((x2+x1)/2), Math.abs((y2+y1)/2), 10, 10, 0, 360);
                Shape connector = new Connector(x1, y1, x2, y2, 10,10,name, color);
                shapeArray[index++] = connector;
            }
            if("CURVERECT".equals(name)&&flag1){

            }
        }
    */
    }

    //鼠标拖动

    @Override
    public void mouseDragged(MouseEvent e) {
        x1 = e.getX() - 10;
        y1 = e.getY() + 28;
        repaint();
        /*
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
        if ("Eraser".equals(name)) {
            color = Color.white;
            g.setColor(color);
            //设置线宽
            ((Graphics2D) g).setStroke(new BasicStroke(20));
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1, y1, x2, y2);
            Shape eraser = new Eraser(x1, y1, x2, y2, name, color);s
            shapeArray[index++] = eraser;
            x1 = x2;
            y1 = y2;
            color = Color.black;
            g.setColor(color);
            ((Graphics2D) g).setStroke(new BasicStroke(1));
        }
        */
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}