package ru.rustavil.sq.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.rustavil.sq.persistence.entity.Question;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    /**
     * StackExchangeQuestion mapping in app Question entity
     * @param question StackExchangeQuestion object
     * @return app Question entity
     */
    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "owner.displayName", target = "publisher"),
            @Mapping(source = "creationDate", target = "date"),
            @Mapping(source = "answerCount", target = "answerCount"),
            @Mapping(source = "link", target = "link"),
            @Mapping(source = "isAnswered", target = "answered")})
    Question fromStackExchangeQuestion(com.google.code.stackexchange.schema.Question question);
}
