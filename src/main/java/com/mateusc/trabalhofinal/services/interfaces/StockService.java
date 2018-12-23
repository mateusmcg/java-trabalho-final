package com.mateusc.trabalhofinal.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Stock;

public interface StockService {
    List<Stock> getAll();
    Stock findById(UUID id);
    void create(Stock stock);
    void update(UUID id, Stock stock);
    void delete(UUID id);
}