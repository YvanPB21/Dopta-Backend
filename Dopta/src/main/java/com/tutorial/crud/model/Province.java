package com.tutorial.crud.model;

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
@Table(name="provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="locatable_id")
    private Locatable locatable;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="countrie_id")
    private Country country;
}
