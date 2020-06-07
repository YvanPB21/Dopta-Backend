package com.dopta.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;

@Data @NoArgsConstructor @Builder
@AllArgsConstructor @Entity
@Table(name="adoption_processes")
public class AdoptionProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="poster_id")
    private Person poster;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pet_id")
    private Pet pet;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_published;

    @NotNull
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_adopted;

    @NotNull
    @ManyToOne(optional=true, fetch = FetchType.EAGER)
    @JoinColumn(nullable=true)
    private Person adopter;

}
