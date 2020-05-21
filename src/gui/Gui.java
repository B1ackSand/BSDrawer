package gui;


import shape.Shapes;
import gui.DrawListener;

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
    JMenuItem item1, item2, item3, item4, item5, item6, item7;
    Color buttonColor = new Color(242, 242, 242);

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
        //文本框按钮面板区域
        JLabel p2 = new JLabel();

        p1.setLayout(new GridLayout(9, 1));
        p2.setLayout(new GridLayout(1, 2));
        jf.add(p1, BorderLayout.WEST);
        jf.add(this, BorderLayout.CENTER);
        p1.add(p2, BorderLayout.SOUTH);

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
        JButton jb1 = new JButton("ROUNDRECT", new ImageIcon(this.getClass().getClassLoader().getResource(ROUNDRECT)));
        JButton jb2 = new JButton("NORMALRECT", new ImageIcon(this.getClass().getClassLoader().getResource(NORMALRECT)));
        JButton jb3 = new JButton("DIAMOND", new ImageIcon(this.getClass().getClassLoader().getResource(DIAMOND)));
        JButton jb4 = new JButton("PARALLELOGRAM", new ImageIcon(this.getClass().getClassLoader().getResource(PARALLELOGRAM)));
        JButton jb5 = new JButton("LINE", new ImageIcon(this.getClass().getClassLoader().getResource(LINE)));
        JButton jb6 = new JButton("CONNECTOR", new ImageIcon(this.getClass().getClassLoader().getResource(CONNECTOR)));
        JButton jb7 = new JButton("BROKENLINE", new ImageIcon(this.getClass().getClassLoader().getResource(BROKENLINE)));
        JButton jb8 = new JButton("SETSTR", new ImageIcon(this.getClass().getClassLoader().getResource(SETSTR)));

        //添加图片按钮
        p1.add(jb1);
        p1.add(jb2);
        p1.add(jb3);
        p1.add(jb4);
        p1.add(jb5);
        p1.add(jb6);
        p1.add(jb7);
        p1.add(jb8);

        //设置按钮背景颜色
        jb1.setBackground(buttonColor);
        jb2.setBackground(buttonColor);
        jb3.setBackground(buttonColor);
        jb4.setBackground(buttonColor);
        jb5.setBackground(buttonColor);
        jb6.setBackground(buttonColor);
        jb7.setBackground(buttonColor);
        jb8.setBackground(buttonColor);

        //按钮设置监听器
        jb1.addActionListener(dl);
        jb2.addActionListener(dl);
        jb3.addActionListener(dl);
        jb4.addActionListener(dl);
        jb5.addActionListener(dl);
        jb6.addActionListener(dl);
        jb7.addActionListener(dl);
        jb8.addActionListener(dl);

        //创建文本框和提交按钮
        final JTextField textField = new JTextField("请修改内容", 8);
        textField.setFont(new Font(null, Font.PLAIN, 16));
        p2.add(textField);

        //提交按钮监听
        JButton strbtn = new JButton("提交");
        strbtn.setFont(new Font(null, Font.PLAIN, 15));
        strbtn.setBackground(buttonColor);
        strbtn.addActionListener(a -> {
            DrawListener.text = textField.getText();
            System.out.println("提交: " + textField.getText());
        });
        p2.add(strbtn);

        jmb = new JMenuBar();
        menu1 = new JMenu("文件(F)");
        // 设置助记符
        menu1.setMnemonic('F');
        menu2 = new JMenu("编辑(C)");
        menu2.setMnemonic('E');
        menu3 = new JMenu("帮助(H)");
        menu3.setMnemonic('H');

        item1 = new JMenuItem("新建(N)");
        item2 = new JMenuItem("打开(O)");
        item3 = new JMenuItem("保存图片(S)");
        item4 = new JMenuItem("退出(X)");
        item5 = new JMenuItem("执行拖动");
        item6 = new JMenuItem("取消拖动");
        item7 = new JMenuItem("矩形拖动删除");

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
        menu2.add(item5);
        menu2.add(item6);
        menu2.addSeparator();// 添加分割线
        menu2.add(item7);

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
        Shapes[] shapesParameter = new Shapes[20000];
        dl.setSp(shapesParameter);


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

        item5.addActionListener(a -> {
            DrawListener.isDrag = true;
        });
        item6.addActionListener(a -> {
            DrawListener.isDrag = false;
        });

        item1.addActionListener(a -> {
            DrawListener.clean = true;
        });
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}