package com.dopta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="districts")
public class Districts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="departments_id")
    private Departments departmentsid;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="locatable_id")
    private Locatable locatableid;
}
