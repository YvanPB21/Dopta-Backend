package com.tutorial.crud.resource.pet;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

import lombok.Data;

@Data
public class PetResource {

    private Integer id;
    private String name;
    private java.util.Date date_of_birth;
    private Byte is_adopted;
    private String image_url;
}
