package com.datapar.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datapar.dto.LoginRequestDTO;
import com.datapar.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import static com.datapar.util.Utilities.log;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Autenticação de Usuário", description = "Endpoint para autenticar o usuário com base no email e senha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Schema(description = "Login credentials", required = true)
            @RequestBody LoginRequestDTO loginRequest) {

    	log.info("[LoginController.login()] - Inicio chamada POST da autenticação do ADM. Email={" + loginRequest.getEmail() + "}");

        boolean isAuthenticated = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "success");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
