package ru.rustavil.sq.business.service;

import ru.rustavil.sq.persistence.entity.Paging;

import java.util.concurrent.CompletableFuture;


public interface SearchService {

    CompletableFuture<Paging> search(String query, int page, int size);
}
