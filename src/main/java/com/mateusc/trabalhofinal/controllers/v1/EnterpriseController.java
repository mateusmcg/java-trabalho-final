package com.mateusc.trabalhofinal.controllers.v1;

import java.util.List;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/enterprise")
@Api(value = "StockExchange", description = "Ações relacionadas às empresas")
public class EnterpriseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Enterprise> getAll() {
        List<Enterprise> enterprises = this.enterpriseService.getAll();
        logger.info("Enterprise count: " + enterprises.size());
        return enterprises;
    }
}