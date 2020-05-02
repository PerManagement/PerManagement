package com.newer.dao;

import com.newer.dto.WagePageDto;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
@Data
public class WageProvide {
    public String findByDate(final @Param("wageDto") WagePageDto wageDto){
        return new SQL(){
            {
                SELECT(" a.*,d.id,d.username,b.subsidy,b.carallwance,b.housingsubsidy,b.medicallnsuranc,b.endowmentinsurance,b.unemploymentinsurance,b.birthinsurance,b.employmentinjuryinsurance,b.reservedfunds ");
                FROM(" t_wage a,welfare b,t_tree_user d ");
                WHERE(" a.welfareid=b.welfareid ");
                WHERE(" d.id=a.userid ");
                if(wageDto!=null){
                    if(wageDto.getBeginDate()!=null && !wageDto.getBeginDate().equals("")){
                        wageDto.setBeginDate(wageDto.getBeginDate());
                        WHERE(" wagedate >= to_date(#{wageDto.beginDate},'yyyy-MM-dd') ");
                    }
                    if(wageDto.getEndDate()!=null && !wageDto.getEndDate().equals("")){
                        wageDto.setEndDate(wageDto.getEndDate());
                        WHERE(" wagedate <= to_date(#{wageDto.endDate},'yyyy-MM-dd') ");
                    }
                }
            }
        }.toString();

    }
}
