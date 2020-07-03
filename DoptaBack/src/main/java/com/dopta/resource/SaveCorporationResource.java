package com.dopta.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
public class SaveCorporationResource {

    @NotNull
    private String email_address;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String corporationName;
    @NotNull
    private Integer ruc;

    private String profile_pic_url;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;

}
