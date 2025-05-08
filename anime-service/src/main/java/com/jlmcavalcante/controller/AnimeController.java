package com.jlmcavalcante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlmcavalcante.domain.Anime;
import com.jlmcavalcante.mapper.AnimeMapper;
import com.jlmcavalcante.request.AnimePostRequest;
import com.jlmcavalcante.response.AnimeGetResponse;
import com.jlmcavalcante.response.AnimePostResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "v1/animes")
@Slf4j
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name {}", name);
        
        var animes = Anime.getAnimes();
        var animesGetResponse = MAPPER.toAnimeGetResponseList(animes);
        if (name == null) return ResponseEntity.ok(animesGetResponse);

        var response = animesGetResponse.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find anime by id: {}", id);

        return ResponseEntity.ok(MAPPER.toAnimeGetResponse(
                Anime.getAnimes().stream().filter(anime -> anime.getId().equals(id)).findFirst().orElse(null)));
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime: {}", request);
        var anime = MAPPER.toAnime(request);
        Anime.getAnimes().add(anime);

        var response = MAPPER.toAnimePostResponse(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
