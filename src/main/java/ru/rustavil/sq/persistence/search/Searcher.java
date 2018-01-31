package ru.rustavil.sq.persistence.search;

import ru.rustavil.sq.persistence.entity.Paging;

public interface Searcher {

    /**
     * Search questions method
     * @param query - search string
     * @param page - page number
     * @param size - entities count in page
     * @return Paging with Questions
     */
    Paging search(String query, int page, int size);
}
