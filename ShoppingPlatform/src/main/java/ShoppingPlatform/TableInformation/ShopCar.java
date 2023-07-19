package ShoppingPlatform.TableInformation;

import ShoppingPlatform.DBUtil.DBUtil;
import ShoppingPlatform.view.UserView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/10:10
 * @Description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ShopCar {
    private int shop_carid;
    private int user_id;
    private int product_id;
    private double product_price;
    private int product_num;
    private double sum_price;
    public static void display(int uid){
        String sql="select * from shopcar where user_id="+uid;
        try(Connection c= DBUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)) {
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
              int id=rs.getInt("user_id");
              int proId=rs.getInt("product_id");
              double proPrice=rs.getDouble("product_price");
              int proNum=rs.getInt("product_num");
              double sumPrice=rs.getDouble("sum_price");
                System.out.println("用户id："+id
                +"  商品id："+proId
                +"  商品价格："+proPrice
                +"  购买数量:"+proNum
                +"  总价:"+sumPrice);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("是否继续操作");
        System.out.println("1.返回操作界面");
        System.out.println("2.退出");
        Scanner sc=new Scanner(System.in);
        String choice=sc.nextLine();
        switch (choice){
            case "1":
                UserView.userFeatureSelection(uid);break;
            case "2":return;
            default:
                System.out.println("操作有误，退出界面");
                break;
        }
    }
}
