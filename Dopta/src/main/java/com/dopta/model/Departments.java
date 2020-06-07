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
@Table(name="departments")
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="locatable_id")
    private Locatable locatableid;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="province_id")
    private Province provinceid;
}
