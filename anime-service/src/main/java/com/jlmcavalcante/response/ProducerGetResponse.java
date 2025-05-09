package com.jlmcavalcante.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducerGetResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
