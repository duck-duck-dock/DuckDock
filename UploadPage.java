import javax.swing.*;
import java.awt.*;

/*
created by Yuxin Zhu in 2021/03/21

 */
public class UploadPage extends JPanel {
    JLabel TestLabel;//Yuxin Zhu: 测试用标签，后续删掉换自己写的组件
    JButton BackButton;

    private void initGUI() {
        TestLabel = new JLabel("这是上传界面");
        BackButton = new JButton("返回");
        this.setBackground(Color.gray);
        this.add(TestLabel);
        this.add(BackButton);
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public UploadPage() {
        initGUI();
    }
}
