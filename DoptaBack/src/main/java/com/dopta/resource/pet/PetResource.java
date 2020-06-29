package com.dopta.resource.pet;

import lombok.Data;

@Data
public class PetResource {

    private Integer id;
    private String name;
    private java.util.Date date_of_birth;
    private Byte is_adopted;
    private String image_url;
}
