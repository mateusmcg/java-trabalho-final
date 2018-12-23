package com.mateusc.trabalhofinal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Stock findById(UUID id) {
        Optional<Stock> stock = this.stockRepository.findById(id);

        if (stock.isEmpty())
            return null;

        return stock.get();
    }

    @Override
    public void create(Stock stock) {
        this.stockRepository.insert(stock);
    }

    @Override
    public void update(UUID id, Stock stock) {
        stock.setId(id);
        this.stockRepository.save(stock);
    }

    @Override
    public void delete(UUID id) {
        this.stockRepository.deleteById(id);
    }
}