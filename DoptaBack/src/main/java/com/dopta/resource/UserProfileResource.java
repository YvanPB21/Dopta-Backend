package com.dopta.resource;


import com.dopta.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResource extends AuditModel {
    private String profile_pic_url;
    private java.util.Date date_of_registration;
}
