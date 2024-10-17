package com.example.bookservice.mapper;

import com.example.bookservice.controller.request.BookCreateRequest;
import com.example.bookservice.controller.request.BookUpdateRequest;
import com.example.bookservice.model.BookModel;
import com.example.bookservice.repository.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface BookMapper {

    @Mapping(target = "author.name", source = "authorName")
    @Mapping(target = "author.surname", source = "authorSurname")
    @Mapping(target = "genre.name", source = "genre")
    BookEntity toEntity(BookModel model);

    List<BookEntity> toEntities(List<BookModel> models);

    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "authorSurname", source = "author.surname")
    @Mapping(target = "genre", source = "genre.name")
    BookModel toModel(BookEntity entity);

    List<BookModel> toModels(List<BookEntity> models);

    BookModel toModel(BookCreateRequest createRequest);

    BookModel toModel(BookUpdateRequest updateRequest);
}
