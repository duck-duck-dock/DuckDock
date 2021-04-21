import java.util.Date;
import java.util.Vector;

/*
created by Yuxin Zhu in 2021/03/21
class:用户

updated by Li Wen in 2021/4/20
更新内容: 新增属性: WordNum(未掌握单词数)
         新增函数: GetWords;//获得单词表
                  GetWordNum;//获得未掌握单词数
 */
public class User {
    private String UserName;//用户名，可以重复
    private String UserID;//用户id，系统随机分配，不能重复
    private String Password;//密码
    private String School;//学校
    private int Grade;//年级，0-4
    private String DreamSchool;
    private Date DateOfTest;//考研时间
    private Vector<Word> Words;//他的单词表
    private Vector<Problem> Problems;//他的题目表
    private int WordNum;//Li Wen: 未掌握单词数


    //Li Wen: 获得单词表
    public Vector<Word> getWords(){
        return Words;
    }

    //Li Wen: 获得未掌握单词数
    public int getWordNum(){
        return WordNum;
    }
}

/*
created by Yuxin Zhu in 2021/03/21
class:所有用户信息
读写文件写成该类的方法
 */
class AllUser{
    private Vector<User> Users;//所有用户
    private int UserNum;//用户数量，>0

    public Vector<User> getAllUser() {
        return Users;
    }
}


