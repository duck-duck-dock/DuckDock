import java.sql.*;
import java.util.Date;
import java.util.Vector;

/*
created by Qingling Zhang in 2021/04/02
class:数据库相关操作
 */
public class JdbcPostgreSql {
    static Connection c=null;
    static Statement stmt=null;

    public static void main(String args[]){
        if (ConnPostgreSql())System.out.println("ok");
    }

    //与数据库建立连接
    public static boolean ConnPostgreSql(){
        try {
            Class.forName("org.postgresql.Driver");
            c=DriverManager.getConnection("jdbc:postgresql://192.168.98.1:5432/postgres","postgres","123456");
            stmt=c.createStatement();

        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        //System.out.println("Opened database suuccessfully");
        return true;
    }

    //验证指定用户是否存在
    public static boolean JdbcExitUser(){
        try {
            ResultSet rs=stmt.executeQuery("select * from users");
            while (rs.next()){
                String UserId=rs.getString("userid");
                String School=rs.getString("school");
                System.out.println(UserId);
                System.out.println(School);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        return true;
    }

    //添加用户
    public static boolean JdbcAddUser(User u) {
        String sql = "insert into users(UserName,UserId,Password)"
                + "values('zql','007','123456');";
        try {
            stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return true;
    }


}
