import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class DefaultPage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;
    TodayPage todaypage;//Li Wen: 今日页面

    private void initGUI() {
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        this.setBorder(lineBorder);

        //Li Wen: 初始化一个今日页面
        todaypage = new TodayPage();
        todaypage.setPreferredSize(new Dimension(800,600));
        this.add(todaypage);
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public DefaultPage() {
        initGUI();
    }
}
