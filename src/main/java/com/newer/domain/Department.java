package com.newer.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="t_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private Integer deptid;
    private String deptname;

}
