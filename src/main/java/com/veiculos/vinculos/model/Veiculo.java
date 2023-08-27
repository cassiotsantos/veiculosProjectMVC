package com.veiculos.vinculos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Veiculo {
    private Integer id;
    private String cor;
    private MarcaEnum marca;
}
