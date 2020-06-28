package com.tutorial.crud.resource;

import com.sun.istack.NotNull;
import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResource extends AuditModel {
    private Integer id;
    private String email_address;
    private String username;
    private String password;
}
