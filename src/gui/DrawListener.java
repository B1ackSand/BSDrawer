package gui;


import shape.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;


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
                g.drawRoundRect(x2, y2, 200, 50, 30, 30);
                Shape roundRect = new RoundRect(x1, y1, x2, y2, 30, 30, name, color);
                shapeArray[index++] = roundRect;

            }
            if ("NORMALRECT".equals(name)) {
                g.drawRect(x2, y2, 200, 50);
                Shape rect = new NormalRect(x1, y1, x2, y2, name, color);
                shapeArray[index++] = rect;
            }
            if ("DIAMOND".equals(name)) {
                int[] xPoints = {x2, x2 + 60, x2 + 120, x2 + 60};
                int[] yPoints = {y2, y2 - 40, y2, y2 + 40};
                g.drawPolygon(xPoints, yPoints, 4);
                Shape diamond = new Diamond(xPoints, yPoints, 4, name, color);
                shapeArray[index++] = diamond;
            }

            if ("PARALLELOGRAM".equals(name) && flag1) {
                int[] xPoints = {x2 + 20, x2 + 80, x2 + 60, x2};
                int[] yPoints = {y2, y2, y2 + 40, y2 + 40};
                g.drawPolygon(xPoints, yPoints, 4);
                Shape parallelogram = new Parallelogram(xPoints, yPoints, 4, name, color);
                shapeArray[index++] = parallelogram;

            }
            if ("ARROWLINE".equals(name) && flag1) {
                g.drawLine(x1, y1, x2, y2);
                Shape line = new Line(x1, y1, x2, y2, name, color);
                shapeArray[index++] = line;
            }
            if ("CONNECTOR".equals(name) && flag1) {
                g.drawArc(x2, y2, 10, 10, 0, 360);
                Shape connector = new Connector(x1, y1, x2, y2, 10, 10, name, color);
                shapeArray[index++] = connector;
            }
            if ("CURVERECT".equals(name) && flag1) {

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
                x2 = 1080;
                y2 = 660;
                g.fillRect(x1, y1, x2, y2);

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

    }
}