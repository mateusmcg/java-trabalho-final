package com.mateusc.trabalhofinal.services;

import java.util.List;

import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.repositories.StockRepository;
import com.mateusc.trabalhofinal.services.interfaces.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAll() {
        return this.stockRepository.findAll();
    }
}