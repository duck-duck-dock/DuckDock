import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class main {
    private AllUser alluser;

    public AllUser getAlluser() {
        return alluser;
    }
    private static User Userr;
    public User getUserr() {
         return Userr;
    }



    public void setAlluser() {
        jdbc.JdbcInitAllUsers(this.alluser);
        System.out.println(this.alluser.getUsers().elementAt(0).getUserName());
        this.alluser.setUserNum(this.alluser.getUsers().size());
    }

    public main() {
        this.alluser = new AllUser();
    }

    public static void main(String[] args) {
        main M = new main();
        M.setAlluser();
        System.out.println(M.getAlluser().getUsers().elementAt(0).getPassword());
        LoginMenu Login = new LoginMenu(M.getAlluser());
        Login.LogLoginJButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
                  if((Userr=Login.JudgeLogin())!=null){
                      MainMenu Menu = new MainMenu(Userr);
                      Login.setVisible(false);
                      Menu.setVisible(true);
                  }
          }
      });

        Login.show();

    }
}

