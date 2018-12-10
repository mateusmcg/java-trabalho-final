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
public class Stock {

    @Id
    private UUID id;
    private Integer initialValue;
    private Integer currentValue;

    @DBRef
    private Person owner;

    @DBRef
    private Enterprise enterprise;
}