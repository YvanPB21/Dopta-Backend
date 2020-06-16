package com.dopta.dto.user;

import com.dopta.model.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class UserDTO {
    //User
    private Integer id;
    private String email_address;
    private String username;
    private String password;
    //UserProfile
    private String district; //District
    private String profile_pic_url;
    private Date date_of_registration;
    private String locatable; //Locatable
    //Person
    private String names;
    private String last_names;
    private Integer dni;
    private Date date_of_birth;
    private Float rating;
    private String gender; //Gender
    //Corporation
    private String corporation_name;
    private Integer ruc;
}
