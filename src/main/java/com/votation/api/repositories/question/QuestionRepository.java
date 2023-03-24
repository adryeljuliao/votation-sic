package com.votation.api.repositories.question;

import com.votation.api.models.question.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
