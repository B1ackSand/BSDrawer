package gui;


import shape.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static common.Path.*;

/**
 * @author Gillo, BlackSand
 */
public class Gui extends JPanel {

    private JFileChooser fileChooser = new JFileChooser();

    /**
     * 菜单
     */
    JMenuBar jmb;
    JMenu menu1, menu2, menu3;
    JMenuItem item1, item2, item3, item4;
    Color buttonColor = new Color(255, 255, 255);

    /**
     * GUI设计
     * *
     **/
    public Gui() {
        //名字
        JFrame jf = new JFrame("BSDrawer——流程图绘制程序");
        //窗口大小
        jf.setSize(1080, 660);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //绘图按钮面板
        JPanel p1 = new JPanel();
        //画板
        //JLabel p2 = new JLabel();

        p1.setLayout(new GridLayout(7, 1));
        jf.add(p1, BorderLayout.WEST);
        jf.add(this, BorderLayout.CENTER);

        //设置jf
        this.setPreferredSize(new Dimension(900, 600));
        this.setBackground(Color.white);

        //事件监听器对象
        DrawListener dl = new DrawListener();
        //给画布加监听器
        this.addMouseListener(dl);
        this.addMouseMotionListener(dl);

        //设置p1
        p1.setPreferredSize(new Dimension(300, 700));
        this.setBackground(Color.white);

        //创建图形按钮
        JButton jb1 = new JButton("ROUNDRECT", new ImageIcon(ROUNDRECT));
        JButton jb2 = new JButton("NORMALRECT", new ImageIcon(NORMALRECT));
        JButton jb3 = new JButton("DIAMOND", new ImageIcon(DIAMOND));
        JButton jb4 = new JButton("PARALLELOGRAM", new ImageIcon(PARALLELOGRAM));
        JButton jb5 = new JButton("ARROWLINE", new ImageIcon(ARROWLINE));
        JButton jb6 = new JButton("CONNECTOR", new ImageIcon(CONNECTOR));
        JButton jb7 = new JButton("CURVERECT", new ImageIcon(CURVERECT));

        //添加图片按钮
        p1.add(jb1);
        p1.add(jb2);
        p1.add(jb3);
        p1.add(jb4);
        p1.add(jb5);
        p1.add(jb6);
        p1.add(jb7);

        //设置按钮背景颜色
        jb1.setBackground(buttonColor);
        jb2.setBackground(buttonColor);
        jb3.setBackground(buttonColor);
        jb4.setBackground(buttonColor);
        jb5.setBackground(buttonColor);
        jb6.setBackground(buttonColor);
        jb7.setBackground(buttonColor);

        //按钮设置监听器
        jb1.addActionListener(dl);
        jb2.addActionListener(dl);
        jb3.addActionListener(dl);
        jb4.addActionListener(dl);
        jb5.addActionListener(dl);
        jb6.addActionListener(dl);
        jb7.addActionListener(dl);


        jmb = new JMenuBar();
        menu1 = new JMenu("文件(F)");
        // 设置助记符
        menu1.setMnemonic('F');
        menu2 = new JMenu("格式(T)");
        menu2.setMnemonic('E');
        menu3 = new JMenu("帮助(H)");
        menu3.setMnemonic('H');

        item1 = new JMenuItem("新建(N)");
        item2 = new JMenuItem("打开(O)");
        item3 = new JMenuItem("保存图片(S)");
        item4 = new JMenuItem("退出(X)");


        // 给菜单选项添加快捷方式
        item1.setMnemonic('N');
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.ALT_MASK));
        item2.setMnemonic('O');
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.ALT_MASK));
        item3.setMnemonic('S');
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.ALT_MASK));

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.addSeparator();// 添加分割线
        menu1.add(item4);

        // 将菜单添加到菜单条上
        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);

        // 将菜单添加到窗体上并使窗口可见
        jf.setJMenuBar(jmb);
        jf.setVisible(true);
        jf.setResizable(false);
        Graphics g = this.getGraphics();
        dl.setGr(g);
        Shape[] shapeParameter = new Shape[20000];
        dl.setSp(shapeParameter);

        //设置保存图片按钮监听
        //图片保存可选择为JPG和PNG格式和路径
        item3.addActionListener(a -> {

            //缓存进BufferedImage
            BufferedImage myImage;

            // 创建文件选择对话框
            fileChooser = new JFileChooser();

            //设置文件过滤器，只列出JPG或GIF格式的图片
            FileFilter filter = new FileNameExtensionFilter("Image（*.jpg, *.png）(文件名不加入扩展名默认为JPG格式)", ".jpg", ".png");
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);

            //另方向使用Robot类抓屏一部分的方式进行图片的保存，因为直接使用Panel类中的导出图片不知为何只能导出空JFrame
            try {
                myImage = new Robot().createScreenCapture(
                        //设定区域为绘图区域大小
                        new Rectangle(jf.getX() + 307, jf.getY() + 54, jf.getWidth() - 310, jf.getHeight() - 57));
                int result = fileChooser.showSaveDialog(null);

                //如果按下保存键/确认键
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file_path = fileChooser.getSelectedFile(); 
                    if (file_path.getPath().endsWith(".jpg")) {
                        System.out.println(file_path.getPath());
                        ImageIO.write(myImage, "jpg", new File(file_path.getPath()));
                    } else if (file_path.getPath().endsWith(".png")) {
                        System.out.println(file_path.getPath());
                        ImageIO.write(myImage, "png", new File(file_path.getPath()));
                    } else if (!file_path.getPath().endsWith(".jpg") && !file_path.getPath().endsWith(".png")) {
                        System.out.println(file_path.getPath());
                        ImageIO.write(myImage, "jpg", new File(file_path.getPath() + ".jpg"));
                    }
                }

                //异常捕获
            } catch (AWTException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}