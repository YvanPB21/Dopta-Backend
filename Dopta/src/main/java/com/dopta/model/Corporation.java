package com.dopta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor
@AllArgsConstructor @Entity
@DiscriminatorValue("2")
@Table(name="corporations")
public class Corporation extends User{
    private String name;
    private Integer ruc;
}