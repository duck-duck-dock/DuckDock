import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Vector;

public class TodayPage extends JPanel {

    JPanel TodayPagePanel;      //Li Wen: 今日页面: 是父组件
    JPanel WordPanel;           //Li Wen: 今日鸡汤组件
    JPanel NewsPanel;           //Li Wen: 新闻推送组件
    JPanel WeatherPanel;        //Li Wen: 天气推送组件
    JPanel TodoPanel;           //Li Wen: Todo组件

    JLabel  DateLabel;          //Li Wen: 今日鸡汤: 日期标签
    JLabel  WordLabel;          //Li Wen: 今日鸡汤: 句子标签
    JLabel  TransLabel;         //Li Wen: 今日鸡汤: 翻译标签
    JLabel  AuthorLabel;        //Li Wen: 今日鸡汤: 作者标签
    String  WordofToday;        //Li Wen: 今日鸡汤: 每日一句的句子
    String  TransofWord;        //Li Wen: 今日鸡汤: 每日一句的翻译
    String  AuthorofWord;       //Li Wen: 今日鸡汤: 每日一句的作者
    String  PicofToday;         //Li Wen: 今日鸡汤: 每日一句的背景图

    JLabel  NewsTitleLabel;     //Li Wen: 新闻: 新闻标题标签
    JLabel  NewsIconLabel;      //Li Wen: 新闻: 新闻图标标签
    JLabel  News1Label;         //Li Wen: 新闻: 1号新闻标签
    JLabel  News2Label;         //Li Wen: 新闻: 2号新闻标签
    JButton NewsChangeButton;   //Li Wen: 新闻: 新闻切换按键
    String  News1title;         //Li Wen: 新闻: 1号新闻标题
    String  News1mtime;         //Li Wen: 新闻: 1号新闻更新时间
    String  News2title;         //Li Wen: 新闻: 2号新闻标题
    String  News2mtime;         //Li Wen: 新闻: 2号新闻更新时间
    JSONArray Newsitems;        //Li Wen: 新闻: 新闻数组
    int     NewsCounter;        //Li Wen: 新闻: 新闻计数

    JLabel  WeaTitleLabel;      //Li Wen: 天气: 今日天气标签
    JLabel  WeaDateLabel;       //Li Wen: 天气: 日期显示标签
    JLabel  WeaWeatherLabel;    //Li Wen: 天气: 天气文字标签
    JLabel  WeaIconLabel;       //Li Wen: 天气: 天气图标标签
    JLabel  WeaTempLabel;       //Li Wen: 天气: 温度标签
    JLabel  WeaFeelLabel;       //Li Wen: 天气: 体感温度标签
    JLabel  WeaWindLabel;       //Li Wen: 天气: 风标签
    JLabel  WeaHumiLabel;       //Li Wen: 天气: 湿度标签
    JLabel  WeaVisuLabel;       //Li Wen: 天气: 能见度标签
    String  WeaIconSrc;         //Li Wen: 天气: 天气图标相对地址

    JLabel  TodoTileLabel;      //Li Wen: Todo: 标题标签
    ToDoList AtodoList;         //Li Wen: Todo: 一个TodoList组件

    String  CurDate;            //Li Wen: 当前日期，格式: 2021-05-04
    Weather CurWeather;         //Li Wen: 当前天气
    User    ThisUser;           //Li Wen: 当前用户

    //Li Wen: TodayPage构造函数
    public TodayPage(User thiuser){
        initCurDate();              //Li Wen: 初始化当天日期
        getNews();                  //Li Wen: 连接新闻API
        getPicforToday();           //Li Wen: 连接获得每日一图
        getWordforToday();          //Li Wen: 连接每日一次API
        getWeather("shenyang");//Li Wen: 连接天气API，不要传中文啊！
        initGUI();                  //Li Wen: 初始化图形界面
    }

    //Li Wen: 初始化图形界面
    public void initGUI(){
        initTodayPage();
    }

