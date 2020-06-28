package com.tutorial.crud.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Data @NoArgsConstructor @Builder
@AllArgsConstructor @Entity
@Table(name="users")
public class User {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String email_address;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Null
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @Null
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    @Null
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="corporation_id")
    private Corporation corporation;
}
