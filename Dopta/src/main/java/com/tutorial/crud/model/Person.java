package com.tutorial.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="rating_id")
    private Rating rating;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gender_id")
    private Gender gender;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