    //初始化TodayPage界面
    public void initTodayPage() {

        //Li Wen: 组件注册
        {
            TodayPagePanel = new JPanel();
            TodayPagePanel.setBackground(new Color(144, 199, 226));
            //给WordPanel加上了背景
            WordPanel   = new JPanel(new BorderLayout()){
                public void paintComponent(Graphics g){
                    ImageIcon bgicon = new ImageIcon(PicofToday);
                    g.drawImage(bgicon.getImage(),0,0,WordPanel.getWidth(),WordPanel.getHeight(),bgicon.getImageObserver());
                }
            };
            NewsPanel   = new JPanel();
            WeatherPanel= new JPanel();
            TodoPanel   = new JPanel();

            //组件注册: 今日鸡汤
            DateLabel       = new JLabel();
            WordLabel       = new JLabel();
            TransLabel      = new JLabel();
            AuthorLabel     = new JLabel();

            //组件注册: 新闻推送
            NewsTitleLabel  = new JLabel("今日热点");
            NewsIconLabel   = new JLabel();
            News1Label      = new JLabel();
            News2Label      = new JLabel();
            NewsChangeButton= new JButton("换一批");

            //组件注册: 天气推送
            WeaTitleLabel   = new JLabel();
            WeaDateLabel    = new JLabel();
            WeaWeatherLabel = new JLabel();
            WeaIconLabel    = new JLabel();
            WeaTempLabel    = new JLabel();
            WeaFeelLabel    = new JLabel();
            WeaWindLabel    = new JLabel();
            WeaHumiLabel    = new JLabel();
            WeaVisuLabel    = new JLabel();

            //组件注册: Todo
            TodoTileLabel   = new JLabel();
            AtodoList = new ToDoList(ThisUser);

        }

        //Li Wen: 页面布局
        {
            //页面布局: 主页面
            {
                //Li Wen: 仅当TodayPage继承自JFrame时，才取消注释。
//                this.setSize(800, 600);
//                this.setResizable(false);

                //设置组件的大小
                WordPanel.setPreferredSize(new Dimension(780,150));
                NewsPanel.setPreferredSize(new Dimension(320,160));
                WeatherPanel.setPreferredSize(new Dimension(300,160));
                TodoPanel.setPreferredSize(new Dimension(450,440));

                //主页面布局
                Box h1 = Box.createHorizontalBox();
                Box v1 = Box.createVerticalBox();
                Box v2 = Box.createVerticalBox();

                v1.add(WordPanel);
                v1.add(Box.createVerticalStrut(5));
                v1.add(h1);
                v1.add(Box.createVerticalGlue());

                h1.add(v2);
                h1.add(Box.createHorizontalStrut(5));
                h1.add(TodoPanel);
                h1.add(Box.createHorizontalGlue());

                v2.add(WeatherPanel);
                v2.add(Box.createVerticalStrut(5));
                v2.add(NewsPanel);
                v2.add(Box.createVerticalStrut(5));
                v2.add(Box.createVerticalGlue());

                TodayPagePanel.add(v1);
                this.add(TodayPagePanel);

                NewsPanel.setBackground(new Color(88,178,220));
                WeatherPanel.setBackground(new Color(88,178,220));
            }

            //页面布局: 今日鸡汤
            {
                //设置文本
                DateLabel.setText(CurDate);
                WordLabel.setText("<html>" + WordofToday + "<html>");
                TransLabel.setText("<html>" + TransofWord + "<html>");
                AuthorLabel.setText("<html>" + AuthorofWord + "<html>");

                //日期标签格式
                Font Dateft = new Font("",Font.BOLD,20);
                DateLabel.setFont(Dateft);
                DateLabel.setForeground(new Color(112,194,178));
                //句子标签格式
                Font Wordft = new Font("",Font.BOLD,14);
                WordLabel.setFont(Wordft);
                TransLabel.setFont(Wordft);
                WordLabel.setForeground(new Color(242,238,214));
                TransLabel.setForeground(new Color(242,238,214));
                //作者标签格式
                Font authorft = new Font("",Font.BOLD,13);
                AuthorLabel.setFont(authorft);
                AuthorLabel.setForeground(new Color(242,238,214));

                //设置布局
                Box v1 = Box.createVerticalBox();
                Box v2 = Box.createVerticalBox();
                Box h1 = Box.createHorizontalBox();

                v1.add(Box.createVerticalStrut(30));
                v1.add(DateLabel);
                v1.add(Box.createVerticalStrut(15));
                v1.add(AuthorLabel);
                v1.add(Box.createVerticalStrut(10));
                v1.add(WordLabel);
                v1.add(Box.createVerticalStrut(5));
                v1.add(TransLabel);
                v1.add(Box.createVerticalGlue());

                v2.add(Box.createVerticalStrut(110));
                v2.add(Box.createVerticalGlue());

                h1.add(Box.createHorizontalStrut(20));
                h1.add(v1);
                h1.add(Box.createHorizontalStrut(300));
                h1.add(Box.createHorizontalGlue());

                WordPanel.add(h1);

            }

            //页面布局: 天气推送
            {
                //设置文本
                WeaTitleLabel.setText("<html>"+"今日天气"+"<html>");
                WeaDateLabel.setText("<html>"+CurDate+"<html>");
                WeaWeatherLabel.setText("<html>" + CurWeather.getWea() + "<html>");
                WeaTempLabel.setText("<html>" + CurWeather.getTemp() + "° /"
                                    + CurWeather.getFeelslike() + "°<html>");
                WeaFeelLabel.setText("<html>" + CurWeather.getFeelslike() + "°<html>");
                WeaWindLabel.setText("<html>" + CurWeather.getWindDir() + " "
                                    + CurWeather.getWindSpeed() + "级<html>");
                WeaHumiLabel.setText("<html>湿度: " + CurWeather.getHumidity() + "<html>");
                WeaVisuLabel.setText("<html>能见度: " + CurWeather.getVisuality() + "<html>");

                //设置标签格式
                Font ft1 = new Font("",Font.BOLD,25);
                Font ft2 = new Font("",Font.BOLD,20);
                Font ft3 = new Font("",Font.PLAIN,15);
                Font ft4 = new Font("",Font.BOLD,22);
                Font ft5 = new Font("",Font.PLAIN,13);
                WeaDateLabel.setFont(ft1);
                WeaWeatherLabel.setFont(ft2);
                WeaWindLabel.setFont(ft3);
                WeaTempLabel.setFont(ft3);
                WeaFeelLabel.setFont(ft4);
                WeaHumiLabel.setFont(ft5);
                WeaVisuLabel.setFont(ft5);
                WeaDateLabel.setFont(ft5);

                //设置字体颜色
                WeaTitleLabel.setForeground(new Color(222,222,222));
                WeaDateLabel.setForeground(new Color(222,222,222));
                WeaWeatherLabel.setForeground(new Color(222,222,222));
                WeaTempLabel.setForeground(new Color(222,222,222));
                WeaFeelLabel.setForeground(new Color(222,222,222));
                WeaWindLabel.setForeground(new Color(222,222,222));
                WeaHumiLabel.setForeground(new Color(222,222,222));
                WeaVisuLabel.setForeground(new Color(222,222,222));

                //设置天气图标
                ImageIcon weaicon = new ImageIcon(WeaIconSrc);
                weaicon.setImage(weaicon.getImage().getScaledInstance(100, 100, 2));
                WeaIconLabel.setIcon(weaicon);

                //布局设置
                Box v1 = Box.createVerticalBox();   //最外层
                Box v2 = Box.createVerticalBox();   //中间一层
                Box v3 = Box.createVerticalBox();   //放温度的
                Box h1 = Box.createHorizontalBox(); //第一行
                Box h2 = Box.createHorizontalBox(); //第二行
                Box h3 = Box.createHorizontalBox(); //第二行

                v1.add(WeaDateLabel);
                v1.add(h1);
                v1.add(Box.createVerticalStrut(5));
                v1.add(h2);
                v1.add(Box.createVerticalStrut(15));
                v1.add(h3);
                v1.add(Box.createVerticalGlue());

                v2.add(Box.createVerticalStrut(25));
                v2.add(WeaWeatherLabel);
                v2.add(Box.createVerticalStrut(5));
                v2.add(WeaWindLabel);
                v2.add(Box.createVerticalGlue());

                v3.add(WeaFeelLabel);
                v3.add(Box.createVerticalStrut(5));
                v3.add(WeaTempLabel);
                v2.add(Box.createVerticalGlue());

                h1.add(Box.createHorizontalStrut(20));
                h1.add(WeaIconLabel);
                h1.add(v2);
                h1.add(Box.createHorizontalStrut(30));
                h1.add(v3);
                h1.add(Box.createHorizontalStrut(50));
                h1.add(Box.createHorizontalGlue());

                h2.add(Box.createHorizontalStrut(100));
                h2.add(WeaHumiLabel);
                h2.add(Box.createHorizontalStrut(30));
                h2.add(WeaVisuLabel);
                h2.add(Box.createHorizontalStrut(10));
                h2.add(Box.createHorizontalGlue());

                h3.add(Box.createHorizontalStrut(240));
                h3.add(WeaDateLabel);
                h3.add(Box.createHorizontalStrut(60));
                h3.add(Box.createHorizontalGlue());

                WeatherPanel.add(v1);

            }

            //页面布局: 新闻推送
            {
                //设置文本内容
                updateNews();
                NewsChangeButton.setText("换一批");

                //设置文本格式
                Font titleft = new Font("等线",Font.BOLD,20);
                Font newsft = new Font("等线",Font.ITALIC,11);
                NewsTitleLabel.setFont(titleft);
                News1Label.setFont(newsft);
                News2Label.setFont(newsft);

                News1Label.setPreferredSize(new Dimension(280,40));
                News2Label.setPreferredSize(new Dimension(280,40));

                //设置切换按钮
                NewsChangeButton.setFocusPainted(false);NewsChangeButton.setBorderPainted(false);NewsChangeButton.setBackground(Color.blue);

                Box v1 = Box.createVerticalBox();
                Box h1 = Box.createHorizontalBox();

                h1.add(Box.createHorizontalStrut(10));
                h1.add(v1);
                h1.add(Box.createHorizontalStrut(5));
                h1.add(Box.createHorizontalGlue());

                v1.add(NewsTitleLabel);
                v1.add(Box.createVerticalStrut(10));
                v1.add(News1Label);
                v1.add(Box.createVerticalStrut(5));
                v1.add(News2Label);
                v1.add(Box.createVerticalStrut(5));
                v1.add(NewsChangeButton);
                v1.add(Box.createVerticalGlue());

                NewsPanel.add(h1);
            }

            //页面布局: Todo
            {
                //设置文本
                TodoTileLabel.setText("今日Todo");

                Font todotitlefont = new Font("",Font.BOLD,20);
                TodoTileLabel.setFont(todotitlefont);

                Box v1 = Box.createVerticalBox();

                v1.add(Box.createVerticalStrut(10));
                v1.add(TodoTileLabel);
                v1.add(Box.createVerticalStrut(5));
                v1.add(AtodoList);
                v1.add(Box.createVerticalGlue());

                TodoPanel.add(v1);

            }

        }

        //Li Wen: 事件绑定
        {
            //新闻推送: 换一批
            NewsChangeButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    //更新新闻
                    updateNews();
                }
            });
        }

    }

    /*
    created by Li Wen in 2021/5/4

    Abstract:
    获得当前日期2021-5-4，赋值给CurrentDate;

    Para:
    null

    Return value:
    null
    */
    public void initCurDate(){
        //获得当前日期，格式: 2021-05-04
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int Month = now.get(Calendar.MONTH)+1;
        int Day = now.get(Calendar.DAY_OF_MONTH);
        CurDate = year + "-" + Month + "-" + Day;
//        System.out.println("CurrentDate: " + CurDate);

    }

    /*
    created by Li Wen in 2021/5/4

    Abstract:
    初始化新闻版块

    Para:
    null

    Return value:
    null
    */
    public void getNews(){
        //这是扇贝每日一句的Web API地址
        String urlpath = "http://c.m.163.com/nc/article/headline/T1348647853363/0-40.html";
        HttpURLConnection conn;
        BufferedReader br;
        try {
            URL url = new URL(urlpath);
            conn = (HttpURLConnection) url.openConnection();

            //设置传递方式
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);

            //设置不用缓存
            conn.setUseCaches(false);

            //开始连接请求
            conn.connect();

            //请求之后的处理
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String a = br.readLine();

            //转换成JSON对象，获得两条新闻的标题、更新时间
            JSONObject NewsJB = new JSONObject(a);
            Newsitems   = new JSONArray(NewsJB.getJSONArray("T1348647853363"));

            //更新新闻计数值
            NewsCounter = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    created by Li Wen in 2021/5/4

    Abstract:
    更新新闻

    Para:
    null

    Return value:
    null
    */
    public void updateNews(){
        News1title = Newsitems.getJSONObject((NewsCounter)%41).getString("title");
        News1mtime = Newsitems.getJSONObject((NewsCounter++)%41).getString("mtime");
        News2title = Newsitems.getJSONObject((NewsCounter)%41).getString("title");
        News2mtime = Newsitems.getJSONObject((NewsCounter++)%41).getString("mtime");

        News1Label.setText("<html>" + News1title + "<br>" + News1mtime + "<html>");
        News2Label.setText("<html>" + News2title + "<br>" + News2mtime + "<html>");

    }

    /*
        created by Li Wen in 2021/5/3

        Abstract:
        获得天气信息，初始化CurWeather, WeaIconSrc。

        Para:
        city, 城市的拼音

        Return value:
        null
        */
    public void getWeather(String city){

        String locationurl = "https://geoapi.qweather.com/v2/city/lookup?key=358b11f60b7e46c4bc7a4b1606b82083&range=cn&lang=zh&gzip=n&location="+city;
        String weatherurl = "https://devapi.qweather.com/v7/weather/now?key=358b11f60b7e46c4bc7a4b1606b82083&gzip=n&location=";
        HttpURLConnection connloc;//Li Wen: 请求城市id的连接
        HttpURLConnection connwea;//Li Wen: 请求天气的连接
        BufferedReader brloc = null;
        BufferedReader brwea = null;
        StringBuilder lineloc;
        StringBuilder linewea;
        String templine = null;

        try {
            lineloc = new StringBuilder();
            linewea = new StringBuilder();

            //开始请求城市id咯
            URL urlloc = new URL(locationurl);
            connloc = (HttpURLConnection) urlloc.openConnection();
            connloc.setRequestProperty("contentType", "application/json;charset=utf-8");

            //开始连接请求
            connloc.connect();

            //成功请求之后的处理
            brloc = new BufferedReader(new InputStreamReader(connloc.getInputStream(),"UTF-8"));
            while ((templine = brloc.readLine()) != null){
                lineloc.append(templine);
            }

            //转换成JSON对象，获得城市id，更新天气请求Web API
            JSONObject jbloc   = new JSONObject(lineloc.toString());
            JSONArray cityarray = (JSONArray) jbloc.get("location");
            JSONObject city0 = (JSONObject) cityarray.get(0);
            String cityid = (String) city0.get("id");
            weatherurl = weatherurl+cityid;
            templine = null;

            //开始请求实时天气信息咯
            URL urlwea = new URL(weatherurl);
            connwea = (HttpURLConnection) urlwea.openConnection();
            connwea.setRequestProperty("contentType", "application/json;charset=utf-8");
            //开始连接请求
            connwea.connect();

            //成功请求之后的处理
            brwea = new BufferedReader(new InputStreamReader(connwea.getInputStream(),"UTF-8"));
            while ((templine = brwea.readLine()) != null){
                linewea.append(templine);
            }

            //转换成JSON对象，获得城市id，更新天气请求Web API
            JSONObject jboringin = new JSONObject(linewea.toString());
            JSONObject jbwea    = jboringin.getJSONObject("now");

            WeaIconSrc          = "./Pics/weathericon-64/" + jbwea.getString("icon") + ".png";//图标文件地址
            String wea          = jbwea.getString("text");      //天气描述
            String temp         = jbwea.getString("temp");      //温度
            String feelslike    = jbwea.getString("feelsLike"); //体感温度
            String winddir      = jbwea.getString("windDir");   //风向
            String windspeed    = jbwea.getString("windSpeed"); //风速
            String visuality    = jbwea.getString("vis");       //可见度
            String humidity     = jbwea.getString("humidity");  //湿度
            CurWeather = new Weather(CurDate,wea,temp,feelslike,winddir,windspeed,visuality,humidity);//初始化当前天气CurWeather

    }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    created by Li Wen in 2021/5/4

    Abstract:
    获得今日一图。

    Para:
    null

    Return value:
    null
    */
    public void getPicforToday(){
        String urlpic = "https://api.kdcc.cn/img/bingimg/dayimg.jpg?day=" + CurDate;

        HttpURLConnection conn = null;
        BufferedReader br;
        PicofToday = "./Pics/Wordbg/" + CurDate + ".jpg";

        try {
            URL url = new URL(urlpic);
            DataInputStream datain = new DataInputStream(url.openStream());
            FileOutputStream fos = new FileOutputStream(new File(PicofToday));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buffer = new byte[2014];
            int len;

            while ((len = datain.read(buffer)) >0 ){
                fos.write(buffer,0,len);
            }

            datain.close();
            fos.close();
            System.out.println("Pic Download finished!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /*
    created by Li Wen in 2021/5/4

    Abstract:
    从扇贝单词API找到今日句子、翻译、作者，赋值给WordforToday、TransofWord、AuthorofWord。

    Para:
    null

    Return value:
    null
    */
    public void getWordforToday(){

        //这是扇贝每日一句的Web API地址
        String urlpath = "https://apiv3.shanbay.com/weapps/dailyquote/quote/?date=" + CurDate;
        HttpURLConnection conn;
        BufferedReader br;
        try {
            URL url = new URL(urlpath);
            conn = (HttpsURLConnection) url.openConnection();

            //设置传递方式
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);

            //设置不用缓存
            conn.setUseCaches(false);

            //开始连接请求
            conn.connect();

            //请求之后的处理
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String a = br.readLine();

            //转换成JSON对象，获得句子、翻译、作者
            JSONObject jb   = new JSONObject(a);
            WordofToday     = jb.getString("content");
            TransofWord     = jb.getString("translation");
            AuthorofWord    = "-" + jb.getString("author");

//            //Li Wen: 测试输出，之后注释掉
//            System.out.println("成功获得每日一句: " + WordofToday);
//            System.out.println("翻译: " + TransofWord);
//            System.out.println("作者: " + AuthorofWord);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

//天气类
class Weather{
    String  Date;       //Li Wen: 日期
    String  Wea;        //Li Wen: 天气
    String  Temp;       //Li Wen: 温度
    String  Feelslike;  //Li Wen: 体感温度
    String  WindDir;    //Li Wen: 风向
    String  WindSpeed;  //Li Wen: 风级
    String  Visuality;  //Li Wen: 能见度
    String  Humidity;   //Li Wen: 湿度

    //Li Wen: Weather的构造函数
    public Weather(String date, String wea,
                   String temp, String feelslike,
                   String winddir, String windspeed,
                   String visuality,String humidity){
        Date    = date;
        Wea     = wea;
        Temp    = temp;
        Feelslike = feelslike;
        WindDir   = winddir;
        WindSpeed = windspeed;
        Visuality = visuality;
        Humidity  = humidity;
    }

    //Li Wen: 获得日期
    public String getDate(){
        return Date;
    }

    //Li Wen: 获得天气
    public String getWea(){
        return Wea;
    }

    //Li Wen: 获得温度
    public String getTemp(){
        return Temp;
    }

    //Li Wen: 获得体感温度
    public String getFeelslike(){
        return Feelslike;
    }

    //Li Wen: 获得风向
    public String getWindDir(){
        return WindDir;
    }

    //Li Wen: 获得风速
    public String getWindSpeed(){
        return WindSpeed;
    }

    //Li Wen: 获得可见度
    public String getVisuality(){
        return Visuality;
    }

    //Li Wen: 获得湿度
    public String getHumidity(){
        return Humidity;
    }

}



