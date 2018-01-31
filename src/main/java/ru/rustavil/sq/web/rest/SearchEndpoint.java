package ru.rustavil.sq.web.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import ru.rustavil.sq.business.mapper.PagingDtoMapper;
import ru.rustavil.sq.business.service.SearchService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/query")
public class SearchEndpoint {

    private static final Logger log = LoggerFactory.getLogger(SearchEndpoint.class);

    private final SearchService searchService;

    /**
     * Async method for searching questions
     * @param query - search string
     * @param page - page number
     * @param size - element count in page
     * @return PagingDto contains a list of questions
     */
    @RequestMapping(method = RequestMethod.GET)
    public DeferredResult<ResponseEntity> search(@RequestParam(name = "value") String query,
                                                            @RequestParam int page,
                                                            @RequestParam int size){
        log.debug("Search query:{} page:{} size:{}", query);
        DeferredResult<ResponseEntity> result = new DeferredResult<>();
        searchService.search(query, page, size)
                .thenAccept((paging)->result.setResult(ResponseEntity.ok(PagingDtoMapper.INSTANCE.toDto(paging))));
        return result;
    }
}
