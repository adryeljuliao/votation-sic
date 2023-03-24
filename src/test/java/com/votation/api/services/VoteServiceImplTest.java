package com.votation.api.services;

import com.votation.api.controllers.associates.responses.AssociateResponse;
import com.votation.api.controllers.questions.responses.QuestionResponse;
import com.votation.api.exceptions.BadRequestException;
import com.votation.api.mappers.AssociateMapper;
import com.votation.api.mappers.QuestionMapper;
import com.votation.api.models.vote.Vote;
import com.votation.api.repositories.vote.VoteRepository;
import com.votation.api.services.associate.AssociateServiceImpl;
import com.votation.api.services.question.QuestionServiceImpl;
import com.votation.api.services.vote.VoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

public class VoteServiceImplTest {
    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private QuestionServiceImpl questionService;

    @Mock
    private AssociateServiceImpl associateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenAnAssociateThatNotVotedOnQuestionThenReturnOk() {
        Long questionId = 1L;
        String documentNumber = "12345678900";
        Boolean statusVote = true;

        QuestionResponse questionResponse = QuestionResponse.builder()
                .id(questionId)
                .finalDate(LocalDateTime.now().plusMinutes(15))
                .description("Quer realizar um teste?")
                .build();

        AssociateResponse associateResponse = AssociateResponse.builder()
                .id(1L)
                .documentNumber(documentNumber)
                .build();

        Vote vote = Vote.builder()
                .question(QuestionMapper.makeQuestion(questionResponse))
                .associate(AssociateMapper.makeAssociate(associateResponse))
                .statusVote(statusVote)
                .createdDate(LocalDateTime.now())
                .build();

        Mockito.when(questionService.findQuestionById(Mockito.anyLong())).thenReturn(questionResponse);
        Mockito.when(associateService.findAssociateByDocumentNumber(Mockito.anyString())).thenReturn(associateResponse);
        Mockito.when(voteRepository.findVoteByAssociateIdAndQuestionId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(voteRepository.save(Mockito.any(Vote.class))).thenReturn(vote);

        voteService.createVote(questionId, documentNumber, statusVote);

        Mockito.verify(questionService, Mockito.times(1)).findQuestionById(questionId);
        Mockito.verify(associateService, Mockito.times(1)).findAssociateByDocumentNumber(documentNumber);
        Mockito.verify(voteRepository, Mockito.times(1)).findVoteByAssociateIdAndQuestionId(associateResponse.getId(), questionResponse.getId());
        Mockito.verify(voteRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void givenAnAssociateThatTryVoteMoreThanOnceOnTheSameQuestionThenNotAllowed() {
        Long questionId = 1L;
        String documentNumber = "12345678900";
        Boolean statusVote = true;

        QuestionResponse questionResponse = QuestionResponse.builder()
                .id(questionId)
                .finalDate(LocalDateTime.now().plusMinutes(15))
                .description("Quer realizar um teste?")
                .build();

        AssociateResponse associateResponse = AssociateResponse.builder()
                .id(1L)
                .documentNumber(documentNumber)
                .build();

        Vote vote = Vote.builder()
                .question(QuestionMapper.makeQuestion(questionResponse))
                .associate(AssociateMapper.makeAssociate(associateResponse))
                .statusVote(statusVote)
                .createdDate(LocalDateTime.now())
                .build();

        Mockito.when(questionService.findQuestionById(Mockito.anyLong())).thenReturn(questionResponse);
        Mockito.when(associateService.findAssociateByDocumentNumber(Mockito.anyString())).thenReturn(associateResponse);
        Mockito.when(voteRepository.findVoteByAssociateIdAndQuestionId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(vote));

        Assertions.assertThrows(BadRequestException.class, () -> voteService.createVote(questionId, documentNumber, statusVote));
    }

    @Test
    void givenAnAssociateThatTryVoteQuestionTimeExpiredThenNotAllowed() {
        Long questionId = 1L;
        String documentNumber = "12345678900";
        Boolean statusVote = true;

        QuestionResponse questionResponse = QuestionResponse.builder()
                .id(questionId)
                .finalDate(LocalDateTime.now().plusMinutes(-15))
                .description("Quer realizar um teste?")
                .build();

        AssociateResponse associateResponse = AssociateResponse.builder()
                .id(1L)
                .documentNumber(documentNumber)
                .build();

        Mockito.when(questionService.findQuestionById(Mockito.anyLong())).thenReturn(questionResponse);
        Mockito.when(associateService.findAssociateByDocumentNumber(Mockito.anyString())).thenReturn(associateResponse);
        Mockito.when(voteRepository.findVoteByAssociateIdAndQuestionId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> voteService.createVote(questionId, documentNumber, statusVote));
    }
}
