package com.votation.api.controllers.questions.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Long id;
    private String description;
    private LocalDateTime finalDate;
    private List<VoteResponse> votes;
    private Long totalVotes;

}
