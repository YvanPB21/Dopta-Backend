package com.tutorial.crud.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@AllArgsConstructor @Entity
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Person poster;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private AdoptionProcess adoptionProcess;

}
