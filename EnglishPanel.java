import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EnglishPanel extends JPanel {
    JButton MemoryButton;      //Li Wen: 记忆单词按键
    JButton FlashButton;       //Li Wen: 速过单词按键
    JButton MyListButton;      //Li Wen: 复习生词按键
    JButton MemEasyButton;     //Li Wen: 记忆单词模式：容易按键
    JButton MemMediumButton;   //Li Wen: 记忆单词模式：模拟按键
    JButton MemHardButton;     //Li Wen: 记忆单词模式：困难按键
    JButton MemBackButton;     //Li Wen: 记忆单词模式：返回按键
    JButton FlashOKButton;     //Li Wen: 速过单词模式：掌握按键
    JButton FlashAgainButton;  //Li Wen: 速过单词模式：再来一遍按键
    JButton FlashBackButton;   //Li Wen: 速过单词模式：返回按键
    JButton MyListBackButton;  //Li Wen: 我的生词本模式：返回按钮
    JTextPane  WordText;         //Li Wen: 单词显示
    JTextPane  MeanText;         //Li Wen: 单词释义
    JTextPane  SentenceText;     //Li Wen: 单词例句

    JPanel EnglishPages;// Li Wen: 页面合集
    JPanel ChoosePage;  //Li Wen: 选择复习模式卡片页面
    JPanel MemoryPage;  //Li Wen: 记忆卡片页面
    JPanel FlashPage;   //Li Wen: 速过卡片页面
    JPanel MyListPage;  //Li Wen: 生词本卡片页面
    CardLayout EnglishLayout;//Li Wen: 复习页面卡片布局

    Word currentWord;//Li Wen: Temp
    int position = 0;
    int circle = 0; //连续判断次数

    //Li Wen: 实验代码
    String w1 = "word1", w2 = "word2", w3 = "word3", w4 = "word4", w5 = "word5",
            m1="mean1", m2="mean2", m3="mean3", m4="mean4", m5="mean5",
            e1="sentence1", e2="sentence2", e3="sentence3", e4="sentence4", e5="sentence5";

    private void initGUI() {
        //Li Wen: 实验代码
        Word ww1 = new Word(w1,m1,e1);
        Word ww2 = new Word(w2,m2,e2);
        Word ww3 = new Word(w3,m3,e3);
        Word ww4 = new Word(w4,m4,e4);
        Word ww5 = new Word(w5,m5,e5);

        Vector<Word> Wordlist = new Vector<Word>();
        Wordlist.add(ww1);
        Wordlist.add(ww2);
        Wordlist.add(ww3);
        Wordlist.add(ww4);
        Wordlist.add(ww5);

        currentWord = Wordlist.elementAt(position); //当前单词

        Border lineBorder = BorderFactory.createLineBorder(Color.BLUE);
        this.setBorder(lineBorder);
        this.setPreferredSize(new Dimension(480,400));

        //Li Wen: EnglishPages卡片布局设置
        {
            //Li Wen: 组件注册
            {
                MemoryButton    = new JButton("记忆单词");
                FlashButton     = new JButton("速过模式");
                MyListButton    = new JButton("我的生词本");
                MemEasyButton   = new JButton("容易");
                MemMediumButton = new JButton("模糊");
                MemHardButton   = new JButton("没想起");
                MemBackButton   = new JButton("返回");
                FlashOKButton   = new JButton("不再出现");
                FlashAgainButton = new JButton("再记一遍");
                FlashBackButton  = new JButton("返回");
                MyListBackButton = new JButton("返回");
                WordText        = new JTextPane();
                MeanText        = new JTextPane();
                SentenceText    = new JTextPane();
                EnglishPages    = new JPanel();
                ChoosePage      = new JPanel();
                MemoryPage      = new JPanel();
                FlashPage       = new JPanel();
                MyListPage      = new JPanel();
                EnglishLayout   = new CardLayout();
            }
            
            EnglishPages.setPreferredSize(this.getPreferredSize());
            this.add(EnglishPages);
            EnglishPages.setLayout(EnglishLayout);
            EnglishPages.add("ChoosePage",ChoosePage);
            EnglishPages.add("MemoryPage", MemoryPage);
            EnglishPages.add("FlashPage", FlashPage);
            EnglishPages.add("MyListPage", MyListPage);

            //li Wen: 找到第一个可以背的生词。
            while(currentWord.isForget() == true){
                MeanText.setText(String.valueOf(circle));
                currentWord = Wordlist.elementAt((++position)/5);
                if ((++circle)==5) break;
                WordText.setText("没有新单词可以显示。");
            }; //若下面的单词都被记住了，就继续往后找

            //Li Wen: ChoosePage设置
            {
                ChoosePage.setPreferredSize(EnglishPages.getPreferredSize());
                Box Choosev1 = Box.createVerticalBox();
                ChoosePage.add(Choosev1);
                Choosev1.add(Box.createVerticalStrut(60));
                Choosev1.add(MemoryButton);
                Choosev1.add(Box.createVerticalStrut(30));
                Choosev1.add(FlashButton);
                Choosev1.add(Box.createVerticalStrut(30));
                Choosev1.add(MyListButton);
                Choosev1.add(Box.createVerticalGlue());

                //Li Wen: ChoosePage的按键事件设置
                {
                    //记忆单词模式按键
                    MemoryButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EnglishLayout.show(EnglishPages, "MemoryPage");
                        }
                    });

                    //速过单词模式按键
                    FlashButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EnglishLayout.show(EnglishPages, "FlashPage");
                        }
                    });

                    //我的生词本模式按钮
                    MyListButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EnglishLayout.show(EnglishPages, "MyListPage");
                        }
                    });
                }

            }

            //Li Wen: MemoryPage设置
            {
                MemoryPage.setPreferredSize(EnglishPages.getPreferredSize());
                MemoryPage.setBackground(Color.orange);
                MemoryPage.add(MemBackButton);
                MemoryPage.add(MemEasyButton);
                MemoryPage.add(MemMediumButton);
                MemoryPage.add(MemHardButton);

                Box Memv1 = Box.createVerticalBox();
                Box Memh1 = Box.createHorizontalBox();
                MemoryPage.add(Memv1);
                Memh1.add(Box.createHorizontalStrut(60));
                Memv1.add(MemHardButton);
                Memv1.add(Box.createHorizontalStrut(60));
                Memv1.add(MemMediumButton);
                Memv1.add(Box.createHorizontalStrut(60));
                Memv1.add(MemEasyButton);
                Memv1.add(Box.createHorizontalGlue());


                //Li Wen: MemoryPage的按键事件设置
                {
                    //返回按键
                    MemBackButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {EnglishLayout.show(EnglishPages,"ChoosePage");
                        }
                    });

                    //容易按键
                    MemEasyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {}
                    });

                    //模糊按键
                    MemMediumButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {}
                    });

                    //想不起按键
                    MemHardButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {}
                    });

                }
            }

            //Li Wen: FlashPage设置
            {
                WordText.setEditable(false);//不可被编辑
                MeanText.setEditable(false);
                SentenceText.setEditable(false);

                WordText.setBackground(Color.orange);
                MeanText.setBackground(Color.orange);
                SentenceText.setBackground(Color.orange);

                WordText.setPreferredSize(new Dimension(100,50));
                MeanText.setPreferredSize(new Dimension(100,80));
                SentenceText.setPreferredSize(new Dimension(100,80));

                FlashPage.setPreferredSize(EnglishPages.getPreferredSize());
                FlashPage.setBackground(Color.gray);
                Box Flashv1 = Box.createVerticalBox();
                Box Flashh1 = Box.createHorizontalBox();
                FlashPage.add(Flashv1);
                Flashv1.add(FlashBackButton);
                Flashv1.add(Box.createVerticalStrut(30));
                Flashv1.add(WordText);
                Flashv1.add(Box.createVerticalStrut(10));
                Flashv1.add(MeanText);
                Flashv1.add(Box.createVerticalStrut(10));
                Flashv1.add(SentenceText);
                Flashv1.add(Box.createVerticalStrut(50));
                Flashv1.add(Flashh1);
                Flashv1.add(Box.createVerticalGlue());
                Flashh1.add(FlashAgainButton);
                Flashh1.add(Box.createHorizontalStrut(90));
                Flashh1.add(FlashOKButton);
                Flashh1.add(Box.createHorizontalGlue());

//                WordText.setText(Wordlist.elementAt(position).getFrontSide());
//                MeanText.setText(Wordlist.elementAt(position).getBackSide());
//                SentenceText.setText(Wordlist.elementAt(position).getEgSentence());

                //Li Wen: FlashPage的按键事件设置
                {
                    //返回按键
                    FlashBackButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            position = (int) Math.random();
                            EnglishLayout.show(EnglishPages,"ChoosePage");
                        }
                    });

                    //掌握按键
                    FlashOKButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            currentWord.setForget(true);
                            do{
                                currentWord = Wordlist.elementAt((++position)/5);
                            }while(currentWord.isForget() == true); //若下面的单词都被记住了，就继续往后找
                            WordText.setText(currentWord.getFrontSide()+ position);
                            MeanText.setText(currentWord.getBackSide());
                            SentenceText.setText(currentWord.getEgSentence());
                        }
                    });

                    //再记一遍按键
                    FlashAgainButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            currentWord.setForget(false);
                            int circle = 1;
                            do{
                                currentWord = Wordlist.elementAt((++position)/5);
                                if ((++circle)==5) break;
                            }while(currentWord.isForget() == true); //若下面的单词都被记住了，就继续往后找
                            WordText.setText(currentWord.getFrontSide()+ position);
                            MeanText.setText(currentWord.getBackSide());
                            SentenceText.setText(currentWord.getEgSentence());
                        }
                    });
                }

            }

            //Li Wen: MyListPage设置
            {
                MyListPage.setPreferredSize(EnglishPages.getPreferredSize());
                MyListPage.setBackground(Color.LIGHT_GRAY);
                MyListPage.add(MyListBackButton);

                //Li Wen: MyListPage的按键事件设置
                {
                    //返回按键
                    MyListBackButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {EnglishLayout.show(EnglishPages,"ChoosePage");
                        }
                    });
                }
            }

        }

        EnglishLayout.show(EnglishPages,"ChoosePage");

    }


    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public EnglishPanel() {
        initGUI();
    }
}
