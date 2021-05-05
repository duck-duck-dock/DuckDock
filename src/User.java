import java.util.Date;
import java.util.Vector;

/*
created by Yuxin Zhu in 2021/03/21
class:用户
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


    public void addWord(Word word){ this.Words.add(word); }
    public void addProblem(Problem problem){
        this.Problems.add(problem);
    }
    public String getUserName() {
        return UserName;
    }
    public String getSchool(){return School;}
    public String getDreamSchool(){return DreamSchool;}
    public int getGrade(){return Grade;}
    public Date getDateOfTest(){return DateOfTest;}
    public String getUserID(){return UserID;}
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
    public Vector<Word> getWords() {
        return Words;
    }
    public Vector<Problem> getProblems() {
        return Problems;
    }
    public void setProblems(Vector<Problem> problems){//设置单词列表
        Problems=problems;
    }

    public User(){
        //UserID="002";
        Words=new Vector<Word>();
        Problems=new Vector<Problem>();
    }
    public User(String userName,String userID,String password,String school,int grade,String dreamSchool,Date dateOfTest){
        this.UserName=userName;
        this.UserID=userID;
        this.Password=password;
        this.School=school;
        this.Grade=grade;
        this.DreamSchool=dreamSchool;
        this.DateOfTest=dateOfTest;
        this.Words=null;
        this.Problems=null;
        Words=new Vector<Word>();
        Problems=new Vector<Problem>();
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
    public Vector<User> getUsers() {
        return Users;
    }
    public int getUserNum() {
        return UserNum;
    }


    public void setUserNum(int userNum) {
        UserNum = userNum;
    }

   public AllUser(){
        Users = new Vector<User>();
    }
}


