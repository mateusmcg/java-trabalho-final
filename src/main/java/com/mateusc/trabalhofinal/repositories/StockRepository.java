package com.mateusc.trabalhofinal.repositories;

import java.util.UUID;

import com.mateusc.trabalhofinal.models.Stock;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, UUID> {

}