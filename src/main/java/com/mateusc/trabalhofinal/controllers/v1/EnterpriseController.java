package com.mateusc.trabalhofinal.controllers.v1;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;

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
@Api(value = "StockExchange", description = "Empresas registradas na bolsa de valores")
public class EnterpriseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("")
    public ResponseEntity<List<Enterprise>> getAll() {
        List<Enterprise> enterprises = this.enterpriseService.getAll();
        logger.info("Enterprise count: " + enterprises.size());
        return new ResponseEntity<List<Enterprise>>(enterprises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enterprise> findById(@PathVariable UUID id) {
        return new ResponseEntity<Enterprise>(HttpStatus.OK);
    }

    @PostMapping("")
    public HttpStatus create(@RequestBody Enterprise newEnterprise) {
        return HttpStatus.OK;
    }

    @PostMapping("/{enterpriseId}/stock")
    public HttpStatus create(@PathVariable UUID enterpriseId, @RequestBody Stock stock) {
        logger.info("EnterpriseId: " + enterpriseId.toString());
        logger.info("Stock", stock);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus update(@PathVariable UUID id, @RequestBody Enterprise newEnterprise) {
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        return HttpStatus.OK;
    }
}