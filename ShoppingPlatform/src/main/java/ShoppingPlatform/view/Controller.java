package ShoppingPlatform.view;

import ShoppingPlatform.Login.Login;
import ShoppingPlatform.Register.Register;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/17/15:24
 * @Description:
 */
public class Controller {
    public static Scanner sc=new Scanner(System.in);
    public static void Main() {
        String k=sc.nextLine();
        switch (k) {
            //登录页面
            case "1":
                //登录
                Login.choiceLogin();
                break;
            case "2":
                //注册
                Register.choiceRegister();
                break;
            default:{
                System.out.println("选择错误，重新选择");
                Main();
            }
        }
    }

}

