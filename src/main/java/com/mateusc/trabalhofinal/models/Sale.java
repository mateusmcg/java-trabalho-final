package com.mateusc.trabalhofinal.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Sale {

    @Id
    private UUID id;
    private LocalDateTime sale_date;
    private LocalDateTime purchase_date;

    @DBRef
    private Person seller;

    @DBRef
    private Person buyer;

    @DBRef
    private Stock stock;
}