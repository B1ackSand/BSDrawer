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
    public static boolean clean = false;
    public static String text = null;
    private int x1, y1, x2, y2;
    private Boolean flag1 = true;
    private String name;
    private Color color;
    private Graphics2D g;
    private JFrame jf;
    private ArrayList<Point> points;
    private Shapes[] shapesArray;
    static int[] list_x = new int[200];
    static int[] list_y = new int[200];
    private int index = 0;


    // 初始化画笔

    public void setGr(Graphics g) {
        this.g = (Graphics2D) g;
    }

    // 初始化图形数组

    public void setSp(Shapes[] shapesArray) {
        this.shapesArray = shapesArray;
    }

    public void setjf(JFrame jf) {
        this.jf = jf;
    }


    //鼠标点击

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //鼠标按下

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        //鼠标释放创建图形
        {
            x2 = e.getX();
            y2 = e.getY();
            g.setColor(Color.BLACK);
            // 绘制直线
            if ("ROUNDRECT".equals(name) && flag1) {
                g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), 30, 30);
                Shapes roundRect = new RoundRect(x1, y1, x2, y2, 30, 30);
                shapesArray[index++] = roundRect;
            }

            if ("NORMALRECT".equals(name) && flag1) {
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

            if ("PARALLELOGRAM".equals(name)) {
                int[] xPoints = {x1 + 30, x2, x2 - 30, x1};
                int[] yPoints = {y1, y1, y2, y2};
                g.drawPolygon(xPoints, yPoints, 4);
                Shapes parallelogram = new Parallelogram(xPoints, yPoints, 4, name, color);
                shapesArray[index++] = parallelogram;
            }

            if ("LINE".equals(name)) {
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

            if ("CONNECTOR".equals(name)) {
                g.drawArc(x1 - 10, y1 - 10, 10, 10, 0, 360);
                Shapes connector = new Connector(x1, y1, x2, y2, 10, 10, name, color);
                shapesArray[index++] = connector;
            }

            if ("BROKENLINE".equals(name)) {
                int[] xPoints = {x1, x2, x2};
                int[] yPoints = {y1, y1, y2};
                g.drawPolyline(xPoints, yPoints, 3);
                Shapes brokenline = new BrokenLine(xPoints, yPoints, 3);
                shapesArray[index++] = brokenline;
            }

            if ("SETSTR".equals(name)) {
                g.setColor(Color.black);
                g.setFont(new Font(null, 0, 16));
                g.drawString(text, x2, y2);
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //鼠标进入绘图区域
        if (clean == true) {
            g.setColor(Color.white);
            x1 = 0;
            y1 = 0;
            x2 = 900;
            y2 = 700;
            g.fillRect(x1, y1, x2, y2);
            g.setColor(Color.black);
            clean = false;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        //鼠标拖动
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //鼠标移动

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
        }

        // 多边形切换设置
        flag1 = true;
    }

}