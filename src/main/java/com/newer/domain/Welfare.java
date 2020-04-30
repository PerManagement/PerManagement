package com.newer.domain;

import lombok.*;

import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="welfare")
public class Welfare {
    private Integer welfareid;
    private BigDecimal subsidy;
    private BigDecimal carAllwance;
    private BigDecimal netpay;
    private BigDecimal medicallnsuranc;
    private BigDecimal socialSecurity;
}
