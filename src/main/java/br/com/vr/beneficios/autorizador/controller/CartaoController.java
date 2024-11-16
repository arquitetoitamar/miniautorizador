package br.com.vr.beneficios.autorizador.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.vr.beneficios.autorizador.dto.CartaoRequest;
import br.com.vr.beneficios.autorizador.dto.CartaoResponse;
import br.com.vr.beneficios.autorizador.mapper.CartaoMapper;
import br.com.vr.beneficios.autorizador.model.cartao.Cartao;
import br.com.vr.beneficios.autorizador.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
@Tag(name = "Cartões", description = "Operações relacionadas a cartões")
public class CartaoController {

    private final CartaoService cartoesService;

    public CartaoController(CartaoService cartaoService) {
        this.cartoesService = cartaoService;
    }

    @Operation(summary = "Criar um novo cartão", description = "Cria um cartão com saldo inicial de R$500,00")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão criado com sucesso",
                    content = @Content(schema = @Schema(implementation = CartaoResponse.class))),
            @ApiResponse(responseCode = "422", description = "Cartão já existe")
    })
    @PostMapping
    public ResponseEntity<CartaoResponse> criarCartao(@Valid @RequestBody CartaoRequest request) {
        Optional<Cartao> cartaoExistente = cartoesService.buscarCartao(request.getNumeroCartao());
        if (cartaoExistente.isPresent()) {
            CartaoResponse response = CartaoMapper.toResponse(cartaoExistente.get());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        CartaoResponse response = cartoesService.criarCartao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Consultar saldo do cartão", description = "Retorna o saldo atual do cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo consultado com sucesso",
                    content = @Content(schema = @Schema(implementation = CartaoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @GetMapping("/{numeroCartao}")
    public ResponseEntity<CartaoResponse> obterSaldo(@PathVariable String numeroCartao) {
        Optional<Cartao> cartao = cartoesService.buscarCartao(numeroCartao);
        return cartao.map(c -> ResponseEntity.ok(CartaoMapper.toResponse(c)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
