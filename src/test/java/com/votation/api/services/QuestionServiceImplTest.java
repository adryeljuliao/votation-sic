package com.votation.api.services;

import com.votation.api.controllers.questions.requests.QuestionRequest;
import com.votation.api.controllers.questions.responses.QuestionResponse;
import com.votation.api.exceptions.NotFoundException;
import com.votation.api.mappers.QuestionMapper;
import com.votation.api.models.question.Question;
import com.votation.api.repositories.question.QuestionRepository;
import com.votation.api.services.question.QuestionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class QuestionServiceImplTest {
    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private QuestionRepository questionRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenDescriptionQuestionToCreateThenReturnOk() {
        QuestionRequest mockQuestionRequest = QuestionRequest.builder()
                .description("Test?")
                .durationMinutesQuestion(15)
                .build();
        Question mockQuestion = QuestionMapper.makeQuestion(mockQuestionRequest);
        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(mockQuestion);

        QuestionResponse questionResponse = questionService.createQuestion(mockQuestionRequest);

        Assertions.assertNotNull(questionResponse);
        Assertions.assertNotNull(mockQuestion);
        Assertions.assertEquals(mockQuestion.getDescription(), questionResponse.getDescription());
        Assertions.assertEquals(mockQuestion.getFinalDate(), questionResponse.getFinalDate());
    }

    @Test
    void givenQuestionIdValidToFindByIdThenReturnQuestionData() {
        Question mockQuestion = Question.builder()
                .id(1L)
                .finalDate(LocalDateTime.now().plusMinutes(2))
                .description("Teste?")
                .createdDate(LocalDateTime.now())
                .build();

        Mockito.when(questionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockQuestion));

        QuestionResponse questionResponse = questionService.findQuestionById(1L);

        Assertions.assertNotNull(questionResponse);
        Assertions.assertNotNull(mockQuestion);
        Assertions.assertEquals(mockQuestion.getDescription(), questionResponse.getDescription());
        Assertions.assertEquals(mockQuestion.getFinalDate(), questionResponse.getFinalDate());
    }

    @Test
    void givenQuestionIdNotValidToFindByIdThenReturnNotFoundException() {
        Mockito.when(questionRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> questionService.findQuestionById(1L));
    }


}
