import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

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
        SinglWordUploadButton.setFocusPainted(false);//去边框
        SinglWordUploadButton.setBorderPainted(false);
        CsvUploadButton = new JButton("批量单词上传");
        CsvUploadButton.setFocusPainted(false);//去边框
        CsvUploadButton.setBorderPainted(false);
        BackButton = new JButton("返回");
        BackButton.setFocusPainted(false);//去边框
        BackButton.setBorderPainted(false);
        test=new JTextField(25);
        Box SetBox=Box.createVerticalBox();//盒式布局管理器，纵向

        this.add(SetBox);
        SetBox.add(Box.createVerticalStrut(30));
        SetBox.add(SinglWordUploadButton);
        SetBox.add(Box.createVerticalStrut(60));
        SetBox.add(CsvUploadButton);
        SetBox.add(Box.createVerticalStrut(60));
        SetBox.add(BackButton);
        SetBox.add(Box.createRigidArea(new Dimension(200,50)));
        SetBox.add(test);

        /*事件监听*/
        //用于选择文件
        this.CsvUploadButton.addActionListener(new CsvActionListener() );
    }

    //点击批量上传后的操作（选择文件，处理数据）
    int Uploadcsv(String filename){
        //获得选中的文件路径，打开文件，并读出所有的数据
        Csvfile=new File(filename);
        if(Csvfile.getName().endsWith(".csv")){
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
            return 1;
        }
        else return 0;
    }

    //批量操作事件监听实现
    class CsvActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0){
            JFileChooser fc=new JFileChooser("C:\\Users\\AAAAA\\Desktop");
            //设置过滤器，未完成
            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.getName().endsWith("csv")) {
                        return true;
                    } else {
                        return false;
                    }
                }

                @Override
                public String getDescription() {
                    return ".csv";
                }
            });
            int val=fc.showOpenDialog(null);//显示选择文件的窗口
            if(val==fc.APPROVE_OPTION){
                test.setText(fc.getSelectedFile().toString());//这里可以获得文件的路径
                int flag=Uploadcsv(fc.getSelectedFile().toString());//调用处理文件的函数
                if(flag==1)JOptionPane.showMessageDialog(null,"上传成功！","提示框",JOptionPane.PLAIN_MESSAGE);
                else JOptionPane.showMessageDialog(null,"文件类型错误","警告",JOptionPane.WARNING_MESSAGE);
            }
            else {
                test.setText("未选择路径！！");//暂时用来显示选中的文件路径
                JOptionPane.showMessageDialog(null,"请选择路径！！","警告",JOptionPane.WARNING_MESSAGE);
            }
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
        UploadButton.setFocusPainted(false);//去边框
        UploadButton.setBorderPainted(false);
        GiveupButton = new JButton("放弃上传");
        GiveupButton.setFocusPainted(false);//去边框
        GiveupButton.setBorderPainted(false);
        Box SetBox=Box.createVerticalBox();//盒式布局管理器，纵向

        this.add(SetBox);
        SetBox.add(Box.createRigidArea(new Dimension(200,50)));
        SetBox.add(WordText);
        SetBox.add(Box.createRigidArea(new Dimension(200,50)));
        SetBox.add(ExplainText);
        SetBox.add(Box.createVerticalStrut(60));
        SetBox.add(UploadButton);
        SetBox.add(Box.createVerticalStrut(60));
        SetBox.add(GiveupButton);

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
class:错题标签选择面板
*/
class LabelSelectPanel extends JPanel{
    /*变量*/
    JButton UpLoadButton;//上传图片用的按钮
    JButton SureButton;//确定按钮
    JButton CancelButton;//取消按钮

    //标签选择相关组件，暂时这么定了
    JComboBox SubjectComboBox1;//高数和专业课暂时未确定如何进行显示，先暂时认为需要考高数
    String[] s1={"--请选择--","政治","英语","高数","专业课"};
    JComboBox SubjectComboBox2;
    String[] s21={"--请选择--","毛概","思政","近现代史纲要","形式与政策"};
    String[] s22={"--请选择--","高等数学","线性代数","概率论"};
    JComboBox SubjectComboBox3;
    String[] s31={"--请选择--","选择题","填空题","简答题"};//政治
    String[] s32={"--请选择--","阅读理解","单项选择","作文","翻译"};//英语
    String[] s33={"--请选择--","选择题","填空题","解答题"};//高数
    String[] s34={"--请选择--","选择","填空","简答"};//专业课

