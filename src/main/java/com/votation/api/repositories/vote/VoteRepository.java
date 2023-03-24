package com.votation.api.repositories.vote;

import com.votation.api.models.vote.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VoteRepository extends CrudRepository<Vote, Long> {

     Optional<Vote> findVoteByAssociateIdAndQuestionId(Long associateId, Long questionId);
}
