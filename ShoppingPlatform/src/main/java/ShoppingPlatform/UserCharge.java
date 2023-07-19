package ShoppingPlatform;

import ShoppingPlatform.DBUtil.DBUtil;
import ShoppingPlatform.view.UserView;
import com.sun.xml.internal.ws.client.dispatch.PacketDispatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/18:52
 * @Description:
 */
public class UserCharge {
    /**
     * 用户登录成功后
     * 选择充值页面
     *
     * @param uid 当前登陆的用户id
     */
    public static void userCharge(int uid) {
        String sql = "select * from user where user_id=" + uid;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs=ps.executeQuery();
                    rs.next();
            double money=rs.getDouble("money");
            System.out.println("您当前的余额为："+money);
            System.out.println("充值金额：");
            Scanner sc=new Scanner(System.in);
            double inMoney=sc.nextDouble();
            if (inMoney<0){
                System.out.println("请输入正确的金额");
                userCharge(uid);
            }else {
                String sql1="update user set money+="+inMoney+"where user_id="+uid;
                System.out.println("充值成功");
                money+=inMoney;
                System.out.println("您当前的余额为："+money);
                UserView.userFeatureSelection(uid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
