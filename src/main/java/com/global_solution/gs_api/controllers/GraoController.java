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

import com.global_solution.gs_api.models.Grao;
import com.global_solution.gs_api.repository.GraoRepository;
import com.global_solution.gs_api.services.GraoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/graos")
public class GraoController {

    Logger log = LoggerFactory.getLogger(GraoController.class);

    List<Grao> graos = new ArrayList<>();

    @Autowired
    private GraoService graoService;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @PostMapping
    @ApiOperation("Cria um novo grão")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grão cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição")
    })
    public void create(@RequestBody Grao grao) {
        graoService.createJPQL(grao);
    }

    @GetMapping("{id}")
    @ApiOperation("Retorna os detalhes de um grão")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grão encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Grão não encontrado")
    })
    public Grao getById(@PathVariable Long id) {
        return graoService.findByIdJPQL(id);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Exclui um grão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grão excluído com sucesso"),
            @ApiResponse(code = 404, message = "Grão não encontrado")
    })
    public void destroy(@PathVariable Long id) {
        graoService.deleteByIdJPQL(id);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza um grão")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grão atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição"),
            @ApiResponse(code = 404, message = "Grão não encontrado")
    })
    public void update(@PathVariable Long id, @RequestBody Grao grao) {
        graoService.updateJPQL(id, grao);
    }

}
