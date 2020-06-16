package com.dopta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor
@AllArgsConstructor @Entity
@DiscriminatorValue("1")
@Table(name="persons")
public class Person extends User{

    private String names;

    private String last_names;

    private Integer dni;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_birth;

    private Float rating;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gender_id")
    private Gender gender;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}
