package com.datapar.controller;

import static com.datapar.util.Utilities.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datapar.dto.RatingDTO;
import com.datapar.exception.RatingException;
import com.datapar.model.Rating;
import com.datapar.service.RatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    

    @Operation(summary = "Criar uma nova avaliação", description = "Cria uma nova avaliação utilizando os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação criada com sucesso",
            content = @Content(schema = @Schema(implementation = Rating.class))),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<RatingDTO> createRating(
        @Parameter(description = "Rating data to be created", required = true) 
        @Valid @RequestBody RatingDTO ratingDTO) throws RatingException {
    	
    	log.info("[RatingController.createRating()] - Inicio chamada POST inserção votação. Request={" + ratingDTO.toString() + "}");
    	
    	RatingDTO dto = ratingService.saveRating(ratingDTO);
        
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Obter todas as avaliações", description = "Recupera uma lista de todas as avaliações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações recuperada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Rating.class))))
    })
    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings() throws RatingException {
    	
    	log.info("[RatingController.getAllRatings()] - Inicio chamada GET para obter votações.");
    	
    	List<RatingDTO> listDto = ratingService.getAllRatings();
		
        return new ResponseEntity<List<RatingDTO>>(listDto, HttpStatus.OK);
    }
    
    @Operation(summary = "Validar acesso por email", description = "Verifica se o email informado já existe na base de dados de avaliações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Email válido"),
        @ApiResponse(responseCode = "404", description = "Email não encontrado")
    })
    @GetMapping("/validate")
    public ResponseEntity<RatingDTO> validateEmailAccess(@RequestParam String email) {
        
    	RatingDTO ratingDTO = ratingService.getRatingByEmail(email);
        
        if (ratingDTO == null) {
            return ResponseEntity.ok(ratingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
