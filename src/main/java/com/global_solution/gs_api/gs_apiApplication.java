package com.global_solution.gs_api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import lombok.var;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.global_solution.gs_api.models.Grao;
import com.global_solution.gs_api.repository.CultivosRepository;
import com.global_solution.gs_api.repository.GraoRepository;
import com.global_solution.gs_api.repository.TipoClimaRepository;
import com.global_solution.gs_api.repository.TipoSoloRepository;
import com.global_solution.gs_api.services.GraoServiceImpl;

@SpringBootApplication
public class gs_apiApplication {

	public static void main(String[] args) {
		SpringApplication.run(gs_apiApplication.class, args);
	}

}
