package com.mateusc.trabalhofinal.controllers.v1;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.services.interfaces.StockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/stock")
@Api(value = "StockExchange", description = "Ações relacionadas às empresas")
public class StockController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("")
    public ResponseEntity<List<Stock>> getAll() {
        List<Stock> stocks = this.stockService.getAll();
        logger.info("Stocks count: " + stocks.size());
        return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> findById(@PathVariable UUID id) {
        logger.info("Stock Id to GET: " + id.toString());

        return new ResponseEntity<Stock>(HttpStatus.OK);
    }

    @PostMapping("")
    public HttpStatus create(@RequestBody Stock newStock) {
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus update(@PathVariable UUID id, @RequestBody Stock updatedStock) {
        logger.info("Stock Id to UPDATE: " + id.toString());
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        logger.info("Stock Id to DELETE: " + id.toString());
        return HttpStatus.OK;
    }
}