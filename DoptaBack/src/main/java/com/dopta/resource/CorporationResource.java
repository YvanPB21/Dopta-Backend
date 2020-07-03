package com.dopta.resource;

import com.dopta.model.AuditModel;
import com.dopta.model.District;
import com.dopta.model.Locatable;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class CorporationResource extends AuditModel {
    private Integer id;
    private String corporationName;
    private Integer ruc;
    private String email_address;
    private String username;
    private String password;
    private String profile_pic_url;
    private java.util.Date date_of_registration;
}
