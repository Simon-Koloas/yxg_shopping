package ShoppingPlatform.TableInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/10:07
 * @Description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Order {
    private int order_num;
    private int user_id;
    private int product_id;
    private String product_name;
    private double product_price;
    private int product_num;
    private double order_sun_price;
    private String order_state;
}