    String str="";//错题图片的路径
    Problem problem;//错题
    Vector<Problem> ProblemList;//错题图片路径列表
    ArrayList Label=new ArrayList();//存放标签的，暂时存先存成字符串

    /*方法*/
    //初始化
    public void setup(User ThisUser){
        UpLoadButton=new JButton("选择上传图片");
        UpLoadButton.setFocusPainted(false);
        UpLoadButton.setBorderPainted(false);
        SureButton=new JButton("确定上传");
        SureButton.setFocusPainted(false);
        SureButton.setBorderPainted(false);
        CancelButton=new JButton("取消上传");
        CancelButton.setFocusPainted(false);
        CancelButton.setBorderPainted(false);
        SubjectComboBox1=new JComboBox();
        SubjectComboBox2=new JComboBox();
        SubjectComboBox3=new JComboBox();

        /*排版*///添加部件到当前面板
        {
            Box SetBox1=Box.createVerticalBox();
            Box SetBox2=Box.createHorizontalBox();
            Box SetBox3=Box.createHorizontalBox();
            Box SetBox4=Box.createHorizontalBox();
            JLabel t1=new JLabel("科目：");
            JLabel t2=new JLabel("具体科目：");
            JLabel t3=new JLabel("题型：");
            this.add(SetBox1);

            SetBox1.add(Box.createVerticalStrut(40));
            SetBox1.add(UpLoadButton);
            SetBox1.add(Box.createVerticalStrut(30));
            SetBox1.add(SetBox2);
            SetBox2.add(Box.createHorizontalStrut(40));
            SetBox2.add(t1);
            SetBox2.add(Box.createHorizontalGlue());
            SetBox2.add(SubjectComboBox1);
            SetBox1.add(Box.createVerticalStrut(30));
            SetBox1.add(SetBox3);
            SetBox3.add(Box.createHorizontalStrut(40));
            SetBox3.add(t2);
            SetBox3.add(Box.createHorizontalGlue());
            SetBox3.add(SubjectComboBox2);
            SetBox1.add(Box.createVerticalStrut(30));
            SetBox1.add(SetBox4);
            SetBox4.add(Box.createHorizontalStrut(40));
            SetBox4.add(t3);
            SetBox4.add(Box.createHorizontalGlue());
            SetBox4.add(SubjectComboBox3);
            SetBox1.add(Box.createVerticalStrut(40));
            SetBox1.add(SureButton);
            SetBox1.add(Box.createVerticalStrut(40));
            SetBox1.add(CancelButton);

            SubjectComboBox1.setEnabled(false);
            SubjectComboBox2.setEnabled(false);
            SubjectComboBox3.setEnabled(false);
        }

        //事件监听
        this.UpLoadButton.addActionListener(new ActionListener() {//选择图片的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc=new JFileChooser("C:\\Users\\AAAAA\\Desktop");
                fc.setFileFilter(new FileFilter() {//文件过滤器
                    @Override
                    public boolean accept(File f) {
                        if (f.getName().endsWith("jpeg")) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @Override
                    public String getDescription() {
                        return ".jpeg";
                    }
                });
                int val=fc.showOpenDialog(null);//显示选择文件的窗口
                if(val==fc.APPROVE_OPTION){
                    str = fc.getSelectedFile().toString();//获得图片的路径
                    problem.setProblemPosition(str);//问题路径保存
                    ThisUser.addProblem(problem);//添加问题图片
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
                    case 2:
                        SubjectComboBox3.setEnabled(true);
                        SubjectComboBox2.setEnabled(false);
                        AddItem(s32,SubjectComboBox3);
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
                        AddItem(s34,SubjectComboBox3);
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
    public LabelSelectPanel(User ThisUser){ setup(ThisUser);ThisUser.setProblems(ProblemList); }
}

/*
created by Han Zhao in 2021/05/04
class:显示单词的页面
*/
class WordViewPanel extends JPanel{
    Vector<Word> WordList;//当前单词本
    JButton LastButton;//上一页按钮
    JButton NextButton;//下一页按钮
    JButton BackButton;//返回按钮
    JTextArea ShowWord;//显示单词用的文本框
    int index=0;//显示时用的下标

    void setup(User ThisUser){//初始化
        LastButton =new JButton("上一个");
        NextButton=new JButton("下一个");
        BackButton =new JButton("返回");
        WordList=ThisUser.getWords();//获得单词表
        ShowWord =new JTextArea(30,100);//显示单词的文本框创建

        //第一页的设置
        for(index=0;index<WordList.size()&&index<30&&index>0;index++){
            String word=WordList.get(index).getFrontSide();
            String expression=WordList.get(index).getBackSide();
            String row=word+" "+expression;
            ShowWord.append(row);
        }

        //排版
        {
            ShowWord.setLineWrap(true);//自动换行
            ShowWord.isEnabled();//显示单词的文本框，设置成只能看，不能改
            JScrollPane jsp=new JScrollPane(ShowWord);//滑动显示

            Box SetBox1=Box.createVerticalBox();
            Box SetBox2=Box.createVerticalBox();
            Box SetBox3=Box.createHorizontalBox();
            this.add(SetBox1);
            SetBox1.add(SetBox2);
            SetBox1.add(Box.createHorizontalStrut(30));
            SetBox1.add(SetBox3);
            SetBox2.add(jsp);
            SetBox3.add(Box.createHorizontalStrut(150));
            SetBox3.add(LastButton);
            SetBox3.add(Box.createHorizontalStrut(100));
            SetBox3.add(NextButton);
            SetBox3.add(Box.createHorizontalStrut(150));
            SetBox3.add(BackButton);
        }

        //事件监听
        {
            LastButton.addActionListener(new ActionListener() {//上一页单词
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowWord.setText("");//清空文本框
                    int temp=0;
                    for(index=index-30;index<WordList.size()&&temp<30&&index>0;index++,temp++){
                        String word=WordList.get(index).getFrontSide();
                        String expression=WordList.get(index).getBackSide();
                        String row=word+" "+expression;
                        ShowWord.append(row);
                    }
                }
            });

            NextButton.addActionListener(new ActionListener() {//下一页单词
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowWord.setText("");//清空文本框
                    int temp=0;
                    for(;index<WordList.size()&&temp<30&&index>0;index++,temp++){
                        String word=WordList.get(index).getFrontSide();
                        String expression=WordList.get(index).getBackSide();
                        String row=word+" "+expression;
                        ShowWord.append(row);
                    }
                }
            });
        }
    }

    //构造函数，传递了当前的用户
    public WordViewPanel(User ThisUser){ setup(ThisUser); }
}

/*
created by Han Zhao in 2021/05/04
class:显示错题图片的页面
*/
class PictureViewPanel extends JPanel{
    Vector<Problem> ProblemList;//当前用户的错题图片列表
    JButton LastButton;
    JButton NextButton;
    JButton BackButton;
    int index=0;//指示应该读取图片的位置

    void setup(User ThisUser){
        ProblemList=new Vector<>();
        Problem p=new Problem();
        p.setProblemPosition("C:\\Users\\AAAAA\\Desktop\\8a5a20370815e97d35e71ac496387697.jpeg");
        ProblemList.add(p);

        LastButton =new JButton("上一个");
        NextButton=new JButton("下一个");
        BackButton =new JButton("返回");

//***************这里有点问题，如果不注释，那么一开始就直接看不见初始界面的按钮，不知道原因
        //ImageIcon imageIcon=new ImageIcon(ProblemList.get(index).getProblemPosition());//显示图片
        index++;//获得下一个
        //JLabel Picture=new JLabel(imageIcon);//显示图片用的

        //排版
        {
            Box SetBox1=Box.createVerticalBox();
            Box SetBox2=Box.createVerticalBox();
            Box SetBox3=Box.createHorizontalBox();
            this.add(SetBox1);
            SetBox1.add(SetBox2);
            SetBox1.add(Box.createHorizontalStrut(30));
            SetBox1.add(SetBox3);
            //SetBox2.add(Picture);//添加的图片
            SetBox3.add(Box.createHorizontalStrut(150));
            SetBox3.add(LastButton);
            SetBox3.add(Box.createHorizontalStrut(100));
            SetBox3.add(NextButton);
            SetBox3.add(Box.createHorizontalStrut(150));
            SetBox3.add(BackButton);
        }
        //事件监听
        {
            LastButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    index--;

                }
            });
            NextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }

    public  PictureViewPanel(User ThisUser){setup(ThisUser);}
}

//在上传面板中(UploadPage为上传主面板)
public class UploadPage extends JPanel {
    /*变量*/
    JButton BackButton;//后退按钮
    JButton WordUploadButton;//上传单词按钮
    JButton PictureUploadButton;//上传错题图片按钮
    JButton WordButton;//查看单词按钮
    JButton PictureButton;//查看错题图片按钮

