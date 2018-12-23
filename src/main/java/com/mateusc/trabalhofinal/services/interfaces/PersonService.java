package com.mateusc.trabalhofinal.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Person;

public interface PersonService {
    List<Person> getAll();
    Person findById(UUID id);
    void create(Person person);
    void update(UUID id, Person person);
    void delete(UUID id);
    void buy(UUID personId, UUID stockId);
    void sell(UUID personId, UUID stockId);
}