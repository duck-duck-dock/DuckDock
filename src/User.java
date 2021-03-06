import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;

/*
created by Yuxin Zhu in 2021/03/21
class:用户

updated by Qingling Zhang in 2021/04/13
更新内容：User带参构造函数以及接口（addWord、addProblem、……）

updated by Han Zhao in 2021/05/07
更新内容：给每一个用户的错题表第一张给一个封面，图片在Cover中；增加向problems中增加元素的方法；增加给problems整体赋值的方法;增加标签

updated by Qingling Zhang in 2021/05/04
更新内容：增加用户备份（user_backup）函数
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
    private Vector<UserToDoList> ToDoLists;//他的待办


    public User(){
        Words=new Vector<Word>();
        Problems=new Vector<Problem>();
        ToDoLists=new Vector<UserToDoList>();
        this.Problems=new Vector<Problem>();//
        Problem p=new Problem();
        p.setProblemPosition("./Cover/cover.png");//初始化的时候，就给用户的错题图片给一个封面
        this.Problems.add(p);
        ArrayList l=new ArrayList();//初始化标签
        l.add("政治");l.add("");l.add("");
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
        p.setProblemPosition("./Cover/cover.png");//初始化的时候，就给用户的错题图片给一个封面
        this.Problems.add(p);
        ArrayList l=new ArrayList();//初始化标签
        l.add("政治");l.add("");l.add("");
        p.setLabel(l);
    }

    //备份用户所有信息
    public void user_backup(){
        if(!jdbc.JdbcExistUser(this)){
            //用户不存在则添加用户
            jdbc.JdbcAddUser(this);
        }
        else {
            jdbc.JdbcUpdateUser(this);  //更新用户信息
            jdbc.JdbcUpdateWandE(this); //更新用户单词表以及问题表
        }
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

    public Vector<UserToDoList> getToDoLists() {
         return ToDoLists;
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

updated by Qingling Zhang in 2021/05/04
更新内容：
    1.将与数据库相关操作均移入AllUser子类jdbc中，调用无需实例化
    2.将Users从private改成protected型
 */
class AllUser{
    protected Vector<User> Users;//所有用户
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

/*
created by Qingling Zhang in 2021/05/04
class:数据库相关操作
内部函数均写成静态函数，无需实例化可直接使用
 */
class jdbc extends AllUser{
    static Connection connectSql =null;
    static Statement stmtSql =null;

    /*
        created by Qingling Zhang in 2021/04/07
        Abstract:与数据库建立连接初始化connectSql和stmtSql
        Return value:Boolean类型，表示连接成功
        */
    private static boolean ConnPostgreSql(){
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
    private static boolean DisConnPostgreSql (){
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

        ConnPostgreSql();
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
        DisConnPostgreSql();
        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:更新形参User个人信息-用户更改个人信息（不包括单词及错题）后调用
    Para:   String updateSql:更新语句
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcUpdateUser(User updateUser){
        ConnPostgreSql();

        String updateSql="update users set UserName = '"+updateUser.getUserName()+"' ,Password= '"+updateUser.getPassword()
                +"' ,School= '"+updateUser.getSchool()+"' ,Grade= '"+updateUser.getGrade()+"' ,DreamSchool= '"+updateUser.getDreamSchool()
                +/*"' ,Date= "+updateUser.getDateOfTest()+*/"' where UserId= '"+updateUser.getUserID()+"' ;";
        try {
            stmtSql.executeUpdate(updateSql);
            connectSql.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        DisConnPostgreSql();

        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:更新形参User的单词表和问题表-用户准备退出时更新单词本及错题本
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcUpdateWandE(User user){
        ConnPostgreSql();

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
            if(!user.getWords().isEmpty())
            for(int i=0;i<user.getWords().size();i++){

                frontSide=user.getWords().elementAt(i).getFrontSide();
                backSide=user.getWords().elementAt(i).getBackSide();
                star=user.getWords().elementAt(i).getStar();
                marked=user.getWords().elementAt(i).getmarked();
                String egSentence=user.getWords().elementAt(i).getEgSentence();

                String addUserWordSql="insert into words(UserID,FrontSide,BackSide,right_count,wrong_count,star,marked,EgSentence)"+
                        "values('"+userID+"','"+frontSide+"','"+backSide+"','0','0','"+(star+1)+"','"+marked+"','"+egSentence+"');";

                stmtSql.executeUpdate(addUserWordSql);

                connectSql.commit();
            }

            /*先删除此用户在数据库中所有问题*/
            String delProblemSql="delete from problems where UserId= '"+userID+"';";
            stmtSql.executeUpdate(delProblemSql);
            connectSql.commit();

            /*将user中所有问题保存到数据库*/
            if(user.getProblems().elementAt(0).getFrontSide()!=null)
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
        DisConnPostgreSql();

        return true;
    }

    /*
    created by Qingling Zhang in 2021/05/04
    Abstract:查询User用户是否存在
    Return value:Boolean类型，表示成功
     */
    public static boolean JdbcExistUser(User user){
        ConnPostgreSql();

        try{
            ResultSet querySql;

            /*将指定用户从数据库读出*/
            //System.out.println(user.getUserID());
            querySql= stmtSql.executeQuery("select * from users where userid='"+user.getUserID()+"'");
            //querySql= stmtSql.executeQuery("select * from users where userid='007'");
            if(querySql==null)return false; //该用户不存在
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        DisConnPostgreSql();

        return true;
    }
    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:新增形参User用户
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcAddUser(User newUser){
        ConnPostgreSql();

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
        DisConnPostgreSql();

        return true;
    }

    /*
    created by Qingling Zhang in 2021/04/07
    Abstract:删除形参User用户
    Return value:Boolean类型，表示成功
    */
    public static boolean JdbcDeleteUser(User delUser){
        ConnPostgreSql();

        String delUserSql = "delete from users where UserId='"+delUser.getUserID()+"';";
        try {
            stmtSql.executeUpdate(delUserSql);
            connectSql.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        DisConnPostgreSql();

        return true;
    }

    /*
    created by Qingling Zhang in 2021/05/06
    Abstract:从数据库中读取城市代号
     */
    public static void JdbcChinaCity(String CityName,Vector<String> CityId,Vector<String> Adm1,Vector<String> Adm2){

        ConnPostgreSql();
        try{
            ResultSet querySql;
            /*将指定城市从数据库读出*/
            querySql= stmtSql.executeQuery("select * from chinacity where location_name_en='"+CityName+"'");
            while (querySql.next()){
                CityId.addElement(querySql.getString("location_id"));
                Adm1.addElement(querySql.getString("adm1_en"));
                Adm2.addElement(querySql.getString("adm1_en"));
            }
            querySql.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }

        DisConnPostgreSql();

    }
}


