package ShoppingPlatform.ProductUpdate;

import ShoppingPlatform.DBUtil.DBUtil;
import ShoppingPlatform.view.AdministratorView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/14:33
 * @Description:
 */
public class ProductUpdate {
    /**
     * 管理员选择修改商品信息时的界面
     *
     * @param adminId 传入管理员id
     */
    public static void productView(int adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.增加商品");
        System.out.println("2.删除商品");
        System.out.println("3.更新商品");
        System.out.println("其他按键退出操作");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                productAdd(adminId);
                break;
            case "2":
                productDelete(adminId);
                break;
            case "3":
                productUpdate(adminId);
                break;
            default:
                AdministratorView.administratorOperate(adminId);
        }
    }

    public static void productAdd(int adminId) {
        Scanner sc = new Scanner(System.in);
        String sql = "insert into product(product_name," +
                "product_price,stock_num,sales_num,property_id,admin_id) " +
                "values(?,?,?,0,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement pr = c.prepareStatement(sql)) {

            System.out.println("输入商品名称：");
            String product_name = sc.nextLine();
            pr.setString(1, product_name);

            System.out.println("输入商品价格：");
            String product_price = sc.nextLine();
            pr.setDouble(2, Double.parseDouble(product_price));

            System.out.println("输入商品存货量：");
            int stock_num = sc.nextInt();
            pr.setInt(3, stock_num);

            System.out.println("输入商品属性id：");
            int property = sc.nextInt();
            pr.setInt(4, property);

            pr.setInt(5, adminId);
            pr.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("新增商品成功");
        AdministratorView.administratorOperate(adminId);
    }

    /**
     * 删除商品
     *
     * @param adminId 登录成功的管理id
     */

    public static void productDelete(int adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除商品id");
        int proId = sc.nextInt();
        String sql = "select product_id from product where admin_id=" + adminId;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            boolean productExist = false;
            while (rs.next()) {
                //判断当前管理员的商店是否含有该商品
                if (proId == rs.getInt("product_id")) {
                    productExist = true;
                }
            }
            if (productExist) {
                String sql1 = "delete from product where product_id=" + proId;
                PreparedStatement ps1 = c.prepareStatement(sql1);
                ps1.execute();
            } else
            //如果没有该商品，重新选择操作
            {
                System.out.println("商品不存在");
                productView(adminId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("删除商品成功");
        AdministratorView.administratorOperate(adminId);
    }

    /**
     * 商品更新
     *
     * @param adminId 登录成功的管理id
     */
    public static void productUpdate(int adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要更新商品id");
        int proId = sc.nextInt();
        String sql = "select product_id from product where admin_id=" + adminId;
        String sql1 = "update product set product_name=?," +
                "product_price=?,stock_num=?,property_id=? where product_id=" + proId;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             PreparedStatement ps1 = c.prepareStatement(sql1)) {
            ResultSet rs = ps.executeQuery();
            boolean productExist = false;
            while (rs.next()) {
                //判断当前管理员的商店是否含有该商品
                if (proId == rs.getInt("product_id")) {
                    productExist = true;
                }
            }


            if (productExist) {
                Scanner sc1 = new Scanner(System.in);

                System.out.println("请输入修改后商品名称");
                String product_name = sc1.nextLine();
                ps1.setString(1, product_name);


                System.out.println("请输入修改后商品价格");
                String product_price = sc1.nextLine();
                ps1.setDouble(2, Double.parseDouble(product_price));

                System.out.println("请输入修改后商品存货量");
                int stock_num = sc1.nextInt();
                ps1.setInt(3, stock_num);

                System.out.println("请输入修改后商品属性id");
                int property_id = sc1.nextInt();
                ps1.setInt(4, property_id);

                ps1.execute();
                System.out.println("更新商品成功");
            } else
            //如果没有该商品，重新选择操作
            {
                System.out.println("商品不存在");
                productView(adminId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        AdministratorView.administratorOperate(adminId);
    }
}
