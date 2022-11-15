package ding.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @projectName: demo
 * @package: ding.dto
 * @className: accountDetailsDto
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/15 3:12
 * @version: 1.0
 */
@Data
public class AccountDetailsDto {

    private int id;
    private String merchantName = "null";
    private String status = "null";
    private String productId = "null";
    private String productName = "null";
    private LocalDateTime creationTime = LocalDateTime.now();
    private String payment = "null";
    private String userId = "null";
    private String trasaction = "null";
    private BigDecimal balance;
}
