import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/*
created by Yuxin Zhu in 2021/03/21
class:用户

updated by Qingling Zhang in 2021/04/13
更新内容：User带参构造函数以及接口（addWord、addProblem、……）

updated by Han Zhao in 2021/05/07
更新内容：给每一个用户的错题表第一张给一个封面，图片在Cover中；增加向problems中增加元素的方法；增加给problems整体赋值的方法;增加标签
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


    public User(){
        Words=new Vector<Word>();
        this.Problems=new Vector<Problem>();//
        Problem p=new Problem();
        p.setProblemPosition("./Cover/cover.jpeg");//初始化的时候，就给用户的错题图片给一个封面
        this.Problems.add(p);
        ArrayList l=new ArrayList();//初始化标签
        l.add("这是封面！！");l.add("");l.add("");
        p.setLabel(l);
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
        //this.Problems=null;
        Words=new Vector<Word>();
        this.Problems=new Vector<Problem>();
        Problem p=new Problem();
        p.setProblemPosition("./Cover/cover.jpeg");//初始化的时候，就给用户的错题图片给一个封面
        this.Problems.add(p);
        ArrayList l=new ArrayList();//初始化标签
        l.add("这是封面！！");l.add("");l.add("");
        p.setLabel(l);
    }

    public void addWord(Word word){ this.Words.add(word); }
    public void addProblem(Problem problem){ this.Problems.add(problem); }//向问题列表添加问题，被添加的是问题对象
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
}

/*
created by Yuxin Zhu in 2021/03/21
class:所有用户信息
读写文件写成该类的方法

updated by Qingling Zhang in 2021/04/13
更新内容：
    1.Connection connectSql：用来与数据库连接。Statement stmtSql执行不带参数的简单SQL语句
    2.与数据库建立连接、断开连接
    3.从数据库中读取所有用户信息（包括单词表和问题表）保存到形参中的AllUser对象中
    4.更新形参User个人信息
    5.新增、删除形参User用户
    6.更新形参User的单词表和问题表
 */
class AllUser{
    private Vector<User> Users;//所有用户
    private int UserNum;//用户数量，>0
    static Connection connectSql =null;
    static Statement stmtSql =null;
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

