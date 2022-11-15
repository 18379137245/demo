package ding.service;


import ding.dto.TopUpDto;
import ding.dto.ShoppingDto;
import ding.pojo.AccountDetails;
import ding.pojo.AccountUserWallet;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    /**
     * 根据用户id查询用户余额
     */
    AccountUserWallet getUserWalletBalance(String id);

    /**
     * 根据用户id充值金额
     */
    void addBalance(TopUpDto dto);

    /**
     * 根据用户订单查询商品总金额进行一个消费及退款
     */
    boolean userConsumption(String userid, ShoppingDto dto);

    /**
     * 用户申请提现
     */
    boolean requestWithdrawal(String userid, BigDecimal deposits);

    /**
     * 通过用户id查询用户钱包金额变动明细的接口
     */
    List<AccountDetails> queryChangeDetailsUserWalletAmount(String userId);

}
