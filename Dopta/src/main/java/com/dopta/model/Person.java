package com.dopta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "person")
    private UserSignIn user;

    @Column(name = "first_name",nullable = false,length = 80)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 80)
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "dni",nullable = false,length = 8)
    private String dni;
}
