package com.mateusc.trabalhofinal.services;

import java.util.List;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.repositories.EnterpriseRepository;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public List<Enterprise> getAll() {
        return this.enterpriseRepository.findAll();
    }
}