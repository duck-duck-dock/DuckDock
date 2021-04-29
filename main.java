import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*
created by Yuxin Zhu in 2021/03/21
class:主函数
 */
public class main {
    private static AllUser Users;

    public AllUser getAlluser() {
        return Users;
    }

    public static void main(String[] args) {
        Users=new AllUser();
        LoginMenu Login = new LoginMenu();
        MainMenu Menu = new MainMenu(Users.getUsers().elementAt(0));
        Login.LogLoginJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Login.JudgeLogin()){
                    Login.setVisible(false);
                    Menu.setVisible(true);
                }
            }
        });

        Login.show();
    }
}

