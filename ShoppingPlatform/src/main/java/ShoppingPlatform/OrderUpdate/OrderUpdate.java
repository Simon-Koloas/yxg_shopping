package ShoppingPlatform.OrderUpdate;

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
 * @Date: 2023/07/18/17:17
 * @Description:
 */
public class OrderUpdate {
    /**
     * 管理员选择修改订单信息时的界面
     *
     * @param adminId 传入管理员id
     */
    public static void productView(int adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.增加订单");
        System.out.println("2.删除订单");
        System.out.println("3.更新订单");
        System.out.println("其他按键退出操作");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                orderAdd(adminId);
                break;
            case "2":
                orderDelete(adminId);
                break;
            case "3":
                orderUpdate(adminId);
                break;
            default:
                AdministratorView.administratorOperate(adminId);
        }
    }

    public static void orderAdd(int adminId) {

        String sql = "insert into orders(user_id,product_id," +
                "product_name,product_price,product_num,order_sum_price,order_state) " +
                "values(?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement pr = c.prepareStatement(sql)) {
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            Scanner sc3 = new Scanner(System.in);
            System.out.println("输入客户id：");
            int user_id = sc.nextInt();
            pr.setInt(1, user_id);

            System.out.println("输入商品id：");
            int product_id = sc.nextInt();
            pr.setInt(2, product_id);

            System.out.println("输入商品名称：");
            String product_name = sc1.nextLine();
            pr.setString(3, product_name);

            System.out.println("输入商品价格:");
            String product_price = sc1.nextLine();
            pr.setDouble(4, Double.parseDouble(product_price));

            System.out.println("输入商品数量");
            int product_num = sc2.nextInt();
            pr.setInt(5, product_num);

            System.out.println("输入总价");
            double order_sum_price = sc2.nextDouble();
            pr.setDouble(6, order_sum_price);

            System.out.println("配送状态：");
            String order_state = sc3.nextLine();
            pr.setString(7, order_state);

            pr.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("新增订单成功");
        AdministratorView.administratorOperate(adminId);
    }

    public static void orderDelete(int adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除订单id");
        int ordId = sc.nextInt();
        String sql = "select order_num from orders where admin_id=" + adminId;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            boolean productExist = false;
            while (rs.next()) {
                //判断当前管理员的商店是否含有该订单
                if (ordId == rs.getInt("order_num")) {
                    productExist = true;
                }
            }
            if (productExist) {
                String sql1 = "delete from orders where order_num=" + ordId;
                PreparedStatement ps1 = c.prepareStatement(sql1);
                ps1.execute();
            } else
            //如果没有该订单，重新选择操作
            {
                System.out.println("订单不存在");
                productView(adminId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("删除订单成功");
        AdministratorView.administratorOperate(adminId);
    }

    public static void orderUpdate(int adminId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要更新的订单id");
        int ordId = sc.nextInt();
        String sql = "select order_num from orders";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            boolean ordExist = false;
            while (rs.next()) {
                //判断当前管理员的商店是否含有该商品
                if (ordId == rs.getInt("order_num")) {
                    ordExist = true;
                }
            }
            if (ordExist) {
                String sql1 = "update orders set user_id=?,product_id=?," +
                        "product_name=?,product_price=?,product_num=?,order_sum_price=?" +
                        "where order_num=" + ordId;
                PreparedStatement ps1 = c.prepareStatement(sql1);

                Scanner sc1 = new Scanner(System.in);

                System.out.println("请输入订单用户id");
                int user_id = sc1.nextInt();
                ps1.setInt(1, user_id);

Scanner sc2=new Scanner(System.in);

                System.out.println("请输入新的商品id");
                int product_id = sc1.nextInt();
                ps1.setInt(2, user_id);

                System.out.println("请输入新的商品名称");
                String product_name = sc2.nextLine();
                ps1.setString(3, product_name);

                Scanner sc3=new Scanner(System.in);

                System.out.println("请输入修改后商品价格");
                double product_price = sc2.nextDouble();
                ps1.setDouble(4, product_price);

                System.out.println("请输入修改后商品数量");
                int product_num = sc3.nextInt();
                ps1.setInt(5, product_num);

                System.out.println("请输入修改后总价");
                double order_sum_price = sc3.nextDouble();
                ps1.setDouble(6, order_sum_price);

                ps1.execute();
            } else
            //如果没有该订单，重新选择操作
            {
                System.out.println("订单不存在");
                productView(adminId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("更新订单成功");
        AdministratorView.administratorOperate(adminId);
    }
}
