import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Vector;

/*
created by Li Wen in 2021/04/1

 */
public class EnglishPanel extends JPanel {
    JButton QueryButton;        //Li Wen: 打开查单词页面按键
    JButton MemoryButton;       //Li Wen: 打开背单词页面按键
    JButton MyListButton;       //Li Wen: 打开我的生词本页面按键
    JTextField QueryInputTextField; //Li Wen: 查单词模式：用户输入框
    JButton QueryStartButton;   //Li Wen: 查单词模式：开始查询按键
    JButton QueryAddButton;     //Li Wen: 查单词模式：加入生词本按键
    JButton QueryBackButton;    //Li Wen: 查单词模式：返回按键
    JTextPane  QueryWordText;   //Li Wen: 查单词模式：单词显示
    JTextPane  QueryMeanText;   //Li Wen: 查单词模式：单词释义
    JTextPane  QuerySentenceText;   //Li Wen: 查单词模式：单词例句和翻译
    JButton MemEasyButton;      //Li Wen: 背单词模式：容易按键
    JButton MemMediumButton;    //Li Wen: 背单词模式：模糊按键
    JButton MemHardButton;      //Li Wen: 背单词模式：困难按键
    JButton MemBackButton;      //Li Wen: 背单词模式：返回按键
    JTextPane  MemWordText;     //Li Wen: 背单词模式：单词显示
    JTextPane  MemMeanText;     //Li Wen: 背单词模式：单词释义
    JTextPane  MemSentenceText; //Li Wen: 背单词模式：单词例句和翻译
    JButton MyListBackButton;   //Li Wen: 我的生词本模式：返回按钮

    JPanel EnglishPages;        //Li Wen: 页面容器
    JPanel ChoosePagePanel;     //Li Wen: 选择模式卡片页面
    JPanel QueryPagePanel;      //Li Wen: 查单词卡片页面
    JPanel MemoryPagePanel;     //Li Wen: 背单词卡片页面
    JPanel MyListPagePanel;     //Li Wen: 生词本卡片页面
    CardLayout EnglishPagesLayout;//Li Wen: 页面卡片布局

    User ThisUser;      //Li Wen: 当前用户
    Vector<Word> WordList;//Li Wen: 当前用户的单词表
    Vector<Word> MemoryingWords; //Li Wen: 用户正在记录的15个单词
    Word currentWord;   //Li Wen: 当前单词
    Word NewWord;       //Li Wen: 查到的单词

    /*
    created by Li Wen in 2021/4/20

    Abstract:
    EnglishPanel的构造函数,
    初始化ThisUser当前用户,
    初始化WordList他的生词本,
    初始化MemoryingWords他正在背的15个单词列表。

    Para:
    thisuser：当前用户

    Return value：
    null
    */
    public EnglishPanel(User thisuser){
        ThisUser = thisuser;
        WordList = thisuser.getWords();
        MemoryingWords = new Vector<>();
        initGUI();
    }

