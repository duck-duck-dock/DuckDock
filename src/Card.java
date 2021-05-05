import java.util.ArrayList;
import java.util.Vector;

/*
created by Yuxin Zhu in 2021/03/21
class:卡片（单词、题目）

updated by Li Wen in 2021/04/20
更新内容: 新增属性: forget(掌握)
         新增函数:

updated by Qingling Zhang in 2021/04/13
更新内容：Card带参构造函数，用于给Card所有私有变量初始化;以及多个get接口
 */
public abstract class Card {
    private String FrontSide, BackSide; //卡片正面和背面内容
    private int right = 0, wrong = 0;   //正确、错误次数
    private int star;   //星级，1-5的整数
    private boolean marked; //是否被标注，true/false
    private int forget = 0; //Li Wen: 默认为0, 为1时表示已掌握, 为-1时表示该去拼写。

    public Card(){}

    /*
    created by Li Wen in 2021/4/20

    Abstract:
    Card的构造函数，
    初始化FrontSide单词，
    初始化BackSide单词信息。

    Para:
    frontside: 单词，
    backside: 单词信息。

    Return value:
    null
    */
    public void Card(String frontside, String backside) {
        FrontSide = frontside;
        BackSide = backside;
    }

    public Card(String frontSide,String backSide,int right,int wrong,int star,boolean marked){
        this.FrontSide=frontSide;
        this.BackSide=backSide;
        this.right=right;
        this.wrong=wrong;
        this.star=star;
        this.marked=marked;
    }

    //Li Wen: 获取正面单词
    public String getFrontSide(){
        return FrontSide;
    }

    //Li Wen: 获取背面释义
    public String getBackSide(){
        return BackSide;
    }

    //Li Wen: 获取掌握情况
    public int isForget(){
//        if (forget == 0){
//            return false;
//        }
//        else return true;
        return forget;
    }

    //Li Wen: 掌握单词
    public void setForget(boolean ch){
        forget = ch == true ? 1:0;
    }

    //Li Wen: 容易/模糊/困难，修改Right
    public void setRight(int i){
        if ((i==0) && (right>0)){
            right--;
        }
        else if (i==1){
            right++;
            if (right == 5){
                setForget(false);
            }
        }
    }

    /*
    created by Li Wen in 2021/4/30

    Abstract:
    设置wrong

    Para:
    i, 对wrong的操作：i为1时，wrong+1; i为0时，wrong置0。

    Return value:
    null
    */
    public void setWrong(int i){
        if (i == 0){
            wrong = 0;
        }
        else{
            wrong++;
        }
    }

    //Li Wen: 判断wrong是否为0
    public boolean isemptyWrong(){
        if (wrong == 0)
            return true;
        else return false;
    }

    public abstract void display();//需要在派生类里override的函数，负责在图形界面显示卡片

    public int getRight() {
        return right;
    }
    public int getWrong() {
        return wrong;
    }
    public int getStar() {
        return star;
    }
    public boolean getmarked(){return marked;}

}

/*
created by Yuxin Zhu in 2021/03/21
class:单词

updated by Li Wen in 2021/04/20
更新内容: 新增属性：EgSentenceTrans;
         新增函数：Word(String s1, String s2, String s3);
                 public String getEgSentence();

updated by Qingling Zhang in 2021/04/13
更新内容：Word带参构造函数，用于给Word所有私有变量初始化（调用super初始化继承自父类的）；以及get接口
 */
class Word extends Card {
    private String EgSentence;//单词例句
    private String EgSentenceTrans;//Li Wen: 单词例句翻译
    private String Accent;//Li Wen: 音标

    @Override
    public void display() {//显示该单词
    }

    //Li Wen: Word构造函数
    Word(String frontside, String backside, String eg, String egtrans, String accent){
        super.Card(frontside,backside);
        EgSentence = eg;
        EgSentenceTrans = egtrans;
        Accent = accent;
    }

    public Word(String frontSide,String backSide,int right,int wrong,int star,boolean marked,String egSentence){
        super(frontSide,backSide,right,wrong,star,marked);
        this.EgSentence=egSentence;
    }

    Word(){}

    //Li Wen: 获取例句
    public String getEgSentence(){
        return EgSentence;
    }

    //Li Wen: 获取例句翻译
    public String getEgSentenceTrans(){
        return EgSentenceTrans;
    }

    //Li Wen: 获取音标
    public String getAccent(){
        return Accent;
    }
}

/*
created by Yuxin Zhu in 2021/03/21
class:题目

updated by Qingling Zhang in 2021/04/13
更新内容：Problem带参构造函数，用于给Problem所有私有变量初始化（调用super初始化继承自父类的）
 */
class Problem extends Card {
    private String ProblemPosition;//图片位置
    //存标签，暂时未定
    public String getProblemPosition() { return ProblemPosition; }//得到图片的位置
    public void setProblemPosition(String position){ProblemPosition=position;}//设置图片位置

    public Problem(){}
    public Problem(String frontSide,String backSide,int right,int wrong,int star,boolean marked){
        super(frontSide,backSide,right,wrong,star,marked);
    }
    @Override
    public void display() {//显示该题目
    }
}
