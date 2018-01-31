package ru.rustavil.sq.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paging {

    private int page;

    private int size;

    private List items;

    private boolean hasMore;

}
