import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class DefaultPage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;
    User ThisUser;
    TodayPage todaypage;//Li Wen: TodayPage

    private void initGUI() {
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        this.setBorder(lineBorder);

        //Li Wen: 初始化一个TodayPage
        todaypage = new TodayPage(ThisUser);
        this.add(todaypage);

    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public DefaultPage(User thisuser) {
        ThisUser = thisuser;
        initGUI();
    }
}