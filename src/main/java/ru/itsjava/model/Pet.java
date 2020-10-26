package ru.itsjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private long idHolder;
    private String typePet;
    private String petName;
}