    /*
    created by Li Wen in 2021/4/20

    Abstract:
    生成EnglishPanel

    Para:
    null

    Return value：
    null
     */
    public void initEnglishPanel(){

        //Li Wen: EnglishPages里各个页面布局
        {
            //Li Wen: 组件注册
            {
                QueryButton     = new JButton("查单词");
                MemoryButton    = new JButton("背单词");
                MyListButton    = new JButton("我的生词本");

                QueryInputTextField = new JTextField();
                QueryStartButton    = new JButton("查询");
                QueryAddButton      = new JButton("添加到生词本");
                QueryBackButton     = new JButton("返回");
                QueryWordText    = new JTextPane();
                QueryMeanText    = new JTextPane();
                QuerySentenceText    = new JTextPane();


                MemEasyButton   = new JButton("容易");
                MemMediumButton = new JButton("模糊");
                MemHardButton   = new JButton("没想起");
                MemBackButton   = new JButton("返回");
                MemWordText     = new JTextPane();
                MemMeanText     = new JTextPane();
                MemSentenceText = new JTextPane();

                MyListBackButton    = new JButton("返回");

                EnglishPages    = new JPanel();
                ChoosePagePanel = new JPanel();
                QueryPagePanel  = new JPanel();
                MemoryPagePanel = new JPanel();
                MyListPagePanel = new JPanel();
                EnglishPagesLayout  = new CardLayout();

                EnglishPages.setPreferredSize(this.getPreferredSize());
                this.add(EnglishPages);
                EnglishPages.setLayout(EnglishPagesLayout);
                EnglishPages.add("ChoosePage",ChoosePagePanel);
                EnglishPages.add("QueryPage", QueryPagePanel);
                EnglishPages.add("MemoryPage", MemoryPagePanel);
                EnglishPages.add("MyListPage", MyListPagePanel);
            }

            //ChoosePagePanel布局
            {
                ChoosePagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                Box Choosev1 = Box.createVerticalBox();
                ChoosePagePanel.add(Choosev1);
                Choosev1.add(Box.createVerticalStrut(60));
                Choosev1.add(QueryButton);
                Choosev1.add(Box.createVerticalStrut(30));
                Choosev1.add(MemoryButton);
                Choosev1.add(Box.createVerticalStrut(30));
                Choosev1.add(MyListButton);
                Choosev1.add(Box.createVerticalGlue());
            }

            //QueryPagePanel布局
            {
                QueryWordText.setEditable(false);//不可被编辑
                QueryMeanText.setEditable(false);
                QuerySentenceText.setEditable(false);

                QueryWordText.setBackground(Color.orange);
                QueryMeanText.setBackground(Color.orange);
                QuerySentenceText.setBackground(Color.orange);

                QueryWordText.setPreferredSize(new Dimension(100,50));
                QueryMeanText.setPreferredSize(new Dimension(100,50));
                QuerySentenceText.setPreferredSize(new Dimension(100,50));

                QueryPagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                QueryPagePanel.setBackground(Color.gray);
                Box Queryv1 = Box.createVerticalBox();
                Box Queryh1 = Box.createHorizontalBox();
                QueryPagePanel.add(Queryv1);
                Queryv1.add(QueryBackButton);
                Queryv1.add(Box.createVerticalStrut(30));
                Queryv1.add(QueryInputTextField);
                Queryv1.add(Box.createVerticalStrut(10));
                Queryv1.add(QueryWordText);
                Queryv1.add(Box.createVerticalStrut(10));
                Queryv1.add(QueryMeanText);
                Queryv1.add(Box.createVerticalStrut(10));
                Queryv1.add(QuerySentenceText);
                Queryv1.add(Box.createVerticalStrut(30));
                Queryv1.add(Queryh1);
                Queryv1.add(Box.createVerticalGlue());
                Queryh1.add(QueryStartButton);
                Queryh1.add(Box.createHorizontalStrut(90));
                Queryh1.add(QueryAddButton);
                Queryh1.add(Box.createHorizontalGlue());
            }

            //MemoryPagePanel布局
            {
                MemWordText.setEditable(false);//不可被编辑
                MemMeanText.setEditable(false);
                MemSentenceText.setEditable(false);

                MemWordText.setBackground(Color.gray);
                MemMeanText.setBackground(Color.gray);
                MemSentenceText.setBackground(Color.gray);

                MemWordText.setPreferredSize(new Dimension(80,50));
                MemMeanText.setPreferredSize(new Dimension(100,80));
                MemSentenceText.setPreferredSize(new Dimension(120,80));

                Box Memv1 = Box.createVerticalBox();
                Box Memv2 = Box.createVerticalBox();
                Box Memh1 = Box.createHorizontalBox();

                //Li Wen: 存放单词、释义、例句的布局
                Memv2.add(MemWordText);
                Memv2.add(Box.createVerticalStrut(20));
                Memv2.add(MemMeanText);
                Memv2.add(Box.createVerticalStrut(20));
                Memv2.add(MemSentenceText);
                Memv2.add(Box.createVerticalStrut(20));
                Memv2.add(Box.createVerticalGlue());

                //Li Wen: 存放容易、模糊、困难按键的布局
                Memh1.add(MemHardButton);
                Memh1.add(Box.createHorizontalStrut(60));
                Memh1.add(MemMediumButton);
                Memh1.add(Box.createHorizontalStrut(60));
                Memh1.add(MemEasyButton);
                Memh1.add(Box.createHorizontalGlue());

                //Li Wen: Memory总体布局
                Memv1.add(Box.createVerticalStrut(30));
                Memv1.add(Memv2);
                Memv1.add(Box.createVerticalStrut(30));
                Memv1.add(Memh1);
                Memv1.add(Box.createVerticalGlue());
                MemoryPagePanel.add(Memv1);
            }

            //MyListPagePanel布局
            {
                MyListPagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                MyListPagePanel.setBackground(Color.LIGHT_GRAY);
                MyListPagePanel.add(MyListBackButton);
            }

        }

        //Li Wen: 监听事件绑定
        {
            //ChoosePagePanel
            {
                //打开查单词页面按键
                QueryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnglishPagesLayout.show(EnglishPages, "QueryPage");
                    }
                });

