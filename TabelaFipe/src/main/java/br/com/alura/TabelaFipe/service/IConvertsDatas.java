package br.com.alura.TabelaFipe.service;

public interface IConvertsDatas {
    <T> T takeDatas(String json, Class<T> tClass);
}
