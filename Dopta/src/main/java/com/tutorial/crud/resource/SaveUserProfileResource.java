package com.tutorial.crud.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter @Setter
public class SaveUserProfileResource {
    private String profile_pic_url;
}