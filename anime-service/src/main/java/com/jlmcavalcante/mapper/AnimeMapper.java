package com.jlmcavalcante.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jlmcavalcante.domain.Anime;
import com.jlmcavalcante.request.AnimePostRequest;
import com.jlmcavalcante.request.AnimePutRequest;
import com.jlmcavalcante.response.AnimeGetResponse;
import com.jlmcavalcante.response.AnimePostResponse;

@Mapper
public interface AnimeMapper {
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    
    @Mapping(target = "id", expression="java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest postRequest);
    Anime toAnime(AnimePutRequest putRequest);
    
    AnimePostResponse toAnimePostResponse(Anime anime);

    AnimeGetResponse toAnimeGetResponse(Anime anime);

    List<AnimeGetResponse> toAnimeGetResponseList(List<Anime> animes);
}
