package com.datapar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapar.dto.DashBoardDTO;
import com.datapar.model.Rating;
import com.datapar.repository.IRatingRepository;

@Service
public class DashBoardService {

    @Autowired
    private IRatingRepository ratingRepository;

    public List<DashBoardDTO> getScoreStatistics() {
        List<Rating> ratings = ratingRepository.findAll();

        return ratings.stream()
                .collect(Collectors.groupingBy(Rating::getScore, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new DashBoardDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
