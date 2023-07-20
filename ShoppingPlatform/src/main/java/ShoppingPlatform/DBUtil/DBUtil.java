package ShoppingPlatform.DBUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/17/15:05
 * @Description:创建数据库连接
 */
public class DBUtil {
    private static String username = "root";
    private static String password = "a12345";
    private static String url = "jdbc:mysql://8.130.46.228:3306/project?characterEncoding=utf8";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(url,username,password);
    }
}
