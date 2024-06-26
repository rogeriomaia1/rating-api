package com.datapar.controller;

import com.datapar.dto.DashBoardDTO;
import com.datapar.dto.RatingDTO;
import com.datapar.service.DashBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.datapar.util.Utilities.log;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @Operation(summary = "Obter estatísticas de avaliações", description = "Obter estatísticas de avaliações agrupadas por pontuação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estatísticas recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/score-statistics")
    public ResponseEntity<DashBoardDTO> getScoreStatistics() {
    	
    	log.info("[DashBoardController.getScoreStatistics()] - Inicio chamada GET para obter dados do dashboard.");
    	
    	DashBoardDTO dto = dashBoardService.getScoreStatistics();
    	
    	return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