    /*
        created by Qingling Zhang in 2021/04/07
        Abstract:与数据库建立连接初始化connectSql和stmtSql
        Return value:Boolean类型，表示连接成功
        */
    public static boolean ConnPostgreSql(){
        try {
            Class.forName("org.postgresql.Driver");
            connectSql =DriverManager.getConnection("jdbc:postgresql://192.168.98.1:5432/postgres","postgres","123456");
            connectSql.setAutoCommit(false);
            stmtSql = connectSql.createStatement();

        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        //System.out.println("Opened database suuccessfully");
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:断开与数据库的所有连接
    Return value:Boolean类型，表示成功
    */
    public static boolean DisConnPostgreSql (){
        try{
            connectSql.commit();
            stmtSql.close();
            connectSql.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:从数据库中读取所有用户信息（包括单词表和问题表）保存到形参中的AllUser对象中
    Para:   ResultSet querySql:接收查询数据库得到的内容
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcInitAllUsers(AllUser users){

        try {
            ResultSet querySql;

            /*将所有用户信息从数据库读出*/
            querySql= stmtSql.executeQuery("select * from users");
            while (querySql.next()){
                String userName=querySql.getString("UserName");
                String userID=querySql.getString("UserId");
                String password=querySql.getString("Password");
                String school=querySql.getString("School");
                int grade=querySql.getInt("Grade");
                String dreamSchool=querySql.getString("DreamSchool");
                Date dateOfTest=querySql.getDate("Date");

                User u=new User(userName,userID,password,school,grade,dreamSchool,dateOfTest);

                System.out.println(userName);
                users.Users.add(u);
            }
            querySql.close();

            /*将每个用户的单词表从数据库读出*/
            for (int s=0;s<users.Users.size();s++){
                querySql= stmtSql.executeQuery("select * from words where UserId= '"+users.Users.elementAt(s).getUserID()+"'");
                while (querySql.next()){
                    String frontSide=querySql.getString("FrontSide");
                    String backSide=querySql.getString("BackSide");
                    int right=querySql.getInt("right_count");
                    int wrong=querySql.getInt("wrong_count");
                    int star=querySql.getInt("star");
                    boolean marked=querySql.getBoolean("marked");
                    String egSentence=querySql.getString("EgSentence");

                    Word w=new Word(frontSide,backSide,right,wrong,star,marked,egSentence);

                    users.Users.elementAt(s).addWord(w);
                }
            }
            querySql.close();
            /*将每个用户的所有问题从数据库读出*/
            for (int s=0;s<users.Users.size();s++){
                querySql= stmtSql.executeQuery("select * from problems where UserId= '"+users.Users.elementAt(s).getUserID()+"'");
                while (querySql.next()){
                    String frontSide=querySql.getString("FrontSide");
                    String backSide=querySql.getString("BackSide");
                    int right=querySql.getInt("right_count");
                    int wrong=querySql.getInt("wrong_count");
                    int star=querySql.getInt("star");
                    boolean marked=querySql.getBoolean("marked");

                    Problem p=new Problem(frontSide,backSide,right,wrong,star,marked);

                    users.Users.elementAt(s).addProblem(p);
                }
            }
            querySql.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:更新形参User个人信息-用户更改个人信息（不包括单词及错题）后调用
    Para:   String updateSql:更新语句
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcUpdateUser(User updateUser){
        String updateSql="update users set UserName = '"+updateUser.getUserName()+"' ,Password= '"+updateUser.getPassword()
                +"' ,School= '"+updateUser.getSchool()+"' ,Grade= '"+updateUser.getGrade()+"' ,DreamSchool= '"+updateUser.getDreamSchool()
                +"' ,Date= '"+updateUser.getDateOfTest()+"' where UserId= '"+updateUser.getUserID()+"' ;";
        try {
            stmtSql.executeUpdate(updateSql);
            connectSql.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:更新形参User的单词表和问题表-用户准备退出时更新单词本及错题本
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcUpdateWandE(User user){
        String userID=user.getUserID();
        String frontSide;
        String backSide;
        int right;
        int wrong;
        int star;
        boolean marked;
        try{
            /*先删除此用户在数据库中所有单词信息*/
            String delWordsSql="delete from words where UserId= '"+userID+"';";
            stmtSql.executeUpdate(delWordsSql);
            connectSql.commit();

            /*将user中单词信息保存到数据库*/
            for(int i=0;i<user.getWords().size();i++){

                frontSide=user.getWords().elementAt(i).getFrontSide();
                backSide=user.getWords().elementAt(i).getBackSide();
                right=user.getWords().elementAt(i).getRight();
                wrong=user.getWords().elementAt(i).getWrong();
                star=user.getWords().elementAt(i).getStar();
                marked=user.getWords().elementAt(i).getmarked();
                String egSentence=user.getWords().elementAt(i).getEgSentence();

                String addUserWordSql="insert into words(UserID,FrontSide,BackSide,right_count,wrong_count,star,marked,EgSentence)"+
                        "values('"+userID+"','"+frontSide+"','"+backSide+"','"+right+"','"+wrong+"','"+star+"','"+marked+"','"+egSentence+"');";

                stmtSql.executeUpdate(addUserWordSql);
                connectSql.commit();
            }

            /*先删除此用户在数据库中所有问题*/
            String delProblemSql="delete from problems where UserId= '"+userID+"';";
            stmtSql.executeUpdate(delProblemSql);
            connectSql.commit();

            /*将user中所有问题保存到数据库*/
            for(int i=0;i<user.getProblems().size();i++){
                frontSide=user.getProblems().elementAt(i).getFrontSide();
                backSide=user.getProblems().elementAt(i).getBackSide();
                right=user.getProblems().elementAt(i).getRight();
                wrong=user.getProblems().elementAt(i).getWrong();
                star=user.getProblems().elementAt(i).getStar();
                marked=user.getProblems().elementAt(i).getmarked();

                String addUserProblemSql="insert into problems(UserID,FrontSide,BackSide,right_count,wrong_count,star,marked)"+
                        "values('"+userID+"','"+frontSide+"','"+backSide+"','"+right+"','"+wrong+"','"+star+"','"+marked+"');";

                stmtSql.executeUpdate(addUserProblemSql);
                connectSql.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:新增形参User用户
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcAddUser(User newUser){
        String newUserSql = "insert into users(UserName,UserId,Password,School,Grade,DreamSchool,Date)"
                + "values('"+newUser.getUserName()+"','"+newUser.getUserID()+"','"+newUser.getPassword()+"','"+newUser.getSchool()
                +"','"+newUser.getGrade()+"','"+newUser.getDreamSchool()+"','"+newUser.getDateOfTest()+"');";
        try {
            stmtSql.executeUpdate(newUserSql);
            connectSql.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:删除形参User用户
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcDeleteUser(User delUser){
        String delUserSql = "delete from users where UserId='"+delUser.getUserID()+"';";
        try {
            stmtSql.executeUpdate(delUserSql);
            connectSql.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return true;
    }
}


