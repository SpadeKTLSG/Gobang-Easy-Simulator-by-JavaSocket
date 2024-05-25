package gobang.pojo.dto;

import gobang.pojo.entity.Function;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一通信对象
 *
 * @author SK
 * @date 2024/05/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {

    /**
     * 操作类型
     */
    Function function;

    /**
     * 数据
     */
    Object data;

}
