import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Vector;

public class ReviewPage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;

    Box h1Box;//Li Wen: Box容器
    Box v1Box;
    EnglishPanel AEnglishPage; //Li Wen: 单词学习页
    User ThisUser;//Li Wen: 当前用户


    private void initGUI() {
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        this.setBorder(lineBorder);
        TestLabel = new JLabel("这是复习界面");
        BackButton = new JButton("返回");

        //Li Wen: 盒式布局
        {
            h1Box = Box.createHorizontalBox();
            v1Box = Box.createVerticalBox();
            AEnglishPage = new EnglishPanel(ThisUser);
            AEnglishPage.setPreferredSize(new Dimension(480,400));

            this.setBackground(Color.green);
            this.add(v1Box);
            h1Box.add(Box.createHorizontalStrut(50));
            h1Box.add(TestLabel);
            h1Box.add(Box.createHorizontalStrut(50));
            h1Box.add(BackButton);
            h1Box.add(Box.createHorizontalGlue());
            v1Box.add(h1Box);
            v1Box.add(Box.createVerticalStrut(10));
            v1Box.add(AEnglishPage);
            v1Box.add(Box.createHorizontalGlue());
        }
    }

    /*
created by Li Wen in 2021/4/20

Abstract:
ReviewPage的构造函数，
初始化ThisUser当前用户

Para:
ThisUser：当前用户

Return value：
null
*/
    public ReviewPage(User thisuser){
        ThisUser = thisuser;
        initGUI();
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public ReviewPage() {
        initGUI();
    }
}
