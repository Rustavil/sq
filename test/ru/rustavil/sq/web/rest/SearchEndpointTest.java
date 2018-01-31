package ru.rustavil.sq.web.rest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.rustavil.sq.business.service.SearchService;
import ru.rustavil.sq.persistence.entity.Paging;
import ru.rustavil.sq.persistence.entity.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(Parameterized.class)
public class SearchEndpointTest {

    private MockMvc mockMvc;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SearchEndpoint searchEndpoint;

    @Parameterized.Parameter
    public String query;
    @Parameterized.Parameter(value = 1)
    public int page;
    @Parameterized.Parameter(value = 2)
    public int size;
    @Parameterized.Parameter(value = 3)
    public List<Question> questions;
    @Parameterized.Parameter(value = 4)
    public boolean hasMore;

    @Before
    public void beforeSetup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(searchEndpoint)
                .build();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        String query = "query1";
        int page = 1;
        int size = 2;
        List<Question> questions = Arrays.asList(
                Question.builder()
                        .title("Title1")
                        .publisher("Publisher1")
                        .answerCount(1)
                        .date(new Date())
                        .build(),
                Question.builder()
                        .title("Title2")
                        .publisher("Publisher2")
                        .answerCount(2)
                        .date(new Date())
                        .build());
        params.add(new Object[]{query, page, size, questions, true});

        query = "query2";
        page = 2;
        size = 2;
        questions = Arrays.asList(
                Question.builder()
                        .title("Title3")
                        .publisher("Publisher3")
                        .answerCount(3)
                        .date(new Date())
                        .build(),
                Question.builder()
                        .title("Title4")
                        .publisher("Publisher4")
                        .answerCount(4)
                        .date(new Date())
                        .build());
        params.add(new Object[]{query, page, size, questions, false});

        query = "query3";
        page = 3;
        size = 2;
        questions = Collections.singletonList(
                Question.builder()
                        .title("Title5")
                        .publisher("Publisher5")
                        .answerCount(5)
                        .date(new Date())
                        .build());
        params.add(new Object[]{query, page, size, questions, false});
        return params;
    }

    @Test
    public void search() throws Exception {
        assertThat(searchService, Matchers.notNullValue());
        assertThat(mockMvc, Matchers.notNullValue());
        when(searchService.search(eq(query), eq(page), eq(size)))
                .thenReturn(
                        CompletableFuture.completedFuture(
                                Paging.builder()
                                        .page(page)
                                        .size(size)
                                        .items(questions)
                                        .hasMore(hasMore)
                                        .build()));

        MvcResult resultActions = mockMvc.perform(get("/query")
                .param("value", query)
                .param("page", Integer.toString(page))
                .param("size", Integer.toString(size)))
                .andExpect(request().asyncStarted())
                .andReturn();


        mockMvc.perform(asyncDispatch(resultActions))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", is(page)))
                .andExpect(jsonPath("$.size", is(size)))
                .andExpect(jsonPath("$.hasMore", is(hasMore)));
    }
}