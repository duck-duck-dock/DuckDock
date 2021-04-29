import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/*
created by Yuxin Zhu in 2021/03/21

 */
public class ProcessPage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;

    private void initGUI() {
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        this.setBorder(lineBorder);
        TestLabel = new JLabel("这是进度界面");
        BackButton = new JButton("返回");
        this.setBackground(Color.pink);
        this.add(TestLabel);
        this.add(BackButton);
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public ProcessPage() {
        initGUI();
    }
}
