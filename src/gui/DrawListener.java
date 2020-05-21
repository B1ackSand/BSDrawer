package gui;


import shape.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


/**
 * @author BlackSand
 */
public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {

    public static boolean isDrag = false;
    private int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
    private Boolean flag1 = true;
    private String name;
    private Color color;
    private Graphics g;
    private ArrayList<Point> points;
    private Shapes[] shapesArray;
    private int index = 0;

    // 初始化画笔

    public void setGr(Graphics g) {
        this.g = g;
    }

    // 初始化图形数组

    public void setSp(Shapes[] shapesArray) {
        this.shapesArray = shapesArray;
    }



    //鼠标点击

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    //鼠标按下

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }


    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //鼠标释放创建图形
        {
            x2 = e.getX();
            y2 = e.getY();
            // 绘制直线
            if ("ROUNDRECT".equals(name)) {
                g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), 30, 30);
                Shapes roundRect = new RoundRect(x1, y1, x2, y2, 30, 30);
                shapesArray[index++] = roundRect;

            }
            if ("NORMALRECT".equals(name)) {
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shapes rect = new NormalRect(x1, y1, x2, y2, name, color);
                shapesArray[index++] = rect;
            }
            if ("DIAMOND".equals(name)) {
                int[] xPoints = {x1, (x1 + x2) / 2, x2, (x1 + x2) / 2};
                int[] yPoints = {(y1 + y2) / 2, y1, (y1 + y2) / 2, y2};
                g.drawPolygon(xPoints, yPoints, 4);
                Shapes diamond = new Diamond(xPoints, yPoints, 4, name, color);
                shapesArray[index++] = diamond;
            }

            if ("PARALLELOGRAM".equals(name) && flag1) {
                int[] xPoints = {x1 + 30, x2, x2 - 30, x1};
                int[] yPoints = {y1, y1, y2, y2};
                g.drawPolygon(xPoints, yPoints, 4);
                Shapes parallelogram = new Parallelogram(xPoints, yPoints, 4, name, color);
                shapesArray[index++] = parallelogram;

            }
            if ("ARROWLINE".equals(name) && flag1) {
                if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
                    g.drawLine(x2, y1, x2, y2);
                    Shapes line = new Line(x2, y1, x2, y2, name, color);
                    shapesArray[index++] = line;
                } else {
                    g.drawLine(x1, y1, x2, y1);
                    Shapes line = new Line(x1, y2, x2, y2, name, color);
                    shapesArray[index++] = line;
                }

            }
            if ("CONNECTOR".equals(name) && flag1) {
                g.drawArc(x1 - 10, y1 - 10, 10, 10, 0, 360);
                Shapes connector = new Connector(x1, y1, x2, y2, 10, 10, name, color);
                shapesArray[index++] = connector;
            }
            if ("BROKENLINE".equals(name) && flag1) {

            }
        }

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }


    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        //鼠标拖动
        if (isDrag == true) {
            x2=e.getX();
            y2=e.getY();
            g.translate(x2,y2);
            System.out.printf("%d %d\n", x1, y1);
        }
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    public void paintComponent(Graphics g) {

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


            if ("CURVERECT".equals(name)) {
                shapesArray = null;

            }
        }


    }
}