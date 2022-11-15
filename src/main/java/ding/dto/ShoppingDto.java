package ding.dto;

import ding.pojo.Shopping;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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
public class ShoppingDto {
    private String status;
    private int productId;
    private String productName;
    private String merchantName;
    private BigDecimal productPrice;

    //private List<Shopping> shoppingList;//多个商品
}
