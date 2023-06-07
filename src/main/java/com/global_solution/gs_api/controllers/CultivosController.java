package com.global_solution.gs_api.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.persistence.EntityManager;
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

import com.global_solution.gs_api.models.Cultivos;
import com.global_solution.gs_api.repository.CultivosRepository;
import com.global_solution.gs_api.services.CultivosDTO;
import com.global_solution.gs_api.services.CultivosService;
import com.global_solution.gs_api.services.CultivosViewDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cultivos")
public class CultivosController {

    Logger log = LoggerFactory.getLogger(CultivosController.class);

    List<Cultivos> cultivos = new ArrayList<>();

    @Autowired
    private CultivosService cultivosService;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @PostMapping
    @ApiOperation("Cria um novo cultivo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cultivo cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição")
    })
    public void create(@RequestBody Cultivos cultivos) {
        cultivosService.createJPQL(cultivos);
    }

    @GetMapping("{id}")
    @ApiOperation("Retorna os detalhes de um cultivos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cultivos encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cultivos não encontrado")
    })
    public CultivosViewDto getById(@PathVariable Long id) {
        return cultivosService.findByIdJPQL(id);
    }

    @GetMapping("/cultivos")
    public List<CultivosViewDto> getAll() {
        return cultivosService.findAllJPQL();
    }

    @GetMapping("/findByClimaAndTipoSolo/{dsClima}/{nmTipoSolo}")
    public List<CultivosViewDto> findByClimaAndTipoSolo(@PathVariable Integer dsClima,
            @PathVariable Integer nmTipoSolo) {
        try {
            return cultivosService.findByDsClimaAndNmTipoSolo(dsClima, nmTipoSolo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving cultivos", e);
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Exclui um cultivo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cultivo excluído com sucesso"),
            @ApiResponse(code = 404, message = "Cultivo não encontrado")
    })
    public void destroy(@PathVariable Long id) {
        cultivosService.deleteByIdJPQL(id);
    }

    @GetMapping
    public List<CultivosViewDto> findByDsClimaAndNmTipoSolo(@RequestParam("dsClima") Integer dsClima,
            @RequestParam("nmTipoSolo") Integer nmTipoSolo) {
        return cultivosService.findByDsClimaAndNmTipoSolo(dsClima, nmTipoSolo);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza um grão")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cultivo atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos dados da requisição"),
            @ApiResponse(code = 404, message = "Cultivo não encontrado")
    })
    public void update(@RequestBody Cultivos cultivos) {
        cultivosService.updateJPQL(cultivos);
    }

}