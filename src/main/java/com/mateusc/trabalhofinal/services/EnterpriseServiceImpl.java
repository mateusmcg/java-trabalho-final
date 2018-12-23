package com.mateusc.trabalhofinal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.repositories.EnterpriseRepository;
import com.mateusc.trabalhofinal.services.interfaces.EnterpriseService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Override
    public Enterprise findById(UUID id) {
        Optional<Enterprise> enterprise = this.enterpriseRepository.findById(id);

        if (!enterprise.isPresent())
            return null;

        return enterprise.get();
    }

    @Override
    public void create(Enterprise enterprise) {
        this.enterpriseRepository.insert(enterprise);
    }

    @Override
    public void update(UUID id, Enterprise enterprise) {
        enterprise.setId(id);
        this.enterpriseRepository.save(enterprise);
    }

    @Override
    public void delete(UUID id) {
        this.enterpriseRepository.deleteById(id);
    }
}