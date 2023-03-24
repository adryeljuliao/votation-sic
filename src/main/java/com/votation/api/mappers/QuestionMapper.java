package com.votation.api.mappers;

import com.votation.api.controllers.questions.requests.QuestionRequest;
import com.votation.api.controllers.questions.responses.QuestionResponse;
import com.votation.api.controllers.questions.responses.VoteResponse;
import com.votation.api.models.question.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class QuestionMapper {
    private static final Integer DURATION_DEFAULT = 1;

    public static Question makeQuestion(QuestionRequest questionRequest) {
        if (Objects.nonNull(questionRequest)) {
            var finalDate = getDurationQuestion(questionRequest.getDurationMinutesQuestion());
            return Question.builder()
                    .createdDate(LocalDateTime.now())
                    .description(questionRequest.getDescription())
                    .finalDate(finalDate)
                    .build();
        }

        return null;
    }

    public static Question makeQuestion(QuestionResponse questionResponse) {
        if (Objects.nonNull(questionResponse)) {
            return Question.builder()
                    .createdDate(LocalDateTime.now())
                    .description(questionResponse.getDescription())
                    .finalDate(questionResponse.getFinalDate())
                    .id(questionResponse.getId())
                    .build();
        }

        return null;
    }

    public static QuestionResponse makeQuestionResponse(Question question) {
       if (Objects.nonNull(question)) {
           List<VoteResponse> votesResponse = new ArrayList<>();
           if (Objects.nonNull(question.getVotes()) && !question.getVotes().isEmpty()) {
               question.getVotes().forEach(vote -> {

                   final VoteResponse voteResponse = VoteResponse.builder()
                           .statusVote(vote.getStatusVote())
                           .associateId(vote.getAssociate().getId())
                           .build();

                   votesResponse.add(voteResponse);
               });
           }

           return QuestionResponse.builder()
                   .id(question.getId())
                   .description(question.getDescription())
                   .votes(votesResponse)
                   .totalVotes(Integer.toUnsignedLong(votesResponse.size()))
                   .finalDate(question.getFinalDate())
                   .build();
       }
       return null;
    }

    private static LocalDateTime getDurationQuestion(Integer durationMinutesQuestion) {
        var duration = durationMinutesQuestion > DURATION_DEFAULT ? durationMinutesQuestion : DURATION_DEFAULT;
        return LocalDateTime.now().plusMinutes(duration);
    }

}
