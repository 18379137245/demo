package ding.service.impl;

import ding.dto.TopUpDto;
import ding.dto.ShoppingDto;
import ding.mapper.AccountMapper;
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

    @Override
    public AccountUserWallet selectBalance(String id) {
        return accountMapper.findById(id);
    }

    @Override
    @Transactional
    public void addBalance(TopUpDto dto) {
        //获取用户余额
        AccountUserWallet accountUserWallet = accountMapper.findById(dto.getUserId());
        BigDecimal Initial = accountUserWallet.getBalance();
        BigDecimal deposits = accountUserWallet.getDeposits();
        //复制到AccountUserWallet对象中
        AccountUserWallet userWallet = BeanHelper.copyProperties(dto, AccountUserWallet.class);
        //用当前用户的余额加上要充值的余额
        BigDecimal newBalance = Initial.add(dto.getBalance());
        //设置新余额
        userWallet.setBalance(newBalance);
        //修改数据库信息
        accountMapper.updateAdd(userWallet.getUserId(), userWallet.getBalance(),userWallet.getDeposits());
    }

    @Override
    @Transactional
    public String shopping(String userid, ShoppingDto dto) {
        //获取用户余额
        AccountUserWallet accountUserWallet = accountMapper.findById(userid);
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
            accountMapper.updateAdd(accountUserWallet.getUserId(), accountUserWallet.getBalance(),accountUserWallet.getDeposits());
            return "支付成功";
        }else {
            BigDecimal productPrice = userWallet.getProductPrice();
            accountUserWallet.setBalance(productPrice.add(accountUserWallet.getBalance()));
            //修改数据库信息
            accountMapper.updateAdd(accountUserWallet.getUserId(), accountUserWallet.getBalance(),accountUserWallet.getDeposits());
            return "支付成功";
        }
    }

    @Override
    @Transactional
    public String withdraw(String userid, BigDecimal deposits) {
        //获取用户余额
        AccountUserWallet accountUserWallet = accountMapper.findById(userid);
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
        accountMapper.updateAdd(accountUserWallet.getUserId(), accountUserWallet.getBalance(),accountUserWallet.getDeposits());
        return "提现成功";
    }
}
