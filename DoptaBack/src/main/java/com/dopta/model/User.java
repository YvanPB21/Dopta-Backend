package com.dopta.model;

import com.dopta.security.entity.Rol;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**User**/
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="email_address")
    private String emailAddress;

    @NotNull
    private String username;

    @NotNull
    private String password;

    /**user profiles**/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    private String profile_pic_url;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locatable_id")
    private Locatable locatable;

    public User(@NotNull String emailAddress, @NotNull String username, @NotNull String password,
                @NotNull String profile_pic_url, @NotNull Date date_of_registration, @NotNull String names,
                @NotNull String last_names, @NotNull Integer dni, @NotNull Date date_of_birth) {
        this.emailAddress=emailAddress;
        this.username=username;
        this.password=password;
        this.profile_pic_url=profile_pic_url;
        this.date_of_registration=date_of_registration;
        this.names=names;
        this.last_names=last_names;
        this.dni=dni;
        this.date_of_birth=date_of_birth;
    }

    /**person**/
    @NotNull
    private String names;

    @NotNull
    private String last_names;

    @NotNull
    private Integer dni;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_birth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
}
