package ding.common;

import ding.dto.AccountDetailsDto;
import ding.dto.ShoppingDto;
import ding.mapper.AccountDetailsMapper;
import ding.mapper.AccountMapper;
import ding.pojo.AccountUserWallet;
import ding.utils.BeanHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@Aspect //切面类
@Slf4j //记录日志
public class AccountAdvice {

    @Autowired
    private AccountDetailsMapper accountDetailsMapper;
    @Autowired
    private AccountMapper accountMapper;

//    @SneakyThrows
//    @AfterReturning("execution(* ding.mapper.*Mapper.updateAdd(..))")
//    public void afterSave(JoinPoint joinPoint) {
//        System.out.println("-----------------");
//
//        Object[] args = joinPoint.getArgs();
//        Object userId = args[0];//得到实体类对象
//        Object balance = args[1];//得到实体类对象
//        accountDetailsMapper.insert((String) userId, (BigDecimal) balance);
//
//    }

    @SneakyThrows
    @AfterReturning("execution(* ding.service.*Service.shopping(..))")
    public void afterShopping(JoinPoint joinPoint) {
        System.out.println("-----------------");

        Object[] args = joinPoint.getArgs();
        String userId = (String)args[0];
        AccountUserWallet balByUserId = accountMapper.getUserWalletBalance(userId);
        ShoppingDto dto = (ShoppingDto)args[1];
        AccountDetailsDto accountDetailsDto = BeanHelper.copyProperties(dto, AccountDetailsDto.class);
        accountDetailsDto.setUserId(userId);
        accountDetailsDto.setBalance(balByUserId.getBalance());
        //accountDetailsDto.setPayment();   通过用户具体调用什么支付接口获取
        //accountDetailsDto.setProductId(); 通过调用购物车来获取商品id
        //accountDetailsDto.setTrasaction(); 通过调用下单后选项中获取一个唯一的单号
        accountDetailsMapper.AddDetailsChangesUserWalletAmount(accountDetailsDto);

    }



}
