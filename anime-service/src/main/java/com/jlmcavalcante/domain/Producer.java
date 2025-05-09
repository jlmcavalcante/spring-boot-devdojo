package com.jlmcavalcante.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {
    // Especificar que objetos com o mesmo id devem ser considerados iguais.
    @EqualsAndHashCode.Include
    private Long id;

    @JsonProperty("full_name")  // Utilizar quando houver divergências entre valores recebidos em uma requisição.
    private String name;
    
    private LocalDateTime createdAt;    
}
