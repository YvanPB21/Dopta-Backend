package com.tutorial.crud.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Builder
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="rater_id")
    private User userRater;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ratee_id")
    private User userRatee;

    @NotNull
    private BigDecimal rating;
}
