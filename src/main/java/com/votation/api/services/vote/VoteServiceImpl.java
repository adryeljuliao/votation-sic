package com.votation.api.services.vote;

import com.votation.api.controllers.associates.responses.AssociateResponse;
import com.votation.api.controllers.questions.responses.QuestionResponse;
import com.votation.api.exceptions.BadRequestException;
import com.votation.api.helpers.MaskSensitiveData;
import com.votation.api.mappers.AssociateMapper;
import com.votation.api.mappers.QuestionMapper;
import com.votation.api.models.associate.Associate;
import com.votation.api.models.question.Question;
import com.votation.api.models.vote.Vote;
import com.votation.api.repositories.vote.VoteRepository;
import com.votation.api.services.associate.AssociateService;
import com.votation.api.services.question.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public non-sealed class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final QuestionService questionService;
    private final AssociateService associateService;

    @Override
    public void createVote(Long questionId, String documentNumber, Boolean statusVote) {
        log.info("m=createVote documentNumber={}, questionId={}",
                MaskSensitiveData.mask(documentNumber),
                questionId
        );

        final QuestionResponse questionResponse = questionService.findQuestionById(questionId);
        final AssociateResponse associateResponse = associateService.findAssociateByDocumentNumber(documentNumber);
        final Vote votePersisted = voteRepository.findVoteByAssociateIdAndQuestionId(associateResponse.getId(), questionResponse.getId())
                .orElse(null);

        validateVotation(votePersisted, questionResponse);

        voteRepository.save(makeVote(questionResponse, associateResponse, statusVote));
    }

    private void validateVotation(Vote vote, QuestionResponse question) {
        var currentDateTime = LocalDateTime.now();

        if (currentDateTime.isAfter(question.getFinalDate())) {
            log.error("m=validateVotation PERMISSION DENIED TO VOTE, VOTING TIME EXCEEDED");
            throw new BadRequestException("Não é permitido votar, tempo de votação excedido");
        }

        if (Objects.nonNull(vote)) {
            log.error("m=validateVotation NOT ALLOWED TO VOTE MORE THAN ONCE ON THE SAME QUESTION");
            throw new BadRequestException("Não é permitido votar mais de uma vez na mesma pauta");
        }

    }


    private Vote makeVote(QuestionResponse questionResponse, AssociateResponse associateResponse, Boolean status) {
        final Question question = QuestionMapper.makeQuestion(questionResponse);
        final Associate associate = AssociateMapper.makeAssociate(associateResponse);
        return Vote.builder()
                .question(question)
                .associate(associate)
                .statusVote(status)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
