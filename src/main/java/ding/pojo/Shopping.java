package ding.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: demo
 * @package: ding.dto
 * @className: shoppingDto
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/15 1:14
 * @version: 1.0
 */
@Data
public class Shopping implements Serializable {
    private int productId;
    private String productName;
    private String merchantName;
    private BigDecimal productPrice;
}
