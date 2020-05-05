package com.newer.domain;

import lombok.*;

import javax.persistence.*;

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
    public Integer  evectionaccountid;
    public Integer evectionid;
    public Integer  subsidy;
    public Integer evectiondays;
    public Integer total;
    public Integer evectionAccountState;
}
