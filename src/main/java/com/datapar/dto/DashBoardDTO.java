package com.datapar.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashBoardDTO {
    private List<String> listScore;
    private List<Long> listCount;
}

