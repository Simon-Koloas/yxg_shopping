package ShoppingPlatform.TableInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Simon
 * @Date: 2023/07/18/10:04
 * @Description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Property {
    private int property_id;
    private String property_name;
    private String property_describe;
}
