import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class ToDoList extends JPanel implements ActionListener {
    private User Userr;
    JButton AddButton;//添加按钮
    JTextField AddJTextField;//添加文本
    JPanel ToDoListJPanel;//待办事项面板
    JScrollPane RollJScrollPane;//滚动面板
    Vector<OneToDoJPanel> todos;//所有todo

    private void initDelete() {
        for (OneToDoJPanel item : todos) {
            item.DeleteJButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for(UserToDoList u:Userr.getToDoLists()){
                        if(u.index==item.index){
                            Userr.getToDoLists().remove(u);
                            todos.remove(item);
                            repaintCom();
                            UpdateUI();
                            return;
                        }
                    }
                }
            });
        }
    }

    private void initGUI() {
        todos=new Vector<>();
        ToDoListJPanel = new JPanel();
        ToDoListJPanel.setBackground(new Color(165, 222, 228));
        ToDoListJPanel.setLayout(new BoxLayout(ToDoListJPanel, BoxLayout.Y_AXIS));//盒子布局.从上到下
        ToDoListJPanel.setBorder(new EmptyBorder(0,0,0,0));
        RollJScrollPane = new JScrollPane(ToDoListJPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        RollJScrollPane.setBackground(new Color(165, 222, 228));RollJScrollPane.setBorder(new EmptyBorder(0,0,0,0));
        add(RollJScrollPane);
        AddButton = new JButton("+");AddButton.setFocusPainted(false);AddButton.setBackground(Color.white);AddButton.setBorderPainted(false);
        AddJTextField = new JTextField(15);
        AddButton.addActionListener(this);
        AddJTextField.addActionListener(this);
        JPanel jps = new JPanel();jps.setBackground(new Color(165, 222, 228));
        jps.add(AddButton);
        jps.add(AddJTextField);
        add(jps, BorderLayout.NORTH);
        this.setSize(300, 220);//大小
        this.setVisible(true);
        for (int i = 0; i < Userr.getToDoLists().size(); i++) {//初始化user中的内容
            OneToDoJPanel tmp = new OneToDoJPanel(Userr, i);
            todos.add(tmp);
            ToDoListJPanel.add(tmp);
        }
        initDelete();

    }

    //刷新界面函数
    private void UpdateUI() {
        SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口
        JScrollBar jsb = RollJScrollPane.getVerticalScrollBar();//得到垂直滚动条
        jsb.setValue(jsb.getMaximum());//把滚动条位置设置到最下面
    }

    private void repaintCom(){//重绘组件
        for(UserToDoList u:Userr.getToDoLists()){
            OneToDoJPanel tmp=new OneToDoJPanel(Userr, u.Content, u.index);
            ToDoListJPanel.add(tmp);//添加1个自己定义的面板组件
        }
        ToDoListJPanel.removeAll();

        for(OneToDoJPanel p:todos){
            ToDoListJPanel.add(p);//添加1个自己定义的面板组件
            p.setVisible(true);
        }
    }


    //构造函数
    public ToDoList(User user) {
        Userr = new User();
//        Userr = user;
//      UserToDoList t = new UserToDoList();
//        t.setFinishIns(true);
//        t.setContent("vsd");
//       Userr.getToDoLists().add(t);
//       UserToDoList t1 = new UserToDoList();
//        t1.setFinishIns(true);
//       t1.setContent("vsdfdd");
//       t1.setIndex(1);
//       Userr.getToDoLists().add(t1);
       //System.out.println(Userr.getToDoLists().get(1).index);
        initGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        if (jb == AddButton) {//当点击添加按钮时
            String AddText = AddJTextField.getText();
            UserToDoList tempToDoList = new UserToDoList();
            int index=Userr.getToDoLists().size();
            tempToDoList.setContent(AddText);
            tempToDoList.setFinishIns(false);
            Userr.getToDoLists().add(tempToDoList);
            OneToDoJPanel tmp=new OneToDoJPanel(Userr, AddText, index);
            todos.add(tmp);
            ToDoListJPanel.add(tmp);//添加1个自己定义的面板组件
            Userr.getToDoLists().lastElement().index=index;
            AddJTextField.setText(null);
            initDelete();
            UpdateUI();//刷新界面
        }
    }
}
