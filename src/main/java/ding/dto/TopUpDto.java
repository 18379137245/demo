package ding.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * @projectName: demo
 * @package: ding.dto
 * @className: TopUp
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/15 0:58
 * @version: 1.0
 */
@Data
public class TopUpDto {
    //用户id
    private String userId;
    private String status;
    //充值金额
    private BigDecimal balance;
}
