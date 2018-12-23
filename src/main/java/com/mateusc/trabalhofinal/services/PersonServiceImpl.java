package com.mateusc.trabalhofinal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Person;
import com.mateusc.trabalhofinal.repositories.PersonRepository;
import com.mateusc.trabalhofinal.services.interfaces.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person findById(UUID id) {
        Optional<Person> person = this.personRepository.findById(id);

        if (person.isEmpty())
            return null;

        return person.get();
    }

    @Override
    public void create(Person person) {
        this.personRepository.insert(person);
    }

    @Override
    public void update(UUID id, Person person) {
        person.setId(id);
        this.personRepository.save(person);
    }

    @Override
    public void delete(UUID id) {
        this.personRepository.deleteById(id);
    }
}