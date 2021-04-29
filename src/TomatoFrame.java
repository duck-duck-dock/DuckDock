import javax.swing.*;
import java.awt.*;

/*
created by Yuxin Zhu in 2021/04/22
 */
public class TomatoFrame extends JFrame {
    int HEIGHT=600;
    int WIDTH=300;
    int mode;//0正计时 1倒计时
    int allTime,remainTime;//mode=0时有效，倒计时初始时间和剩下时间
    int runTime;//mode=1时有效，正计时已运行时间
    JPanel drawClock;//绘制钟的面板



    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(new Color(165,222,228));
        int r=100;

        GradientPaint gradient =new GradientPaint(WIDTH/2, HEIGHT/2,Color.white, WIDTH, HEIGHT, new Color(111, 164, 229),true);
        g2d.setPaint(gradient);
        g2d.fillOval(WIDTH/2-r, HEIGHT/2-r, r*2, r*2);

    }

    public void initGUI(){
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);
        this.repaint();
    }
    public TomatoFrame(){
        super("番茄钟");
        initGUI();
    }

}

class Main{
    public static void main(String[] args){
        TomatoFrame t=new TomatoFrame();
        t.show();
    }
}
