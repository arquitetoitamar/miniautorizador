package br.com.vr.beneficios.autorizador.controller;

import br.com.vr.beneficios.autorizador.dto.TransacaoRequest;
import br.com.vr.beneficios.autorizador.dto.TransacaoResponse;
import br.com.vr.beneficios.autorizador.service.AutorizacaoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transacoes", description = "Operações relacionadas a transacoes")
public class TransacaoController {

    private final AutorizacaoService autorizacaoService;

    public TransacaoController(AutorizacaoService autorizacaoService) {
        this.autorizacaoService = autorizacaoService;
    }

    @Operation(summary = "Realizar uma transação", description = "Autoriza uma transação com validação de saldo e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso",
                    content = @Content(schema = @Schema(implementation = TransacaoResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na transação",
                    content = @Content(schema = @Schema(implementation = TransacaoResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TransacaoResponse> realizarTransacao(@Valid @RequestBody TransacaoRequest request) {
        // Chama o método autorizarTransacao e recebe o TransacaoResponse diretamente
        TransacaoResponse response = autorizacaoService.validarTransacao(request);
        // Define o status HTTP com base no resultado da transação
        HttpStatus status = "OK".equals(response.getStatus()) ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status).body(response);
    }
}
