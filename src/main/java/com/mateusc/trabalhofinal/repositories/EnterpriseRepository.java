package com.mateusc.trabalhofinal.repositories;

import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends MongoRepository<Enterprise, UUID> {

}