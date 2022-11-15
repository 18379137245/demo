package ding.service.impl;

import ding.dto.TopUpDto;
import ding.dto.ShoppingDto;
import ding.mapper.AccountDetailsMapper;
import ding.mapper.AccountMapper;
import ding.pojo.AccountDetails;
import ding.pojo.AccountUserWallet;
import ding.pojo.Shopping;
import ding.service.AccountService;
import ding.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: demo
 * @package: ding.service.impl
 * @className: AccountServiceImpl
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/14 20:44
 * @version: 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountDetailsMapper accountDetailsMapper;

    /**
     * 根据用户id查询用户余额
     */
    @Override
    public AccountUserWallet findBalByUserId(String id) {
        return accountMapper.findBalByUserId(id);
    }

    /**
     * 根据用户id充值金额
     */
    @Override
    @Transactional
    public void addBalance(TopUpDto dto) {
        try {
            //获取用户余额
            AccountUserWallet accountUserWallet = accountMapper.findBalByUserId(dto.getUserId());
            BigDecimal Initial = accountUserWallet.getBalance();
            BigDecimal deposits = accountUserWallet.getDeposits();
            //复制到AccountUserWallet对象中
            AccountUserWallet userWallet = BeanHelper.copyProperties(dto, AccountUserWallet.class);
            //根据是第三方金额还是自己的银行卡余额进行
            if(dto.getStatus().equals("第三方金额")){
                //用当前用户的余额加上要充值的余额
                BigDecimal newBalance = Initial.add(dto.getBalance());
                //设置新余额
                userWallet.setBalance(newBalance);
                userWallet.setDeposits(deposits);
                //修改数据库信息
                String userId = userWallet.getUserId();
                accountMapper.updateUserWallet(userId, userWallet.getBalance(),userWallet.getDeposits());
            }else if(dto.getStatus().equals("银行卡余额")){
                //用当前用户的余额加上要充值的余额
                BigDecimal newBalance = Initial.add(dto.getBalance());
                //设置新余额
                userWallet.setBalance(newBalance);
                userWallet.setDeposits(deposits.subtract(dto.getBalance()));
                //修改数据库信息
                String userId = userWallet.getUserId();
                accountMapper.updateUserWallet(userId, userWallet.getBalance(),userWallet.getDeposits());
            }else {
                throw new RuntimeException("状态金额来源有异");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统异常前稍后再试");
        }
    }


    /**
     * 根据用户订单查询商品总金额
     * 进行一个消费及退款
     */
    @Override
    @Transactional
    public String userConsumption(String userid, ShoppingDto dto) {
        try {
            //获取用户余额
            AccountUserWallet accountUserWallet = accountMapper.findBalByUserId(userid);
            BigDecimal Initial = accountUserWallet.getBalance();
            BigDecimal deposits = accountUserWallet.getDeposits();
            Shopping userWallet = BeanHelper.copyProperties(dto, Shopping.class);


            if(dto.getStatus().equals("支付")){
                BigDecimal productPrice = userWallet.getProductPrice();

                BigDecimal subtract = Initial;

                int count = 0;
                if (Initial.compareTo(productPrice) == 1 || Initial.compareTo(productPrice) == 0) {
                    subtract = Initial.subtract(productPrice);
                    count = 1;
                }else if(deposits.compareTo(productPrice) == 1 || deposits.compareTo(productPrice) == 0){
                    deposits = deposits.subtract(productPrice);
                    count = 2;
                }else {
                    throw new RuntimeException("余额不足");
                }

                if(count == 1){
                    accountUserWallet.setBalance(subtract);
                }else if(count == 2){
                    accountUserWallet.setDeposits(deposits);
                }
                //修改数据库信息
                accountMapper.updateUserWallet(accountUserWallet.getUserId(), accountUserWallet.getBalance(),accountUserWallet.getDeposits());
                return "支付成功";
            }else if(dto.getStatus().equals("退款")) {
                BigDecimal productPrice = userWallet.getProductPrice();
                accountUserWallet.setBalance(productPrice.add(accountUserWallet.getBalance()));
                //修改数据库信息
                accountMapper.updateUserWallet(accountUserWallet.getUserId(), accountUserWallet.getBalance(),accountUserWallet.getDeposits());
                return "支付成功";
            }else {
                return "参数异常";
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("系统异常前稍后再试");
        }
    }

    /**
     * 用户申请提现
     */
    @Override
    @Transactional
    public String requestWithdrawal(String userid, BigDecimal deposits) {
        try {
            //获取用户余额
            AccountUserWallet accountUserWallet = accountMapper.findBalByUserId(userid);
            BigDecimal balance = accountUserWallet.getBalance();
            BigDecimal subtract= balance;
            if(balance.compareTo(deposits) == 1 || balance.compareTo(deposits) == 0){
                subtract = balance.subtract(deposits);
            }else {
                throw new RuntimeException("余额不足");
            }
            accountUserWallet.setBalance(subtract);
            accountUserWallet.setDeposits(deposits.add(accountUserWallet.getDeposits()));

            //修改数据库信息
            accountMapper.updateUserWallet(accountUserWallet.getUserId(), accountUserWallet.getBalance(),accountUserWallet.getDeposits());
            return "提现成功";
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("系统异常前稍后再试");
        }
    }

    /**
     * 通过用户id查询用户钱包金额变动明细的接口
     * @param userId
     */
    @Override
    public List<AccountDetails> queryChangeDetailsUserWalletAmount(String userId) {
        return accountDetailsMapper.queryChangeDetailsUserWalletAmount(userId);
    }
}
