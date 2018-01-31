package ru.rustavil.sq.business.configuration;

import com.google.code.stackexchange.schema.StackExchangeSite;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rustavil.sq.persistence.search.Searcher;
import ru.rustavil.sq.persistence.search.StackExchangeSearcher;

@Configuration
public class SearchConfiguration {

    @Value("${stack.exchange.application.key}")
    private String applicationKey;

    @Value("${stack.exchange.application.site}")
    private String searchSite;

    /**
     * Initializing the StackExchange searcher
     * @return Searcher
     */
    @Bean
    public Searcher searcher(){
        return new StackExchangeSearcher(StackExchangeSite.fromValue(searchSite), applicationKey);
    }
}
