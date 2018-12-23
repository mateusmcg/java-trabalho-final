package com.mateusc.trabalhofinal.controllers.v1;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;
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
@RequestMapping("/api/v1/enterprise")
@Api(value = "EnterpriseController", description = "Empresas registradas na bolsa de valores")
public class EnterpriseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EnterpriseService enterpriseService;
    private final StockService stockService;

    public EnterpriseController(EnterpriseService enterpriseService, StockService stockService) {
        this.enterpriseService = enterpriseService;
        this.stockService = stockService;
    }

    @GetMapping("")
    public ResponseEntity<List<Enterprise>> getAll() {
        List<Enterprise> enterprises = this.enterpriseService.getAll();
        logger.info("Enterprise count: " + enterprises.size());
        return new ResponseEntity<List<Enterprise>>(enterprises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enterprise> findById(@PathVariable UUID id) {
        Enterprise enterprise = this.enterpriseService.findById(id);
        return new ResponseEntity<Enterprise>(enterprise, HttpStatus.OK);
    }

    @PostMapping("")
    public HttpStatus create(@RequestBody Enterprise enterprise) {
        this.enterpriseService.create(enterprise);
        return HttpStatus.OK;
    }

    @PostMapping("/{enterpriseId}/stock")
    public ResponseEntity<String> createStock(@PathVariable UUID enterpriseId, @RequestBody Stock stock) {
        try {
            logger.info("EnterpriseId: " + enterpriseId.toString());
            logger.info("Stock", stock);
            stock.setEnterprise(Enterprise.builder().id(enterpriseId).build());
            this.stockService.create(stock);
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public HttpStatus update(@PathVariable UUID id, @RequestBody Enterprise newEnterprise) {
        this.enterpriseService.update(id, newEnterprise);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        this.enterpriseService.delete(id);
        return HttpStatus.OK;
    }
}