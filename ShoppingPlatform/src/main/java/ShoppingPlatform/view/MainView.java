package ShoppingPlatform.view;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/17/15:53
 * @Description:
 * 显示操作页面
 * 然后选择登录，登录成功后
 * 选择操作，进入相对应的操作界面
 */
public class MainView {
     public  static  Scanner sc=new Scanner(System.in);

    /**
     * 选择操作
     */
    public static void mainView() {
        System.out.println("欢迎来到购物系统");
        System.out.println("选择操作");
        System.out.println("1.登录");
        //登录分为管理员登录和用户登录；
        System.out.println("2.注册");
        //管理员注册和用户注册
        Controller.Main();
    }

}
