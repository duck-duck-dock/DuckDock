import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class OneToDoJPanel extends JPanel {
    public JCheckBox ChangeJCheckBox;//勾选代表已完成
    public JLabel ToDoJLabel;//待办事项文本输入框
    public JButton DeleteJButton;//待办事项删除按钮
    int index;//编号

    public OneToDoJPanel(User Userr,String AddText,int index) {
        this.setBackground(new Color(165, 222, 228));
        this.index=index;
        ChangeJCheckBox = new JCheckBox();
        ChangeJCheckBox.setBorderPainted(false);ChangeJCheckBox.setBackground(new Color(165, 222, 228));
        ChangeJCheckBox.setFocusPainted(false);
        ChangeJCheckBox.setSelected(Userr.getToDoLists().get(index).getFinishIns());
        ToDoJLabel = new JLabel(AddText);
        DeleteJButton = new JButton("删除");
        DeleteJButton.setBorderPainted(false);DeleteJButton.setFocusPainted(false);DeleteJButton.setBackground(Color.white);
        add(ChangeJCheckBox);
        add(ToDoJLabel);
        add(DeleteJButton);

        ChangeJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Userr.getToDoLists().get(index).setFinishIns(!ChangeJCheckBox.isSelected());

            }
        });

    }
    public OneToDoJPanel(User Userr,int index) {
        this.setBackground(new Color(165, 222, 228));
        this.index=index;
        ChangeJCheckBox = new JCheckBox();
        ChangeJCheckBox.setBorderPainted(false);ChangeJCheckBox.setBackground(new Color(165, 222, 228));
        ChangeJCheckBox.setFocusPainted(false);
        ChangeJCheckBox.setSelected(Userr.getToDoLists().get(index).getFinishIns());
        ToDoJLabel = new JLabel(Userr.getToDoLists().get(index).getContent());
        DeleteJButton = new JButton("删除");
        DeleteJButton.setBorderPainted(false);DeleteJButton.setFocusPainted(false);DeleteJButton.setBackground(Color.white);
        add(ChangeJCheckBox);
        add(ToDoJLabel);
        add(DeleteJButton);
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
