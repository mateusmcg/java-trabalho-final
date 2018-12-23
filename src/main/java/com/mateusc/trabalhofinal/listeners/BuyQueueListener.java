package com.mateusc.trabalhofinal.listeners;

import java.time.Instant;
import java.util.List;

import com.mateusc.trabalhofinal.config.EmailConfig;
import com.mateusc.trabalhofinal.config.EmailSender;
import com.mateusc.trabalhofinal.config.RabbitMQConfig;
import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.models.Message;
import com.mateusc.trabalhofinal.models.Person;
import com.mateusc.trabalhofinal.models.Stock;
import com.mateusc.trabalhofinal.services.interfaces.PersonService;
import com.mateusc.trabalhofinal.services.interfaces.StockService;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BuyQueueListener {
    static final Logger logger = LoggerFactory.getLogger(BuyQueueListener.class);

    private final PersonService personService;
    private final StockService stockService;

    public BuyQueueListener(PersonService personService, StockService stockService) {
        this.personService = personService;
        this.stockService = stockService;
    }

    @RabbitListener(queues = RabbitMQConfig.BUY_QUEUE)
    public void processMessage(Message message) {
        logger.info("Message Received - BUY");
        logger.info("PersonId:" + message.getPersonId());
        logger.info("StockId:" + message.getStockId());

        Person person = this.personService.findById(message.getPersonId());
        Stock stock = this.stockService.findById(message.getStockId());

        if (stock.getIsForSale() == null || !stock.getIsForSale()) {
            this.logger.info("This stock is not for sale (Sending email to buyer)");
            EmailSender.sendEmail("[Stock Exchange] - Oops, esta ação não está a venda!",
                    "Não foi possível realizar a compra da ação de Nº " + stock.getId() + " pois ela não está a venda (" + Instant.now() + ")",
                    person.getEmail());
            this.logger.info("Email sent");

            return;
        }

        String sellerEmail = null;
        if (stock.getOwner() != null) {
            sellerEmail = stock.getOwner().getEmail();
        }

        this.logger.info("Person stock count before buying: " + person.getStocks().size());
        person.getStocks().add(stock);
        this.logger.info("Person stock count after buying: " + person.getStocks().size());

        this.personService.update(person.getId(), person);
        this.logger.info("Person updated");

        stock.setOwner(Person.builder().id(person.getId()).build());
        stock.setIsForSale(false);
        
        this.stockService.update(stock.getId(), stock);
        this.logger.info("Stock updated");

        this.logger.info("Sending email to buyer");
        EmailSender.sendEmail("[Stock Exchange] - Compra realizada com sucesso!",
                "A compra da ação de Nº " + stock.getId() + " foi realizada com sucesso em " + Instant.now(),
                person.getEmail());
        this.logger.info("Email sent");

        if (sellerEmail != null) {
            this.logger.info("Sending email to seller");
            EmailSender.sendEmail("[Stock Exchange] - Venda realizada com sucesso!",
                    "A ação de Nº " + stock.getId() + " foi vendida com sucesso em " + Instant.now(), sellerEmail);
            this.logger.info("Email sent");
        } else {
            this.logger.info("This is the first buyer of the stock: " + stock.getId());
        }
    }
}