package ShoppingPlatform.Register;

import ShoppingPlatform.DBUtil.DBUtil;
import ShoppingPlatform.view.MainView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/17/19:30
 * @Description:
 */
public class Register {
    static Scanner sc = new Scanner(System.in);

    public static void choiceRegister() {
        System.out.println("选择注册");
        System.out.println("1.管理员注册");
        System.out.println("2.用户注册");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                //管理员注册
                administratorRegister();
                break;
            case "2":
                //用户注册
                userRegister();
                break;
            default: {
                System.out.println("操作有误，请重新选择操作");
                MainView.mainView();
            }

        }
    }

    public static void administratorRegister() {
        System.out.println("请输入注册管理员用户名");
        String administratorName = sc.nextLine();
        //设置判断用户名不为空
        if (!(administratorName == null || administratorName.equals(""))) {
            System.out.println("请输入注册管理员密码");
            String administratorPassword = sc.nextLine();
            //设置判断密码不为空
            if (!(administratorPassword == null || administratorPassword.equals(""))) {
                System.out.println("请输入您的电话号");
                String phone = sc.nextLine();
                //将输入的用户名密码存入对应的表中
                String sql = "insert into admin(admin_name,admin_password,admin_phone) value (?,?,?)";
                try (Connection c = DBUtil.getConnection();
                     PreparedStatement ps = c.prepareStatement(sql)) {
                    ps.setString(1, administratorName);
                    ps.setString(2, administratorPassword);
                    ps.setString(3, phone);
                    ps.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //若密码为空，重新注册
            else {
                System.out.println("密码不能为空");
                administratorRegister();
            }
        }
        //若管理员用户名为空，重新注册
        else {
            System.out.println("管理员用户名不能为空");
            administratorRegister();
        }
        System.out.println("注册成功");
        System.out.println("是否返回操作:1.yes/2.no");
        String choice = sc.nextLine();
        if ("yes".equals(choice) || "1".equals(choice)) {
            MainView.mainView();
        } else {
            return;
        }
    }

    public static void userRegister() {
        System.out.println("请输入注册用户名");
        String userName = sc.nextLine();
        //判断用户名是否为空
        if (!(userName == null && userName.equals(""))) {
            System.out.println("请输入注册用户密码");
            String userPassword = sc.nextLine();
            //判断密码为空
            if (!(userPassword == null && userPassword.equals(""))) {
                System.out.println("请输入电话");
                String phone = sc.nextLine();
                System.out.println("请设置一个默认地址");
                String address = sc.nextLine();
                //将输入的用户名密码存入对应的表中
                String sql = "insert into user(user_name,user_password,user_phone,user_address) value (?,?,?,?)";
                try (Connection c = DBUtil.getConnection();
                     PreparedStatement ps = c.prepareStatement(sql)) {
                    ps.setString(1, userName);
                    ps.setString(2, userPassword);
                    ps.setString(3, phone);
                    ps.setString(4, address);
                    ps.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //如果为空，重新注册
        else {
            System.out.println("用户名不能为空，请重新输入");
            userRegister();
        }
        System.out.println("注册成功");
        System.out.println("是否返回操作:1.yes/2.no");
        String choice = sc.nextLine();
        if ("yes".equals(choice) || "1".equals(choice)) {
            MainView.mainView();
        } else {
            return;
        }

    }
}