    User ThisUser;//用户

    WordViewPanel WvPanel;//显示单词的页面
    PictureViewPanel PvPanel;//显示错题图片的页面
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
        WordUploadButton.setFocusPainted(false);//去边框
        WordUploadButton.setBorderPainted(false);
        PictureUploadButton = new JButton("上传错题图片");
        PictureUploadButton.setFocusPainted(false);//去边框
        PictureUploadButton.setBorderPainted(false);
        BackButton = new JButton("返回");
        BackButton.setFocusPainted(false);//去边框
        BackButton.setBorderPainted(false);
        WordButton=new JButton("查看单词");
        WordButton.setFocusPainted(false);//去边框
        WordButton.setBorderPainted(false);
        PictureButton=new JButton("查看错题");
        PictureButton.setFocusPainted(false);//去边框
        PictureButton.setBorderPainted(false);

        CardPanel = new JPanel();//用于实现面板跳转的面板
        CdLayout = new CardLayout();//卡片布局管理器
        CardPanel.setLayout(CdLayout);//设置面板的布局管理为卡片式

        MainPanel = new JPanel();//主面板
        Wd_UploadPanel= new WordUpLoadPanel();//创建面板
        SWd_UploadPanel =new SingleWordUploadPanel();//单个单词上传面板
        L_SelectPanel=new LabelSelectPanel(ThisUser);//图片上传面板
        WvPanel=new WordViewPanel(ThisUser);//单词查看面板
        PvPanel=new PictureViewPanel(ThisUser);//错题查看面板

