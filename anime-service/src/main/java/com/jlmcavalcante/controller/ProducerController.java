package com.jlmcavalcante.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlmcavalcante.domain.Producer;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    @GetMapping
    public List<Producer> listAll(@RequestParam(required = false) String name) {
        var producers = Producer.getProducers();
        if(name != null) {
            return producers.stream().filter(Producer -> Producer.getName().equals(name)).toList();
        }
        return producers;
    }
    
    @GetMapping("{id}")
    public Producer findById(@PathVariable Long id) {
        return Producer.getProducers().stream().filter(Producer -> Producer.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Producer save(@RequestBody Producer Producer) {
        Producer.setId(ThreadLocalRandom.current().nextLong(1000));
        Producer.getProducers().add(Producer);
        return Producer;
    }
}
