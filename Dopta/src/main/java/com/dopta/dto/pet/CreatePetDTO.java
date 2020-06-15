package com.dopta.dto.pet;

import com.dopta.model.Sex;
import com.dopta.model.Size;
import com.dopta.model.Species;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class CreatePetDTO {

    private String name;
    private Date date_of_birth;
    private Integer sizeId;
    private Integer speciesId;
    private Integer sexId;
    private Byte is_adopted;
    private String image_url;
}
