package com.tomaszdabrowski.lab1.ctftask.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode()
public class Task implements Serializable {
    private Long id;
    private String name;

    @ToString.Exclude
    private Category category;
    private String description;
    private int points;

    @ToString.Exclude
    private String flag;
}
