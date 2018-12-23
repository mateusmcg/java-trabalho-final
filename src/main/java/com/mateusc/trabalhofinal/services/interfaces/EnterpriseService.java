package com.mateusc.trabalhofinal.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;

public interface EnterpriseService {
    List<Enterprise> getAll();
    Enterprise findById(UUID id);
    void create(Enterprise enterprise);
    void update(UUID id, Enterprise enterprise);
    void delete(UUID id);
}