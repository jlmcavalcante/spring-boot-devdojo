package com.jlmcavalcante.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Producer {
    private Long id;
    @JsonProperty("full_name")
    private String name;
    private static List<Producer> producers = new ArrayList<>();

    static {
        var producer1 = new Producer(1L, "P1");
        var producer2 = new Producer(2L, "P2");
        var producer3 = new Producer(3L, "P3");

        producers.addAll(List.of(producer1, producer2, producer3));
    }

    public static List<Producer> getProducers() {
        return producers;
    }
    
}
