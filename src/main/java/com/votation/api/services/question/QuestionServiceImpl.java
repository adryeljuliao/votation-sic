package com.votation.api.services.question;

import com.votation.api.controllers.questions.requests.QuestionRequest;
import com.votation.api.controllers.questions.responses.QuestionResponse;
import com.votation.api.exceptions.NotFoundException;
import com.votation.api.mappers.QuestionMapper;
import com.votation.api.models.question.Question;
import com.votation.api.repositories.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public non-sealed class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public QuestionResponse createQuestion(QuestionRequest questionRequest) {
        log.info("m=createQuestion questionRequest={}", questionRequest);
        final Question question = Objects.requireNonNull(QuestionMapper.makeQuestion(questionRequest));
        final Question questionPersisted = questionRepository.save(question);
        return QuestionMapper.makeQuestionResponse(questionPersisted);
    }

    @Override
    public QuestionResponse findQuestionById(Long id) {
        log.info("m=findQuestionById questionId={}", id);
        final Question question = questionRepository.findById(id).orElseThrow(() -> {
            log.error("m=findQuestionById questionId={} NOT FOUND", id);
            throw new NotFoundException("QuestionId Not Found");
        });

        return QuestionMapper.makeQuestionResponse(question);
    }

}
