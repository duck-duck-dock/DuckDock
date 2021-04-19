/*
created by Yuxin Zhu in 2021/03/21
class:卡片（单词、题目）
 */
public abstract class Card {
    private String FrontSide, BackSide;//卡片正面和背面内容
    private int right = 0, wrong = 0;//正确、错误次数
    private int star;//星级，1-5的整数
    private boolean marked;//是否被标注，true/false

    private int forget = 0;//Li Wen: 已经掌握，为1时

    //Li Wen: temp构造函数
    public Card(){}
    public Card(String s1, String s2){
        FrontSide = s1;
        BackSide = s2;
    }

    //Li Wen: 获取正面单词
    public String getFrontSide(){
        return FrontSide;
    }

    //Li Wen: 获取背面释义
    public String getBackSide(){
        return BackSide;
    }

    //Li Wen: 是否已经掌握
    public boolean isForget(){
        if (forget == 1){
            return true;
        }
        else return false;
    }

    //Li Wen: 设置是否记住该单词
    public void setForget(boolean ch){

        forget = ch == true ? 1:0;
    }

    //Li Wen: 答对一次
    public void setRight(){
        right++;
    }

    //Li Wen: 答错一次
    public void setWrong(){
        wrong++;
    }



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

    Word(String s1, String s2, String s3){
        super(s1,s2);
        EgSentence = s3;
    }

    //Li Wen: 获取单词例句
    public String getEgSentence(){
        return EgSentence;
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

    public Problem(){

    }
}
