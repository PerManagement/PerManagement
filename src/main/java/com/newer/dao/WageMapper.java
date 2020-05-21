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
    @Select("select a.*,b.*,nvl(c.evectionAccount,0) as evectionAccount,nvl(d.overtim,0) as countovertim from  \n" +
            " (select a.*,d.username uname,d.basepay,c.deptname,b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,\n" +
            "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds from t_wage a,welfare b,t_department c,t_tree_user d \n" +
            "where a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid   and issuestate='未发放')a left join \n" +
            "(select e.userid as attendance_userid,count(e.remark)*20 belated \n" +
            "            from(select a.*,d.username uname,c.deptname,b.subsidy,b.carallwance,\n" +
            "            b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,b.unemploymentinsurance,\n" +
            "            b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds from t_wage a,\n" +
            "           welfare b,t_department c,t_tree_user d where a.welfareid=b.welfareid and \n" +
            "            c.deptid=a.deptid and d.userid=a.userid and issuestate='未发放')a,t_attendance e \n" +
            "            where a.userid=e.userid and e.remark='迟到了' group by e.userid) b on \n" +
            "            a.userid=b.attendance_userid left join (select sum(total) evectionAccount,userid evection_userid from \n" +
            "            t_evectionAccount a,t_evection b where a.evectionid=b.evectionid group by \n" +
            "            b.userid)c on a.userid=c.evection_userid left join (select count(remark) overtim,userid overtim_userid\n" +
            "            from t_overtim group by userid) d on a.userid=d.overtim_userid")
    @Results(id="wageMap",value={
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
            @Result(column = "uname",property = "user.username"),
            @Result(column = "issuer",property = "issuer"),
            @Result(column = "deptname",property = "dept.deptname"),
            @Result(column = "basepay",property = "user.basepay"),
            @Result(column = "welfareid",property = "welfare.welfareid"),
            @Result(column = "subsidy",property = "welfare.subsidy"),
            @Result(column = "carallwance",property = "welfare.carallwance"),
            @Result(column = "housingsubsidy",property = "welfare.housingsubsidy"),
            @Result(column = "medicallnsuranc",property = "welfare.medicallnsuranc"),
            @Result(column = "endowmentinsurance",property = "welfare.endowmentinsurance"),
            @Result(column = "unemploymentinsurance",property = "welfare.unemploymentinsurance"),
            @Result(column = "birthinsurance",property = "welfare.birthinsurance"),
            @Result(column = "employmentinjuryinsurance",property = "welfare.employmentinjuryinsurance"),
            @Result(column = "reservedfunds",property = "welfare.reservedfunds"),
            @Result(column = "countovertim",property = "overtim.countovertim"),
            @Result(column = "overtim_userid",property = "overtim.userid"),
            @Result(column = "remark",property = "evection.remark"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "evection_userid",property = "evection.userid"),
            @Result(column = "evectionid",property = "evectionaccount.evectionid"),
            @Result(column = "evectionaccount",property = "evectionaccount.total"),
            @Result(column = "belated",property = "attendance.remark"),
            @Result(column = "attendance_userid",property = "attendance.userid"),
    })
    List<Wage> pageInfo();


    @Select("select * from (select a.*,d.username uname,d.basepay,c.deptname,b.subsidy," +
            "b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance," +
            "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance," +
            "b.reservedfunds from t_wage a,welfare b,t_department c,t_tree_user d where " +
            "a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid " +
            "and  wageState='未审核')a left join (select e.userid,count(e.remark)*20 " +
            "belated from(select a.*,d.username uname,c.deptname,b.subsidy,b.carallwance," +
            "b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,b.unemploymentinsurance," +
            "b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds from t_wage a," +
            "welfare b,t_department c,t_tree_user d where a.welfareid=b.welfareid and " +
            "c.deptid=a.deptid and d.userid=a.userid and  wageState='未审核')a,t_attendance e " +
            "where a.userid=e.userid and e.remark='迟到了' group by e.userid) b on " +
            "a.userid=b.userid left join (select sum(total) evectionaccount,userid from " +
            "t_evectionAccount a,t_evection b where a.evectionid=b.evectionid group by b.userid)c " +
            "on a.userid=c.userid left join (select count(remark) countOvertim,userid from " +
            "t_overtim group by userid) d on a.userid=d.userid")
    @Results(id="wageMap2",value={
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
            @Result(column = "uname",property = "user.username"),
            @Result(column = "issuer",property = "issuer"),
            @Result(column = "deptname",property = "dept.deptname"),
            @Result(column = "basepay",property = "user.basepay"),
            @Result(column = "welfareid",property = "welfare.welfareid"),
            @Result(column = "subsidy",property = "welfare.subsidy"),
            @Result(column = "carallwance",property = "welfare.carallwance"),
            @Result(column = "housingsubsidy",property = "welfare.housingsubsidy"),
            @Result(column = "medicallnsuranc",property = "welfare.medicallnsuranc"),
            @Result(column = "endowmentinsurance",property = "welfare.endowmentinsurance"),
            @Result(column = "unemploymentinsurance",property = "welfare.unemploymentinsurance"),
            @Result(column = "birthinsurance",property = "welfare.birthinsurance"),
            @Result(column = "employmentinjuryinsurance",property = "welfare.employmentinjuryinsurance"),
            @Result(column = "reservedfunds",property = "welfare.reservedfunds"),
            @Result(column = "countOvertim",property = "overtim.countovertim"),
            @Result(column = "userid",property = "overtim.userid"),
            @Result(column = "remark",property = "evection.remark"),
            @Result(column = "userid",property = "evection.userid"),
            @Result(column = "evectionid",property = "evectionAccount.evectionid"),
            @Result(column = "evectionaccount",property = "evectionaccount.total"),
            @Result(column = "belated",property = "attendance.remark"),
            @Result(column = "userid",property = "attendance.userid"),
    })
    List<Wage> pageInfo2();

    @Select("select * from (select a.*,b.userid as u_id,b.username from " +
            "(select a.*,d.username uname,d.basepay,c.deptname,b.subsidy,b.carallwance," +
            "b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance," +
            "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance," +
            "b.reservedfunds from t_wage a,welfare b,t_department c,t_tree_user d " +
            "where a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid) a," +
            "t_tree_user b where a.issuer=b.userid)a left join (select e.userid,count" +
            "(e.remark)*20 belated from(select a.*,b.userid as u_id,b.username from " +
            "(select a.*,d.username uname,c.deptname,b.subsidy,b.carallwance," +
            "b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance," +
            "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance," +
            "b.reservedfunds from t_wage a,welfare b,t_department c,t_tree_user d " +
            "where a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid) a," +
            "t_tree_user b where a.issuer=b.userid )a,t_attendance e where " +
            "a.userid=e.userid and e.remark='迟到了' group by e.userid) b on " +
            "a.userid=b.userid left join (select sum(total) evectionAccount,userid " +
            "from t_evectionAccount a,t_evection b where a.evectionid=b.evectionid " +
            "group by b.userid)c on a.userid=c.userid left join (select count(remark) " +
            "overtim,userid from t_overtim group by userid) d on a.userid=d.userid  " +
            "where a.userid=#{userId}")
    @Results(id="wageMap3",value={
            @Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "uname",property = "userissuer.username"),
            @Result(column = "deptname",property = "dept.deptname"),
            @Result(column = "welfareid",property = "welfare.welfareid"),
            @Result(column = "subsidy",property = "welfare.subsidy"),
            @Result(column = "carallwance",property = "welfare.carallwance"),
            @Result(column = "housingsubsidy",property = "welfare.housingsubsidy"),
            @Result(column = "medicallnsuranc",property = "welfare.medicallnsuranc"),
            @Result(column = "endowmentinsurance",property = "welfare.endowmentinsurance"),
            @Result(column = "unemploymentinsurance",property = "welfare.unemploymentinsurance"),
            @Result(column = "birthinsurance",property = "welfare.birthinsurance"),
            @Result(column = "employmentinjuryinsurance",property = "welfare.employmentinjuryinsurance"),
            @Result(column = "reservedfunds",property = "welfare.reservedfunds"),
            @Result(column = "countOvertim",property = "overtim.countovertim"),
            @Result(column = "userid",property = "overtim.userid"),
            @Result(column = "remark",property = "evection.remark"),
            @Result(column = "userid",property = "evection.userid"),
            @Result(column = "evectionid",property = "evectionAccount.evectionid"),
            @Result(column = "evectionaccount",property = "evectionaccount.total"),
            @Result(column = "belated",property = "attendance.remark"),
            @Result(column = "userid",property = "attendance.userid"),
    })
    List<Wage> findByUserId(WagePageDto wageDto);

    @Results(
            {
            //@Result(column = "wageid",property = "wageid"),
            @Result(column = "userid",property = "user.userid"),
            @Result(column = "uname",property = "user.username"),
            @Result(column = "basepay",property = "user.basepay"),
            @Result(column = "username",property = "userissuer.username"),
            @Result(column = "deptname",property = "dept.deptname"),
            @Result(column = "welfareid",property = "welfare.welfareid"),
            @Result(column = "subsidy",property = "welfare.subsidy"),
            @Result(column = "carallwance",property = "welfare.carallwance"),
            @Result(column = "housingsubsidy",property = "welfare.housingsubsidy"),
            @Result(column = "medicallnsuranc",property = "welfare.medicallnsuranc"),
            @Result(column = "endowmentinsurance",property = "welfare.endowmentinsurance"),
            @Result(column = "unemploymentinsurance",property = "welfare.unemploymentinsurance"),
            @Result(column = "birthinsurance",property = "welfare.birthinsurance"),
            @Result(column = "employmentinjuryinsurance",property = "welfare.employmentinjuryinsurance"),
            @Result(column = "reservedfunds",property = "welfare.reservedfunds"),
            @Result(column = "username",property = "userissuer.username"),
            @Result(column = "countOvertim",property = "overtim.countovertim"),
            @Result(column = "userid",property = "overtim.userid"),
            @Result(column = "remark",property = "evection.remark"),
            @Result(column = "userid",property = "evection.userid"),
            @Result(column = "evectionid",property = "evectionAccount.evectionid"),
            @Result(column = "evectionaccount",property = "evectionaccount.total"),
            @Result(column = "belated",property = "attendance.remark"),
            @Result(column = "userid",property = "attendance.userid"),
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

//    @Update("update t_wage set issuestate='已发放',issuer=#{arg0} where wageid=#{arg1}")
//    int updateWage(Integer  arg0,Integer arg1);

    @Update("update t_wage set wageState = '已审核' where wageid=#{wageid}")
    int updateState(Integer wageId);

//    @Update("update t_wage set wageState = '驳回', where wageid=#{wageid}")
//    int updateState2(Integer wageId);

//    @Update("update t_wage set wageState = '未审核' where wageid=#{wageid}")
//    int updateState3(Integer wageId);

}
