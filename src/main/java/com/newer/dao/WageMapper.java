package com.newer.dao;


import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能描述：薪资管理模块Dao层
 * 作者：龙珊
 * 时间：2020-04-27 20：40
 */

public interface WageMapper extends Mapper<Wage> {
    @Select("select a.*,d.username," +
            "b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc," +
            "b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance," +
            "b.employmentinjuryinsurance,b.reservedfunds " +
            "from t_wage a,welfare b,t_tree_user d where a.welfareid=b.welfareid " +
            "and d.userid=a.userid and issuestate='未发放'")
    @Results(id="wageMap",value={
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
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
    List<Wage> pageInfo();


    @Select("select a.*,d.username," +
            "b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc," +
            "b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance," +
            "b.employmentinjuryinsurance,b.reservedfunds " +
            "from t_wage a,welfare b,t_tree_user d where a.welfareid=b.welfareid " +
            "and d.userid=a.userid and wageState='未审核'")
    @Results(id="wageMap2",value={
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
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
    List<Wage> pageInfo2();

    @Select("select a.*,d.username," +
            "b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc," +
            "b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance," +
            "b.employmentinjuryinsurance,b.reservedfunds " +
            "from t_wage a,welfare b,t_tree_user d where a.welfareid=b.welfareid " +
            "and d.userid=a.userid and d.userid=#{userId}")
    @Results(id="wageMap3",value={
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
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
    List<Wage> findByUserId(WagePageDto wageDto);

    @Results(
            {
            //@Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
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

//    @Results({
//            @Result(column = "userid",property = "user",many = @Many(select = "com.newer.dao.UserDaoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
//            @Result(column = "welfareid",property = "welfare",many = @Many(select = "com.newer.dao.WelfareMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
//
//            })
//    @SelectProvider(method = "findByDate",type = WageProvide.class )
//    List<Wage> pageInfoByDate(@Param("wageDto") WagePageDto wageDto);

    @Update("update t_wage set issuestate='已发放',issuer=#{arg0} where wageid=#{arg1}")
    int updateWage(Integer  arg0,Integer arg1);

    @Update("update t_wage set wageState = '已审核' where wageid=#{wageid}")
    int updateState(Integer wageId);

    @Update("update t_wage set wageState = '驳回' where wageid=#{wageid}")
    int updateState2(Integer wageId);

    @Update("update t_wage set wageState = '未审核' where wageid=#{wageid}")
    int updateState3(Integer wageId);

}
