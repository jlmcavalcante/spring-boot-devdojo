package com.jlmcavalcante.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlmcavalcante.domain.Anime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "v1/animes")
@Slf4j
public class AnimeController {

    @GetMapping
    public List<Anime> listAll(@RequestParam(required = false) String name) {
        var animes = Anime.getAnimes();
        if(name != null) {
            return animes.stream().filter(anime -> anime.getName().equals(name)).toList();
        }
        return animes;
    }
    
    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.getAnimes().stream().filter(anime -> anime.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Anime save(@RequestBody Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(1000));
        Anime.getAnimes().add(anime);
        return anime;
    }
}
