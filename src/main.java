import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class main {
    private AllUser alluser;
    private User currentUser;

    public AllUser getAlluser() {
        return alluser;
    }

    public main() {
        this.alluser = new AllUser();
        currentUser=new User();
    }

    public static void main(String[] args) {
//        MainMenu Menu=new MainMenu();
//        Menu.show();

        main M = new main();
        LoginMenu Login = new LoginMenu(M.getAlluser());
        MainMenu Menu = new MainMenu();
        Login.LogLoginJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Login.JudgeLogin()) {
                    Login.setVisible(false);
                    Menu.setVisible(true);
                }
            }
        });

        Login.show();

    }
}

