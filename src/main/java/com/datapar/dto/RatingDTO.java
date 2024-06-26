package com.datapar.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private String email;
    private Integer score;
    private String comments;
    private String contactNumber;
    private String contactTime;
    private Boolean contactRequest;
}

