package br.com.alura.TabelaFipe.service;

import java.util.List;

public interface IConvertsDatas {
    <T> T takeDatas(String json, Class<T> tClass);

    <T> List<T> takeList(String json, Class<T> tClass);
}
