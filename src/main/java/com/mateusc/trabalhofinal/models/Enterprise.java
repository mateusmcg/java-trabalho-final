package com.mateusc.trabalhofinal.models;

import java.util.ArrayList;
import java.util.List;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Enterprise {

    @Id
    private UUID id; 
    private String name;
    private Integer maxStocksToSell;

    @DBRef
    private List<Stock> stocks = new ArrayList<Stock>();
}