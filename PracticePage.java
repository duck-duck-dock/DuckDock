import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PracticePage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;

    private void initGUI() {
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        this.setBorder(lineBorder);
        TestLabel = new JLabel("这是练习界面");
        BackButton = new JButton("返回");
        this.setBackground(Color.cyan);
        this.add(TestLabel);
        this.add(BackButton);
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public PracticePage() {
        initGUI();
    }
}
