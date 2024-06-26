package com.datapar.controller;

import com.datapar.dto.DashBoardDTO;
import com.datapar.service.DashBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @Operation(summary = "Get rating statistics", description = "Get statistics of ratings grouped by score")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved statistics"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/score-statistics")
    public List<DashBoardDTO> getScoreStatistics() {
        return dashBoardService.getScoreStatistics();
    }
}
