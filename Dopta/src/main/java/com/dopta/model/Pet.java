package com.dopta.model;

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
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="size_id")
    private Size size;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="species_id")
    private Species species;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="sex_id")
    private Sex sex;

    @NotNull
    private Byte is_adopted;

    private String image_url;
}
