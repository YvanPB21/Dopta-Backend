package com.dopta.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corporations")
public class Corporation extends User {

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

    /**Corporation**/
    @NotNull
    private String corporationName;

    @NotNull
    private Integer ruc;

    /**User profile**/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    private String profile_pic_url;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locatable_id")
    private Locatable locatable;

}