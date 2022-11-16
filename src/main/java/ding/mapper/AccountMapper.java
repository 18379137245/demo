package ding.mapper;

import ding.pojo.AccountUserWallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;


@Mapper
public interface AccountMapper {


    /**
     * 通过id查询一个用户钱包信息
     * @param id
     */
    @Select("select * from account_user_wallet where user_id=#{id}")
    AccountUserWallet getUserWalletBalance(String id);

    /**
     * 通过id修改一个用户钱包信息
     * @param userId
     */
    @Update("UPDATE account_user_wallet SET balance=#{balance},deposits=#{deposits} WHERE user_id=#{userId}")
    void updateUserWallet(String userId, BigDecimal balance,BigDecimal deposits);
}
