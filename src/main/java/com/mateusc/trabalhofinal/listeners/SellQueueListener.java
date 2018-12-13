package com.mateusc.trabalhofinal.listeners;

import com.mateusc.trabalhofinal.config.RabbitMQConfig;
import com.mateusc.trabalhofinal.models.Enterprise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class SellQueueListener {
	static final Logger logger = LoggerFactory.getLogger(SellQueueListener.class);

    @RabbitListener(queues = RabbitMQConfig.SELL_QUEUE)
    public void processMessage(Enterprise message) {
        //ToDo: Alterar a fila/receptor para receber/enviar um objeto que represente uma venda a ser processada
        logger.info("Message Received - SELL");
        logger.info("EnterpriseNam:" + message.getName());
    }
}