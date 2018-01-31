package ru.rustavil.sq.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.rustavil.sq.persistence.entity.Question;
import ru.rustavil.sq.web.dto.QuestionDto;

@Mapper
public interface QuestionDtoMapper {

    QuestionDtoMapper INSTANCE = Mappers.getMapper(QuestionDtoMapper.class);

    /**
     * Question entity mapping to Data Transfer Object
     * @param question
     * @return QuestionDto
     */
    QuestionDto toDto(Question question);
}
