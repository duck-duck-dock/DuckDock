//import java.util.Date;
//import java.util.Vector;
//
///*
//created by Yuxin Zhu in 2021/03/21
//class:用户
// */
//public class User {
//    private String UserName;//用户名，可以重复
//    private String UserID;//用户id，系统随机分配，不能重复
//    private String Password;//密码
//    private String School;//学校
//    private int Grade;//年级，0-4
//    private String DreamSchool;
//    private Date DateOfTest;//考研时间
//    private Vector<Word> Words;//他的单词表
//    private Vector<Problem> Problems;//他的题目表
//
//    public String getUserName() {
//        return UserName;
//    }
//    public void setUserName(String userName) {
//        UserName = userName;
//    }
//    public String getPassword() {
//        return Password;
//    }
//    public void setPassword(String password) {
//        Password = password;
//    }
//    public void setUserID(String userID) {
//        UserID = userID;
//    }
//    public void setGrade(int grade) {
//        Grade = grade;
//    }
//    public void setSchool(String school) {
//        School = school;
//    }
//    public void setDreamSchool(String dreamSchool) {
//        DreamSchool = dreamSchool;
//    }
//    public void setDateOfTest(Date dateOfTest) {
//        DateOfTest = dateOfTest;
//    }
//}
//
///*
//created by Yuxin Zhu in 2021/03/21
//class:所有用户信息
//读写文件写成该类的方法
// */
//class AllUser{
//    private Vector<User> Users;//所有用户
//    private int UserNum;//用户数量，>0
//    public Vector<User> getUsers() {
//        return Users;
//    }
//    public int getUserNum() {
//        return UserNum;
//    }
//
//    public void setUserNum(int userNum) {
//        UserNum = userNum;
//    }
//
//   public AllUser(){
//        Users = new Vector<User>();
//    }
//}
//
//
