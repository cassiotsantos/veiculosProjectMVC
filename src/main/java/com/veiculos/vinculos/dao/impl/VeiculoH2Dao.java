package com.veiculos.vinculos.dao.impl;

import com.veiculos.vinculos.dao.ConfiguracaoJdbc;
import com.veiculos.vinculos.dao.IDao;
import com.veiculos.vinculos.model.MarcaEnum;
import com.veiculos.vinculos.model.Veiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class VeiculoH2Dao implements IDao<Veiculo> {

    private static final String  SQL_CRIAR_VEICULO = """
            INSERT INTO Veiculo(cor,marca) VALUES (?, ?)
            """;
    private static final String  SQL_BUSCAR_POR_ID = """
            SELECT * FROM Veiculo WHERE id = ?;
            """;
    private static final String SQL_BUSCAR_TODOS = """
            SELECT * FROM Veiculo;
            """;
    private static final String SQL_ATUALIZR_VEICULO = """
            UPDATE Veiculo SET cor =?, marca =?;
            """;
    private static final String SQL_EXCLUIR_POR_ID = """
            DELETE * FROM Veiculo WHERE id = ?;
            """;

    private final ConfiguracaoJdbc configuracaoJdbc = new ConfiguracaoJdbc();

    @Override
    public Veiculo criar(Veiculo entidade) {
        log.info("Criando um novo veículo");
        Connection connection = configuracaoJdbc.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(SQL_CRIAR_VEICULO, Statement.RETURN_GENERATED_KEYS)){
            log.info("criando um veículo que retorna id");
            statement.setString(1, entidade.getCor());
            statement.setString(2, entidade.getMarca().name());
            log.info("executando script de veículo: " + entidade);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            while(resultSet.next()){
                entidade.setId(resultSet.getInt(1));
            };
            log.info("id encontrado: " + entidade.getId());
            return entidade;
        } catch (Exception e){
            log.error("Deu algo errado!");
            return null;
        }
    }

    @Override
    public Optional<Veiculo> buscarPorId(Integer id) {
        log.info("Buscar veículo por ID");
        Connection connection = configuracaoJdbc.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(SQL_BUSCAR_POR_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Veiculo veiculo = null;
            while (resultSet.next()){
                veiculo = this.getVeiculoByResultSet(resultSet);
                log.info("veículo encontrado: " + veiculo);
            }
            return Optional.ofNullable(veiculo);
        } catch (Exception e){
            log.error("Deu algo errado!");
            return null;
        }
    }

    @Override
    public List<Veiculo> buscarTodos() {
        log.info("Buscar todos veículos");
        Connection connection = configuracaoJdbc.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_BUSCAR_TODOS);

            List<Veiculo> veiculos = new ArrayList<>();
            while (resultSet.next()) {
                Veiculo veiculo = getVeiculoByResultSet(resultSet);
                veiculos.add(veiculo);
            }
            return veiculos;
        } catch (Exception e) {
            log.error("Um erro inesperado aconteceu!", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Veiculo atualizar(Veiculo entidade) {

        Connection connection = configuracaoJdbc.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(SQL_ATUALIZR_VEICULO)){
            statement.setString(1,entidade.getCor());
            statement.setString(2,entidade.getMarca().name());
            statement.setInt(3, entidade.getId());
            statement.executeUpdate();
            return entidade;

        }catch (Exception e){
            log.error("Ocorreu um erro", e);
            return null;
        }
    }

    @Override
    public void excluir(Integer id) {
        Connection connection = configuracaoJdbc.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(SQL_EXCLUIR_POR_ID)){

            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (Exception e){
            log.error("Ocorreu um erro", e);
        }

    }

    private Veiculo getVeiculoByResultSet(ResultSet resultSet) throws SQLException {
        Veiculo veiculo;
        Integer id = resultSet.getInt(1);
        String cor = resultSet.getString(2);
        MarcaEnum marcaEnum = MarcaEnum.valueOf(resultSet.getString(3));
        veiculo = new Veiculo(id, cor, marcaEnum);
        return veiculo;
    }


}


