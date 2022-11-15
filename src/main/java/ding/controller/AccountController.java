package ding.controller;

import ding.dto.TopUpDto;
import ding.dto.ShoppingDto;
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
     * 根据用户id查询用户余额
     * @param userid
     * @return
     */
    @GetMapping("/{userid}")
    public List<BigDecimal> select(@PathVariable String userid){
        List<BigDecimal> bigDecimals = new ArrayList<>();
        AccountUserWallet accountUserWallet = accountService.selectBalance(userid);
        bigDecimals.add(accountUserWallet.getBalance());
        bigDecimals.add(accountUserWallet.getDeposits());
        return bigDecimals;
    }

    /**
     * 根据用户id添加金额
     * 金额来源（第三方接口获取）（支付宝，微信等等）
     * @param dto 模拟通过第三方金额来的
     * @return
     */
    @PostMapping()
    public String addBalance(@RequestBody TopUpDto dto){
        accountService.addBalance(dto);
        return "操作成功";
    }

    /**
     * 根据用户订单查询商品总金额 我这里只实现了一个商品的，
     * 如需要多个商品可以进行一个集合封装每一个商品通过遍历来获取金额 取总数
     * @param dto 模拟前端传过来订单数据
     * @return
     */
    @PostMapping({"/{userid}"})
    public String shopping(@PathVariable String userid,@RequestBody ShoppingDto dto){
        accountService.shopping(userid,dto);
        return "操作成功";
    }

    /**
     * 根据用户id
     * @param deposits 模拟前端传过来订单数据
     * @return
     */
    @PostMapping({"/{userid}/{deposits}"})
    public String withdraw(@PathVariable String userid,@PathVariable BigDecimal deposits){
        accountService.withdraw(userid,deposits);
        return "操作成功";
    }
}
