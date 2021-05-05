import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
created by Yuxin Zhu in 2021/03/21
updated by Yuxin Zhu in 2021/04/22
new feature: Tomato Clock
 */
public class MainMenu extends JFrame {
    JToolBar BottomMenu;//zyx:底部菜单栏
    Box ButtonBar;//底部按钮布局
    Box MainBar;//总布局
    JPanel MainPanel;//主界面
    JButton Review, Practice, Upload, Process, Setting,Tomato;//按钮组件
    UploadPage UploadFrame;//功能模块
    SettingPage SettingFrame;//下为内嵌的分功能页面
    ReviewPage ReviewFrame;
    ProcessPage ProcessFrame;
    PracticePage PracticeFrame;
    DefaultPage DefaultFrame;
    CardLayout MainLayout;//卡片布局

    public User ThisUser;

    //Yuxin Zhu: initialize the memu GUI
    private void initTools() {
        BottomMenu = new JToolBar();
        ButtonBar = Box.createHorizontalBox();
        Review = new JButton("复习");
        Practice = new JButton("练习");
        Upload = new JButton("+");
        Process = new JButton("进度");
        Setting = new JButton("设置");
        Tomato=new JButton("番茄钟");
        ButtonBar.add(Review);
        ButtonBar.add(Practice);
        ButtonBar.add(Upload);
        ButtonBar.add(Process);
        ButtonBar.add(Setting);
        ButtonBar.add(Tomato);

        BottomMenu.add(ButtonBar);
        BottomMenu.setFloatable(false);

        Review.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Review");
            }
        });

        Practice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Practice");
            }
        });
        Process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Process");
            }
        });
        Setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Setting");

            }
        });
        Upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Upload");

            }
        });
        Tomato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TomatoFrame t = new TomatoFrame();
                t.setSize(300,400);
                t.show();
            }
        });
    }

    //Yuxin Zhu: initialize the sub pages
    private void initPages() {
        Border lineBorder = BorderFactory.createLineBorder(Color.YELLOW);


        MainBar = Box.createVerticalBox();
        MainPanel = new JPanel();
        MainPanel.setBorder(lineBorder);
        MainPanel.setSize(800, 500);
        MainLayout = new CardLayout();
        MainPanel.setLayout(MainLayout);

        UploadFrame = new UploadPage();
        UploadFrame.setSize(MainPanel.getSize());
        PracticeFrame = new PracticePage();
        PracticeFrame.setSize(MainPanel.getSize());
        ProcessFrame = new ProcessPage();
        ProcessFrame.setSize(MainPanel.getSize());
        ReviewFrame = new ReviewPage(ThisUser);
        ReviewFrame.setSize(MainPanel.getSize());
        SettingFrame = new SettingPage();
        SettingFrame.setSize(MainPanel.getSize());
        DefaultFrame = new DefaultPage();
        DefaultFrame.setSize(MainPanel.getSize());

        MainPanel.add("Default", DefaultFrame);
        MainPanel.add("Review", ReviewFrame);
        MainPanel.add("Practice", PracticeFrame);
        MainPanel.add("Upload", UploadFrame);
        MainPanel.add("Process", ProcessFrame);
        MainPanel.add("Setting", SettingFrame);


        MainBar.add(MainPanel);
        MainBar.add(BottomMenu);
        this.add(MainBar);


        UploadFrame.BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Default");
            }
        });

        PracticeFrame.BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Default");
            }
        });

        ProcessFrame.BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Default");
            }
        });
        SettingFrame.BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Default");
            }
        });
        ReviewFrame.BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLayout.show(MainPanel, "Default");
            }
        });
    }

    //Yuxin Zhu: init all the GUI
    private void initGUI() {//zyx:初始化界面
        this.setSize(800, 600);
        initTools();
        initPages();

    }


    //Yuxin Zhu: main func, DON'T change this func
    public MainMenu(User thisuser) {
        super("主页");
        ThisUser = thisuser;
        initGUI();
    }

}
