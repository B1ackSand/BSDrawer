package gui;

import shape.SymbolType;
import shape.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static common.Path.*;

/**
 * @author Gillo
 */
public class Gui extends JFrame {
    public static void main(String[] args) {

        Gui gui1 = new Gui();
        //直接关闭应用程序
        gui1.setDefaultCloseOperation(3);
    }


    /**
     * 右侧图标
     */
    private Container container;
    private JButton jb1 = new JButton(new ImageIcon(ROUNDRECT));
    private JButton jb2 = new JButton(new ImageIcon(NORMALRECT));
    private JButton jb3 = new JButton(new ImageIcon(DIAMOND));
    private JButton jb4 = new JButton(new ImageIcon(PARALLELOGRAM));
    private JButton jb5 = new JButton(new ImageIcon(ARROWLINE));
    private JButton jb6 = new JButton(new ImageIcon(CONNECTOR));
    private JButton jb7 = new JButton(new ImageIcon(CURVERECT));

    private ArrayList<Symbol> flowchart = new ArrayList<>();

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
    Color ButtonColor = new Color(255, 255, 255);

    public Gui() {
        setLayout(new BorderLayout(5, 10));
        //绘图按钮面板
        JPanel p1 = new JPanel();
        //画板
        JPanel p2 = new JPanel();
        p1.setLayout(new GridLayout(7, 1));
        p1.setLayout(new GridLayout(7, 1));
        this.add(p1, BorderLayout.EAST);
        this.add(p2, BorderLayout.CENTER);
        container = getContentPane();

        p1.add(jb1);
        p1.add(jb2);
        p1.add(jb3);
        p1.add(jb4);
        p1.add(jb5);
        p1.add(jb6);
        p1.add(jb7);

        //设置背景颜色
        jb1.setBackground(ButtonColor);
        jb2.setBackground(ButtonColor);
        jb3.setBackground(ButtonColor);
        jb4.setBackground(ButtonColor);
        jb5.setBackground(ButtonColor);
        jb6.setBackground(ButtonColor);
        jb7.setBackground(ButtonColor);
        p2.setBackground(Color.GRAY);

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

        // 将菜单添加到窗体上
        this.setJMenuBar(jmb);
        this.setTitle("流程图绘制程序");
        //画布大小
        this.setSize(1080, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(200, 200);
        this.setVisible(true);

        //按钮监听
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                int x = (int) (Math.random() * p2.getWidth() / 2);
                int y = (int) (Math.random() * p2.getHeight() / 2);
                Symbol symbol = new Symbol(SymbolType.ROUNDRECT, x, y, 100, 40, "Process\nTest");
                System.out.println("按钮被点击");
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                System.out.println("按钮被点击");
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                System.out.println("按钮被点击");
            }
        });
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                System.out.println("按钮被点击");
            }
        });
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                System.out.println("按钮被点击");
            }
        });
        jb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                System.out.println("按钮被点击");
            }
        });
        jb7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取到的事件源就是按钮本身
                System.out.println("按钮被点击");
            }
        });
    }
}