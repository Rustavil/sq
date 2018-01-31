package ru.rustavil.sq.persistence.mapper;

import org.hamcrest.Matchers;
import ru.rustavil.sq.persistence.entity.Question;
import com.google.code.stackexchange.schema.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

@RunWith(JUnit4.class)
public class QuestionMapperTest {

    @Test
    public void fromStackExchangeQuestion() {
        Date date = new Date();
        com.google.code.stackexchange.schema.Question question = new com.google.code.stackexchange.schema.Question();
        question.setTitle("Title");
        question.setCreationDate(date);
        User owner = new User();
        owner.setDisplayName("Publisher");
        question.setOwner(owner);
        question.setAnswerCount(50);
        question.setLink("link");
        Question expected = Question.builder()
                .title("Title").publisher("Publisher")
                .answerCount(50).date(date)
                .link("link")
                .build();
        Question actual = QuestionMapper.INSTANCE
                .fromStackExchangeQuestion(question);
        assertThat(actual, Matchers.equalTo(expected));
    }
}