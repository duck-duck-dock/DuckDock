import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Vector;

/*
created by Li Wen in 2021/04/1
 */
public class EnglishPanel extends JPanel {
    JButton     QueryButton;    //Li Wen: 打开查单词页面按键
    JButton     MemoryButton;   //Li Wen: 打开背单词页面按键
    JButton     MyListButton;   //Li Wen: 打开我的生词本页面按键
    JComboBox   SetCountCBox;   //Li Wen: 设置单轮记忆单词数

    JTextField  QueryInputTextField; //Li Wen: 查单词模式：用户输入框
    JButton     QueryStartButton;   //Li Wen: 查单词模式：开始查询按键
    JButton     QueryAddButton;     //Li Wen: 查单词模式：加入生词本按键
    JButton     QueryBackButton;    //Li Wen: 查单词模式：返回按键
    JTextPane   QueryWordText;      //Li Wen: 查单词模式：单词显示
    JTextPane   QueryMeanText;      //Li Wen: 查单词模式：单词释义
    JTextPane   QuerySentenceText;  //Li Wen: 查单词模式：单词例句和翻译

    JButton     MemForgetButton;    //Li Wen: 背单词模式：不再出现按键
    JButton     MemEasyButton;      //Li Wen: 背单词模式：容易按键
    JButton     MemMediumButton;    //Li Wen: 背单词模式：模糊按键
    JButton     MemHardButton;      //Li Wen: 背单词模式：困难按键
    JButton     MemBackButton;      //Li Wen: 背单词模式：返回按键

    JTextPane   MemWordText;        //Li Wen: 背单词模式：单词显示
    JTextPane   MemMeanText;        //Li Wen: 背单词模式：单词释义
    JTextPane   MemSentenceText;    //Li Wen: 背单词模式：单词例句和翻译

    JButton     DicBackButton;      //Li Wen: 拼单词模式：返回按键
    JButton     DicClearButton;     //Li Wen: 拼单词模式：清空输入按键
    JButton     DicHintsButton;     //Li Wen: 拼单词模式：提示按键
    JButton     DicCheckButton;     //Li Wen: 拼单词模式：验证按键
    JTextPane   DicWordText;        //Li Wen: 拼单词模式：正确单词
    JTextPane   DicMeanText;        //Li Wen: 拼单词模式：中文释义
    JTextPane   DicAccentText;      //Li Wen: 拼单词模式：音标提示
    JTextField  DicInputTextField;  //Li Wen: 拼单词模式：用户输入框

    JButton     MyListBackButton;   //Li Wen: 我的生词本模式：返回按键

    JPanel      EnglishPages;       //Li Wen: 页面容器
    JPanel      ChoosePagePanel;    //Li Wen: 选择模式卡片页面
    JPanel      QueryPagePanel;     //Li Wen: 查单词卡片页面
    JPanel      MemoryPagePanel;    //Li Wen: 背单词卡片页面
    JPanel      DictatePagePanel;   //Li Wen: 拼单词卡片页面
    JPanel      MyListPagePanel;    //Li Wen: 生词本卡片页面
    CardLayout  EnglishPagesLayout; //Li Wen: 页面卡片布局

    User    ThisUser;       //Li Wen: 当前用户
    Word    CurWord;        //Li Wen: 当前单词，默认为WordList的第0个单词。
    Word    NewWord;        //Li Wen: 查到的单词，初始为null
    int     Curposition;    //Li Wen: 当前背到WordList单词表的哪个单词了，初始值为WordList中第一个未被掌握的词的位置。每次打开背单词页面就会被更新。
    int     roundcounter;   //Li Wen: 本轮：待拼写+掌握。达到capcounter时，就去拼写。初始为0。
    int     capcounter;     //Li Wen: 单轮单词数。通过下拉框选择，初始为10
    int     WordsOverFlag;  //Li Wen: 为1时，表示已经背完所有单词。
    Vector<Word> WordList;  //Li Wen: 当前用户的单词表
    Vector<Word> MemoryingWords;//Li Wen: 用户正在记录的15个单词,正在背的+正在拼写的
    Vector<Word> MasteredWords; //Li Wen: 用户已经掌握的单词表

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
        MemoryingWords = new Vector<Word>();
        MasteredWords = new Vector<Word>();
        this.setBackground(new Color(88,178,220));

        NewWord = null;
        Curposition = 0;
        roundcounter = 0;
        capcounter = 3;

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
        this.setBackground(new Color(88,178,220));
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

