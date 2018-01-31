package ru.rustavil.sq.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.rustavil.sq.persistence.entity.Paging;
import ru.rustavil.sq.web.dto.PagingDto;

@Mapper
public interface PagingDtoMapper {

    PagingDtoMapper INSTANCE = Mappers.getMapper(PagingDtoMapper.class);

    /**
     * Paging entity mapping to Data Transfer Object
     * @param paging
     * @return PagingDto
     */
    PagingDto toDto(Paging paging);
}
