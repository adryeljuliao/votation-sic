package com.votation.api.controllers.questions.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteResponse {

    private Long associateId;
    private Boolean statusVote;
}
