package ShoppingPlatform.TableInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/9:52
 * @Description:
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Admin {
    private int admin_id;
    private String admin_name;
    private String admin_password;
    private int admin_phone;
}
