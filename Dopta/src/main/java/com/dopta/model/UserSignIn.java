package com.dopta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignIn {

    @Id
    private Long idUser;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name="idUser",nullable = false)
    private Person person;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "idRol"))
    private List<Role> roles;


}
