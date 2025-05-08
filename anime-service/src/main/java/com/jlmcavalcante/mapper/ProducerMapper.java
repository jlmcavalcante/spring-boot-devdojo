package com.jlmcavalcante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jlmcavalcante.domain.Producer;
import com.jlmcavalcante.request.ProducerPostRequest;
import com.jlmcavalcante.response.ProducerPostResponse;

// Consultas em: https://mapstruct.org/
@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    // Transformar o request para o domínio.
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest postRequest);

    ProducerPostResponse toProducerPostResponse(Producer producer);
}
