/*
created by Yuxin Zhu in 2021/03/21
class:卡片（单词、题目）

updated by Li Wen in 2021/04/20
更新内容: 新增属性: forget(掌握)
         新增函数:
 */
public abstract class Card {
    private String FrontSide, BackSide; //卡片正面和背面内容
    private int right = 0, wrong = 0;   //正确、错误次数
    private int star;   //星级，1-5的整数
    private boolean marked; //是否被标注，true/false
    private int forget = 0; //Li Wen: 为1时表示已掌握, 为-1时表示该去拼写。默认为0，普通单词。


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
        return forget;
    }

    /*
    created by Li Wen in 2021/4/29

    Abstract:
    设置当前单词卡片的状态forget参数：普通0/待拼写-1/已掌握1。

    Para:
    i: 目标状态

    Return value:
    null
    */
    public void setForget(int i){
        switch(i){
            case -1: forget = -1;break;//该去拼写
            case  0: forget =  0;break;//一般单词
            case  1: forget =  1;break;//已掌握
        };
    }

    /*
    created by Li Wen in 2021/4/29

    Abstract:
    设置答对次数right，并根据答对次数设置是否进入拼单词模式。

    Para:
    i: 是否答对。为1时表示答对，为0时表示答错。

    Return value:
    Vector<Word>类型，表示所有查到的单词。
    */
    public void setRight(int i){
        if ((i==0) && (right>0)){ //困难
            right--;
        }
        else if (i==1){ //容易，right到5就去拼写。
            right++;
            if (right == 5){
                setForget(-1);
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

}

/*
created by Yuxin Zhu in 2021/03/21
class:单词

updated by Li Wen in 2021/04/20
更新内容: 新增属性：EgSentenceTrans;
         新增函数：Word(String s1, String s2, String s3);
                 public String getEgSentence();
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
 */
class Problem extends Card {
    @Override
    public void display() {//显示该题目
    }
}
