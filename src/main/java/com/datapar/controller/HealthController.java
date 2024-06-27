package com.datapar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class HealthController {

	@Operation(summary = "Verificar status da aplicação", description = "Verifica se a aplicação está em execução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aplicação está em execução")
    })
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.status(HttpStatus.OK).body("Application is running!");
    }
}
