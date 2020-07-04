package com.dopta.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**User**/
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

    /**person**/
    private String names;

    private String last_names;

    private Integer dni;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_birth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private Gender gender;


}
