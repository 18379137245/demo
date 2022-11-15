package ding.service;


import ding.dto.TopUpDto;
import ding.dto.ShoppingDto;
import ding.pojo.AccountUserWallet;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountUserWallet selectBalance(String id);

    void addBalance(TopUpDto dto);

    String shopping(String userid, ShoppingDto dto);

    String withdraw(String userid, BigDecimal deposits);
}
