package com.newer.dao;

import com.newer.dto.WagePageDto;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
@Data
public class WageProvide {
    public String findByDate(final @Param("wageDto") WagePageDto wageDto) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from (select a.*,b.userid as u_id,b.username from " +
                "(select a.*,d.username uname,d.basepay,c.deptname,b.subsidy,b.carallwance," +
                "b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance," +
                "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance," +
                "b.reservedfunds from t_wage a,welfare b,t_department c,t_tree_user d " +
                "where a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid) " +
                "a,t_tree_user b where a.issuer=b.userid and  issuestate='已发放')a " +
                "left join (select e.userid,count(e.remark)*20 belated from(select a.*," +
                "b.userid as u_id,b.username from (select a.*,d.username uname,c.deptname," +
                "b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc," +
                "b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance," +
                "b.employmentinjuryinsurance,b.reservedfunds from t_wage a,welfare b," +
                "t_department c,t_tree_user d where a.welfareid=b.welfareid and " +
                "c.deptid=a.deptid and d.userid=a.userid) a,t_tree_user b where " +
                "a.issuer=b.userid  and  issuestate='已发放')a,t_attendance e where " +
                "a.userid=e.userid and e.remark='迟到了' group by e.userid) b on " +
                "a.userid=b.userid left join  (select sum(total) evectionAccount,userid " +
                "from t_evectionAccount a,t_evection b where a.evectionid=b.evectionid " +
                "group by b.userid)c  on a.userid=c.userid left join (select count(remark) " +
                "overtim,userid from t_overtim group by userid) d on a.userid=d.userid where 1=1");

//                SELECT(" * ");
//                FROM(" a,t_tree_user b  ");
//                SELECT(" (a.*,d.username uname,c.deptname,b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds ");
//                FROM(" t_wage a,welfare b,t_department c,t_tree_user d ");
//                WHERE(" a.welfareid=b.welfareid ");
//                WHERE(" d.userid=a.userid ");
//                WHERE(" c.deptid=a.deptid ");
//                WHERE(" issuestate='已发放')a ");
//                WHERE(" a.issuer=b.userid ");
        if (wageDto != null) {
            if (wageDto.getBeginDate() != null && !wageDto.getBeginDate().equals("")) {
                wageDto.setBeginDate(wageDto.getBeginDate());
                stringBuffer.append(" and  wagedate >= to_date(#{wageDto.beginDate},'yyyy-MM-dd') ");
            }
            if (wageDto.getEndDate() != null && !wageDto.getEndDate().equals("")) {
                wageDto.setEndDate(wageDto.getEndDate());
                stringBuffer.append(" and  wagedate <= to_date(#{wageDto.endDate},'yyyy-MM-dd') ");
            }
        }

        return stringBuffer.toString();
    }
}
