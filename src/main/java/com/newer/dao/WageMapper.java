package com.newer.dao;


import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能描述：薪资管理模块Dao层
 * 作者：龙珊
 * 时间：2020-04-27 20：40
 */

public interface WageMapper extends Mapper<Wage> {
    @Select("select a.*,d.id,d.username,b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds from t_wage a,welfare b,t_tree_user d where a.welfareid=b.welfareid and d.id=a.userid")
    @Results(id="wageMap",value={
        @Result(column = "wageid",property = "wageid"),
        @Result(column = "userid",property = "user.id"),
        @Result(column = "username",property = "user.username"),
        @Result(column = "welfareid",property = "welfare.welfareid"),
        @Result(column = "subsidy",property = "welfare.subsidy"),
        @Result(column = "carallwance",property = "welfare.carallwance"),
        @Result(column = "medicallnsuranc",property = "welfare.medicallnsuranc"),
        @Result(column = "endowmentinsurance",property = "welfare.endowmentinsurance"),
        @Result(column = "unemploymentinsurance",property = "welfare.unemploymentinsurance"),
        @Result(column = "birthinsurance",property = "welfare.birthinsurance"),
        @Result(column = "employmentinjuryinsurance",property = "welfare.employmentinjuryinsurance"),
        @Result(column = "reservedfunds",property = "welfare.reservedfunds")
    })
    List<Wage> pageInfo();

    @Results(
            {
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.id"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "welfareid",property = "welfare.welfareid"),
            @Result(column = "subsidy",property = "welfare.subsidy"),
            @Result(column = "carallwance",property = "welfare.carallwance"),
            @Result(column = "housingsubsidy",property = "welfare.housingsubsidy"),
            @Result(column = "medicallnsuranc",property = "welfare.medicallnsuranc"),
            @Result(column = "endowmentinsurance",property = "welfare.endowmentinsurance"),
            @Result(column = "unemploymentinsurance",property = "welfare.unemploymentinsurance"),
            @Result(column = "birthinsurance",property = "welfare.birthinsurance"),
            @Result(column = "employmentinjuryinsurance",property = "welfare.employmentinjuryinsurance"),
            @Result(column = "reservedfunds",property = "welfare.reservedfunds")
    })
    @SelectProvider(method = "findByDate",type = WageProvide.class )
    List<Wage> pageInfoByDate(@Param("wageDto") WagePageDto wageDto);



}
