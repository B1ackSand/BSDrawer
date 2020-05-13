package gui;

import shape.RoundRect;
import shape.Shape;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.*;

import static common.Path.*;

/**
 * @author BlackSand
 */
public class MouseComponent extends JComponent implements MouseMotionListener {
    private static int n = 0;
    private Rectangle rect;
    private boolean isTopLeft;
    private boolean isTop;
    private boolean isTopRight;
    private boolean isRight;
    private boolean isBottomRight;
    private boolean isBottom;
    private boolean isBottomLeft;
    private boolean isDrag;
    private boolean isLeft;
    private Graphics g;
    private int x,y;
    private Shape[] shapeArray;
    private int index = 0;
    //矩形最小宽和高为多少
    private final static int RESIZE_WIDTH = 5;
    private final static int MIN_WIDTH = 20;
    private final static int MIN_HEIGHT = 20;

    //单击增加的矩形大小
    private static final int SIDELENGTH = 50;
    //所有的矩形
    private ArrayList<Rectangle> squares;


    public MouseComponent() {
        this.squares = new ArrayList<Rectangle>();
        this.rect = null;
        addMouseMotionListener(this);
        // 监听鼠标点击事件
        this.addMouseListener(new MouseHandle());
        //this.addMouseMotionListener(new MouseMotionHandler());// 监听鼠标移动事件
    }

    // 如果鼠标单击屏幕的地方有矩形则不绘制矩形
    public Rectangle find(Point2D p) {
        for (Rectangle r : squares) {
            if (r.contains(p)) {
                return r;
            }
        }
        return null;
    }


