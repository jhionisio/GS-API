package com.global_solution.gs_api.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.global_solution.gs_api.models.TipoClima;
import com.global_solution.gs_api.repository.TipoClimaRepository;
import com.global_solution.gs_api.services.TipoClimaDto;
import com.global_solution.gs_api.services.TipoClimaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-clima")
public class TipoClimaController {

    Logger log = LoggerFactory.getLogger(TipoClimaController.class);

    List<TipoClima> tipoClimas = new ArrayList<>();

    @Autowired
    private TipoClimaService tipoClimaService;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @PostMapping
    @ApiOperation("Cria um novo clima")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Clima cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição")
    })
    public void create(@RequestBody TipoClima tipoClima) {
        tipoClimaService.createJPQL(tipoClima);
    }

    @GetMapping("{id}")
    @ApiOperation("Retorna os detalhes de um clima")
    @ApiResponses({
            @ApiResponse(code = 200, message = "clima encontrado com sucesso"),
            @ApiResponse(code = 404, message = "clima não encontrado")
    })
    public TipoClima getById(@PathVariable Long id) {
        return tipoClimaService.findByIdJPQL(id);
    }

    @GetMapping("/JPQL/{string}")
    @ApiOperation("Retorna os detalhes de um clima")
    @ApiResponses({
            @ApiResponse(code = 200, message = "clima encontrado com sucesso"),
            @ApiResponse(code = 404, message = "clima não encontrado")
    })
    public List<TipoClima> findAllByLike(@PathVariable String string) {
        return tipoClimaService.findByLikeJPQL(string);
    }

    @GetMapping("/tipo-clima")
    public List<TipoClimaDto> getAllTipoClima() {
        return tipoClimaService.findAllJPQL();
    }

    @DeleteMapping("{id}")
    @ApiOperation("Exclui um grão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Clima excluído com sucesso"),
            @ApiResponse(code = 404, message = "Clima não encontrado")
    })
    public void destroy(@PathVariable Long id) {
        tipoClimaService.deleteByIdJPQL(id);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza um clima")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clima atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição"),
            @ApiResponse(code = 404, message = "Clima não encontrado")
    })
    public void update(@PathVariable Long id, @RequestBody TipoClima tipoClima) {
        tipoClimaService.updateJPQL(id, tipoClima);
    }

}