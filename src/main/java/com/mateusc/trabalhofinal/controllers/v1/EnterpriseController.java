package com.mateusc.trabalhofinal.controllers.v1;

import com.mateusc.trabalhofinal.config.EmailSender;

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

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String getEnterprise() {
        logger.info("INFO: teste");
        return "teste";
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(String subject, String body) {
        logger.info("Email enviado com sucesso");
        EmailSender.sendEmail(subject, body);
    }
}