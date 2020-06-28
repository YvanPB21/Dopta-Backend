package com.tutorial.crud.model;

import com.sun.istack.NotNull;
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
@Table(name="promo_codes")
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="is_used")
    @NotNull
    private Byte isUsed;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="promo_id")
    private com.tutorial.crud.model.Promo promo;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="benefieciary_id")
    private User user;
}
