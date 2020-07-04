package com.dopta.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SaveUserResource {
    /**User**/
    @NotNull
    private String emailAdress;

    @NotNull
    private String username;

    @NotNull
    private String password;

    /**user profiles**/

    private String profile_pic_url;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;

    /**person**/
    @NotNull
    private String names;

    @NotNull
    private String last_names;

    @NotNull
    private Integer dni;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_birth;

    private Set<String> roles = new HashSet<>();
}
