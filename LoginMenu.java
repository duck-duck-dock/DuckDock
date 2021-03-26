import javax.swing.*;

/*
created by Yuxin Zhu in 2021/03/21
class:登录界面

 */
public class LoginMenu extends JFrame {
    JButton NextPage;

    private void initGUI() {//zyx:初始化界面
        this.setSize(400, 300);
        NextPage = new JButton("登录");
        this.add(NextPage);
    }

    //Yuxin Zhu: main func, DON'T change this func
    public LoginMenu() {
        initGUI();
    }
}
