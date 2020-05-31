package com.newer.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 出差详细表 周怡珊
 */
@Table(name="t_evectionAccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvectionAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_evectionAccount.nextval from dual")
    public Integer  userid;
    public Integer  evectionaccountid;
    public Integer evectionid;
    public BigDecimal subsidy;
    public Integer evectiondays;
    public BigDecimal total;
    public String evectionaccountstate;
    public Integer approver;
    public User user;
    public Evection evection;
}
