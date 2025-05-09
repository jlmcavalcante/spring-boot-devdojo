package com.jlmcavalcante.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jlmcavalcante.domain.Producer;
import com.jlmcavalcante.request.ProducerPostRequest;
import com.jlmcavalcante.request.ProducerPutRequest;
import com.jlmcavalcante.response.ProducerGetResponse;
import com.jlmcavalcante.response.ProducerPostResponse;

// Consultas em: https://mapstruct.org/
@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    // Transformar o request para o domínio.
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest postRequest);

    Producer toProducer(ProducerPutRequest putRequest);

    // Pode add um argumento para outros atributos (mesmo nome) que não estão presentes no DTO.
    Producer toProducerWithArg(ProducerPutRequest putRequest, LocalDateTime createdAt);

    ProducerPostResponse toProducerPostResponse(Producer producer);

    ProducerGetResponse toProducerGetResponse(Producer producer);
    List<ProducerGetResponse> toProducerGetResponseList(List<Producer> producer);
}
