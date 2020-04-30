package com.newer.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 出差详细表 周怡珊
 */
@Table(name="t_evectionAccount")
@Getter
@Setter
public class EvectionAccount {
    @Id
    @Column(name="evectionAccountID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_evectionAccount.nextval from dual")
    public Integer  evectionaccountid;
    @Column(name="evectionID")
    public Integer evectionid;
    public Integer  subsidy;
    public Integer evectiondays;
    public Integer total;
    @Column(name=" evectionAccountState")
    public Integer evectionAccountState;
}
