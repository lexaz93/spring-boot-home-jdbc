package ru.itsjava.dao;


import ru.itsjava.model.Pet;

public interface PetDaoJdbc {
    int count();
    void insert(Pet pet);
    Pet findById(long idHolder);
    void changeName(Pet pet, String newName);
    void deletePet(Pet pet);
}
