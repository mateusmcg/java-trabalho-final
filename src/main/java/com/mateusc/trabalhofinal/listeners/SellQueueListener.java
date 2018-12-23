package com.mateusc.trabalhofinal.listeners;

import java.time.Instant;

import com.mateusc.trabalhofinal.config.EmailSender;
import com.mateusc.trabalhofinal.config.RabbitMQConfig;
import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.models.Message;
import com.mateusc.trabalhofinal.models.Person;
import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.services.interfaces.PersonService;
import com.mateusc.trabalhofinal.services.interfaces.StockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class SellQueueListener {
    static final Logger logger = LoggerFactory.getLogger(SellQueueListener.class);
    
    private final PersonService personService;
    private final StockService stockService;

    public SellQueueListener(PersonService personService, StockService stockService) {
        this.personService = personService;
        this.stockService = stockService;
    }

    @RabbitListener(queues = RabbitMQConfig.SELL_QUEUE)
    public void processMessage(Message message) {
        logger.info("Message Received - SELL");
        logger.info("PersonId:" + message.getPersonId());
        logger.info("StockId:" + message.getStockId());

        Stock stock = this.stockService.findById(message.getStockId());
        Person person = this.personService.findById(message.getPersonId());
        
        stock.setIsForSale(true);

        this.stockService.update(stock.getId(), stock);

        this.logger.info("Sending email to seller");
        EmailSender.sendEmail("[Stock Exchange] - Venda processada com sucesso!",
                "A venda da ação de Nº " + stock.getId() + " foi processada com sucesso em " + Instant.now(),
                person.getEmail());
        this.logger.info("Email sent");
    }
}