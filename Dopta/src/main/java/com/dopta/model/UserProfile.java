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


    private Integer district_id;

    private String profile_pic_url;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;


    private Integer locatable_id;
}
