package com.jlmcavalcante.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
// Métodos do controlador respondem às requisições na URL definida: 
@RequestMapping(value = "v1/greetings")
@Slf4j
public class HelloController {

    @GetMapping
    public String hi() {
        return "Hello World!";
    }

    @PostMapping
    public Long save(@RequestBody String name) {
        log.info("save {}", name);
        
        // Gera um número aleatório de forma eficiente e segura em ambientes multithread, evitando problemas de concorrência.
        return ThreadLocalRandom.current().nextLong(1, 1000);
    }
    
}
