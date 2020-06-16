package com.dopta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor
@AllArgsConstructor @Entity
@DiscriminatorValue("2")
@Table(name="corporations")
public class Corporation extends User{
    private String name;
    private Integer ruc;
    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}