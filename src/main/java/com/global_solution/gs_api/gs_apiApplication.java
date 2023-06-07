package com.global_solution.gs_api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.var;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.global_solution.gs_api.repository.CultivosRepository;
import com.global_solution.gs_api.repository.GraoRepository;
import com.global_solution.gs_api.repository.TipoClimaRepository;
import com.global_solution.gs_api.repository.TipoSoloRepository;
import com.global_solution.gs_api.services.GraoServiceImpl;

@SpringBootApplication
public class gs_apiApplication {

	public static void main(String[] args) {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTEGS-PU");
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			throw e;
		}
	}

}
