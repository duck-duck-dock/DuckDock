import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/*
created by Yuxin Zhu in 2021/03/21
class:主函数
 */
public class main {
    private static AllUser Users;//记得初始化

    private AllUser users;

    public static void main(String[] args) {
        LoginMenu Login = new LoginMenu();
        MainMenu Menu = new MainMenu();

        Login.NextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.setVisible(false);
                Menu.setVisible(true);
            }
        });

        Login.show();

    }
}