                //打开背单词页面按键
                MemoryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnglishPagesLayout.show(EnglishPages, "MemoryPage");
                    }
                });

                //打开我的生词本页面按键
                MyListButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnglishPagesLayout.show(EnglishPages, "MyListPage");
                    }
                });
            }

            //QueryPagePanel
            {
//                //查单词模式：输入框限制输入
//                QueryInputTextField.addCaretListener(new CaretListener() {
//                    @Override
//                    public void caretUpdate(CaretEvent e) {
//                        String input = QueryInputTextField.getText().toString();//获得输入内容
//                        if (input.length()==0) {
//                            return;
//                        }
//                        char ch = input.charAt(input.length()-1);
//                        if (!(ch >='A') && (ch <= 'z')){
//                            JOptionPane.showMessageDialog(QueryInputTextField,"只能输入大小写英文字母","提示",2);
//                        }
//                        SwingUtilities.invokeLater(new Runnable(){
//                            @Override
//                            public void run() {
//                                QueryInputTextField.setText(input.substring(0,input.length()-1));
//                            }
//                        });
//                    }
//                });
                //查单词模式：开始查询按键
                QueryStartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QueryWordText.setText("");//清除上一次的结果
                        String input = QueryInputTextField.getText().toString();
                        //按空格切片，若输入为空，则提示要输入单词
                        if (input.length()==0){
                            JOptionPane.showMessageDialog(QueryStartButton,"请输入您要查询的单词哦~","查询失败",2);
                        }
                        //若输入多个单词，以空格隔开，则只显示第一个单词的查询结果。
                        NewWord = getWordByName(input);   //从API获得单词相关内容
                        QueryWordText.setText(NewWord.getFrontSide()+" "+NewWord.getAccent());
                        QueryMeanText.setText(NewWord.getBackSide());
                        QuerySentenceText.setText(NewWord.getEgSentence()+"\n"+NewWord.getEgSentenceTrans());
                    }
                });

                //查单词模式：加入生词本按键
                QueryAddButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //当新单词有返回结果时，才会加入到生词本。
                        if (NewWord.getFrontSide()!=""){
                            WordList.add(NewWord);
                        }
                    }
                });

                //查单词模式：返回按键
                QueryBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnglishPagesLayout.show(EnglishPages,"ChoosePage");
                    }
                });
            }

            //MemoryPagePanel
            {
                //背单词模式：容易按键
                MemEasyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentWord.setRight(1);
                        //当前记住15个单词时，进入拼写模式
                        if (MemoryingWords.size()>14){

                        }
                        getNextWord();
                    }
                });

                //背单词模式：模糊按键
                MemMediumButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getNextWord();
                    }
                });

                //背单词模式：困难按键
                MemHardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentWord.setRight(0);
                        getNextWord();
                    }
                });

                //背单词模式：返回按键
                MemBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnglishPagesLayout.show(EnglishPages,"ChoosePage");
                    }
                });

            }

            //MyListPagePanel
            {
                //我的生词本模式：返回按键
                MyListBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {EnglishPagesLayout.show(EnglishPages,"ChoosePage");
                    }
                });
            }

        }

        EnglishPagesLayout.show(EnglishPages,"ChoosePage");
    }

    /*
    created by Li Wen in 2021/4/20

    Abstract:
    找到下一个要显示的单词。

    Para:
    null

    Return value：
    Word类型，表示下一个要背的单词。
    */
    public void getNextWord(){

    }


    /*
    created by Li Wen in 2021/4/20

    Abstract:
    从百词斩API中找到单词资料，处理后返回一个Word实例。

    Para:
    wordname: 待查询单词


    Return value：
    Word类型，表示查到的单词信息
    */
    public Word getWordByName(String wordname){
        String URL = "https://cdn.jsdelivr.net/gh/lyc8503/baicizhan-word-meaning-API/data/words/"+wordname+".json";//地址
        HttpURLConnection conn = null;
        BufferedReader dataIn = null;
        try {
            java.net.URL url = new java.net.URL(URL);

            // 将url以open方法返回的urlConnection连接强转为HttpURLConnection连接(标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            conn = (HttpURLConnection) url.openConnection();

            // 设置连接输入流为true
            conn.setDoInput(true);
            // 设置请求方式为get
            conn.setRequestMethod("GET");
            // get请求缓存设为false
            conn.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            conn.setInstanceFollowRedirects(true);

            // 设置内容的类型,设置为经过urlEncoded编码过的from参数
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestProperty("accept", "application/xml");

            // 建立连接
            // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            conn.connect();

            // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
            // 若找不到该单词
            if (conn.getInputStream()==null){
                JOptionPane.showMessageDialog(this,"没有找到这个单词哦，您可以试试其他的单词呢~","查询失败",2);
                return new Word("","","","","");
            }

            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            sb.append(dataIn.readLine());

            //将结果转换为JSON对象，并获得Word的属性值
            JSONObject jb = new JSONObject(sb.toString());
            String accent = StringEscapeUtils.unescapeJava(jb.getString("accent"));
            String mean_cn = StringEscapeUtils.unescapeJava(jb.getString("mean_cn"));
            String mean_en = jb.getString("mean_en");
            String sentence = jb.getString("sentence");
            //当找不到例句时
            if (sentence == null){
                sentence = "啊亲爱的例句，你在哪里~";
            }
            String sentence_trans = jb.getString("sentence_trans");

            return new Word(wordname, mean_cn,sentence,sentence_trans,accent);

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            try {
                // 重要且易忽略步骤 (关闭流,切记!)
                if (dataIn != null) {
                    dataIn.close();
                }
                // 销毁连接
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private void initGUI() {

        Border lineBorder = BorderFactory.createLineBorder(Color.BLUE);
        this.setBorder(lineBorder);
        this.setPreferredSize(new Dimension(480,400));

        //Li Wen: 生成EnglishPanel界面
        initEnglishPanel();

    }

    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public EnglishPanel() {
        initGUI();
    }
}
