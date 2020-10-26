package ru.itsjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private final long id;
    private final String name;
    private final int age;
    private Mail email;
    private Pet pet;
}
