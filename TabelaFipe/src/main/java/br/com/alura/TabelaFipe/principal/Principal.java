package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.service.APIConsumption;

import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);

    private final String URL_PRIMARY = "https://parallelum.com.br/fipe/api/v1/";
    private APIConsumption consumption = new APIConsumption();

    public void showMenu() {
        var menu = """

                *** OPÇÕES ***
                Carro 
                Moto
                Caminhão
                Digite uma das opções para consultar: 
                
                _______________
                """;
        System.out.println(menu);
        var option = sc.nextLine();
        String address;

        if (option.toLowerCase().contains("carr")) {
            address = URL_PRIMARY + "carros/marcas";
        }
        else if (option.toLowerCase().contains("mot")) {
            address = URL_PRIMARY + "motos/marcas";
        }
        else {
            address = URL_PRIMARY + "caminhoes/marcas";
        }

        var json = consumption.takeDatas(address);
        System.out.println(json);

    }
}
