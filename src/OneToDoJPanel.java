import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class OneToDoJPanel extends JPanel {
    public JCheckBox ChangeJCheckBox;//勾选代表已完成
    public JLabel ToDoJLabel;//待办事项文本输入框
    public JButton DeleteJButton;//待办事项删除按钮
    int index;//编号

    public OneToDoJPanel(User Userr,String AddText,int index) {
        this.setBorder(BorderFactory.createLineBorder(new Color(38, 145, 196)));
        this.setPreferredSize(new Dimension(430,40));
        this.setMaximumSize(new Dimension(430,40));
        this.setMinimumSize(new Dimension(430,40));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(88,178,220));
        this.index=index;
        ChangeJCheckBox = new JCheckBox();
        ChangeJCheckBox.setBorderPainted(false);ChangeJCheckBox.setBackground(new Color(88,178,220));
        ChangeJCheckBox.setFocusPainted(false);
//        ChangeJCheckBox.setSelected(Userr.getToDoLists().get(index-1).getFinishIns());
        ToDoJLabel = new JLabel(AddText);
        DeleteJButton = new JButton("删除");
        DeleteJButton.setBorderPainted(false);DeleteJButton.setFocusPainted(false);DeleteJButton.setBackground(Color.white);
        add(ChangeJCheckBox,BorderLayout.WEST);
        add(ToDoJLabel,BorderLayout.CENTER);
        add(DeleteJButton,BorderLayout.EAST);

        ChangeJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Userr.getToDoLists().get(index).setFinishIns(!ChangeJCheckBox.isSelected());

            }
        });

    }
    public OneToDoJPanel(User Userr,int index) {
        this.setBorder(BorderFactory.createLineBorder(new Color(38, 145, 196)));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(88,178,220));
        this.setPreferredSize(new Dimension(430,40));
        this.setMaximumSize(new Dimension(430,40));
        this.setMinimumSize(new Dimension(430,40));
        this.index=index;
        ChangeJCheckBox = new JCheckBox();
        ChangeJCheckBox.setBorderPainted(false);ChangeJCheckBox.setBackground(new Color(88,178,220));
        ChangeJCheckBox.setFocusPainted(false);
        ChangeJCheckBox.setSelected(Userr.getToDoLists().get(index).getFinishIns());
        ToDoJLabel = new JLabel(Userr.getToDoLists().get(index).getContent());
        DeleteJButton = new JButton("删除");
        DeleteJButton.setBorderPainted(false);DeleteJButton.setFocusPainted(false);DeleteJButton.setBackground(Color.white);
        add(ChangeJCheckBox,BorderLayout.WEST);
        add(ToDoJLabel,BorderLayout.CENTER);
        add(DeleteJButton,BorderLayout.EAST);
//        DeleteJButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Userr.getToDoLists().remove(index);
//
//            }
//        });
        ChangeJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Userr.getToDoLists().get(index).setFinishIns(!ChangeJCheckBox.isSelected());

            }
        });
    }

    public JCheckBox getChangeJCheckBox() {
        return ChangeJCheckBox;
        }
    public JButton getDeleteJButton() {
        return DeleteJButton;
        }
    public JLabel  getToDoJLabel() {
        return ToDoJLabel;
        }

}
