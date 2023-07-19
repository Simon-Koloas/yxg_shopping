package ShoppingPlatform.view;

import ShoppingPlatform.ProductUpdate.ProductUpdate;
import ShoppingPlatform.ProductUpdate.SelectProduct;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/17/19:09
 * @Description:
 */
public class AdministratorView {
    /**
     * 管理员可以对商品操作
     * 对订单操作
     *
     */
    public static void administratorOperate(int adminId){
        System.out.println("管理员你好，有什么需要帮助：");
        System.out.println("1.查看我的售货店");
        System.out.println("2.修改商品信息");
        System.out.println("3.修改订单信息");
        System.out.println("其他操作退出界面");
        Scanner sc=new Scanner(System.in);
        String choice=sc.nextLine();
        switch (choice){
            case "1":
                SelectProduct.display(adminId);
                break;
            case "2":
                ProductUpdate.productView(adminId);
                break;
            case "3":break;
            default:MainView.mainView();
        }
    }

}
