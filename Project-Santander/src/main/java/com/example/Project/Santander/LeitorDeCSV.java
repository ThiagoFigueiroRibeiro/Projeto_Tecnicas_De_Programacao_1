package com.example.Project.Santander;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class LeitorDeCSV {

    List<Produto> openCsv() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("lista_de_produtos.csv"));
        CsvToBean<Produto> csvToBean =
                new CsvToBeanBuilder<Produto>(reader)
                        .withType(Produto.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
        return csvToBean.parse();
    }

    int countCategorias(List<Produto> lista) {
        long count = lista.stream()
                .map(Produto::getCategoria)
                .distinct()
                .count();

        System.out.println("Existem " + count + " categorias.");
        return (int) count;
    }

    void countProdutosPorCategoria(List<Produto> lista) {
        List<String> novaLista = new ArrayList<>();
        lista.forEach(produto -> novaLista.add(produto.getCategoria()));
        Collections.sort(novaLista);
        int numero = 1;
        for (int i = 0; i<novaLista.size()-1;i++) {
            if (Objects.equals(novaLista.get(i + 1), novaLista.get(i))) {
                numero++;
            } else {
                System.out.println(novaLista.get(i) + ": " + numero);
                numero = 1;
            }
        }
    }

    BigDecimal valorMedio(List<Produto> lista) {
        BigDecimal media = lista.stream()
                .map(Produto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal resultado = media.divide(BigDecimal.valueOf(lista.size()));
        System.out.println("O preço médio dos produtos é de " + resultado);
        return media.divide(resultado);
    }

    List<Produto> estoqueBaixo(List<Produto> lista) {
        List<Produto> novaLista = lista.stream()
                .filter(produto -> produto.getQuantidade() <= 3)
                .collect(Collectors.toList());

        System.out.println("Produtos com baixo estoque:");
        novaLista.forEach(produto -> System.out.println("- " + produto.getNome() + ": " + produto.getQuantidade()));

        return novaLista;
    }

}
