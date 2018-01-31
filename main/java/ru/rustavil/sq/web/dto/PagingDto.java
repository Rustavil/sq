package ru.rustavil.sq.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object Page view
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto {

    /**
     * Page number
     */
    private int page;

    /**
     * Page size
     */
    private int size;

    /**
     * Page items
     */
    private List items;

    /**
     * Field specifies more pages
     */
    private boolean hasMore;

}
