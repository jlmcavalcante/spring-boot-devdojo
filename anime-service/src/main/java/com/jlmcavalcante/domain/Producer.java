package com.jlmcavalcante.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Producer {
    private Long id;

    @JsonProperty("full_name")  // Utilizar quando houver divergências entre valores recebidos em uma requisição.
    private String name;
    
    private LocalDateTime createdAt;

    private static List<Producer> producers = new ArrayList<>();

    static {
        var producer1 = Producer.builder().id(1L).name("P1").createdAt(LocalDateTime.now()).build();
        var producer2 = Producer.builder().id(2L).name("P2").createdAt(LocalDateTime.now()).build();
        var producer3 = Producer.builder().id(3L).name("P3").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(producer1, producer2, producer3));
    }

    public static List<Producer> getProducers() {
        return producers;
    }
    
}
