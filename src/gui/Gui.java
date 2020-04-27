package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Gui extends JFrame{

    public static void main(String[] args) {

        Gui gui1 = new Gui();
        //直接关闭应用程序
        gui1.setDefaultCloseOperation(3);
    }

    /** 右侧图标
     *
     */
    private Container container;
    private  JButton jb1 = new JButton(new ImageIcon("src/images/1.png"));
    private  JButton jb2 = new JButton(new ImageIcon("src/images/2.png"));
    private  JButton jb3 = new JButton(new ImageIcon("src/images/3.png"));
    private  JButton jb4 = new JButton(new ImageIcon("src/images/4.png"));
    private  JButton jb5 = new JButton(new ImageIcon("src/images/5.png"));
    private  JButton jb6 = new JButton(new ImageIcon("src/images/6.png"));
    private  JButton jb7 = new JButton(new ImageIcon("src/images/7.png"));

    /** 菜单
     *
     */
    JMenuBar jmb;
    JMenu menu1, menu2, menu3, menu4, menu5;
    JMenuItem item2, item3, item4, item5, item6, item7;

    /** 二级菜单
     **
     **/
    JMenu xinjian;
    JMenuItem file, project;
    JTextArea jta;
    Color ButtonColor= new Color(255, 255, 255);

    /** 实例化ButtonListener，实现接口
     *
     */
    ButtonListener btl = new ButtonListener();
    public Gui(){
        setLayout(new BorderLayout(5,10));
        //绘图按钮面板
        JPanel p1 = new JPanel();
        //画板
        JPanel p2 = new JPanel();
        p1.setLayout(new GridLayout(7,1));
        p1.setLayout(new GridLayout(7,1));
        this.add(p1,BorderLayout.EAST);
        this.add(p2,BorderLayout.CENTER);
        container = getContentPane();

        p1.add(jb1) ;
        p1.add(jb2) ;
        p1.add(jb3) ;
        p1.add(jb4) ;
        p1.add(jb5) ;
        p1.add(jb6) ;
        p1.add(jb7) ;

        //设置背景颜色
        jb1.setBackground(ButtonColor);
        jb2.setBackground(ButtonColor);
        jb3.setBackground(ButtonColor);
        jb4.setBackground(ButtonColor);
        jb5.setBackground(ButtonColor);
        jb6.setBackground(ButtonColor);
        jb7.setBackground(ButtonColor);
        p2.setBackground(Color.WHITE);

        jmb = new JMenuBar();
        menu1 = new JMenu("文件(F)");
        // 设置助记符
        menu1.setMnemonic('F');
        menu2 = new JMenu("编辑(E)");
        menu2.setMnemonic('E');
        menu3 = new JMenu("格式(O)");
        menu3.setMnemonic('O');
        menu4 = new JMenu("查看(V)");
        menu4.setMnemonic('V');
        menu5 = new JMenu("帮助(H)");
        menu5.setMnemonic('H');

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
        item5 = new JMenuItem("页面设置");
        item6 = new JMenuItem("打印");
        item7 = new JMenuItem("退出");
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
        menu1.add(item6);
        menu1.addSeparator();
        menu1.add(item7);

        // 将菜单添加到菜单条上
        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);
        jmb.add(menu4);
        jmb.add(menu5);

        // 将菜单添加到窗体上
        this.setJMenuBar(jmb);
        this.setTitle("流程图绘制程序");
        this.setSize(1080, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(200, 200);
        this.setVisible(true);

        //加入按钮的监听
        jb1.addActionListener(btl);
        jb2.addActionListener(btl);
        jb3.addActionListener(btl);
        jb4.addActionListener(btl);
        jb5.addActionListener(btl);
        jb6.addActionListener(btl);
        jb7.addActionListener(btl);
    }
}