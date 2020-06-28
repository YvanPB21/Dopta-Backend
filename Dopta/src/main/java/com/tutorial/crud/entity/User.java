package com.tutorial.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Data @NoArgsConstructor @Builder
@AllArgsConstructor @Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email_address;

    private String username;

    private String password;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="corporation_id")
    private Corporation corporation;
}
