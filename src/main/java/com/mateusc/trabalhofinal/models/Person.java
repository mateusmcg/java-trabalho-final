package com.mateusc.trabalhofinal.models;

import java.time.LocalDate;
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
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private UUID id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Address address;

    @DBRef
    private List<Stock> stocks = new ArrayList<Stock>();
}