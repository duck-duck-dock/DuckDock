import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;
import org.jdesktop.swingx.JXDatePicker;
import java.util.Date;


public class LoginMenu extends JFrame{//zyx:登陆界面
    private AllUser Users;
    JPanel LogJPanel,RegJPanel,Reg2JPanel;//登录,注册面板
    JLabel LogNameJLabel,LogPwdJLabel,LogMsgLabel;//登录用户名，密码，消息标签
    JLabel RegNameJLabel,RegPwdJLabel,RegRePwdJLabel,RegMsgLabel;//注册用户名，密码，确认密码，消息标签
    JLabel RegGradeJLabel,RegSchoolJLabel,RegDreamSchoolJLabel,RegTimeJLabel;//年级，学校，目标院校，考研时间
    JTextField LogTxtNameJTextField,RegTxtNameJTextField;//登录用户名，注册用户名文本框
    JPasswordField LogTxtPwdJPasswordField,RegTxtPwdJPasswordField,RegReTxtPwdJPasswordField;//登录密码框，注册密码，确认密码框
    JButton LogRegJButton,LogLoginJButton,LogExitJButton,RegRegisterJButton,RegCancelJButton;//登录（注册，登录，退出）按钮,注册（注册，取消）按钮
    JButton RegReserveJButton;//保存按钮
    Date RegOfTestDate;//考研时间
    JXDatePicker DatePick;
    JComboBox RegGradeJComboBox,RegSchoolJComboBox,RegDreamSchoolJComboBox;//年级,学校
    CardLayout cl;
    private void initLogTools() {
        LogNameJLabel=new JLabel("用户名");
        LogPwdJLabel=new JLabel("密   码");
        //创建一个长度为20 的文本框
        LogTxtNameJTextField=new JTextField(20);
        //创建密码框长度为20
        LogTxtPwdJPasswordField=new JPasswordField(20);
        //设置密码框显示的字符为*
        LogTxtPwdJPasswordField.setEchoChar('*');
        //创建三个按钮
        LogRegJButton=new JButton("注册");
        LogLoginJButton= new JButton("登录");
        LogExitJButton= new JButton("退出");
        LogRegJButton.setBackground(Color.white);
        LogRegJButton.setBorderPainted(false);LogRegJButton.setFocusPainted(false);
        LogLoginJButton.setBackground(Color.white);
        LogLoginJButton.setBorderPainted(false);LogLoginJButton.setFocusPainted(false);
        LogExitJButton.setBackground(Color.white);
        LogExitJButton.setBorderPainted(false);LogExitJButton.setFocusPainted(false);
        //显示信息的标签
        LogMsgLabel= new JLabel();
        LogNameJLabel.setBounds(200,100,100,50);
        LogTxtNameJTextField.setBounds(300,100,150,40);
        LogPwdJLabel.setBounds(200,200,60,25);
        LogTxtPwdJPasswordField.setBounds(300,200,150,40);
        LogMsgLabel.setBounds(400,300,200,40);
        LogRegJButton.setBounds(200,400,100,40);
        LogLoginJButton.setBounds(350,400,100,40);
        LogExitJButton.setBounds(500,400,100,40);
        //添加所有组件
        LogJPanel.add(LogNameJLabel);
        LogJPanel.add(LogTxtNameJTextField);
        LogJPanel.add(LogPwdJLabel);
        LogJPanel.add(LogTxtPwdJPasswordField);
        LogJPanel.add(LogMsgLabel);
        LogJPanel.add(LogRegJButton);
        LogJPanel.add(LogLoginJButton);
        LogJPanel.add(LogExitJButton);

    }
    private void initRegTools() {
        RegNameJLabel=new JLabel("用户名");
        RegPwdJLabel=new JLabel("密   码");
        RegRePwdJLabel=new JLabel("确认密码");
        //创建一个长度为20 的文本框
        RegTxtNameJTextField=new JTextField(20);
        //创建两个密码框长度为20
        RegTxtPwdJPasswordField=new JPasswordField(20);
        RegReTxtPwdJPasswordField=new JPasswordField(20);
        //设置密码框显示的字符为*
        RegTxtPwdJPasswordField.setEchoChar('*');
        RegReTxtPwdJPasswordField.setEchoChar('*');
        //创建两个按钮
        RegRegisterJButton=new JButton("注册");
        RegRegisterJButton.setBackground(Color.white);
        RegRegisterJButton.setBorderPainted(false);RegRegisterJButton.setFocusPainted(false);
        RegCancelJButton= new JButton("取消");
        RegCancelJButton.setBackground(Color.white);
        RegCancelJButton.setBorderPainted(false);RegCancelJButton.setFocusPainted(false);
        //显示信息的标签
        RegMsgLabel= new JLabel();

        RegNameJLabel.setBounds(200,100,60,25);
        RegPwdJLabel.setBounds(200,180,120,25);
        RegRePwdJLabel.setBounds(200,260,60,25);
        RegTxtNameJTextField.setBounds(300,100,150,40);
        RegTxtPwdJPasswordField.setBounds(300,180,150,40);
        RegReTxtPwdJPasswordField.setBounds(300,260,150,40);
        RegRegisterJButton.setBounds(200,400,100,40);
        RegCancelJButton.setBounds(500,400,100,40);
        RegMsgLabel.setBounds(400,320,180,25);

        //添加所有组件
        RegJPanel.add(RegNameJLabel);
        RegJPanel.add(RegPwdJLabel);
        RegJPanel.add(RegRePwdJLabel);
        RegJPanel.add(RegTxtNameJTextField);
        RegJPanel.add(RegTxtPwdJPasswordField);
        RegJPanel.add(RegReTxtPwdJPasswordField);
        RegJPanel.add(RegRegisterJButton);
        RegJPanel.add(RegCancelJButton);
        RegJPanel.add(RegMsgLabel);
    }
    private void initReg2Tools(){
        RegGradeJLabel=new JLabel("年级：");
        RegSchoolJLabel=new JLabel("所在学校：");
        RegDreamSchoolJLabel=new JLabel("目标院校：");
        RegTimeJLabel=new JLabel("考研时间：");
        RegReserveJButton=new JButton("保存");
        RegReserveJButton.setFocusPainted(false);RegReserveJButton.setBorderPainted(false);
        RegReserveJButton.setBackground(Color.white);
        Date RegOfTestDate=new Date();
        DatePick = new JXDatePicker();
        DatePick.setDate(RegOfTestDate);
         RegGradeJComboBox=new JComboBox();
        RegGradeJComboBox.addItem("1");
        RegGradeJComboBox.addItem("2");
        RegGradeJComboBox.addItem("3");
        RegGradeJComboBox.addItem("4");
         RegSchoolJComboBox=new JComboBox();
        RegSchoolJComboBox.addItem("学校1");
        RegSchoolJComboBox.addItem("学校2");
        RegSchoolJComboBox.addItem("学校3");
        RegSchoolJComboBox.addItem("学校4");
        RegDreamSchoolJComboBox=new JComboBox();
        RegDreamSchoolJComboBox.addItem("学校1");
        RegDreamSchoolJComboBox.addItem("学校2");
        RegDreamSchoolJComboBox.addItem("学校3");
        RegDreamSchoolJComboBox.addItem("学校4");
        RegGradeJLabel.setBounds(100,100,100,25);
        RegSchoolJLabel.setBounds(100,150,100,25);
        RegDreamSchoolJLabel.setBounds(100,200,100,25);
        RegTimeJLabel.setBounds(100,250,100,25);
        RegGradeJComboBox.setBounds(200,100,100,25);
        RegSchoolJComboBox.setBounds(200,150,100,25);
        RegDreamSchoolJComboBox.setBounds(200,200,100,25);
        RegReserveJButton.setBounds(200,300,100,25);
        DatePick.setBounds(200, 250, 200, 25);
        Reg2JPanel.add(RegGradeJLabel);
        Reg2JPanel.add(RegSchoolJLabel);
        Reg2JPanel.add(RegDreamSchoolJLabel);
        Reg2JPanel.add(RegTimeJLabel);
        Reg2JPanel.add(RegGradeJComboBox);
        Reg2JPanel.add(RegSchoolJComboBox);
        Reg2JPanel.add(RegDreamSchoolJComboBox);
        Reg2JPanel.add(RegReserveJButton);
        Reg2JPanel.add(DatePick);
    }
    private void initTools() {
        JPanel CardsJPanel = new JPanel(new CardLayout());CardsJPanel.setBackground(new Color(165, 222, 228));
        LogJPanel = new JPanel(null);LogJPanel.setBackground(new Color(165, 222, 228));
        RegJPanel = new JPanel(null);RegJPanel.setBackground(new Color(165, 222, 228));
        Reg2JPanel = new JPanel(null);Reg2JPanel.setBackground(new Color(165, 222, 228));
        CardsJPanel.add(LogJPanel, "card1");
        CardsJPanel.add(RegJPanel, "card2");
        CardsJPanel.add(Reg2JPanel, "card3");
        CardLayout cl = (CardLayout) (CardsJPanel.getLayout());
        cl.show(CardsJPanel, "card1");
        initLogTools();
        initRegTools();
        initReg2Tools();
        this.add(CardsJPanel);
        this.setSize(800, 600);
        this.setVisible(true);
        LogExitJButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        LogRegJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogTxtNameJTextField.setText(null);
                LogTxtPwdJPasswordField.setText(null);
                cl.show(CardsJPanel, "card2");
            }
        });
        RegRegisterJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置信息标签为空 清楚原来的历史信息
                RegMsgLabel.setText("");
                //获取用户输入的用户名
                String strName = RegTxtNameJTextField.getText();
                if (strName == null || strName.equals("")) {

                    RegMsgLabel.setText("用户名不能为空");
                    return;
                }
                //获取用户名密码
                String strPwd = new String(RegTxtPwdJPasswordField.getPassword());
                if (strPwd == null || strPwd.equals("")) {

                    RegMsgLabel.setText("密码不能为空");
                    return;
                }
                String strRePwd = new String(RegReTxtPwdJPasswordField.getPassword());
                if (strRePwd == null || strRePwd.equals("")) {

                    RegMsgLabel.setText("确认密码不能为空");
                    return;
                }

                //判断确认密码是否跟密码相同
                if (!strRePwd.equals(strPwd)) {

                    RegMsgLabel.setText("确认密码跟密码不同");
                    return;
                }
                //判断是否没有初始化
                //判断是否没有用户
                if(Users!=null) {//判断是否用户名已存在
                    for (int i = 0; i < Users.getUserNum(); i++) {
                        if (strName.equals(Users.getUsers().get(i).getUserName())) {
                            RegMsgLabel.setText("该用户名已被使用");
                            return;
                        }
                    }
                }
                else{ }
                //保存用户名和密码，并创立用户ID
                User tempUser=new User();
                tempUser.setUserName(strName);
                tempUser.setPassword(strPwd);
                tempUser.setUserID(RandomStringUtils.randomAlphanumeric(5));
                Users.getUsers().add(tempUser);
                Users.setUserNum(Users.getUserNum()+1);
                JOptionPane.showMessageDialog(null, "注册成功！");
                RegTxtNameJTextField.setText(null);
                RegTxtPwdJPasswordField.setText(null);
                RegReTxtPwdJPasswordField.setText(null);
                cl.show(CardsJPanel, "card3");//转到注册2界面
            }
        });
        RegCancelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegTxtNameJTextField.setText(null);
                RegTxtPwdJPasswordField.setText(null);
                RegReTxtPwdJPasswordField.setText(null);
                cl.show(CardsJPanel, "card1");//转到登录界面
            }
        });
        RegReserveJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Users.getUsers().get(Users.getUserNum()-1).setGrade(Integer.parseInt(String.valueOf(RegGradeJComboBox.getSelectedItem())));
                Users.getUsers().get(Users.getUserNum()-1).setSchool((String)RegSchoolJComboBox.getSelectedItem());
                Users.getUsers().get(Users.getUserNum()-1).setDreamSchool((String)RegDreamSchoolJComboBox.getSelectedItem());
                Users.getUsers().get(Users.getUserNum()-1).setDateOfTest(DatePick.getDate());
                JOptionPane.showMessageDialog(null, "保存成功！");
                cl.show(CardsJPanel, "card1");
            }
        });
    }
    private void initGUI(){//zyx:初始化界面
        initTools();
    }
    /*
        created by LHM in 2021/04/17
        Abstract:
        判断用户名和密码是否匹配
        Return value:
        boolean类型，表示是否查到此用户名和密码
        */
    public boolean JudgeLogin(){
        //设置信息标签为空 清楚原来的历史信息
        LogMsgLabel.setText("");
        //获取用户输入的用户名
        String strName = LogTxtNameJTextField.getText();
        //获取密码
        String strPwd = new String(LogTxtPwdJPasswordField.getPassword());
        if ((strName == null && strPwd == null) || (strName.equals("") && strPwd.equals(""))) {
            LogMsgLabel.setText("用户名密码不能为空");
            return false;
        }
        if (strName == null || strName.equals("")) {
            LogMsgLabel.setText("用户名不能为空");
            return false;
        }
        if (strPwd == null || strPwd.equals("")) {
            LogMsgLabel.setText("密码不能为空");
            return false;
        }
       //判断密码是否与用户名相匹配
        for (int i = 0; i < Users.getUserNum(); i++) {
            if (strName.equals(Users.getUsers().get(i).getUserName()) && strPwd.equals(Users.getUsers().get(i).getPassword())) {
                JOptionPane.showMessageDialog(null, "登录成功！");
                return true;
            }
        }
        LogMsgLabel.setText("用户名与密码不匹配");
        return false;
    }
    public LoginMenu(AllUser alluser){
        super("登录界面");
        Users = new AllUser();
        Users = alluser;
        initGUI();

    }
}
