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

import com.global_solution.gs_api.models.TipoSolo;
import com.global_solution.gs_api.repository.TipoSoloRepository;
import com.global_solution.gs_api.services.TipoSoloService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-solo")
public class TipoSoloController {

    Logger log = LoggerFactory.getLogger(TipoSoloController.class);

    List<TipoSolo> tipoSolos = new ArrayList<>();

    @Autowired
    private TipoSoloService tipoSoloService;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @PostMapping
    @ApiOperation("Cria um novo solo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Solo cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição")
    })
    public void create(@RequestBody TipoSolo tipoSolo) {
        tipoSoloService.createJPQL(tipoSolo);
    }

    @GetMapping("{id}")
    @ApiOperation("Retorna os detalhes de um solo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Solo encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Solo não encontrado")
    })
    public TipoSolo getById(@PathVariable Long id) {
        return tipoSoloService.findByIdJPQL(id);
    }

    @GetMapping("/JPQL/{string}")
    @ApiOperation("Retorna os detalhes dos solos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Solo encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Solo não encontrado")
    })
    public List<TipoSolo> getByLike(@PathVariable String string) {
        return tipoSoloService.findByLikeJPQL(string);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Exclui um solo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Solo excluído com sucesso"),
            @ApiResponse(code = 404, message = "Solo não encontrado")
    })
    public void destroy(@PathVariable Long id) {
        tipoSoloService.deleteByIdJPQL(id);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza um solo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Solo atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição"),
            @ApiResponse(code = 404, message = "Solo não encontrado")
    })
    public void update(@PathVariable Long id, @RequestBody TipoSolo tipoSolo) {
        tipoSoloService.updateJPQL(id, tipoSolo);
    }

}
