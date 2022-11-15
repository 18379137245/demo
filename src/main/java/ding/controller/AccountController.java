package ding.controller;

import ding.dto.TopUpDto;
import ding.dto.ShoppingDto;
import ding.pojo.AccountDetails;
import ding.pojo.AccountUserWallet;
import ding.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: demo
 * @package: ding.controller
 * @className: AccountController
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/14 19:26
 * @version: 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 根据用户id查询用户余额 （这里为了方便测试数据返回余额和银行卡钱）
     * @param userid
     * @return
     */
    @GetMapping("/{userid}")
    public List<BigDecimal> queryUserWalletBalance(@PathVariable String userid){
        List<BigDecimal> bigDecimals = new ArrayList<>();
        try {
            AccountUserWallet accountUserWallet = accountService.findBalByUserId(userid);
            bigDecimals.add(accountUserWallet.getBalance());
            bigDecimals.add(accountUserWallet.getDeposits());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统异常前稍后再试");
        }
        return bigDecimals;
    }

    /**
     * 根据用户id充值金额
     * 金额来源（第三方接口获取）（支付宝，微信等等）
     * @param dto 模拟通过第三方金额充值
     * @return
     */
    @PostMapping()
    public boolean addBalance(@RequestBody TopUpDto dto){
        accountService.addBalance(dto);
        return true;
    }

    /**
     * 根据用户订单查询商品总金额 我这里只实现了一个商品的（如需要多个商品可以进行一个集合封装每一个商品通过遍历来获取金额 取总数）
     * @param dto 模拟前端传过来订单数据
     * @return
     */
    @PostMapping({"/{userid}"})
    public boolean userConsumption(@PathVariable String userid,@RequestBody ShoppingDto dto){
        accountService.userConsumption(userid,dto);
        return true;
    }

    /**
     * 用户申请提现
     * @param deposits 模拟前端传过来提现金额
     * @return
     */
    @PostMapping({"/{userid}/{deposits}"})
    public boolean requestWithdrawal(@PathVariable String userid,@PathVariable BigDecimal deposits){
        accountService.requestWithdrawal(userid,deposits);
        return true;
    }

    /**
     * 通过用户id查询用户钱包金额变动明细的接口
     * @return
     */
    @GetMapping("query/{userid}")
    public List<AccountDetails> queryChangeDetailsUserWalletAmount(@PathVariable String userid){
        return accountService.queryChangeDetailsUserWalletAmount(userid);
    }
}
