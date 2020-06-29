package com.dopta.resource;

import com.dopta.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource extends AuditModel {
    private Integer id;
    private String email_address;
    private String username;
    private String password;
}
