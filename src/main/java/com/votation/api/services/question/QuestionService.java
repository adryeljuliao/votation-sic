package com.votation.api.services.question;

import com.votation.api.controllers.questions.requests.QuestionRequest;
import com.votation.api.controllers.questions.responses.QuestionResponse;

public sealed interface QuestionService permits QuestionServiceImpl {

    QuestionResponse createQuestion(QuestionRequest questionRequest);

    QuestionResponse findQuestionById(Long id);

}
