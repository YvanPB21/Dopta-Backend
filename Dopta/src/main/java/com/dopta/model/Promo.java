package com.dopta.model;

import com.sun.istack.NotNull;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="promos")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="corporation_id")
    private Corporation corporation;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Column(name="image_url")
    private String imageUrl;

}
