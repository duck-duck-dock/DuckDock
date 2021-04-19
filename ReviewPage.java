import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ReviewPage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;

    Box h1Box;//Li Wen: 横向Box容器
    Box v1Box;//Li Wen: 纵向Box容器
    EnglishPanel AEnglishPage; //Li Wen: 单词学习页


    private void initGUI() {
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        this.setBorder(lineBorder);
        TestLabel = new JLabel("这是复习界面");
        BackButton = new JButton("返回");

        //Li Wen: 盒式布局
        {
            h1Box = Box.createHorizontalBox();
            v1Box = Box.createVerticalBox();
            AEnglishPage = new EnglishPanel();
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

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public ReviewPage() {
        initGUI();
    }
}
