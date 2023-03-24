package com.votation.api.controllers.questions.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionRequest {

    @NotBlank(message = "Campo não pode ser nulo ou vazio")
    private String description;

    @Min(value = 1, message = "O valor minimo deve ser maior ou igual a 1")
    private Integer durationMinutesQuestion;
}
