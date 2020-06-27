package com.tutorial.crud.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Data @NoArgsConstructor
@AllArgsConstructor @Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="sender_id")
    private Person sender;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="receiver_id")
    private Person receiver;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @Null
    private String text;

    @Null
    private String image_url;

    @Null
    private String recording_url;

    @NotNull
    private String video_url;
}
