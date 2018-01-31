package ru.rustavil.sq.business.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.rustavil.sq.persistence.entity.Paging;
import ru.rustavil.sq.persistence.search.Searcher;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StackExchangeSearchService implements SearchService {

    private static Logger log = LoggerFactory.getLogger(StackExchangeSearchService.class);
    private final Searcher searcher;

    @Override
    @CacheEvict(value="queries", allEntries=true)
    public CompletableFuture<Paging> search(String query, int page, int size) {
        return CompletableFuture.supplyAsync(()->searcher.search(query, page, size));
    }
}
