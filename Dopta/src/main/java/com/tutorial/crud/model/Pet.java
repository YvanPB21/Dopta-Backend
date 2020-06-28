package com.tutorial.crud.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @Builder
@AllArgsConstructor @Entity
@Table(name="pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_birth;

    @NotNull
    @ManyToOne
    @JoinColumn(name="size_id")
    private com.tutorial.crud.model.Size size;

    @NotNull
    @ManyToOne
    @JoinColumn(name="species_id")
    private com.tutorial.crud.model.Species species;

    @NotNull
    @ManyToOne
    @JoinColumn(name="sex_id")
    private Sex sex;

    @NotNull
    private Byte is_adopted;

    private String image_url;
}
