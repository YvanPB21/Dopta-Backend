package com.dopta.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Builder
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private AdoptionProcess adoptionProcess;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="commenter_id")
    private Person poster;

    @NotNull
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;
}
