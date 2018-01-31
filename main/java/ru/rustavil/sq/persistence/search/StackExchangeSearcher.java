package ru.rustavil.sq.persistence.search;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.StackExchangeSite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rustavil.sq.persistence.entity.Paging;
import ru.rustavil.sq.persistence.mapper.QuestionMapper;

import static java.util.stream.Collectors.toList;

public class StackExchangeSearcher implements Searcher {

    private static final Logger log = LoggerFactory.getLogger(StackExchangeSearcher.class);

    private final StackExchangeSite site;
    private final String applicationKey;

    public StackExchangeSearcher(StackExchangeSite site, String applicationKey) {
        this.site = site;
        this.applicationKey = applicationKey;
    }

    /**
     * Search questions method
     * @param query - search string
     * @param page - page number
     * @param size - entities count in page
     * @return Paging with Questions
     */
    @Override
    public Paging search(String query, int page, int size) {
        log.debug("Search query[{}] page[{}] size[{}]", query, page, size);
        com.google.code.stackexchange.schema.Paging paging = new com.google.code.stackexchange.schema.Paging(page, size);
        PagedList<com.google.code.stackexchange.schema.Question> questions = queryFactory()
                .newAdvanceSearchApiQuery()
                .withPaging(paging)
                .withTitle(query)
                .list();

        return Paging.builder()
                .page(page)
                .size(size)
                .items(questions.stream()
                        .map(QuestionMapper.INSTANCE::fromStackExchangeQuestion)
                        .collect(toList()))
                .hasMore(questions.hasMore())
                .build();
    }

    /**
     * Get QueryFactory for site
     * @return StackExchangeApiQueryFactory
     */
    private StackExchangeApiQueryFactory queryFactory(){
        return StackExchangeApiQueryFactory
                .newInstance(applicationKey, site);
    }
}
