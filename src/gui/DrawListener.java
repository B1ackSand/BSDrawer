package gui;


import shape.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * @author BlackSand
 * 监听类 主要负责图形的绘制，按钮监听以及画布清空
 */
public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {

    //初始化
    public static boolean isDrag = false;
    public static boolean clean = false;
    public static boolean areaclean = false;
    public static String text = "请修改内容";
    private int x1, y1, x2, y2;
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


    @Override
    public void mouseClicked(MouseEvent e) {
        //鼠标在绘图区域点击
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //鼠标在绘图区域按下
        //修正获取的坐标位于鼠标尖上
        x1 = e.getX() - 5;
        y1 = e.getY() - 5;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        //鼠标释放在绘图区域创建图形
        {
            x2 = e.getX() - 5;
            y2 = e.getY() - 5;
            g.setColor(Color.BLACK);
            // 绘制各图形
            if ("ROUNDRECT".equals(name) && areaclean == false) {
                g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), 30, 30);
                Shapes roundRect = new RoundRect(x1 - 5, y1 - 5, x2 - 5, y2 - 5, 30, 30);
                shapesArray[index++] = roundRect;
            }

            if ("NORMALRECT".equals(name) && areaclean == false) {
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                Shapes rect = new NormalRect(x1, y1, x2, y2);
                shapesArray[index++] = rect;
            }

            if ("DIAMOND".equals(name) && areaclean == false) {
                int[] xPoints = {x1, (x1 + x2) / 2, x2, (x1 + x2) / 2};
                int[] yPoints = {(y1 + y2) / 2, y1, (y1 + y2) / 2, y2};
                g.drawPolygon(xPoints, yPoints, 4);
                Shapes diamond = new Diamond(xPoints, yPoints, 4);
                shapesArray[index++] = diamond;
            }

            if ("PARALLELOGRAM".equals(name) && areaclean == false) {
                int[] xPoints = {x1 + 30, x2, x2 - 30, x1};
                int[] yPoints = {y1, y1, y2, y2};
                g.drawPolygon(xPoints, yPoints, 4);
                Shapes parallelogram = new Parallelogram(xPoints, yPoints, 4);
                shapesArray[index++] = parallelogram;
            }

            if ("LINE".equals(name) && areaclean == false) {
                if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
                    g.drawLine(x2, y1, x2, y2);
                    Shapes line = new Line(x1, y1, x2, y2);
                    shapesArray[index++] = line;
                } else {
                    g.drawLine(x1, y1, x2, y1);
                    Shapes line = new Line(x1, y1, x2, y2);
                    shapesArray[index++] = line;
                }
            }

            if ("CONNECTOR".equals(name) && areaclean == false) {
                g.drawArc(x1 - 5, y1 - 5, 10, 10, 0, 360);
                Shapes connector = new Connector(x1, y1, x2, y2, 10, 10);
                shapesArray[index++] = connector;
            }

            if ("BROKENLINE".equals(name) && areaclean == false) {
                int[] xPoints = {x1, x2, x2};
                int[] yPoints = {y1, y1, y2};
                g.drawPolyline(xPoints, yPoints, 3);
                Shapes brokenline = new BrokenLine(xPoints, yPoints, 3);
                shapesArray[index++] = brokenline;
            }

            if ("SETSTR".equals(name) && areaclean == false) {
                FontMetrics fm = g.getFontMetrics();
                g.setColor(Color.black);
                g.setFont(new Font(null, 0, 16));
                g.drawString(text, x2 - (fm.stringWidth(text) / 2), y2 + 5);
            }

            if (areaclean == true) {
                g.setColor(Color.white);
                g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                g.setColor(Color.black);
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
        //鼠标离开绘图区域
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        //鼠标在绘图区域拖动

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //鼠标在绘图区域移动

    }

    public void paintComponent(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("".equals(e.getActionCommand())) {
            // 获取当前事件源，并强制转换
            JButton jb = (JButton) e.getSource();
        } else {
            name = e.getActionCommand();
        }
    }
}