        CardPanel.add(MainPanel,"Main");//卡片式布局的面板：主面板
        CardPanel.add(Wd_UploadPanel,"WordUpload");//卡片式布局的面板：上传单词
        CardPanel.add(SWd_UploadPanel,"SingleWordUpload");//卡片式布局的面板：上传单个单词
        CardPanel.add(L_SelectPanel,"LabelSelectPanel");//卡片式布局的面板：给错题添加标签
        CardPanel.add(WvPanel,"WordViewPanel");//显示单词的页面
        CardPanel.add(PvPanel,"PictureViewPanel");//显示错题图片的页面

        //排版
        {
            Box SetBox=Box.createVerticalBox();
            this.MainPanel.add(SetBox);
            SetBox.add(Box.createVerticalStrut(40));
            SetBox.add(WordUploadButton);
            SetBox.add(Box.createVerticalStrut(40));
            SetBox.add(PictureUploadButton);
            SetBox.add(Box.createVerticalStrut(40));
            SetBox.add(WordButton);
            SetBox.add(Box.createVerticalStrut(40));
            SetBox.add(PictureButton);
            SetBox.add(Box.createVerticalStrut(40));
            SetBox.add(BackButton);
            this.add(CardPanel);//将卡片布局放到当前上传主panel中
        }

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

        this.WordButton.addActionListener(new ActionListener() {//查看单词
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"WordViewPanel");
            }
        });
        this.WvPanel.BackButton.addActionListener(new ActionListener() {//返回主面板
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"Main");
            }
        });

        this.PictureButton.addActionListener(new ActionListener() {//查看错题图片
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"PictureViewPanel");
            }
        });
        this.PvPanel.BackButton.addActionListener(new ActionListener() {//返回主面板
            @Override
            public void actionPerformed(ActionEvent e) {
                CdLayout.show(CardPanel,"Main");
            }
        });
    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public UploadPage(User thisUser) {
        ThisUser=thisUser;
        initGUI();
    }//构造函数，创建时直接调用了
}