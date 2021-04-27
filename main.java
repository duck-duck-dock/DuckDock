import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*
created by Yuxin Zhu in 2021/03/21
class:主函数
 */
public class main {
    private AllUser Users;
    public AllUser getAlluser() {
        return Users;
    }
    public main(){
        this.Users=new AllUser();
    }
    public static void main(String[] args) {
        LoginMenu Login = new LoginMenu();
        MainMenu Menu = new MainMenu(Users.getUsers().elementAt(0));

        Login.NextPage.addActionListener(new ActionListener() {
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

