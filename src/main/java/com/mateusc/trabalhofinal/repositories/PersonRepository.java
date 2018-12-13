package com.mateusc.trabalhofinal.repositories;

import java.util.UUID;

import com.mateusc.trabalhofinal.models.Person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, UUID> {

}