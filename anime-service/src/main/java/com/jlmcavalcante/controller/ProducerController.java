package com.jlmcavalcante.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jlmcavalcante.domain.Producer;
import com.jlmcavalcante.mapper.ProducerMapper;
import com.jlmcavalcante.request.ProducerPostRequest;
import com.jlmcavalcante.request.ProducerPutRequest;
import com.jlmcavalcante.response.ProducerGetResponse;
import com.jlmcavalcante.response.ProducerPostResponse;
import com.jlmcavalcante.service.ProducerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    private ProducerService service;

    public ProducerController() {
        this.service = new ProducerService();
    }

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all producers, param name {}", name);

        var producers = service.findAll(name);
        var producerGetResponse = MAPPER.toProducerGetResponseList(producers);

        return ResponseEntity.ok(producerGetResponse);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find producer by id {}", id);

        var response = MAPPER.toProducerGetResponse(service.findByIdOrThrowNotFound(id));
        
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

        var producerSaved = service.save(MAPPER.toProducer(producerPostRequest));

        var responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", "My key");
    
        var producerPostResponse = MAPPER.toProducerPostResponse(producerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(producerPostResponse);
    }

    // Idempotent method: quando executado diversas vezes além da primeira, não altera o status do servidor.
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete producer by id {}", id);

        service.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        log.debug("Request to update producer {}", request);
        
        var producerToUpdate = MAPPER.toProducer(request);

        service.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }
}
