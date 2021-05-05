import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Objects;

/*
created by Yuxin Zhu in 2021/04/22
 */

//整个页面
//封装传递引用

public class TomatoFrame extends JFrame {
    int HEIGHT = 600;
    int WIDTH = 300;
    TomatoPanel drawClock;//绘制钟的面板
    JPanel buttonPanel;
    JButton start, pulse;//开始键，暂停键
    boolean Flag=false;//是否在运行



    public void initGUI() {
        drawClock = new TomatoPanel();
        getContentPane().add(drawClock, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        start = new JButton("开始");
        pulse = new JButton("暂停");
        start.setBackground(Color.orange);
        pulse.setBackground(Color.orange);
        start.setSize(80, 40);
        pulse.setSize(80, 40);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        pulse.setFocusPainted(false);
        pulse.setBorderPainted(false);
        start.setFont(new Font("雅黑", 12, 12));
        pulse.setFont(new Font("雅黑", 12, 12));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(start);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(pulse);
        buttonPanel.add(Box.createHorizontalGlue());
        //计时器线程
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawClock.mode = 1;

                Flag=true;

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        while(drawClock.remainTime>=0){
                            if(Flag){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                drawClock.remainTime--;
                                SwingUtilities.invokeLater(new Runnable(){
                                    public void run() {
                                        drawClock.timeLabel=drawClock.timeToString();
                                        drawClock.repaint();
                                    }
                                });
                            }
                        }

                    }
                }).start();
            }
        });
        pulse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawClock.mode = 2;
                Flag=false;
            }
        });
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.repaint();
    }
    public TomatoFrame(){
        super("番茄钟");
        initGUI();
    }

}

//绘制番茄钟
class TomatoPanel extends JPanel {
    int HEIGHT = 300;
    int WIDTH = 300;
    int mode = 0;//0未开始 1已开始 2被暂停
    Integer allTime = 0;//倒计时初始时间和剩下时间(秒)
    Integer remainTime = 0;
    int endX = WIDTH / 2, endY = HEIGHT / 2 - 100;
    int r = 100;//钟的半径
    String timeLabel = "00:00:00";
    int arcAngle = 0;

    public TomatoPanel() {//番茄钟
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setSize(WIDTH, HEIGHT);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mode == 1)//运行的时候禁止拖动
                    return;
                super.mouseDragged(e);
                int X = e.getX(), Y = e.getY();
                int deltaX = (X - WIDTH / 2), deltaY = (Y - HEIGHT / 2);
                double degree = Math.atan((double) deltaX / (double) deltaY);//旋转角
                double roundX = r * Math.sin(degree);
                double rountY = r * Math.cos(degree);
                double deg = Math.toDegrees(degree);//旋转角度

                if (deltaX > 0) {
                    if (deltaY > 0) {//右下角
                        endX = (int) roundX + WIDTH / 2;
                        endY = (int) rountY + HEIGHT / 2;
                        allTime = (int) (120 * ((180 - deg) / 360));
                        arcAngle = -180 + (int) deg;
                    } else {//右上角
                        endX = -(int) roundX + WIDTH / 2;
                        endY = -(int) rountY + HEIGHT / 2;
                        allTime = -(int) (120 * (deg / 360));
                        arcAngle = (int) deg;
                    }
                } else {
                    if (deltaY > 0) {//左下角
                        endX = (int) roundX + WIDTH / 2;
                        endY = (int) rountY + HEIGHT / 2;
                        allTime = (int) (120 * ((270 - deg) / 360) - 30);
                        arcAngle = -180 + (int) deg;
                    } else {//左上角
                        endX = -(int) roundX + WIDTH / 2;
                        endY = -(int) rountY + HEIGHT / 2;
                        allTime = (int) (120 * ((360 - deg) / 360));
                        arcAngle = -360 + (int) deg;
                    }
                }
                allTime *= 60;//秒数
                remainTime=allTime;
                //计算时分秒
                timeLabel=timeToString();
                repaint();
            }
        });
        repaint();
    }

    public void drawOval(int centerX, int centerY, int r, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(centerX - r, centerY - r, 2 * r, 2 * r);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(new Color(165, 222, 228));
        drawOval(WIDTH / 2, HEIGHT / 2, r, graphics);//时钟圆盘
        g2d.setColor(Color.orange);
        g2d.setStroke(new BasicStroke(10));
        graphics.drawArc(WIDTH / 2 - r, HEIGHT / 2 - r, 2 * r, 2 * r, 90, arcAngle);//绘制圆弧
        g2d.setColor(new Color(121, 167, 172));
        drawOval(WIDTH / 2, HEIGHT / 2 - r, 10, graphics);//起始点
        graphics.setColor(Color.orange);
        drawOval(endX, endY, 10, graphics);
        Font MyFont1 = new Font("雅黑", 12, 30);
        graphics.setFont(MyFont1);
        graphics.setColor(Color.white);
        graphics.drawString(timeLabel, WIDTH / 2 - 57, HEIGHT / 2 + 10);

    }

    public String timeToString(){//将剩余秒数转换为HH:MM:SS
        int hour = (int) Math.floor(remainTime / 3600);
        int minute = (int) Math.floor((remainTime - hour * 3600) / 60);
        int sec = remainTime % 60;
        String m = Integer.toString(minute);//填充分钟到两位
        if (minute < 10) m = '0' + m;
        String s = Integer.toString(sec);//填充秒钟到两位
        if (sec < 10) s = '0' + s;
        String timeLabel1 = '0' + Integer.toString(hour) + ":" + m + ':' + s;
        return timeLabel1;
    }

}
