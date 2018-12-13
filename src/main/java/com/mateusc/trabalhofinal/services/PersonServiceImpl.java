package com.mateusc.trabalhofinal.services;

import java.util.List;

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
}