package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @author jiajun
 */
public class Mouse extends JFrame {

    private JPanel jContentPane = null;
    private JPanel jPanel = null;


    ImageIcon imageIcon = new ImageIcon("src\\1.png");
    /*图片*/


    private JPanel getJContentPane() {

        jContentPane = new JPanel();
        jContentPane.setLayout(null);
        jContentPane.add(new ImagePanel(imageIcon), null);
        return jContentPane;
    }

    class ImagePanel extends JPanel implements MouseListener,
            MouseMotionListener {
        int x1, y1;

        private JLabel northWest = null;

        private JLabel north = null;

        private JLabel northEast = null;

        private JLabel east = null;

        private JLabel southEast = null;

        private JLabel south = null;

        private JLabel southWest = null;

        private JLabel west = null;

        private JLabel middle = null;
        /*8个位点*/
        ImageIcon imageIcon = null;
        /*图片*/
        boolean isSelected = false;

        /*是否被选中*/
        public ImagePanel(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
            this.setBounds(200, 200, 100, 100);
            x1 = this.getWidth();
            y1 = this.getHeight();

            northWest = new JLabel();
            northWest.setText("");
            northWest.setBorder(new LineBorder(Color.WHITE, 1));
            northWest.addMouseListener(this);
            northWest.addMouseMotionListener(this);
            northWest.setVisible(false);

            north = new JLabel();
            north.setText("");
            north.setBorder(new LineBorder(Color.WHITE, 1));
            north.addMouseListener(this);
            north.addMouseMotionListener(this);
            north.setVisible(false);

            northEast = new JLabel();
            northEast.setText("");
            northEast.setBorder(new LineBorder(Color.WHITE, 1));
            northEast.addMouseListener(this);
            northEast.addMouseMotionListener(this);
            northEast.setVisible(false);

            east = new JLabel();
            east.setText("");
            east.setBorder(new LineBorder(Color.WHITE, 1));
            east.addMouseListener(this);
            east.addMouseMotionListener(this);
            east.setVisible(false);

            southEast = new JLabel();
            southEast.setText("");
            southEast.setBorder(new LineBorder(Color.WHITE, 1));
            southEast.addMouseListener(this);
            southEast.addMouseMotionListener(this);
            southEast.setVisible(false);

            south = new JLabel();
            south.setText("");
            south.setBorder(new LineBorder(Color.WHITE, 1));
            south.addMouseListener(this);
            south.addMouseMotionListener(this);
            south.setVisible(false);

            southWest = new JLabel();
            southWest.setText("");
            southWest.setBorder(new LineBorder(Color.WHITE, 1));
            southWest.addMouseListener(this);
            southWest.addMouseMotionListener(this);
            southWest.setVisible(false);

            west = new JLabel();
            west.setText("");
            west.setBorder(new LineBorder(Color.WHITE, 1));
            west.addMouseListener(this);
            west.addMouseMotionListener(this);
            west.setVisible(false);

            northWest.setBounds(0, 0, 5, 5);
            north.setBounds((x1 - 3) / 2, 0, 5, 5);
            northEast.setBounds(x1 - 5, 0, 5, 5);
            east.setBounds(x1 - 5, (y1 - 3) / 2, 5, 5);
            southEast.setBounds(x1 - 5, y1 - 5, 5, 5);
            south.setBounds((x1 - 3) / 2, y1 - 5, 5, 5);
            southWest.setBounds(0, y1 - 5, 5, 5);
            west.setBounds(0, (y1 - 3) / 2, 5, 5);
            /*初始化8个位点*/

            this.setLayout(null);
            this.setMaximumSize(new Dimension(150, 150));
            this.setMinimumSize(new Dimension(20, 20));
            this.addMouseListener(this);
            this.addMouseMotionListener(this);

            this.add(northWest, null);
            this.add(north, null);
            this.add(northEast, null);
            this.add(east, null);
            this.add(southEast, null);
            this.add(south, null);
            this.add(southWest, null);
            this.add(west, null);
            /*初始化*/
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (isSelected == false) {/*选中图片*/
                isSelected = true;
                this.setBorder(new LineBorder(Color.BLACK, 4));
                northWest.setVisible(true);
                north.setVisible(true);
                northEast.setVisible(true);
                east.setVisible(true);
                southEast.setVisible(true);
                south.setVisible(true);
                southWest.setVisible(true);
                west.setVisible(true);
                this.repaint();

            } else if (isSelected == true) {
                if (e.getClickCount() == 1) {/*取消选中*/
                    isSelected = false;
                    this.setBorder(new LineBorder(Color.BLACK, 4));
                    northWest.setVisible(false);
                    north.setVisible(false);
                    northEast.setVisible(false);
                    east.setVisible(false);
                    southEast.setVisible(false);
                    south.setVisible(false);
                    southWest.setVisible(false);
                    west.setVisible(false);
                    this.repaint();/*删除*/
                } else {
                    this.addKeyListener(new KeyListener() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                                northWest.setVisible(false);
                                north.setVisible(false);
                                northEast.setVisible(false);
                                east.setVisible(false);
                                southEast.setVisible(false);
                                south.setVisible(false);
                                southWest.setVisible(false);
                                west.setVisible(false);
                                jContentPane.setVisible(false);
                                repaint();/*删除*/
                            }
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                        }

                        @Override
                        public void keyTyped(KeyEvent e) {
                        }
                    });
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {/*防止鼠标在拖动过程中移出范围*/
            if (isSelected) {
                if (getCursor().getType() != Cursor.DEFAULT_CURSOR) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {/*鼠标进入哪个区域*/
            if (isSelected) {
                if (e.getComponent() == northWest) {
                    setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                } else if (e.getComponent() == north) {
                    setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                } else if (e.getComponent() == northEast) {
                    setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                } else if (e.getComponent() == east) {
                    setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                } else if (e.getComponent() == southEast) {
                    setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                } else if (e.getComponent() == south) {
                    setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                } else if (e.getComponent() == southWest) {
                    setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                } else if (e.getComponent() == west) {
                    setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                } /*else if(e.getComponent() == this){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }   */
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {/*缩放图片*/
            if (isSelected) {
                int type = getCursor().getType();
                int x, y, w, h;
                x = this.getX();
                y = this.getY();
                w = this.getWidth();
                h = this.getHeight();
                switch (type) {
                    case Cursor.NW_RESIZE_CURSOR:
                        if (w - e.getX() > 20 && h - e.getY() > 20) {
                            w = w - e.getX();
                            h = h - e.getY();
                            this.setBounds(x + e.getX(), y + e.getY(), w, h);
                        }
                        break;
                    case Cursor.N_RESIZE_CURSOR:
                        if (h - e.getY() > 20) {
                            h = h - e.getY();
                            this.setBounds(x, y + e.getY(), w, h);
                        }
                        break;
                    case Cursor.NE_RESIZE_CURSOR:
                        if (w + e.getX() > 20 && h - e.getY() > 20) {
                            w = w + e.getX();
                            h = h - e.getY();
                            this.setBounds(x, y + e.getY(), w, h);
                        }
                        break;
                    case Cursor.E_RESIZE_CURSOR:
                        if (w + e.getX() > 20) {
                            w = w + e.getX();
                            this.setBounds(x, y, w, h);
                        }
                        break;
                    case Cursor.SE_RESIZE_CURSOR:
                        if (w + e.getX() > 20 && h + e.getY() > 20) {
                            w = w + e.getX();
                            h = h + e.getY();
                            this.setBounds(x, y, w, h);
                        }
                        break;
                    case Cursor.S_RESIZE_CURSOR:
                        if (h + e.getY() > 20) {
                            h = h + e.getY();
                            this.setBounds(x, y, w, h);
                        }
                        break;
                    case Cursor.SW_RESIZE_CURSOR:
                        if (w - e.getX() > 20 && h + e.getY() > 20) {
                            w = w - e.getX();
                            h = h + e.getY();
                            this.setBounds(x + e.getX(), y, w, h);
                        }
                        break;
                    case Cursor.W_RESIZE_CURSOR:
                        if (w - e.getX() > 20) {
                            w = w - e.getX();
                            this.setBounds(x + e.getX(), y, w, h);
                        }
                        break;
                    case Cursor.DEFAULT_CURSOR:
                        if (e.getComponent() == this) {

                            this.setBounds(x + e.getX() - w / 2, y + e.getY() - h / 2, w, h);/*拖动图片*/
                        }
                    default:
                        break;
                }
                if (w > 300) {
                    this.setSize(300, this.getHeight());
                }
                if (h > 300) {
                    this.setSize(this.getWidth(), 300);
                }

                northWest.setBounds(0, 0, 5, 5);
                north.setBounds((w - 3) / 2, 0, 5, 5);
                northEast.setBounds(w - 5, 0, 5, 5);
                east.setBounds(w - 5, (h - 3) / 2, 5, 5);
                southEast.setBounds(w - 5, h - 5, 5, 5);
                south.setBounds((w - 3) / 2, h - 5, 5, 5);
                southWest.setBounds(0, h - 5, 5, 5);
                west.setBounds(0, (h - 3) / 2, 5, 5);
                this.repaint();/*重画*/
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.drawImage(this.imageIcon.getImage(), 5, 5, this.getWidth() - 10,
                    this.getHeight() - 10, this);
        }

    }

    class LinePanel extends JPanel implements MouseListener, MouseMotionListener {
        int x1, y1, x2, y2;
        private JLabel start = null;
        private JLabel end = null;
        boolean isSelected = false;

        public LinePanel(int x1, int y1, int x2, int y2) {/*初始化线段位点*/
            start = new JLabel();
            start.setText("");
            start.setBorder(new LineBorder(Color.WHITE, 1));
            start.addMouseListener(this);
            start.addMouseMotionListener(this);
            start.setVisible(false);
            start.setLocation(x1, y1);
            end = new JLabel();
            end.setText("");
            end.setBorder(new LineBorder(Color.WHITE, 1));
            end.addMouseListener(this);
            end.addMouseMotionListener(this);
            end.setVisible(false);
            end.setLocation(x2, y2);
            this.setLayout(null);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.add(start, null);
            this.add(end, null);
        }

        @Override
        public void mouseDragged(MouseEvent e) {/*拖动直线*/
            if (isSelected) {
                int type = getCursor().getType();
                switch (type) {
                    case Cursor.CROSSHAIR_CURSOR:
                        this.setLocation(x1 + e.getX(), y1 + e.getY());
                        break;
                    case Cursor.HAND_CURSOR:
                        this.setLocation(x2 + e.getX(), y2 + e.getY());
                        break;
                    default:
                        break;
                }
                start.setLocation(x1, y1);
                end.setLocation(x2, y2);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {/*选中直线*/
            if (isSelected == false) {
                isSelected = true;
                this.setBorder(new LineBorder(Color.BLACK, 4));
                start.setVisible(true);
                end.setVisible(true);
                this.repaint();
            } else {
                if (e.getClickCount() == 2) {
                    isSelected = false;
                    this.setBorder(new LineBorder(Color.BLACK, 4));
                    start.setVisible(false);
                    end.setVisible(false);
                    this.repaint();
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {/*鼠标进入哪个位点区域*/
            if (isSelected) {
                if (e.getComponent() == start) {
                    setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                } else if (e.getComponent() == end) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {/*防止鼠标在拖动过程中移出范围*/
            if (isSelected) {
                if (getCursor().getType() != Cursor.DEFAULT_CURSOR) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }

    }
    /*
    public static void main(String[] args) {
        Mouse mouse = new Mouse();
    }
    */
}