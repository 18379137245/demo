package ding.mapper;

import ding.pojo.AccountUserWallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: demo
 * @package: ding.mapper
 * @className: AccountUserWallet
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/14 20:42
 * @version: 1.0
 */
@Mapper
public interface AccountMapper {


    /**
     * 通过id查询一个用户
     * @param id
     */
    @Select("select * from account_user_wallet where user_id=#{id}")
    AccountUserWallet findById(String id);



    @Update("UPDATE account_user_wallet SET balance=#{balance},deposits=#{deposits}WHERE user_id=#{userId}")
    void updateAdd(String userId, BigDecimal balance,BigDecimal deposits);
}
