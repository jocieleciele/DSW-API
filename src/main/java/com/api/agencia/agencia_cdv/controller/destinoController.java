package com.api.agencia.agencia_cdv.controller;

import com.api.agencia.agencia_cdv.model.destino;
import com.api.agencia.agencia_cdv.service.destinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para a API de Destinos de Viagem.
 * Define os endpoints e os métodos HTTP para as operações.
 */
@RestController
@RequestMapping("/api/destinos")
public class destinoController{

    private final destinoService destinoService;

    public destinoController(destinoService destinoService) {
        this.destinoService = destinoService;
    }

    // 1. Endpoint de Cadastro de Destinos de Viagem (POST)
    @PostMapping
    public ResponseEntity<destino> cadastrarDestino(@RequestBody destino novoDestino) {
        if (novoDestino.getNome()== null || novoDestino.getLocalizacao() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e localização do destino são obrigatórios.");
        }
        destino destinoCriado = destinoService.cadastrar(novoDestino);
        // Retorna 201 Created
        return new ResponseEntity<>(destinoCriado, HttpStatus.CREATED);
    }

    // 2. Endpoint de Listagem de Destinos de Viagem (GET)
    // 3. Endpoint de Pesquisa de Destinos (GET com parâmetro 'q')
    @GetMapping
    public ResponseEntity<List<destino>> listarOuPesquisarDestinos(@RequestParam(required = false) String q) {
        List<destino> destinos = destinoService.buscarPorTermo(q);
        // Retorna 200 OK
        return ResponseEntity.ok(destinos);
    }

    // 4. Endpoint de Visualização de Informações Detalhadas (GET por ID)
    @GetMapping("/{id}")
    public ResponseEntity<destino> buscarDestinoPorId(@PathVariable Long id) {
        return destinoService.buscarPorId(id)
                // Retorna 200 OK
                .map(ResponseEntity::ok)
                // Retorna 404 Not Found se o destino não existir
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // 5. Endpoint de Avaliação de Destino de Viagens (PATCH)
    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<destino> avaliarDestino(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        Integer nota = payload.get("nota");

        if (nota == null || nota < 1 || nota > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nota deve ser um valor inteiro entre 1 e 10.");
        }

        return destinoService.avaliarDestino(id, nota)
                // Retorna 200 OK com o destino atualizado
                .map(ResponseEntity::ok)
                // Retorna 404 Not Found se o destino não existir
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // 6. Endpoint de Exclusão de Destinos de Viagem (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id){
        boolean excluido = destinoService.excluir(id);

        if (excluido) {
            // Retorna 204 No Content se a exclusão for bem-sucedida
            return ResponseEntity.noContent().build();
        } else {
            // Retorna 404 Not Found se o destino não for encontrado para exclusão
            return ResponseEntity.notFound().build();
        }
    }
}