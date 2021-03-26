/*
created by Yuxin Zhu in 2021/03/21
class:卡片（单词、题目）
 */
public abstract class Card {
    private String FrontSide, BackSide;//卡片正面和背面内容
    private int right, wrong;//正确、错误次数
    private int star;//星级，1-5的整数
    private boolean marked;//是否被标注，true/false

    public abstract void display();//需要在派生类里override的函数，负责在图形界面显示卡片
}

/*
created by Yuxin Zhu in 2021/03/21
class:单词
 */
class Word extends Card {
    private String EgSentence;//单词例句

    @Override
    public void display() {//显示该单词
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
