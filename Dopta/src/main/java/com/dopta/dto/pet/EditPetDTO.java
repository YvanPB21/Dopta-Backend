package com.dopta.dto.pet;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EditPetDTO {
    private String name;
    private Byte is_adopted;
    private String image_url;
}
