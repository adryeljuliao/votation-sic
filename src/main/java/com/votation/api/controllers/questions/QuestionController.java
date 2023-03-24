package com.votation.api.controllers.questions;

import com.votation.api.controllers.questions.requests.QuestionRequest;
import com.votation.api.controllers.questions.responses.QuestionResponse;
import com.votation.api.services.question.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @Operation(description = "Responsável por criar uma pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso, retorna os dados da pauta"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody @Valid QuestionRequest questionRequest) {
        log.info("m=createQuestion questionRequest={}", questionRequest);
        final QuestionResponse question = questionService.createQuestion(questionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping("/{questionId}")
    @Operation(description = "Responsável por buscar uma pauta com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso, retorna os dados da pauta"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<QuestionResponse> getQuestionWithVote(@PathVariable Long questionId) {
        log.info("m=getQuestionWithVote questionId={}", questionId);
        final QuestionResponse questionWithVote = questionService.findQuestionById(questionId);
        return ResponseEntity.ok(questionWithVote);
    }


}
