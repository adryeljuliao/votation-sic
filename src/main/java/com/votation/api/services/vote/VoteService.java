package com.votation.api.services.vote;

public sealed interface VoteService permits VoteServiceImpl{
    void createVote(Long questionId, String documentNumber, Boolean statusVote);
}
