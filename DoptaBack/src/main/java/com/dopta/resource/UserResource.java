package com.dopta.resource;

import com.dopta.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResource extends AuditModel {
    private Integer id;
    private String email_address;
    private String username;
    private String password;
    private String profile_pic_url;
    private Date date_of_registration;
    private String name;
    private String last_names;
    private Integer dni;
    private Date date_of_birth;
}
