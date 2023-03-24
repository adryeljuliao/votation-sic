package com.votation.api.controllers.associates;

import com.votation.api.controllers.associates.requests.AssociateRequest;
import com.votation.api.controllers.associates.responses.AssociateResponse;
import com.votation.api.helpers.MaskSensitiveData;
import com.votation.api.services.associate.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/associates")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService associateService;

    @GetMapping("/{associateId}")
    @Operation(description = "Responsável por buscar os dados de um associado com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso, retorna os dados do associado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<AssociateResponse> findAssociateById(@PathVariable Long associateId) {
        log.info("m=findById associateId={}", associateId);
        final AssociateResponse associate = associateService.findAssociateResponseById(associateId);
        return ResponseEntity.ok(associate);
    }

    @PostMapping
    @Operation(description = "Responsável por salvar um associado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso, retorna os dados do associado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<AssociateResponse> createAssociate(@RequestBody AssociateRequest associateResponse) {
        log.info("m=createAssociate documentNumber={}", MaskSensitiveData.mask(associateResponse.getDocumentNumber()));
        final AssociateResponse associate = associateService.saveAssociate(associateResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(associate);
    }
}
