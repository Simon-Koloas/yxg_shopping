package ShoppingPlatform.view;

import ShoppingPlatform.TableInformation.Catalog;
import ShoppingPlatform.TableInformation.ShopCar;
import ShoppingPlatform.UserCharge;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/17/19:10
 * @Description:
 */

public class UserView {
    /**
     * 用户登录成功后，可以进行的操作
     * 功能：浏览商城
     * 充值
     * 查看购物车
     */
    public static void userFeatureSelection(int uid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("您可以进行的操作");
        System.out.println("1.浏览商城");
        System.out.println("2.充值");
        System.out.println("3.查看个人购物车");
        System.out.println("其他操作将返回上一级");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                Catalog.merchandiseDisplay();
                break;
            case "2":
                UserCharge.userCharge(uid);
                break;
            case "3":
            {ShopCar.display(uid);
                break;}
            default: {
                MainView.mainView();
                break;
            }
        }

    }

}
