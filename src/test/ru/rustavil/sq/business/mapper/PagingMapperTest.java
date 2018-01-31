package ru.rustavil.sq.business.mapper;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.rustavil.sq.persistence.entity.Paging;
import ru.rustavil.sq.web.dto.PagingDto;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class PagingMapperTest {

    @Test
    public void pagingOf() {
        List list = Arrays.asList(1,2,3,4,5);
        int page = 1;
        int size = 50;
        Paging paging = Paging.builder()
                .page(page)
                .size(size)
                .items(list)
                .hasMore(true)
                .build();
        PagingDto expected = PagingDto
                .builder().items(list)
                .page(page).size(size)
                .hasMore(true).build();
        PagingDto actual = PagingDtoMapper.INSTANCE.toDto(paging);
        assertThat(actual, Matchers.equalTo(expected));
    }
}