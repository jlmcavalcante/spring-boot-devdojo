package com.jlmcavalcante.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.jlmcavalcante.domain.Anime;

public class AnimeHardCodedRepository {
    private static final List<Anime> ANIMES = new ArrayList<>();

    // Bloco estático em Java é executado uma vez quando a classe é carregada (antes de qualquer instância), para inicializar variáveis estáticas.
    static {
        var anime1 = Anime.builder().id(1L).name("ANIME 1").build();
        var anime2 = Anime.builder().id(2L).name("ANIME 2").build();
        var anime3 = Anime.builder().id(3L).name("ANIME 3").build();

        ANIMES.addAll(List.of(anime1, anime2, anime3));
    }

    public List<Anime> getAnimes() {
        return ANIMES;
    }

    public List<Anime> findAll(String name) {
        return name == null ? ANIMES : ANIMES.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    public Anime findById(Long id) {
        return ANIMES.stream().filter(a -> a.getId().equals(id)).findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }

    public void deleteById(Long id) {
        var animeToDelete = findById(id);
        ANIMES.remove(animeToDelete);
    }

    public void update(Anime animeToUpdate) {
        deleteById(animeToUpdate.getId());
        save(animeToUpdate);
    }
}
