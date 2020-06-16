package com.dopta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor
@AllArgsConstructor @Entity
@DiscriminatorValue("3")
@Table(name="user_profiles")
public class UserProfile extends User {

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="district_id")
    private District district;
    private String profile_pic_url;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="locatable_id")
    private Locatable locatable;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}
