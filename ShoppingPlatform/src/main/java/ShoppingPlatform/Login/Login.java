package ShoppingPlatform.Login;

import ShoppingPlatform.DBUtil.DBUtil;
import ShoppingPlatform.TableInformation.Admin;
import ShoppingPlatform.view.AdministratorView;
import ShoppingPlatform.view.MainView;
import ShoppingPlatform.view.UserView;

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
 * @Date: 2023/07/17/18:55
 * @Description:
 */
public class Login {
    static Scanner sc = new Scanner(System.in);

    public static void choiceLogin() {
        System.out.println("选择登录方式");
        System.out.println("1.管理员登录");
        System.out.println("2.用户登录");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                administratorLoginView();
                break;
            case "2":
                userLogin();
                break;
            default: {
                System.out.println("操作有误，请重新选择登录方式");
                choiceLogin();
                break;
            }
        }
    }

    /**
     * 管理员登录
     */
    public static void administratorLoginView() {

        //传参管理员表格比较，判断登录成功
        //登陆成功显示管理员界面
        System.out.println("输入管理员用户名");
        String administratorName = sc.nextLine();
        System.out.println("输入密码");
        String password = sc.nextLine();
        String sql = "select * from admin";
        List<Admin> adminList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            //判断管理员用户密码是否正确
            //正确失败分表跳转页面
            boolean isAdmin = false;
            while (rs.next()) {
                if (administratorName.equals(rs.getString("admin_name")) &&
                        password.equals(rs.getString("admin_password"))) {
                    isAdmin = true;
                    break;
                } else {
                    isAdmin = false;
                }
            }
            //登录成功，进入管理员界面
            if (isAdmin) {
                System.out.println("管理员" + "“" + administratorName + "”" + "登录成功");
                AdministratorView.administratorOperate(rs.getInt("admin_id"));
            }
            //登录失败，重新选择操作
            else {
                System.out.println("账号或者密码错误,请重新选择");
                System.out.println();
                MainView.mainView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户登录
     */
    public static void userLogin() {
        System.out.println("输入用户用户名");
        String userName = sc.nextLine();
        System.out.println("输入密码");
        String password = sc.nextLine();
        String sql = "select * from user";
        List<Admin> userList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            //判断用户密码是否正确
            //正确失败分表跳转页面
            boolean isUser = false;
            while (rs.next()) {
                if (userName.equals(rs.getString("user_name")) &&
                        password.equals(rs.getString("user_password"))) {
                    isUser = true;
                    break;
                } else {
                    isUser = false;
                }
            }

            //登录成功，进入到用户界面
            if (isUser) {
                int id=rs.getInt("user_id");
                System.out.println("用户" + "“" + userName + "”" + "登录成功");
                UserView.userFeatureSelection(id);
            }
            //登录失败，重新选择操作
            else {
                System.out.println("账号或者密码错误,请重新选择");
                System.out.println();
                MainView.mainView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
