package com.votation.api.controllers.votations.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteRequest {

    private Long questionId;
    private String documentNumber;

    private Boolean statusVote;
}
