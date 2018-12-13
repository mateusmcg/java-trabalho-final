package com.mateusc.trabalhofinal.services;

import java.util.List;

import com.mateusc.trabalhofinal.config.RabbitMQConfig;
import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.repositories.EnterpriseRepository;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Enterprise> getAll() {
        return this.enterpriseRepository.findAll();
    }

    @Override
    public void sendTestMessage(Enterprise message) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.SELL_QUEUE, message);
    }
}