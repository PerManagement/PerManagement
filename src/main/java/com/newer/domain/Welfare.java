package com.newer.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="welfare")
public class Welfare {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY,generator="select seq_welfare.nextval from dual")
    private Integer welfareid;
    private BigDecimal subsidy;
    private BigDecimal carallwance;
    private BigDecimal housingsubsidy;
    private BigDecimal medicallnsuranc;
    private BigDecimal endowmentinsurance;
    private BigDecimal unemploymentinsurance;
    private BigDecimal birthinsurance;
    private BigDecimal employmentinjuryinsurance;
    private BigDecimal reservedfunds;
}
