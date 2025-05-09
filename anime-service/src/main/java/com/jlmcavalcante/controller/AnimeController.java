package com.jlmcavalcante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlmcavalcante.Repository.AnimeHardCodedRepository;
import com.jlmcavalcante.mapper.AnimeMapper;
import com.jlmcavalcante.request.AnimePostRequest;
import com.jlmcavalcante.request.AnimePutRequest;
import com.jlmcavalcante.response.AnimeGetResponse;
import com.jlmcavalcante.response.AnimePostResponse;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "v1/animes")
@Slf4j
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    private AnimeHardCodedRepository repository;

    public AnimeController() {
        this.repository = new AnimeHardCodedRepository();
    }

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name {}", name);
        
        var animes = repository.findAll(name);
        var animesGetResponse = MAPPER.toAnimeGetResponseList(animes);
        
        return ResponseEntity.ok(animesGetResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find anime by id: {}", id);

        var animeGetResponse = MAPPER.toAnimeGetResponse(repository.findById(id));

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime: {}", request);

        var animeUpdated = repository.save(MAPPER.toAnime(request));

        var response = MAPPER.toAnimePostResponse(animeUpdated);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by {}", id);

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Request to update anime {}", request);

        var animeToUpdate = MAPPER.toAnime(request);

        repository.update(animeToUpdate);
        
        return ResponseEntity.noContent().build();
    }
}
