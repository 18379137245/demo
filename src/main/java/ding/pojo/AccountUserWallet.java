package ding.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountUserWallet implements Serializable {
    private String userId;
    private String balanceId;
    private BigDecimal balance;
    private BigDecimal deposits;

}