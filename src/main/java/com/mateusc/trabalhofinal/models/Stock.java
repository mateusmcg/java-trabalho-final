package com.mateusc.trabalhofinal.models;

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
public class Stock {

    @Id
    private UUID id;
    private Integer initialValue;
    private Integer currentValue;

    private Boolean isForSale;

    @DBRef
    private Person owner;

    @DBRef
    private Enterprise enterprise;
}