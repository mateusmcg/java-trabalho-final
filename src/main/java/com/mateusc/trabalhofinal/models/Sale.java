package com.mateusc.trabalhofinal.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
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