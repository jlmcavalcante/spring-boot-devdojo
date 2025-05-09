package com.jlmcavalcante.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlmcavalcante.domain.Producer;
import com.jlmcavalcante.mapper.ProducerMapper;
import com.jlmcavalcante.request.ProducerPostRequest;
import com.jlmcavalcante.response.ProducerGetResponse;
import com.jlmcavalcante.response.ProducerPostResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all producers, param name {}", name);

        var producers = Producer.getProducers();
        var responseList = MAPPER.toProducerGetResponseList(producers);

        if(name == null) return ResponseEntity.ok(responseList);

        var responseFiltered = responseList.stream().filter(producerResponse -> producerResponse.getName().equalsIgnoreCase(name)).toList();
        return ResponseEntity.ok(responseFiltered);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find producer by id {}", id);

        var response = Producer.getProducers().stream().filter(p -> p.getId().equals(id)).findFirst().map(MAPPER::toProducerGetResponse).orElse(null);

        return ResponseEntity.ok(response);
    }

    // Definir o formato do dado que está sendo produzido ou recebido.
    // Definir um determinado header como obrigatório. Pode ser usado para colocar a versão (x-v1).
    // Obter todos os headers da requisição com @RequestHeader.
    // Aprimorar os retornos através do ResponseEntity, onde podemos colocar informações adicionais. 
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
    headers = "x-api-key=123")  
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        
        log.info("HEADERS DA REQUISIÇÃO: {}", headers);

        var producer = MAPPER.toProducer(producerPostRequest);
        Producer.getProducers().add(producer);

        var responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", "My key");

    
        var response = MAPPER.toProducerPostResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(response);
    }
}
