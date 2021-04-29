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

    //Li Wen: 测试代码，2021.4.26
    public User(int i){
        UserName = "testUser " + i;
        UserID = "TestUserID " + i;
        Password = "testPassWord " + i;
        School = "testSchool " + i;
        Grade = 0;
        DreamSchool = "testDreamSchool " + i;
        DateOfTest = new Date(System.currentTimeMillis());
        Word w1 = new Word(UserID+"frontside1",
                UserID+"backside1",UserID+"eg1",
                UserID+"egtrans1",UserID+"accent1");
        Word w2 = new Word(UserID+"frontside2",
                UserID+"backside2",UserID+"eg2",
                UserID+"egtrans2",UserID+"accent2");
        Word w3 = new Word(UserID+"frontside3",
                UserID+"backside3",UserID+"eg3",
                UserID+"egtrans3",UserID+"accent3");
        Word w4 = new Word(UserID+"frontside4",
                UserID+"backside4",UserID+"eg4",
                UserID+"egtrans4",UserID+"accent4");
        Word w5 = new Word(UserID+"frontside5",
                UserID+"backsid5e",UserID+"eg5",
                UserID+"egtrans5",UserID+"accent5");
        Words = new Vector<Word>();
        Words.add(w1);Words.add(w2);Words.add(w3);Words.add(w4);Words.add(w5);
        WordNum = 5;


    }



    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setUserID(String userID) {
        UserID = userID;
    }
    public void setGrade(int grade) {
        Grade = grade;
    }
    public void setSchool(String school) {
        School = school;
    }
    public void setDreamSchool(String dreamSchool) {
        DreamSchool = dreamSchool;
    }
    public void setDateOfTest(Date dateOfTest) {
        DateOfTest = dateOfTest;
    }
}

/*
created by Yuxin Zhu in 2021/03/21
class:所有用户信息
读写文件写成该类的方法
 */
class AllUser{
    private Vector<User> Users = new Vector<User>();;//所有用户
    private int UserNum;//用户数量，>0


    //Li Wen: 测试代码，2021.4.26
    //AllUser初始化函数
    public AllUser() {
        Users.add(new User(1));
        Users.add(new User(2));
        Users.add(new User(3));
        Users.add(new User(4));
        Users.add(new User(5));
    }

    //获得Users对象

    public Vector<User> getUsers() {
        return Users;
    }
    public int getUserNum() {
        return UserNum;
    }
}


