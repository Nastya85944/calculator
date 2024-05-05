package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "calculator")
public class Calculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "parameter1", nullable= false)
    private long parameter1;

    @NotNull
    @Column(name = "parameter2", nullable= false)
    private long parameter2;

    @Column(name = "result", nullable= false)
    private long result;

    @NotNull
    @Column(name = "operation", nullable= false)
    private String operation;
}