    // 绘制图形，矩形
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (n == 2) {
            for (Rectangle r : squares) {
                g2.draw(r);
            }
        }
    }

    // 添加图形，矩形到屏幕
    public void add(Point2D p) {

        if (n == 2) {
            double x = p.getX();
            double y = p.getY();
            rect = new Rectangle((int) (x - SIDELENGTH / 2), (int) (y - SIDELENGTH / 2), SIDELENGTH, SIDELENGTH);
            squares.add(rect);
            repaint();
        }
    }

    // 删除矩形
    public void remove(Rectangle s) {
        if (s == null) {
            return;
        }
        if (s == rect) {
            rect = null;
        }
        squares.remove(s);
        repaint();
    }

    private class MouseHandle extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            rect = find(e.getPoint());
            // 当前位置如果有矩形，且点击大于2次，则删除矩形
            if (rect != null && e.getClickCount() >= 2) {
                remove(rect);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (n == 1) {
                int x=e.getX();
                int y=e.getY();
                g.drawRoundRect(x, y, 200, 50, 30, 30);
                Shape roundRect = new RoundRect(x, y, 200, 50, 30, 30);
                shapeArray[index++] = roundRect;
            }
            if (n == 2) {
                rect = find(e.getPoint());
                // 当前位置没有矩形则绘制
                if (rect == null) {
                    add(e.getPoint());
                }
            }
        }

    }

    private class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // 如果当前位置有矩形，进行拖拽。
            if (rect != null) {
                int x = e.getX();
                int y = e.getY();

                //将此矩形2d的外部边界的位置和大小设置为指定的矩形值。
                rect.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // 鼠标在空白屏幕默认箭头
            if (find(e.getPoint()) == null) {
                setCursor(Cursor.getDefaultCursor());
            }
            // 碰到矩形区域区域变成十字架
            else {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }

    }

    //当鼠标指针移动到组件上但没有按下按钮时调用。
    //此方法设置游标类型。在各个方位，各种条件下，都用调整大小的光标类型
    @Override
    public void mouseMoved(MouseEvent event) {
        int cursorType = Cursor.DEFAULT_CURSOR;
        if (rect != null) {
            int x = event.getX() - rect.x;
            int y = event.getY() - rect.y;
            int width = rect.width;
            int height = rect.height;

            isTopLeft = isTop = isTopRight = isRight = isBottomRight = isBottom = isBottomLeft = isLeft = isDrag = false;
            if (x > 0 && y > 0 && x < width && y < height) {
                if (y <= RESIZE_WIDTH) {
                    if (x <= RESIZE_WIDTH) {
                        isTopLeft = true;
                        cursorType = Cursor.NW_RESIZE_CURSOR;
                    } else if (x >= width - RESIZE_WIDTH) {
                        isTopRight = true;
                        cursorType = Cursor.NE_RESIZE_CURSOR;
                    } else {
                        isTop = true;
                        cursorType = Cursor.N_RESIZE_CURSOR;
                    }
                } else if (y >= height - RESIZE_WIDTH) {
                    if (x <= RESIZE_WIDTH) {
                        isBottomLeft = true;
                        cursorType = Cursor.SW_RESIZE_CURSOR;
                    } else if (x >= width - RESIZE_WIDTH) {
                        isBottomRight = true;
                        cursorType = Cursor.SE_RESIZE_CURSOR;
                    } else {
                        isBottom = true;
                        cursorType = Cursor.S_RESIZE_CURSOR;
                    }
                } else if (x <= RESIZE_WIDTH) {
                    isLeft = true;
                    cursorType = Cursor.W_RESIZE_CURSOR;
                } else if (x >= width - RESIZE_WIDTH) {
                    isRight = true;
                    cursorType = Cursor.E_RESIZE_CURSOR;
                } else if (find(event.getPoint()) != null) {
                    isDrag = true;
                    cursorType = Cursor.CROSSHAIR_CURSOR;
                }
            }
        }

        //将光标图像设置为指定光标
        setCursor(new Cursor(cursorType));
    }

    //在组件上按下鼠标按钮然后拖动时调用。
    //此方法实现拖动到结束的一系列的处理

    @Override
    public void mouseDragged(MouseEvent event) {
        boolean flag = true;
        int x = event.getX() - rect.x;
        int y = event.getY() - rect.y;
        int width = rect.width;
        int height = rect.height;

        int nextX = rect.x;
        int nextY = rect.y;
        int nextWidth = width;
        int nextHeight = height;
        if (isTopLeft || isLeft || isBottomLeft) {
            nextX += x;
            nextWidth -= x;
        }
        if (isTopLeft || isTop || isTopRight) {
            nextY += y;
            nextHeight -= y;
        }
        if (isTopRight || isRight || isBottomRight) {
            nextWidth = x;
        }
        if (isBottomLeft || isBottom || isBottomRight) {
            nextHeight = y;
        }
        if (nextWidth <= MIN_WIDTH) {
            nextWidth = MIN_WIDTH;
            if (isTopLeft || isLeft || isBottomLeft) {
                nextX = rect.x + width - nextWidth;
            }
        }
        if (nextHeight <= MIN_HEIGHT) {
            nextHeight = MIN_HEIGHT;
            if (isTopLeft || isTop || isTopRight) {
                nextY = rect.y + height - nextHeight;
            }
        }
        // 如果当前位置有矩形，进行拖拽。
        if (rect != null && isDrag) {
            nextX = event.getX();
            nextY = event.getY();
            rect.setBounds(nextX - nextWidth / 2, nextY - nextHeight / 2, nextWidth, nextHeight);
            repaint();
            flag = false;
        }
        if (flag) {
            rect.setBounds(nextX, nextY, nextWidth, nextHeight);
            repaint();
        }
    }




    public static void main(String[] args) {

        /**
         * 菜单
         */
        JMenuBar jmb;
        JMenu menu1, menu2, menu3;
        JMenuItem item2, item3, item4, item5;

        /**
         * 二级菜单
         * *
         **/
        JMenu xinjian;
        JMenuItem file, project;
        JTextArea jta;

        JFrame frame = new JFrame("流程图绘制程序");
        frame.setSize(1080, 660);
        MouseComponent mouseComponent = new MouseComponent();
        frame.add(mouseComponent);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 1));
        frame.add(p1, BorderLayout.WEST);

        p1.setPreferredSize(new Dimension(300, 700));
        p1.setBackground(Color.white);

        //创建图形按钮
        JButton jb1 = new JButton("ROUNDRECT", new ImageIcon(ROUNDRECT));
        JButton jb2 = new JButton("NORMALRECT", new ImageIcon(NORMALRECT));
        JButton jb3 = new JButton("DIAMOND", new ImageIcon(DIAMOND));
        JButton jb4 = new JButton("PARALLELOGRAM", new ImageIcon(PARALLELOGRAM));
        JButton jb5 = new JButton("ARROWLINE", new ImageIcon(ARROWLINE));
        JButton jb6 = new JButton("CONNECTOR", new ImageIcon(CONNECTOR));
        JButton jb7 = new JButton("CURVERECT", new ImageIcon(CURVERECT));

        //添加图片按钮
        {
            p1.add(jb1);
            p1.add(jb2);
            p1.add(jb3);
            p1.add(jb4);
            p1.add(jb5);
            p1.add(jb6);
            p1.add(jb7);
        }

        //设置按钮背景颜色
        {
            jb1.setBackground(Color.white);
            jb2.setBackground(Color.white);
            jb3.setBackground(Color.white);
            jb4.setBackground(Color.white);
            jb5.setBackground(Color.white);
            jb6.setBackground(Color.white);
            jb7.setBackground(Color.white);
        }

        //按钮设置监听器
        jb1.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 1;
            System.out.println("n=1");
        });
        jb2.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 2;
            System.out.println("n=2");
        });
        jb3.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 3;
            System.out.println("n=3");
        });
        jb4.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 4;
            System.out.println("n=4");
        });
        jb5.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 5;
            System.out.println("n=5");
        });
        jb6.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 6;
            System.out.println("n=6");
        });
        jb7.addActionListener(e -> {
            // 进行逻辑处理即可
            n = 7;
            System.out.println("n=7");
        });

        jmb = new JMenuBar();
        menu1 = new JMenu("文件(F)");
        // 设置助记符
        menu1.setMnemonic('F');
        menu2 = new JMenu("编辑(E)");
        menu2.setMnemonic('E');
        menu3 = new JMenu("帮助(H)");
        menu3.setMnemonic('H');

        // item1=new JMenuItem(“新建”)
        xinjian = new JMenu("新建");
        file = new JMenuItem("文件");
        project = new JMenuItem("工程");

        item2 = new JMenuItem("打开", new ImageIcon("images\\77.png"));
        item3 = new JMenuItem("保存(S)");
        item3.setMnemonic('S');
        // 给菜单选项添加快捷方式
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.ALT_MASK));
        item4 = new JMenuItem("另存为");
        item5 = new JMenuItem("退出");
        jta = new JTextArea();
        // 将菜单添加到菜单栏上
        xinjian.add(file);
        xinjian.add(project);

        menu1.add(xinjian);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.addSeparator();// 添加分割线
        menu1.add(item5);

        // 将菜单添加到菜单条上
        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);

        // 将菜单添加到窗体上并使窗口可见
        frame.setJMenuBar(jmb);
        frame.setVisible(true);
    }


}
