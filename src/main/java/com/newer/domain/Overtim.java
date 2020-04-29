package com.newer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name ="t_overtim")
@Getter
@Setter
public class Overtim {
    @Id
    @Column(name="overtimID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_overtim_overtimID .nextval from dual")
    private Integer overtimID;
}
