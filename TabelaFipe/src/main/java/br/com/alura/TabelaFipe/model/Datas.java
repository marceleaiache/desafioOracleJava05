package br.com.alura.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Datas(@JsonAlias("codigo") String code,
                    @JsonAlias("nome") String name) {
}
