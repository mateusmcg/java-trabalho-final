package com.mateusc.trabalhofinal.controller.v1;

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

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String getEnterprise() {
        return "teste";
    }
}