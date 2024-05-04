package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.model.Datas;
import br.com.alura.TabelaFipe.model.Models;
import br.com.alura.TabelaFipe.model.Vehicle;
import br.com.alura.TabelaFipe.service.APIConsumption;
import br.com.alura.TabelaFipe.service.ConvertsDatas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);

    private final String URL_PRIMARY = "https://parallelum.com.br/fipe/api/v1/";
    private APIConsumption consumption = new APIConsumption();
    private ConvertsDatas converter = new ConvertsDatas();

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
        var brands = converter.takeList(json, Datas.class);
        brands.stream()
                .sorted(Comparator.comparing(Datas::code))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var brandCode = sc.nextLine();

        address += "/" + brandCode + "/modelos";
        json = consumption.takeDatas(address);
        var listModel = converter.takeDatas(json, Models.class);

        System.out.println();
        System.out.println("Modelos dessa marca: ");
        listModel.models().stream()
                .sorted(Comparator.comparing(Datas::code))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Digite um trecho do nome do carro a ser procurado: ");
        var nameModel = sc.nextLine();

        List<Datas> filteredModels = listModel.models().stream()
                .filter(m -> m.name().toLowerCase().contains(nameModel.toLowerCase()))
                        .collect(Collectors.toList());

        System.out.println();
        System.out.println("Modelos filtrados: ");
        filteredModels.forEach(System.out::println);

        System.out.println();
        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var modelCode = sc.nextLine();

        address += "/" + modelCode + "/anos";
        json = consumption.takeDatas(address);
        List<Datas> years = converter.takeList(json, Datas.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for(int i=0; i<years.size(); i++) {
            var yearAddress = address + "/" + years.get(i).code();
            json = consumption.takeDatas(yearAddress);
            Vehicle vehicle = converter.takeDatas(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println();
        System.out.println("Todos os veículos filtrados com avaliações por ano: ");
        vehicles.forEach(System.out::println);
    }
}
