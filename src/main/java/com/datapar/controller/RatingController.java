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
    

    @Operation(summary = "Create a new rating", description = "Creates a new rating using the provided data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rating successfully created",
            content = @Content(schema = @Schema(implementation = Rating.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<RatingDTO> createRating(
        @Parameter(description = "Rating data to be created", required = true) 
        @Valid @RequestBody RatingDTO ratingDTO) throws RatingException {
    	
    	log.info("[RatingController.createRating()] - Inicio chamada POST inserção votação. Request={" + ratingDTO.toString() + "}");
    	
    	RatingDTO dto = ratingService.saveRating(ratingDTO);
        
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Get all ratings", description = "Retrieves a list of all ratings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of ratings retrieved successfully",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Rating.class))))
    })
    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings() throws RatingException {
    	
    	log.info("[RatingController.getAllRatings()] - Inicio chamada GET para obter votações.");
    	
    	List<RatingDTO> listDto = ratingService.getAllRatings();
		
        return new ResponseEntity<List<RatingDTO>>(listDto, HttpStatus.OK);
    }
    
   
    @GetMapping("/validate")
    public ResponseEntity<RatingDTO> validateAccess(@RequestParam String email) {
        
    	RatingDTO ratingDTO = ratingService.getRatingByEmail(email);
        
        if (ratingDTO == null) {
            return ResponseEntity.ok(ratingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
