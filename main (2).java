import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class main {
    private AllUser alluser;
    public AllUser getAlluser() {
        return alluser;
    }
    private User Userr;
    public User getUserr() {
         return Userr;
    }

    public main(){
        this.alluser=new AllUser();
        this.Userr =new User();
    }
    public  static void main(String[] args) {
        main M=new main();
        /*LoginMenu Login = new LoginMenu(M.getAlluser());*/
        ToDoList list=new ToDoList(M.getUserr());
        MainMenu Menu = new MainMenu();
      /*  Login.LogLoginJButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
                  if(Login.JudgeLogin()){
                      Login.setVisible(false);
                      Menu.setVisible(true);
                  }
          }
      });

       Login.show();*/

        list.show();

    }
}
