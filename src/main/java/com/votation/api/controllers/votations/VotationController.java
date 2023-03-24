package com.votation.api.controllers.votations;

import com.votation.api.controllers.votations.requests.VoteRequest;
import com.votation.api.helpers.MaskSensitiveData;
import com.votation.api.services.vote.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/votations")
@RequiredArgsConstructor
public class VotationController {

    private final VoteService voteService;

    @PostMapping
    @Operation(description = "Responsável por realizar votações em uma pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<Void> createVote(@RequestBody VoteRequest voteRequest) {
        final String maskedDocumentNumber = MaskSensitiveData.mask(voteRequest.getDocumentNumber());
        log.info("m=createVote questionId={}, documentNumer={}, statusVote={}", voteRequest.getQuestionId(), maskedDocumentNumber, voteRequest.getStatusVote());
        voteService.createVote(voteRequest.getQuestionId(), voteRequest.getDocumentNumber(), voteRequest.getStatusVote());
        return ResponseEntity.noContent().build();
    }
}
