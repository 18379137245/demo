package ding.mapper;

import ding.dto.AccountDetailsDto;
import ding.pojo.AccountDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: demo
 * @package: ding.mapper
 * @className: accountDetailsMapper
 * @author: DingXingYun
 * @description: TODO
 * @date: 2022/11/14 22:52
 * @version: 1.0
 */
@Mapper
public interface AccountDetailsMapper {
//    @Insert("INSERT INTO account_details(details_id,user_id,balance) VALUE(null,#{userId},#{balance})")
//    void insert(String userId, BigDecimal balance);
    /**
     * 通过aop后置增强进行一个明细添加
     * @param dto
     */
    void AddDetailsChangesUserWalletAmount(@Param("dto") AccountDetailsDto dto);

    /**
     * 通过用户id查询用户钱包金额变动明细的接口
     * @param userId
     */
    @Select("SELECT * FROM `account_details` where user_id = #{userId}")
    List<AccountDetails> queryChangeDetailsUserWalletAmount(String userId);
}
