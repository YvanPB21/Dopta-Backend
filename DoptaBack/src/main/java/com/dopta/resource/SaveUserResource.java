package com.dopta.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserResource {
    @NotNull
    private String email_address;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
