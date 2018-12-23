package com.mateusc.trabalhofinal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.repositories.StockRepository;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;
import com.mateusc.trabalhofinal.services.interfaces.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Stock> getAll() {
        return this.stockRepository.findAll();
    }

    @Override
    public List<Stock> getStockForSale() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isForSale").is(true));
        List<Stock> stocksForSale = this.mongoTemplate.find(query, Stock.class);
        return stocksForSale;
    }

    @Override
    public Stock findById(UUID id) {
        Optional<Stock> stock = this.stockRepository.findById(id);

        if (!stock.isPresent())
            return null;

        return stock.get();
    }

    @Override
    public void create(Stock stock) throws Exception {
        Enterprise enterprise = this.enterpriseService.findById(stock.getEnterprise().getId());
        if((enterprise.getStocks().size() + 1) > enterprise.getMaxStocksToSell()){
            throw new Exception("Esta empresa atingiu o limite de ações no mercado, não é possível vincular novas ações a ela.");
        }

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