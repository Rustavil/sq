package ru.rustavil.sq.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object containing information about the Question
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    /**
     * Question title
     */
    private String title;

    /**
     * Question create date
     */
    private Date date;

    /**
     * Question publisher
     */
    private String publisher;

    /**
     * Answers count to the question
     */
    private long answerCount;

    /**
     * Question link in site
     */
    private String link;

    /**
     * Field specifies is answered question
     */
    private boolean isAnswered;

}
