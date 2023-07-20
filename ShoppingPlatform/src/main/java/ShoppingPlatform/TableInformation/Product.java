package ShoppingPlatform.TableInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/10:02
 * @Description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Product {
    private int product_id;
    private String product_name;
    private double product_price;
    private int stock_num;
    private int sales_num;
    private int property_id;
    private int admin_id;

    public void setProduct_name(String product_name) {
    }
}
