package com.mateusc.trabalhofinal.bootstrap;

import java.util.UUID;

import com.mateusc.trabalhofinal.models.Enterprise;
import com.mateusc.trabalhofinal.repositories.EnterpriseRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private EnterpriseRepository enterpriseRepository;

	public ApplicationBootstrap(EnterpriseRepository enterpriseRepository) {
		this.enterpriseRepository = enterpriseRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (enterpriseRepository.count() == 0L) {
			enterpriseRepository.deleteAll();
			loadEnterprises();
		}
	}

	private void loadEnterprises() {
		Enterprise teste = Enterprise.builder().id(UUID.randomUUID()).name("Nome Teste").maxStocksToSell(10000).build();
		enterpriseRepository.save(teste);
	}
}