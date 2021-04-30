import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/*
created by Han Zhao in 2021/04/02
class:单词上传面板
*/
class WordUpLoadPanel extends JPanel {
    /*变量*/
    JButton SinglWordUploadButton;//单个单词上传，点击后进入对应界面
    JButton CsvUploadButton;//批量单词上传
    JButton BackButton;//返回上传面板UpLoadPage
    JTextField test;
    File Csvfile;//批量单词的csv文件
    Map<String,String> Wordlist=new HashMap<String,String>();

    /*方法*/

    //初始化
    public void setup(){
        SinglWordUploadButton =new JButton("单个单词上传");
        CsvUploadButton = new JButton("批量单词上传");
        BackButton = new JButton("返回");
        test=new JTextField(25);

        this.add(SinglWordUploadButton);//添加按钮
        this.add(CsvUploadButton);
        this.add(BackButton);
        this.add(test);

        /*事件监听*/
        //用于选择文件
        this.CsvUploadButton.addActionListener(new CsvActionListener() );
    }

    //点击批量上传后的操作（选择文件，处理数据）
    void Uploadcsv(String filename){
        //获得选中的文件路径，打开文件，并读出所有的数据
        Csvfile=new File(filename);
        BufferedReader readcsv=null;
        try{
            readcsv=new BufferedReader(new FileReader(Csvfile));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        String line="";
        try{
            while ((line = readcsv.readLine()) != null) // 读取到的内容给line变量
            {
                String[] item=line.split(",");
                Wordlist.put(item[0],item[1]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //批量操作事件监听实现
    class CsvActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0){
            JFileChooser fc=new JFileChooser("C:\\Users\\AAAAA\\Desktop");
            //fc.setFileFilter();//设置过滤器，未完成
            int val=fc.showOpenDialog(null);//显示选择文件的窗口
            if(val==fc.APPROVE_OPTION){
                test.setText(fc.getSelectedFile().toString());//这里可以获得文件的路径
                Uploadcsv(fc.getSelectedFile().toString());//调用处理文件的函数
            }
            else test.setText("weixuanze!!!!!!!!");//暂时用来显示选中的文件路径
        }
    }
    //构造函数
    public WordUpLoadPanel(){
        setup();
    }
}

/*
created by Han Zhao in 2021/04/02
class:单个单词上传面板
*/
class SingleWordUploadPanel extends JPanel{
    /*变量*/
    JTextField WordText;//单词输入文本框
    JTextField ExplainText;//解释输入（暂时未定如何输入解释的格式）文本框
    String Word=new String("");
    String Explain=new String("");//单词和解释字符串
    JButton UploadButton;//确定上传按钮
    JButton GiveupButton;//放弃上传按钮，返回单词上传面板（WordULPanel）
    //提示弹窗(上传成功或者失败)，触发后返回单词上传面板或者单个单词上传面板（未定）

    /*方法*/
    //初始化
    public void setup() {
        WordText = new JTextField(18);
        WordText.setForeground(Color.lightGray);
        WordText.setText("--请输入单词--");
        ExplainText = new JTextField(18);
        ExplainText.setForeground(Color.lightGray);
        ExplainText.setText("--请输入解释--");
        UploadButton = new JButton("确定上传");
        GiveupButton = new JButton("放弃上传");

        this.add(WordText);//添加部件到面板上
        this.add(ExplainText);
        this.add(UploadButton);
        this.add(GiveupButton);

        /*事件监听*/
        //单词相关
        this.WordText.addFocusListener(new FocusAdapter() {//焦点
            @Override
            public void focusGained(FocusEvent e) {
                if(WordText.getText().equals("--请输入单词--")){
                    System.out.println(WordText.getText().toString());
                    WordText.setText("");
                    WordText.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(WordText.getText().equals("")){
                    WordText.setForeground(Color.lightGray);
                    WordText.setText("--请输入单词--");
                }
                else Word=WordText.getText().toString();

            }
        });
        //解释相关
        this.ExplainText.addFocusListener(new FocusAdapter() {//焦点
            @Override
            public void focusGained(FocusEvent e) {
                if(ExplainText.getText().equals("--请输入解释--")){
                    ExplainText.setText("");
                    ExplainText.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(ExplainText.getText().equals("")){
                    ExplainText.setForeground(Color.lightGray);
                    ExplainText.setText("--请输入解释--");
                }
                 else Explain=ExplainText.getText().toString();
            }
        });
        //确认上传成功弹窗
        this.UploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Explain.length()>0&&Word.length()>0){
                    JOptionPane.showMessageDialog(null,"上传成功！","提示框",JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"有信息未填写！请填写完整","警告",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    //构造函数
    public SingleWordUploadPanel(){
        setup();
    }
}

/*
created by Han Zhao in 2021/04/02
class:错题标签选择面板(暂时未定如何选择)
*/
class LabelSelectPanel extends JPanel{
    /*变量*/
    JButton UpLoadButton;//上传图片用的按钮
    JButton SureButton;//确定按钮
    JButton CancelButton;//取消按钮

    //标签选择相关组件，暂时这么定了（专业课根据注册时的信息进行选择，大概）
    JComboBox SubjectComboBox1;//高数和专业课暂时未确定如何进行显示，先暂时认为需要考高数
    String[] s1={"--请选择--","政治","英语","高数","专业课"};
    JComboBox SubjectComboBox2;
    String[] s21={"--请选择--","毛概","思政","近现代史纲要","形式与政策"};
    String[] s22={"--请选择--","高等数学","线性代数","概率论"};
    JComboBox SubjectComboBox3;
    String[] s31={"--请选择--","z选择题","填空题","简答题"};//政治
    String[] s32={"--请选择--","e阅读理解","单项选择","作文","翻译"};//英语
    String[] s33={"--请选择--","m选择题","填空题","解答题"};//高数
    String[] s34={"--请选择--","p选择","填空","简答"};//专业课
    //JComboBox SubjectComboBox4;

    String str="";//错题图片的路径
    ArrayList Label=new ArrayList();//存放标签的，暂时存先存成字符串

    /*方法*/
    //初始化
    public void setup(){
        UpLoadButton=new JButton("选择上传图片");
        SureButton=new JButton("确定上传");
        CancelButton=new JButton("取消上传");
        SubjectComboBox1=new JComboBox();
        SubjectComboBox2=new JComboBox();
        SubjectComboBox3=new JComboBox();
        //SubjectComboBox4=new JComboBox();

        this.add(UpLoadButton);//添加部件到当前面板
        this.add(new JLabel("科目："));
        this.add(SubjectComboBox1);
        this.add(new JLabel("具体科目："));
        this.add(SubjectComboBox2);
        this.add(new JLabel("题型："));
        this.add(SubjectComboBox3);
        //this.add(SubjectComboBox4);
        this.add(SureButton);
        this.add(CancelButton);

        SubjectComboBox1.setEnabled(false);
        SubjectComboBox2.setEnabled(false);
        SubjectComboBox3.setEnabled(false);

        //事件监听
        this.UpLoadButton.addActionListener(new ActionListener() {//选择图片的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc=new JFileChooser("C:\\Users\\AAAAA\\Desktop");
                //fc.setFileFilter();//设置过滤器，未完成
                int val=fc.showOpenDialog(null);//显示选择文件的窗口
                if(val==fc.APPROVE_OPTION){
                    str = fc.getSelectedFile().toString();//获得图片的路径
                    SubjectComboBox1.setEnabled(true);
                    AddItem(s1,SubjectComboBox1);
                }
            }
        });

        this.SubjectComboBox1.addItemListener(new ItemListener() {//下拉框监听
            @Override
            public void itemStateChanged(ItemEvent e) {
                int flag=SubjectComboBox1.getSelectedIndex();
                SubjectComboBox2.removeAllItems();
                SubjectComboBox3.removeAllItems();
                switch (flag){
                    case 0:
                        break;
                    case 1:
                        SubjectComboBox2.setEnabled(true);
                        SubjectComboBox3.setEnabled(true);
                        AddItem(s21,SubjectComboBox2);
                        AddItem(s31,SubjectComboBox3);
                        break;
                    case 3:
                        SubjectComboBox2.setEnabled(true);
                        SubjectComboBox3.setEnabled(true);
                        AddItem(s22,SubjectComboBox2);
                        AddItem(s32,SubjectComboBox3);
                        break;
                    default:
                        SubjectComboBox3.setEnabled(true);
                        SubjectComboBox2.setEnabled(false);
                        AddItem(s32,SubjectComboBox3);
                        break;
                }
            }
        });

        this.SureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Label.add(SubjectComboBox1.getSelectedItem().toString());
                Label.add(SubjectComboBox3.getSelectedItem().toString());
                if(SubjectComboBox1.getSelectedIndex()==1||SubjectComboBox1.getSelectedIndex()==3){
                    Label.add(SubjectComboBox2.getSelectedItem().toString());
                }
                else Label.add("");
                int i=0;
                for(i=0;i<Label.size();i++){
                    if(Label.get(i).equals("--请选择--"))break;
                }
                if(i<Label.size()){
                    JOptionPane.showMessageDialog(null,"还有标签未选择","警告",JOptionPane.WARNING_MESSAGE);
                    Label.clear();
                }
                else {
                    JOptionPane.showMessageDialog(null,"上传成功","提示",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    //添加选项到下拉列表
    public void AddItem(String[] temp,JComboBox cb){
        for(int i=0;i<temp.length;i++){
            cb.addItem(temp[i]);
        }
    }

    //构造函数
    public LabelSelectPanel(){ setup();}//初始化函数
}

//在上传面板中(UploadPage为上传主面板)
public class UploadPage extends JPanel {
    /*变量*/
    JButton BackButton;//后退按钮
    JButton WordUploadButton;//上传单词按钮
    JButton PictureUploadButton;//上传错题图片按钮

    WordUpLoadPanel Wd_UploadPanel;//用于上传单词的面板
    SingleWordUploadPanel SWd_UploadPanel;//用于上传单个单词的面板
    LabelSelectPanel L_SelectPanel;//用于给错题上标签的面板
    JPanel MainPanel;//主面板

    JPanel CardPanel;//卡片式布局管理面板
    CardLayout CdLayout;//卡片式布局管理器

    /*方法*/

    /*初始化界面*/
    private void initGUI() {
        WordUploadButton = new JButton("上传单词");
        PictureUploadButton = new JButton("上传错题图片");
        BackButton = new JButton("返回");

        CardPanel = new JPanel();//用于实现面板跳转的面板
        CdLayout = new CardLayout();//卡片布局管理器
        CardPanel.setLayout(CdLayout);//设置面板的布局管理为卡片式

        MainPanel = new JPanel();//主面板
        Wd_UploadPanel= new WordUpLoadPanel();//创建面板
        SWd_UploadPanel =new SingleWordUploadPanel();
        L_SelectPanel=new LabelSelectPanel();

        CardPanel.add(MainPanel,"Main");//卡片式布局的面板：主面板
        CardPanel.add(Wd_UploadPanel,"WordUpload");//卡片式布局的面板：上传单词
        CardPanel.add(SWd_UploadPanel,"SingleWordUpload");//卡片式布局的面板：上传单个单词
        CardPanel.add(L_SelectPanel,"LabelSelectPanel");//卡片式布局的面板：给错题添加标签

        this.MainPanel.add(WordUploadButton);
        this.MainPanel.add(PictureUploadButton);
        this.MainPanel.add(BackButton);
        this.add(CardPanel);//将卡片布局放到当前上传主panel中

        /*事件监听*/
        WordUploadButton.addActionListener(new ActionListener() {//从主面板转到单词上传面板
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"WordUpload");//显示单词上传面板
            }
        });

        this.Wd_UploadPanel.BackButton.addActionListener(new ActionListener() {//从单词上传面板回到主面板，复原主面板
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"Main");
            }
        });

        PictureUploadButton.addActionListener(new ActionListener() {//从主面板转到错题上传面板
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"LabelSelectPanel");
            }
        });

        this.L_SelectPanel.CancelButton.addActionListener(new ActionListener() {//从错题上传面板转到主面板
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"Main");
            }
        });

        this.Wd_UploadPanel.SinglWordUploadButton.addActionListener(new ActionListener() {//从单词上传界面转到单个单词上传界面
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"SingleWordUpload");
            }
        });

        this.SWd_UploadPanel.GiveupButton.addActionListener(new ActionListener() {//从单个单词上传界面转到单词上传界面
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"WordUpload");
            }
        });
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public UploadPage() {
        initGUI();
    }//构造函数，创建时直接调用了
}