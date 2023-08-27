package com.veiculos.vinculos.controller;

import com.veiculos.vinculos.model.Veiculo;
import com.veiculos.vinculos.service.VeiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("listaveiculos")
public class VeiculoController {
    private VeiculoService veiculoService;
    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public Veiculo criarVeiculo(@RequestBody Veiculo veiculo){
        log.info("Criando veiculo");
        return veiculoService.criarVeiculo(veiculo);
    }

    @GetMapping("{id}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Integer id){
        log.info("buscar veiculo por id: " + id);
        try{
            return ResponseEntity.ok(veiculoService.buscarVeiculoPorID(id));
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public List<Veiculo> buscarPorTodosVeiculos() {
        log.info("Buscar veiculos");
        return veiculoService.buscarPorTodosVeiculos();
    }

    @PutMapping("{id}")
    public Veiculo atualizarVeiculo(@PathVariable Integer id, @RequestBody Veiculo veiculo) {
        log.info("Atualizar Veiculo");
        veiculo.setId(id);
        return veiculoService.atualizarVeiculo(veiculo);
    }

    @DeleteMapping("{id}")
    public void excluirVeiculo(@PathVariable Integer id){
        log.info("Excluir veiculo");
        veiculoService.excluirVeiculo(id);
    }

}
