package com.tutorial.crud.resource.pet;

import com.sun.istack.NotNull;
import com.tutorial.crud.entity.Sex;
import com.tutorial.crud.entity.Size;
import com.tutorial.crud.entity.Species;
import lombok.Data;

import javax.persistence.*;

@Data
public class PetResource {

    private Integer id;
    private String name;
    private java.util.Date date_of_birth;
    private Byte is_adopted;
    private String image_url;
}
