package ding.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @projectName: demo
 * @package: ding.pojo
 * @className: accountDetails
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/15 22:11
 * @version: 1.0
 */
@Data
public class AccountDetails {
    private int id;
    private String merchantName;
    private String status;
    private String productName;
    private LocalDateTime creationTime;
    private String payment;
    private String trasaction;
    private String userId;
    private BigDecimal balance;
}
