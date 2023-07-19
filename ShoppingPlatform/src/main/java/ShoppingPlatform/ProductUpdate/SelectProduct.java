package ShoppingPlatform.ProductUpdate;

import ShoppingPlatform.DBUtil.DBUtil;
import ShoppingPlatform.TableInformation.Product;
import ShoppingPlatform.view.AdministratorView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/14:43
 * @Description:
 */
public class SelectProduct {
    /**
     * 管理员登录成功后，可以查看自己名下的商品清单
     *
     * @param adminId 管理员id
     */
    public static void display(int adminId) {
        String sql = "select * from product where admin_id=" + adminId;
        List<Product> productList=new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //找寻商品属性
                String sql1="select property_name from property where property_id="+rs.getInt("product_id");
                PreparedStatement ps1=c.prepareStatement(sql1);
                ResultSet rs1=ps1.executeQuery();
                rs1.next();
                 int product_id=rs.getInt("product_id");
                 String product_name=rs.getString("product_name");
                 double product_price=rs.getDouble("product_price");
                 int stock_num=rs.getInt("stock_num");
                 int sales_num=rs.getInt("sales_num");
                 int property_id=rs.getInt("property_id");
                 Product product=new Product();
                 product.setProduct_id(product_id);
                 product.setProduct_name(product_name);
                 product.setProduct_price(product_price);
                 product.setStock_num(stock_num);
                 product.setSales_num(sales_num);
                 product.setProperty_id(property_id);
                 productList.add(product);
                System.out.println("商品id:" + rs.getInt("product_id")
                +"  商品名称:"+rs.getString("product_name")
                +"  商品价格:"+rs.getDouble("product_price")
                +"  存货量:"+rs.getInt("stock_num")
                +"  销量:"+rs.getInt("sales_num")
                +"  商品属性:"+rs1.getString("property_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdministratorView.administratorOperate(adminId);
    }
}
