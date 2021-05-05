import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

//用于查词的弹出菜单
class popMenu extends JPopupMenu{
    JMenuItem search;
    public popMenu(){
        this.setBorderPainted(false);
        this.setBackground(Color.white);
        search=new JMenuItem("查询");
        this.add(search);
    }
}
public class PracticePage extends JPanel {
    JButton BackButton;
    JPanel articleMain,article1, article2, article3;//右边界面+三个文章界面
    JPanel leftPanel;//左侧按钮界面
    JButton first,second,third;//三套阅读的按钮
    JTextArea test1,test2,test3;
    CardLayout thisLayout;//卡片布局
    JScrollPane jp,jp2,jp3;//用来显示滚动文本框

    private void initText(){
        test1=new JTextArea("\n\nIt is undoubtedly the case that the world today has become a global village. One of the effects of this is that increasingly people in all corners of the world are exposed to similar services and products and adopt similar habits. My view is that this is largely a beneficial process and in this essay I will explain why.\n" +
                "The first point to make is that there are some downsides to this process of cultural globalisation, but these are relatively minor. The most significant of these disadvantages is that it can weaken national culture and traditions. For example, if people watch films and television programmes produced in the United States, sometimes they adopt aspects of the lifestyle of the American characters they see on television. Typically, however, this only affects minor details such as clothing and does not seriously threaten national identity.\n" +
                "When we turn to the other side of the argument, there are two major points to make in favour of this process. The first of these is that the more we share habits, products and services, the better we understand each other and this reduces prejudice against other nations. The other point relates to modernity. It is a sign of progress in a society that people no longer are restricted to brands and advertisements from their own society but are able to access more international goods. If, for example, there were unable to drink Coca Cola or wear Nike, then that would mean their society was not part of the international community.\n" +
                "In conclusion, I understand the point of view of people who worry about cultural globalisation because it is a threat to national traditions. However, this is outweighed by its positive impact on international understanding and the fact that it represents progress within a society."+"\n\nIt is unquestionable that rising unemployment is one of the most pressing issues in the industrial world. One solution that has been put forward is to cut the working week to a maximum of 35 hours. However, in my view this solution is rather controversial and other solutions need to be found.\n" +
                "It is fairly easy to understand the reasons why this proposal has been made. The reasoning is that if workers are not allowed to work for more than 35 hours weekly, then employers will be forced to engage more staff. There would be at least two advantages to this. Not only would unemployment be reduced, but the working conditions of employees on very long shifts would also be significantly improved. For example, a factory employing 300 manual workers doing 10 hours a day might employ 450 workers.\n" +
                "There is also, however, a strong argument not to implement this proposal. This argument is based on economic competitiveness. If a company was forced to employ more workers to produce the same amount of goods, then its wage bill would rise and its products might become more expensive and less competitive compared to companies with longer working weeks. In this case, it is possible that the company either might become insolvent or it would have to make some employees redundant. As a result, the intended benefit to the personnel would not happen.\n" +
                "In summary, we can see that this is clearly a complex issue as there are significant advantages and disadvantages to the proposal. My own personal view is that it would be better not to introduce the shortened working week because it works only in theory and not in practice.");
        test1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        test1.setBackground(new Color(165, 222, 228));
        test1.setFont(new Font("雅黑",Font.PLAIN,16));
        test1.setLineWrap(true);
        test1.setWrapStyleWord(true);
        jp=new JScrollPane(test1);

        test2=new JTextArea("\n\nIt is true that many older people believe in traditional values that often seem incompatible with the needs of younger people. While I agree that some traditional ideas are outdated, I believe that others are still useful and should not be forgotten.\n" +
                "On the one hand, many of the ideas that elderly people have about life are becoming less relevant for younger people. In the past, for example, people were advised to learn a profession and find a secure job for life, but today’s workers expect much more variety and diversity from their careers. At the same time, the ‘rules’ around relationships are being eroded as young adults make their own choices about who and when to marry. But perhaps the greatest disparity between the generations can be seen in their attitudes towards gender roles. The traditional roles of men and women, as breadwinners and housewives, are no longer accepted as necessary or appropriate by most younger people.\n" +
                "On the other hand, some traditional views and values are certainly applicable to the modern world. For example, older generations attach great importance to working hard, doing one’s best, and taking pride in one’s work, and these behaviours can surely benefit young people as they enter today’s competitive job market. Other characteristics that are perhaps seen as traditional are politeness and good manners. In our globalised world, young adults can expect to come into contact with people from a huge variety of backgrounds, and it is more important than ever to treat others with respect. Finally, I believe that young people would lead happier lives if they had a more ‘old-fashioned’ sense of community and neighbourliness.\n" +
                "In conclusion, although the views of older people may sometimes seem unhelpful in today’s world, we should not dismiss all traditional ideas as irrelevant."+"\n\nIt is true that many older people believe in traditional values that often seem incompatible with the needs of younger people. While I agree that some traditional ideas are outdated, I believe that others are still useful and should not be forgotten.\n" +
                "On the one hand, many of the ideas that elderly people have about life are becoming less relevant for younger people. In the past, for example, people were advised to learn a profession and find a secure job for life, but today’s workers expect much more variety and diversity from their careers. At the same time, the ‘rules’ around relationships are being eroded as young adults make their own choices about who and when to marry. But perhaps the greatest disparity between the generations can be seen in their attitudes towards gender roles. The traditional roles of men and women, as breadwinners and housewives, are no longer accepted as necessary or appropriate by most younger people.\n" +
                "On the other hand, some traditional views and values are certainly applicable to the modern world. For example, older generations attach great importance to working hard, doing one’s best, and taking pride in one’s work, and these behaviours can surely benefit young people as they enter today’s competitive job market. Other characteristics that are perhaps seen as traditional are politeness and good manners. In our globalised world, young adults can expect to come into contact with people from a huge variety of backgrounds, and it is more important than ever to treat others with respect. Finally, I believe that young people would lead happier lives if they had a more ‘old-fashioned’ sense of community and neighbourliness.\n" +
                "In conclusion, although the views of older people may sometimes seem unhelpful in today’s world, we should not dismiss all traditional ideas as irrelevant.");
        test2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        test2.setBackground(new Color(165, 222, 228));
        test2.setFont(new Font("雅黑",Font.PLAIN,16));
        test2.setLineWrap(true);
        test2.setWrapStyleWord(true);
        jp2=new JScrollPane(test2);

        test3=new JTextArea("\n\nIt is true that ex-prisoners can become normal, productive members of society. I completely agree with the idea that allowing such people to speak to teenagers about their experiences is the best way to discourage them from breaking the law.\n" +
                "In my opinion, teenagers are more likely to accept advice from someone who can speak from experience. Reformed offenders can tell young people about how they became involved in crime, the dangers of a criminal lifestyle, and what life in prison is really like. They can also dispel any ideas that teenagers may have about criminals leading glamorous lives. While adolescents are often indifferent to the guidance given by older people, I imagine that most of them would be extremely keen to hear the stories of an ex-offender. The vivid and perhaps shocking nature of these stories is likely to have a powerful impact.\n" +
                "The alternatives to using reformed criminals to educate teenagers about crime would be much less effective. One option would be for police officers to visit schools and talk to young people. This could be useful in terms of informing teens about what happens to lawbreakers when they are caught, but young people are often reluctant to take advice from figures of authority. A second option would be for school teachers to speak to their students about crime, but I doubt that students would see teachers as credible sources of information about this topic. Finally, educational films might be informative, but there would be no opportunity for young people to interact and ask questions.\n" +
                "In conclusion, I fully support the view that people who have turned their lives around after serving a prison sentence could help to deter teenagers from committing crimes."+"\n\nIt is true that ex-prisoners can become normal, productive members of society. I completely agree with the idea that allowing such people to speak to teenagers about their experiences is the best way to discourage them from breaking the law.\n" +
                "In my opinion, teenagers are more likely to accept advice from someone who can speak from experience. Reformed offenders can tell young people about how they became involved in crime, the dangers of a criminal lifestyle, and what life in prison is really like. They can also dispel any ideas that teenagers may have about criminals leading glamorous lives. While adolescents are often indifferent to the guidance given by older people, I imagine that most of them would be extremely keen to hear the stories of an ex-offender. The vivid and perhaps shocking nature of these stories is likely to have a powerful impact.\n" +
                "The alternatives to using reformed criminals to educate teenagers about crime would be much less effective. One option would be for police officers to visit schools and talk to young people. This could be useful in terms of informing teens about what happens to lawbreakers when they are caught, but young people are often reluctant to take advice from figures of authority. A second option would be for school teachers to speak to their students about crime, but I doubt that students would see teachers as credible sources of information about this topic. Finally, educational films might be informative, but there would be no opportunity for young people to interact and ask questions.\n" +
                "In conclusion, I fully support the view that people who have turned their lives around after serving a prison sentence could help to deter teenagers from committing crimes.");
        test3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        test3.setBackground(new Color(165, 222, 228));
        test3.setFont(new Font("雅黑",Font.PLAIN,16));
        test3.setLineWrap(true);
        test3.setWrapStyleWord(true);
        jp3=new JScrollPane(test3);
    }//就是有点丑
    private void initButton(){//初始化左侧button
        leftPanel=new JPanel();
        leftPanel.setBackground(Color.white);
        leftPanel.setSize(600,300);
        leftPanel.setLayout(new GridLayout(4,1));
        first=new JButton("第一套");
        second=new JButton("第二套");
        third=new JButton("第三套");
        BackButton = new JButton("返回");
        first.setBorderPainted(false);first.setFocusPainted(false);first.setBackground(new Color(165, 222, 228));first.setForeground(Color.white);
        second.setBorderPainted(false);second.setFocusPainted(false);second.setBackground(new Color(168, 148, 199));second.setForeground(Color.white);
        third.setBorderPainted(false);third.setFocusPainted(false);third.setBackground(new Color(168, 148, 199));third.setForeground(Color.white);
        BackButton.setBorderPainted(false);BackButton.setFocusPainted(false);BackButton.setBackground(new Color(168, 148, 199));BackButton.setForeground(Color.white);
        leftPanel.add(first);leftPanel.add(second);leftPanel.add(third);leftPanel.add(BackButton);
        leftPanel.setBorder(new EmptyBorder(0,0,0,0));
    }
    private void initPanel() {
        initText();
        article1 = new JPanel();article1.setBorder(new EmptyBorder(0,0,0,0));
        article2 = new JPanel();article2.setBorder(new EmptyBorder(0,0,0,0));
        article3 = new JPanel();article3.setBorder(new EmptyBorder(0,0,0,0));
        articleMain=new JPanel();articleMain.setBorder(new EmptyBorder(0,0,0,0));
        thisLayout=new CardLayout();
        articleMain.setLayout(thisLayout);
        articleMain.setBackground(new Color(165, 222, 228));
        article1.setBackground(new Color(165, 222, 228));
        article2.setBackground(new Color(165, 222, 228));
        article3.setBackground(new Color(165, 222, 228));
        articleMain.setSize(600,500);
        article1.setSize(600,500);
        article2.setSize(600,500);
        article3.setSize(600,500);

        articleMain.add("first",article1);
        articleMain.add("second",article2);
        articleMain.add("third",article3);

        article1.setLayout(new GridLayout());article2.setLayout(new GridLayout());article3.setLayout(new GridLayout());
        article1.add(jp);//加入测试文字
        article2.add(jp2);
        article3.add(jp3);
    }
    private void initLayout(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0,0};  //设置了总共有一列
        gridBagLayout.rowHeights = new int[]{0};  //设置了总共有2行
        gridBagLayout.columnWeights = new double[]{0.15,0.85};  //设置了列的宽度为容器宽度
        gridBagLayout.rowWeights = new double[]{1.0};  //第一行的高度占了容器的2份，第二行的高度占了容器的8份
        setLayout(gridBagLayout);

        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(leftPanel, gbc_panel);

        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 0;
        add(articleMain, gbc_panel_1);

        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.setBackground(new Color(165, 222, 228));
                second.setBackground(new Color(168, 148, 199));
                third.setBackground(new Color(168, 148, 199));
                thisLayout.show(articleMain,"first");
            }
        });
        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.setBackground(new Color(168, 148, 199));
                second.setBackground(new Color(165, 222, 228));
                third.setBackground(new Color(168, 148, 199));
                thisLayout.show(articleMain,"second");
            }
        });
        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.setBackground(new Color(168, 148, 199));
                second.setBackground(new Color(168, 148, 199));
                third.setBackground(new Color(165, 222, 228));
                thisLayout.show(articleMain,"third");
            }
        });
    }
    private void initGUI() {
        initButton();
        initPanel();
        initLayout();

        this.setBackground(Color.cyan);
    }
    private void initPopMenu(){//初始化菜单
        test1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    popMenu pp=new popMenu();
                    pp.show(e.getComponent(), e.getX(), e.getY());
                    pp.search.addActionListener(new ActionListener() {//弹出查询的单词
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            String selected=test1.getSelectedText();
                            Word w=new searchWord().getWordByName(selected);
                            if(w==null){
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(new JLabel("未查到单词"+selected));
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }else{
                                Font FB=new Font("雅黑",Font.BOLD,16);
                                Font F=new Font("雅黑",Font.PLAIN,10);
                                String WORD=w.getFrontSide();
                                String MEAN=w.getBackSide();
                                String SEN=w.getEgSentence();
                                JLabel j1=new JLabel(WORD);j1.setFont(FB);
                                JLabel j2=new JLabel(MEAN);j2.setFont(F);
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(j1);newPage.add(j2);
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }
                        }
                    });
                }
            }


        });
        test2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    popMenu pp=new popMenu();
                    pp.show(e.getComponent(), e.getX(), e.getY());
                    pp.search.addActionListener(new ActionListener() {//弹出查询的单词
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            String selected=test1.getSelectedText();
                            Word w=new searchWord().getWordByName(selected);
                            if(w==null){
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(new JLabel("未查到单词"+selected));
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }else{
                                Font FB=new Font("雅黑",Font.BOLD,16);
                                Font F=new Font("雅黑",Font.PLAIN,10);
                                String WORD=w.getFrontSide();
                                String MEAN=w.getBackSide();
                                String SEN=w.getEgSentence();
                                JLabel j1=new JLabel(WORD);j1.setFont(FB);
                                JLabel j2=new JLabel(MEAN);j2.setFont(F);
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(j1);newPage.add(j2);
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }
                        }
                    });
                }
            }


        });
        test3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    popMenu pp=new popMenu();
                    pp.show(e.getComponent(), e.getX(), e.getY());
                    pp.search.addActionListener(new ActionListener() {//弹出查询的单词
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            String selected=test1.getSelectedText();
                            Word w=new searchWord().getWordByName(selected);
                            if(w==null){
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(new JLabel("未查到单词"+selected));
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }else{
                                Font FB=new Font("雅黑",Font.BOLD,16);
                                Font F=new Font("雅黑",Font.PLAIN,10);
                                String WORD=w.getFrontSide();
                                String MEAN=w.getBackSide();
                                String SEN=w.getEgSentence();
                                JLabel j1=new JLabel(WORD);j1.setFont(FB);
                                JLabel j2=new JLabel(MEAN);j2.setFont(F);
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(j1);newPage.add(j2);
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }
                        }
                    });
                }
            }


        });

    }
    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public PracticePage() {
        initGUI();
        initPopMenu();
    }
    public static void main(String[] args){
        JFrame jf=new JFrame();
        jf.add(new PracticePage());
        jf.setSize(800,600);
        jf.show();
    }
}
class searchWord{
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
                JOptionPane.showMessageDialog(null,"没有找到这个单词哦，您可以试试其他的单词呢~","查询失败",2);
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
}