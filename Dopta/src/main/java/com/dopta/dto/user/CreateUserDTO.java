package com.dopta.dto.user;

import com.dopta.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateUserDTO {
    //User
    private String email_address;
    private String username;
    private String password;
    //UserProfile
    private Integer districtId; //District
    private String profile_pic_url;
    private Date date_of_registration;
    private Integer locatableId; //Locatable
    //Person
    private String names;
    private String last_names;
    private Integer dni;
    private Date date_of_birth;
    private Float rating;
    private Integer genderId; //Gender
    //Corporation
    private String corporation_name;
    private Integer ruc;
}
