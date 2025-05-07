package com.jlmcavalcante.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "v1/heroes")
public class HeroController {
    private static final List<String> HEROES = List.of("h1", "h2", "h3", "h4");

    @GetMapping()
    public List<String> listAllHeroes() {
        return HEROES;
    }

    @GetMapping("filter")
    public List<String> listAllHeroesParam(@RequestParam(required = false, defaultValue = "h2") String name) {
        return HEROES.stream().filter(heroes -> heroes.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterList")
    public List<String> listAllHeroesParamList(@RequestParam List<String> names) {
        return HEROES.stream().filter(names::contains).toList();
    }

    @GetMapping("{name}")
    public String findByName(@PathVariable String name) {
        return HEROES.stream().filter(hero -> hero.equalsIgnoreCase(name)).findFirst().orElse("Não encontrado");
    }
    
}