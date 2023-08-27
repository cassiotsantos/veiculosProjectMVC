package com.veiculos.vinculos.dao;

import java.util.List;
import java.util.Optional;

public interface IDao <E> {
   E criar (E entidade);
   Optional<E> buscarPorId (Integer id);
   List<E> buscarTodos();
   E atualizar (E entidade);
   void excluir(Integer id);
}
