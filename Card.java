/*
created by Yuxin Zhu in 2021/03/21
class:卡片（单词、题目）

updated by Qingling Zhang in 2021/04/13
更新内容：Card带参构造函数，用于给Card所有私有变量初始化;以及多个get接口
 */
public abstract class Card {
    private String FrontSide, BackSide;//卡片正面和背面内容
    private int right, wrong;//正确、错误次数
    private int star;//星级，1-5的整数
    private boolean marked;//是否被标注，true/false

    public abstract void display();//需要在派生类里override的函数，负责在图形界面显示片

    public Card(String frontSide,String backSide,int right,int wrong,int star,boolean marked){
        this.FrontSide=frontSide;
        this.BackSide=backSide;
        this.right=right;
        this.wrong=wrong;
        this.star=star;
        this.marked=marked;
    }

    public String getFrontSide() {
        return FrontSide;
    }
    public String getBackSide() {
        return BackSide;
    }
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

updated by Qingling Zhang in 2021/04/13
更新内容：Word带参构造函数，用于给Word所有私有变量初始化（调用super初始化继承自父类的）；以及get接口
 */
class Word extends Card {
    private String EgSentence;//单词例句

    public Word(String frontSide,String backSide,int right,int wrong,int star,boolean marked,String egSentence){
        super(frontSide,backSide,right,wrong,star,marked);
        this.EgSentence=egSentence;
    }

    public String getEgSentence() {
        return EgSentence;
    }

    @Override
    public void display() {//显示该单词
    }

}

/*
created by Yuxin Zhu in 2021/03/21
class:题目

updated by Qingling Zhang in 2021/04/13
更新内容：Problem带参构造函数，用于给Problem所有私有变量初始化（调用super初始化继承自父类的）
 */
class Problem extends Card {

    public Problem(String frontSide,String backSide,int right,int wrong,int star,boolean marked){
        super(frontSide,backSide,right,wrong,star,marked);
    }

    @Override
    public void display() {//显示该题目
    }
}
