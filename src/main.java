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
        MainMenu Menu = new MainMenu();
        Login.LogLoginJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Login.JudgeLogin()) {
                    /*Vector<String>CityID=new Vector<>();
                    Vector<String>adm1=new Vector<>();
                    Vector<String>adm2=new Vector<>();
                    jdbc.JdbcChinaCity("Chaoyang",CityID,adm1,adm2);
                    for (int i=0;i<2;i++){
                        System.out.println(CityID.elementAt(i));
                    }*/
                    Login.setVisible(false);
                    Menu.setVisible(true);
                }
            }
        });

        Login.show();

    }
}

