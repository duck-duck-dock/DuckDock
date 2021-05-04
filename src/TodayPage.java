import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TodayPage extends JFrame {

    JPanel WordPanel;           //Li Wen: 今日鸡汤组件
    JPanel NewsPanel;           //Li Wen: 新闻推送组件
    JPanel WeatherPanel;        //Li Wen: 天气推送组件
    JPanel TodoPanel;           //Li Wen: Todo组件


    JLabel  WordLabel;          //Li Wen: 今日鸡汤: 句子标签
    JLabel  WordIconLabel;      //Li Wen: 今日鸡汤: 收藏图标按键

    JLabel  NewsTitleLabel;     //Li Wen: 新闻: 新闻标题标签
    JLabel  NewsIconLabel;      //Li Wen: 新闻: 新闻图标标签
    JLabel  News1Label;         //Li Wen: 新闻: 1号新闻标签
    JLabel  News2Label;         //Li Wen: 新闻: 2号新闻标签
    JButton NewsChangeButton;   //Li Wen: 新闻: 新闻切换按键


    JLabel  WeaDateLabel;       //Li Wen: 天气: 日期显示标签
    JLabel  WeaWeatherLabel;    //Li Wen: 天气: 天气文字标签
    JLabel  WeaIconLabel;       //Li Wen: 天气: 天气图标标签
    JLabel  WeaRainLabel;       //Li Wen: 天气: 降雨量标签
    JLabel  WeaTemperatureLabel;//Li Wen: 天气: 温度标签


    JLabel  TodoDateLabel;      //Li Wen: Todo: 日期显示标签
    JButton TodoSettingButton;  //Li Wen: Todo: 设置按键
    JButton TodoDayButton;      //Li Wen: Todo: "日"视图按键
    JButton TodoWeekButton;     //Li Wen: Todo: "周"视图按键
    JPanel  TodoDayPage;        //Li Wen: Todo: "日"视图页
    JPanel  TodoWeekPage;       //Li Wen: Todo: "周"视图页
    JPanel  TodoWeekNavigator;  //Li Wen: Todo: "周"导航栏

    CardLayout TodoLayout;      //Li Wen: Todo卡片布局

    public void initTodayPage(){

        //Li Wen: 组件注册
        {
            //给WordPanel加上了背景
            WordPanel   = new JPanel(new BorderLayout()){
                public void paintComponent(Graphics g){
                    ImageIcon bgicon = new ImageIcon("./Pics/Wordbgpic.JPG");
                    g.drawImage(bgicon.getImage(),0,0,WordPanel.getWidth(),WordPanel.getHeight(),bgicon.getImageObserver());
                }
            };
            NewsPanel   = new JPanel();
            WeatherPanel= new JPanel();
            TodoPanel   = new JPanel();

            //组件注册: 今日鸡汤
            WordLabel       = new JLabel();
            WordIconLabel   = new JLabel();

            //组件注册: 新闻推送
            NewsTitleLabel  = new JLabel("今日热点");
            NewsIconLabel   = new JLabel();
            News1Label      = new JLabel();
            News2Label      = new JLabel();
            NewsChangeButton= new JButton("换一批");

            //组件注册: 天气推送
            WeaDateLabel    = new JLabel();
            WeaWeatherLabel = new JLabel();
            WeaIconLabel    = new JLabel();
            WeaRainLabel    = new JLabel();
            WeaTemperatureLabel = new JLabel();

            //组件注册: Todo
            TodoDateLabel   = new JLabel();
            TodoDayButton   = new JButton("日");
            TodoWeekButton  = new JButton("周");
            TodoDayPage     = new JPanel();
            TodoWeekPage    = new JPanel();
            TodoWeekNavigator = new JPanel();
            TodoSettingButton = new JButton("Todo Setting");
            //Todo卡片布局设置
            TodoLayout = new CardLayout();
            TodoPanel.setLayout(TodoLayout);
            TodoPanel.add("DayPage",TodoDayPage);
            TodoPanel.add("WeekPage",TodoWeekPage);

        }

        //Li Wen: 页面布局
        {
            //页面布局: 主页面
            {
                //设置组件的大小
                this.setSize(480,400);
                WordPanel.setPreferredSize(new Dimension(460,80));
                NewsPanel.setPreferredSize(new Dimension(450,80));
                WeatherPanel.setPreferredSize(new Dimension(180,180));
                TodoPanel.setPreferredSize(new Dimension(200,200));

                //主页面布局
                Box h1 = Box.createHorizontalBox();
                Box v1 = Box.createVerticalBox();

                h1.add(WeatherPanel);
                h1.add(Box.createHorizontalStrut(5));
                h1.add(TodoPanel);
                h1.add(Box.createHorizontalGlue());

                v1.add(WordPanel);
                v1.add(Box.createVerticalStrut(5));
                v1.add(NewsPanel);
                v1.add(Box.createVerticalStrut(5));
                v1.add(h1);
                v1.add(Box.createVerticalGlue());

                this.add(v1);

                //之后记得删掉: 设置背景色
                WordPanel.setBackground(Color.orange);
                NewsPanel.setBackground(Color.gray);
                WeatherPanel.setBackground(Color.pink);
                TodoPanel.setBackground(Color.blue);
            }

            //页面布局: 今日鸡汤
            {
                //设置文本
                WordLabel.setText("树在，山在，大地在，岁月在，我在，你还要怎样更好的世界？");
                WordLabel.setHorizontalAlignment(SwingConstants.CENTER);
                WordLabel.setVerticalAlignment(SwingConstants.CENTER);
                WordLabel.setFont(new Font("宋体",Font.BOLD,30));
                //设置文本颜色
                WordLabel.setForeground(Color.white);

                WordPanel.add(WordLabel);
            }

            //页面布局: 天气推送
            {
                getWeather("成都"," ");

            }

        }

        //Li Wen: 事件绑定
        {

        }

    }


    /*
    created by Li Wen in 2021/5/3

    Abstract:
    获得天气信息

    Para:
    null

    Return value:
    null
    */
    public void getWeather(String city, String adm){
        //传入用户选择的城市代号
        String URLcity = "https://geoapi.qweather.com/v2/city/lookup?key=358b11f60b7e46c4bc7a4b1606b82083&location=" + city;

        //如果知道上级行政区，就加上到URLcity里。
        if(!adm.equals(" ")){
            URLcity = URLcity + "&adm=" + adm;
        }

        //先去找城市代码
        HttpURLConnection conn = null;
        BufferedReader dataIn = null;
        try {
            URL url = new URL(URLcity);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);

            // 设置内容的类型,设置为经过urlEncoded编码过的from参数
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestProperty("accept", "application/xml");

            //建立连接
            conn.connect();

            //发起请求，服务器响应
            //若无应答，则报错返回
            if (conn.getInputStream()==null) {
                JOptionPane.showMessageDialog(this, "网络错误，请检查您的网络连接", "获得天气失败", 2);
                return;
            }

            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            sb.append(dataIn.readLine());

            //获得当前城市的id号，加到URLweather里。
            JSONObject jb   = new JSONObject(sb.toString());
            String id = jb.getString("id");
            System.out.println(id);




        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Li Wen: TodayPage构造函数
    public TodayPage(){
        initGUI();
    }

    //Li Wen: 初始化图形界面
    public void initGUI(){
        initTodayPage();
    }

    public static void main(String[] args){
        TodayPage tempTodayPage = new TodayPage();
        tempTodayPage.show();
    }

}

class Weather{
    String  Date;       //Li Wen: 日期
    String  Week;       //Li Wen: 周几
    String  Wea;        //Li Wen: 天气
    String  Wind;       //Li Wen: 风向
    String  WindSpeed;  //Li Wen: 风级
    String  WindMeter;  //Li Wen: 风速
    String  AirLevel;   //Li Wen: 空气质量
    String  AirTips;    //Li Wen: 基于空气质量的活动建议

    //Li Wen: Weather的构造函数
    public Weather(String date, String week, String wea,
                   String wind, String windspeed, String windmeter,
                   String airlevel, String airtips){
        Date    = date;
        Week    = week;
        Wea     = wea;
        Wea     = wea;
        Wind    = wind;
        WindSpeed = windspeed;
        WindMeter = windmeter;
        AirLevel = airlevel;
        AirTips = airtips;
    }

    //Li Wen:
    public Weather(){}

}


