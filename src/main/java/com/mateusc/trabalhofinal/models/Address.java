package com.mateusc.trabalhofinal.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
}