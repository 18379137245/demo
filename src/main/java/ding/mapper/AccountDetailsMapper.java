package ding.mapper;

import ding.dto.AccountDetailsDto;
import ding.dto.ShoppingDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.math.BigDecimal;

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
    @Insert("INSERT INTO account_details(details_id,user_id,balance) VALUE(null,#{userId},#{balance})")
    void insert(String userId, BigDecimal balance);


    void insert2(@Param("dto") AccountDetailsDto dto);
}
