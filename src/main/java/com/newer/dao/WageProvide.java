package com.newer.dao;

import com.newer.dto.WagePageDto;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
@Data
public class WageProvide {
    public String findByDate(final @Param("wageDto") WagePageDto wageDto) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select a.*,b.*,nvl(c.evectionAccount,0) as evectionAccount,nvl(d.overtim,0) as overtim,nvl(e.dur,0) as leavesal,leavereason from  \n" +
                "(select a.*,b.userid as u_id,b.username from \n" +
                "(select a.*,d.username uname,d.basepay,c.deptname,b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,\n" +
                "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds from t_wage a,welfare b,t_department c,\n" +
                "t_tree_user d where a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid) a,t_tree_user b \n" +
                "where a.issuer=b.userid and issuestate='已发放')a left join \n" +
                "(select e.userid as attendance_userid,count(e.clockinstate)*20 belated ,a.approveruserid,a.approvername\n" +
                "from(select a.*,b.userid as approveruserid,b.username approvername from \n" +
                "(select a.*,d.username uname,c.deptname,b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,\n" +
                "b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds from t_wage a,welfare b,t_department c,\n" +
                "t_tree_user d where a.welfareid=b.welfareid and c.deptid=a.deptid and d.userid=a.userid) a,t_tree_user b \n" +
                "where a.approver=b.userid and issuestate='已发放')a,t_attendance e \n" +
                " where a.userid=e.userid and e.clockinstate='迟到' group by e.userid,a.approveruserid,a.approvername) b on \n" +
                "a.userid=b.attendance_userid left join (select sum(total) evectionAccount,userid evection_userid from \n" +
                "t_evectionAccount a,t_evection b where a.evectionid=b.evectionid group by \n" +
                "b.userid)c on a.userid=c.evection_userid left join (select count(remark) overtim,userid overtim_userid " +
                "from t_overtim group by userid) d on a.userid=d.overtim_userid left join \n" +
                "(select userid leaveuserid,count(duration) dur,leavereason from t_leave group by userid，duration，leavereason) e \n" +
                "on e.leaveuserid=a.userid  " +
                " where 1=1");

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
