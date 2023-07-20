package ShoppingPlatform.TableInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/9:53
 * @Description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_address;
    private int user_phone;
    private double money;
}
