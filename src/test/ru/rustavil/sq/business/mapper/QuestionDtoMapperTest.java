package ru.rustavil.sq.business.mapper;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.rustavil.sq.persistence.entity.Question;
import ru.rustavil.sq.web.dto.QuestionDto;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(JUnit4.class)
public class QuestionDtoMapperTest {

    @Test
    public void questionToDto() {
        Date date = new Date();
        Question question = Question.builder()
                .title("title").date(date)
                .answerCount(100).publisher("Publisher")
                .build();
        QuestionDto expected = QuestionDto.builder()
                .title("title").date(date)
                .answerCount(100).publisher("Publisher")
                .build();
        QuestionDto actual = QuestionDtoMapper.INSTANCE.toDto(question);
        assertThat(actual, Matchers.equalTo(expected));
    }
}