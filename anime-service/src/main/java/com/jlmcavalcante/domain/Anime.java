package com.jlmcavalcante.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<>();

    // Bloco estático em Java é executado uma vez quando a classe é carregada (antes de qualquer instância), para inicializar variáveis estáticas.
    static {
        var anime1 = new Anime(1L, "ANIME 1");
        var anime2 = new Anime(2L, "ANIME 2");
        var anime3 = new Anime(3L, "ANIME 3");

        animes.addAll(List.of(anime1, anime2, anime3));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }

}
