package com.tutorial.crud.resource;

import com.sun.istack.NotNull;

public class SaveUserResource {
    @NotNull
    private String email_address;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
