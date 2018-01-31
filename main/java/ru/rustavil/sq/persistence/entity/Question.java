package ru.rustavil.sq.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String title;

    private Date date;

    private String publisher;

    private long answerCount;

    private String link;

    private boolean isAnswered;

}
