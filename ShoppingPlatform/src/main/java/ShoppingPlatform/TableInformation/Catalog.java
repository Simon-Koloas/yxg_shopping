package ShoppingPlatform.TableInformation;

import ShoppingPlatform.DBUtil.DBUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/10:05
 * @Description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Catalog {
    private int catalog_id;
    private int product_id;
    private String product_name;
    private double product_price;

    public static void merchandiseDisplay() {
        String sql = "select * from catalog";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("catalog_id");
                int pid = rs.getInt("product_id");
                String proName = rs.getString("product_name");
                double price = rs.getDouble("product_price");
                System.out.println("商品id：" + pid + "  商品名称：" + proName + "  " + "  商品价格：" + price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shopping(int uid) {
        System.out.println("购买请输入商品id：");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String sql = "select * from product where product_id=" + str;

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            //判断商品是否还有存货
            ResultSet rs = ps.executeQuery();
            if (rs.getInt("stock_num") < 0) {
                System.out.println("暂无存货，请选择其他商品");
                shopping(uid);
            } else {
                //修改商品存货数量
                //加入到客户购物车
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
