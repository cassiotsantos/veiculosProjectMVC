package com.veiculos.vinculos.service.impl;

import com.veiculos.vinculos.dao.IDao;
import com.veiculos.vinculos.model.Veiculo;
import com.veiculos.vinculos.service.VeiculoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final IDao<Veiculo> veiculoIDao;
    public VeiculoServiceImpl(IDao<Veiculo> veiculoIDao) {
        this.veiculoIDao = veiculoIDao;
    }

    @Override
    public Veiculo criarVeiculo(Veiculo vinculo) {
        return veiculoIDao.criar(vinculo);
    }

    @Override
    public Veiculo buscarVeiculoPorID(Integer id) {
        return veiculoIDao.buscarPorId(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<Veiculo> buscarPorTodosVeiculos() {
        return veiculoIDao.buscarTodos();
    }

    @Override
    public Veiculo atualizarVeiculo(Veiculo veiculo) {
        return veiculoIDao.atualizar(veiculo);
    }

    @Override
    public void excluirVeiculo(Integer id) {
        veiculoIDao.excluir(id);
    }
}
