package com.tutorial.crud.resource;

import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResource extends AuditModel {
    private String email_address;
    private String username;
    private String password;
}