                MemForgetButton = new JButton("不再出现");
                MemEasyButton   = new JButton("容易");
                MemMediumButton = new JButton("模糊");
                MemHardButton   = new JButton("没想起");
                MemBackButton   = new JButton("返回");
                MemWordText     = new JTextPane();
                MemMeanText     = new JTextPane();
                MemSentenceText = new JTextPane();

                DicBackButton = new JButton("返回");
                DicClearButton = new JButton("清空");
                DicHintsButton = new JButton("提示");
                DicCheckButton = new JButton("√");
                DicWordText    = new JTextPane();
                DicMeanText    = new JTextPane();
                DicAccentText  = new JTextPane();
                DicInputTextField = new JTextField();

                MyListBackButton    = new JButton("返回");

                EnglishPages    = new JPanel();
                ChoosePagePanel = new JPanel();
                QueryPagePanel  = new JPanel();
                MemoryPagePanel = new JPanel();
                DictatePagePanel = new JPanel();
                EnglishPagesLayout  = new CardLayout();

                EnglishPages.setPreferredSize(this.getPreferredSize());
                this.add(EnglishPages);
                EnglishPages.setLayout(EnglishPagesLayout);
                EnglishPages.add("ChoosePage",ChoosePagePanel);
                EnglishPages.add("QueryPage", QueryPagePanel);
                EnglishPages.add("MemoryPage", MemoryPagePanel);
                EnglishPages.add("DictatePage", DictatePagePanel);
            }

            //ChoosePagePanel布局
            {
                ChoosePagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                ChoosePagePanel.setBackground(new Color(88,178,220));
                //设置选择单轮单词数下拉框
                final String[] CBoxItemData = new String[]{"10","15","20"};
                SetCountCBox = new JComboBox<String>(CBoxItemData);
                SetCountCBox.setPreferredSize(new Dimension(30,20));

                //ChoosPagePanel布局
                Box Choosev1 = Box.createVerticalBox();
                ChoosePagePanel.add(Choosev1);
                Choosev1.add(Box.createVerticalStrut(60));
                Choosev1.add(QueryButton);
                Choosev1.add(Box.createVerticalStrut(30));
                Choosev1.add(MemoryButton);
                Choosev1.add(Box.createVerticalStrut(30));
                Choosev1.add(SetCountCBox);
                Choosev1.add(Box.createVerticalGlue());
            }

            //QueryPagePanel布局
            {
                QueryPagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                QueryWordText.setEditable(false);//不可被编辑
                QueryMeanText.setEditable(false);
                QuerySentenceText.setEditable(false);

                QueryWordText.setBackground(new Color(144, 199, 226));
                QueryMeanText.setBackground(new Color(144, 199, 226));
                QuerySentenceText.setBackground(new Color(144, 199, 226));

                QueryWordText.setPreferredSize(new Dimension(100,50));
                QueryMeanText.setPreferredSize(new Dimension(100,50));
                QuerySentenceText.setPreferredSize(new Dimension(100,50));

                QueryPagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                QueryPagePanel.setBackground(new Color(88,178,220));
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
                MemoryPagePanel.setBackground(new Color(88,178,220));
                MemoryPagePanel.setPreferredSize(EnglishPages.getPreferredSize());
                MemWordText.setEditable(false);//不可被编辑
                MemMeanText.setEditable(false);
                MemSentenceText.setEditable(false);

                MemWordText.setBackground(new Color(144, 199, 226));
                MemMeanText.setBackground(new Color(144, 199, 226));
                MemSentenceText.setBackground(new Color(144, 199, 226));

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
                Memh1.add(Box.createHorizontalStrut(20));
                Memh1.add(MemMediumButton);
                Memh1.add(Box.createHorizontalStrut(20));
                Memh1.add(MemEasyButton);
                Memh1.add(Box.createHorizontalStrut(20));
                Memh1.add(MemForgetButton);
                Memh1.add(Box.createHorizontalGlue());

                //Li Wen: Memory总体布局
                Memv1.add(MemBackButton);
                Memv1.add(Box.createVerticalStrut(10));
                Memv1.add(Memv2);
                Memv1.add(Box.createVerticalStrut(30));
                Memv1.add(Memh1);
                Memv1.add(Box.createVerticalGlue());
                MemoryPagePanel.add(Memv1);
            }

            //DictatePagePanel布局
            {
                DictatePagePanel.setPreferredSize(EnglishPages.getPreferredSize());

                DicWordText.setBackground(Color.red);
                DicMeanText.setBackground(new Color(144, 199, 226));
                DicAccentText.setBackground(new Color(144, 199, 226));

                DicWordText.setPreferredSize(new Dimension(70,30));
                DicMeanText.setPreferredSize(new Dimension(70,30));
                DicAccentText.setPreferredSize(new Dimension(70,30));

                DicWordText.setEditable(false);
                DicMeanText.setEditable(false);
                DicAccentText.setEditable(false);

                DicWordText.setVisible(false);
                DicAccentText.setVisible(false);

                Box v1 = Box.createVerticalBox();   //页面总体的垂直布局
                Box v2 = Box.createVerticalBox();   //显示内容的垂直布局
                Box h1 = Box.createHorizontalBox(); //下方按钮的水平布局

                v2.add(DicMeanText);
                v2.add(Box.createVerticalStrut(15));
                v2.add(DicInputTextField);
                v2.add(Box.createVerticalStrut(15));
                v2.add(DicWordText);
                v2.add(Box.createVerticalStrut(15));
                v2.add(DicAccentText);
                v2.add(Box.createVerticalGlue());

                h1.add(DicClearButton);
                h1.add(Box.createHorizontalStrut(20));
                h1.add(DicHintsButton);
                h1.add(Box.createHorizontalStrut(20));
                h1.add(DicCheckButton);
                h1.add(Box.createHorizontalGlue());

                v1.add(DicBackButton);
                v1.add(Box.createVerticalStrut(20));
                v1.add(v2);
                v1.add(Box.createVerticalStrut(20));
                v1.add(h1);

                DictatePagePanel.add(v1);

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
                        QueryInputTextField.setText("");
                        QueryWordText.setText("");
                        QueryMeanText.setText("");
                        QuerySentenceText.setText("");
                        EnglishPagesLayout.show(EnglishPages, "QueryPage");
                    }
                });

                //选择单轮单词书下拉框
                SetCountCBox.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            System.out.println(SetCountCBox.getSelectedIndex());
                            capcounter = 5 * (SetCountCBox.getSelectedIndex()+2);
                        }
                    }
                });

                //打开背单词页面按键
                MemoryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       OpenMemoryPage();
                    }
                });
            }

            //QueryPagePanel
            {
                //查单词模式：开始查询按键
                QueryStartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //清空上次显示内容
                        QueryWordText.setText("");
                        QueryMeanText.setText("");
                        QuerySentenceText.setText("");

                        //获取输入，并按空格切片
                        String tempinput = QueryInputTextField.getText();
                        String input[] = tempinput.split("\\s+");

                        //若没有输入/只输入了空格，则提示要输入单词，并清空输入框
                        if (tempinput.equals("") || input.length == 0){
                            JOptionPane.showMessageDialog(QueryPagePanel,"请输入您要查询的单词哦~","Failure",2);
                            QueryInputTextField.setText("");
                        }
                        else{//否则，按空格分割，取第一个合法输入
                            //从API获得单词相关内容，并显示。
                            NewWord = getWordByName(input[0]);
                            if (NewWord != null){
                                QueryWordText.setText(NewWord.getFrontSide()+" "+NewWord.getAccent());
                                QueryMeanText.setText(NewWord.getBackSide());
                                QuerySentenceText.setText(NewWord.getEgSentence()+"\n"+NewWord.getEgSentenceTrans());
                            }else{
                                JOptionPane.showMessageDialog(QueryPagePanel,"没有查到该单词呢，试试其他单词~","Failure",2);
                            }
                        }
                    }
                });

                //查单词模式：加入生词本按键
                QueryAddButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //当有查询结果，且不在生词本中时，添加到生词本
                        if (NewWord != null){
                            int flag = 0;   //为1时表示已在单词本
                            for (int i=0; i<WordList.size(); i++){
                                if (WordList.elementAt(i).getFrontSide().equals(NewWord.getFrontSide())){
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag==0){
                                WordList.add(NewWord);
                                JOptionPane.showMessageDialog(QueryPagePanel, "已添加到生词本！", "Success", 1);
                            }else{
                                JOptionPane.showMessageDialog(QueryPagePanel, "这个单词已经在单词本啦~", "Failure", 2);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(QueryPagePanel, "没有返回结果的单词，怎么能入生词本呢！", "Failure", 1);
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
                //背单词模式：不再出现按键
                MemForgetButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CurWord.setForget(1);
                        roundcounter++;
                        //掌握的单词不需要拼写
                        WordList.remove(CurWord);
                        MemoryingWords.remove(CurWord);
                        JOptionPane.showMessageDialog(MemoryPagePanel,"后续不会出现该单词的记忆环节啦~","Success",2);
                        toSpell();
                        getNextWord(1);
                    }
                });

                //背单词模式：容易按键
                MemEasyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CurWord.setRight(1);
                        if(CurWord.isForget()==-1){
                            roundcounter++;
                            if (toSpell() == false){
                                getNextWord(1);
                            }
                        }
                    }
                });

                //背单词模式：模糊按键
                MemMediumButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getNextWord(1);
                    }
                });

                //背单词模式：困难按键
                MemHardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CurWord.setRight(0);
                        getNextWord(1);
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

            //DictatePagePanel
            {

                //拼单词模式：返回按键
                DicBackButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnglishPagesLayout.show(EnglishPages,"ChoosePage");
                    }
                });

                //拼单词模式：输入框焦点获得/失去事件
                DicInputTextField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        DicWordText.setVisible(false);
                        DicAccentText.setVisible(false);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                    }
                });

                //拼单词模式：清空输入按键
                DicClearButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("Clear Input");

                        DicInputTextField.setText("");
                        DicWordText.setVisible(false);
                        DicAccentText.setVisible(false);
                    }
                });

                //拼单词模式：提示按键
                DicHintsButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Hint");
                        DicAccentText.setVisible(true);
                    }
                });

                //拼单词模式：验证按键
                DicCheckButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Checking");
                        System.out.println("now the Word is: "+CurWord.getFrontSide());
                        System.out.println("now WordText is: "+DicWordText.getText());

                        String inputValue = DicInputTextField.getText();

                        //若未输入拼写，则提示
                        if (inputValue.equals("")){
                            JOptionPane.showMessageDialog(DictatePagePanel, "请输入你的答案啦~", "Failure",2);
                        }

                        //若拼写正确
                        else if (inputValue.equals(DicWordText.getText())){

                            //如果wrong的次数为0，就掌握它。
                            if (CurWord.isemptyWrong()){
                                CurWord.setForget(1);
                            }
                            //如果wrong的次数大于0，则之后还会背到这个单词，并清空拼错次数。
                            else {
                                CurWord.setForget(0);
                                CurWord.setWrong(0);

                            }
                            //从MemoryingWords中移出当前单词，并找到下一个单词。
                            MemoryingWords.remove(CurWord);
                            getNextWord(2);
                            DicInputTextField.setText("");
                        }

                        //若拼写错误,则wrong+1，继续拼写该单词。短暂显示正确答案。
                        else{
                            CurWord.setWrong(1);
                            DicWordText.setVisible(true);
                        }
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

        //默认显示选择页
        EnglishPagesLayout.show(EnglishPages,"ChoosePage");
    }

    //打开背单词页面
    public void OpenMemoryPage(){
        //若用户的生词本为空，则提示，停留在本页面
        if (WordList.isEmpty()){
            JOptionPane.showMessageDialog(ChoosePagePanel,"您的生词本为空呢，快去发现生词吧~","Failure",2);
            return;
        }

        //若用户的生词本不为空
        Word tempNextWord = new Word();//循环需要
        int i = 0;

        //找到第一个未被掌握的单词的位置
        for (; i<WordList.size();i++){
            tempNextWord = WordList.elementAt(i);
            if (tempNextWord.isForget() != 1){
                Curposition = i;
                break;
            }
        }

        //如果有生词，就打开背单词页面，否则提示。
        if (i == WordList.size()){
            JOptionPane.showMessageDialog(ChoosePagePanel, "今天没有需要复习的生词，你太棒啦！", "Success", 2);
            EnglishPagesLayout.show(EnglishPages, "ChoosePage");
            return;
        }
        else{
            //把第一个没掌握的词加到正在背诵的单词列表，并设置组件的显示值。
            CurWord = WordList.elementAt(Curposition);

            MemWordText.setText(CurWord.getFrontSide());
            MemMeanText.setText(CurWord.getBackSide());
            MemSentenceText.setText(CurWord.getEgSentence()+"\n"+CurWord.getEgSentenceTrans());
            MemoryingWords.add(CurWord);
            EnglishPagesLayout.show(EnglishPages, "MemoryPage");
            return;
        }
    }

    /*
    created by Li Wen in 2021/4/20
    Abstract:
    从API中找到单词资料，处理后返回一个Word实例。
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

    /*
    created by Li Wen in 2021/4/20
    Abstract:
    找到下一个要显示的单词。
    Para:
    choice: 应用场景。1表示正在背单词，2表示正在背单词。
    Return value:
    null
    */
    public void getNextWord(int choice){
        Word tempNextWord = new Word();//循环需要
        int i = 0;//循环需要

        //正在背单词，找新词哎~
        if (choice == 1){

            //若当前memoryingWords的个数小于capcount，则从WordList中取一个新的加到MemoryingWords中，并设置curentWord。
            if(MemoryingWords.size() < capcounter){
                for (; Curposition < WordList.size()-1 ;) {
                    Curposition = (++Curposition) % WordList.size();
                    tempNextWord = WordList.elementAt(Curposition);
                    if(tempNextWord.isForget() == 0){
                        CurWord = tempNextWord;
                        MemoryingWords.add(CurWord);

                        MemWordText.setText(CurWord.getFrontSide());
                        MemMeanText.setText(CurWord.getBackSide());
                        MemSentenceText.setText(CurWord.getEgSentence()+"\n"+CurWord.getEgSentenceTrans());
                        return;
                    }
                }

                //如果Wordlist已经没有新的生词，就从正在记忆的生词里面找下一个单词
                for (i=MemoryingWords.size()-1; i >-1 ;i--) {
                    tempNextWord = MemoryingWords.elementAt(i);
                    if(tempNextWord.isForget() == 0) {
                        CurWord = tempNextWord;

                        MemWordText.setText(CurWord.getFrontSide());
                        MemMeanText.setText(CurWord.getBackSide());
                        MemSentenceText.setText(CurWord.getEgSentence()+"\n"+CurWord.getEgSentenceTrans());
                        return;
                    }
                }

                //都找不到的话，就是该去拼写啦。
                System.out.println("没有新词可以复习，可能去拼写。");
                WordsOverFlag = 1;
                if (toSpell() == false){
                    EnglishPagesLayout.show(EnglishPages, "ChoosePage");
                }
            }

            //若当前memoryingwords的个数等于capcount，但是还不能拼写，则从memoryingowrds中找一个。一定找的到.
            else{
                for (i=0; i < capcounter ;i++) {
                    tempNextWord = MemoryingWords.elementAt(i);

                    //若遇到一个普通单词，就切换到该单词。
                    if(tempNextWord.isForget() == 0) {
                        CurWord = tempNextWord;
                        MemWordText.setText(CurWord.getFrontSide());
                        MemMeanText.setText(CurWord.getBackSide());
                        MemSentenceText.setText(CurWord.getEgSentence()+"\n"+CurWord.getEgSentenceTrans());
                        return;
                    }
                }
            }
        }

        //正在拼单词
        else if (choice == 2){
            int flagcount = 0;//标志是否成功背完这组单词。小于capcount则表示没有背完。
            roundcounter = MemoryingWords.size();

            //找到没拼写通过的词就返回。
            for (;flagcount < roundcounter;) {
                Curposition = (++Curposition) % roundcounter;
                tempNextWord = MemoryingWords.elementAt(Curposition);
                if(tempNextWord.isForget() == -1) {
                    CurWord = tempNextWord;
                    DicWordText.setText(CurWord.getFrontSide());
                    DicMeanText.setText(CurWord.getBackSide());
                    DicAccentText.setText(CurWord.getAccent());
                    return;
                }
                else{
                    flagcount++;
                }
            }

            //本轮所有的词都拼写通过，开始新一轮背单词。
            System.out.println("这轮单词都拼写通过了，去背新单词了。");
            roundcounter = 0;
            JOptionPane.showMessageDialog(DictatePagePanel,"本轮拼单词结束","Success",2);
            OpenMemoryPage();
        }

    }

    /*
    created by Li Wen in 2021/4/30
    Abstract:
    判断是否达到打开拼单词模式条件。
    若roundcounter等于10，打开拼单词模式。
    Para:
    null
    Return value:
    null
    */
    public boolean toSpell(){
        if ((WordsOverFlag == 1) || (roundcounter == capcounter) ){
            if (MemoryingWords.size() != 0){
                JOptionPane.showMessageDialog(EnglishPages,"即将进入拼单词模式","Success",2);

                //初始化DictatePagePanel的一些组件
                Curposition = 0;
                roundcounter = MemoryingWords.size();
                CurWord = MemoryingWords.elementAt(Curposition);
                DicWordText.setText(CurWord.getFrontSide());
                DicMeanText.setText(CurWord.getBackSide());
                DicAccentText.setText(CurWord.getAccent());
                EnglishPagesLayout.show(EnglishPages,"DictatePage");
                return true;
            }
        }
        return false;
    }

    private void initGUI() {

        Border lineBorder = BorderFactory.createLineBorder(Color.BLUE);
        this.setBorder(lineBorder);
        this.setPreferredSize(new Dimension(480,400));

        //Li Wen: 生成EnglishPanel界面
        initEnglishPanel();

    }

}