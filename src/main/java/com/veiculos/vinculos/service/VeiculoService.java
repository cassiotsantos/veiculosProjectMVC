package com.veiculos.vinculos.service;

import com.veiculos.vinculos.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    Veiculo criarVeiculo(Veiculo vinculo);
    Veiculo buscarVeiculoPorID (Integer id);
    List<Veiculo> buscarPorTodosVeiculos();
    Veiculo atualizarVeiculo(Veiculo veiculo);
    void excluirVeiculo(Integer id);
}
