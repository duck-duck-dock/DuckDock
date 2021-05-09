//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Random;
//
//public class mainfake {
//    private AllUser alluser;
//    public AllUser getAlluser() {
//        return alluser;
//    }
//    public mainfake(){
//        this.alluser=new AllUser();
//    }
//    public  static void main(String[] args) {
//        mainfake M=new mainfake();
//        LoginMenu Login = new LoginMenu(M.getAlluser());
//        MainMenu Menu = new MainMenu(new User());
//        Login.LogLoginJButton.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//                  if(Login.JudgeLogin()){
//                      Login.setVisible(false);
//                      Menu.setVisible(true);
//                  }
//          }
//      });
//
//       Login.show();
//
//    }
//}
//
