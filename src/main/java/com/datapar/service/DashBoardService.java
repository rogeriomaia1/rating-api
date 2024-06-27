package com.datapar.service;

import java.util.List;
import java.util.Map;
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

    public DashBoardDTO getScoreStatistics() {
        List<Rating> ratings = ratingRepository.findAll();
        
        Map<String, Long> groupedRatings = ratings.stream()
                .collect(Collectors.groupingBy(
                        rating -> convertScoreToString(rating.getScore()),
                        Collectors.counting()
                ));
        
        List<String> scores = groupedRatings.keySet().stream().collect(Collectors.toList());
        List<Long> counts = groupedRatings.values().stream().collect(Collectors.toList());
       
        return new DashBoardDTO(scores, counts);
    }

    private String convertScoreToString(int score) {
        switch (score) {
            case 1:
                return "Péssimo";
            case 2:
                return "Ruim";
            case 3:
                return "Regular";
            case 4:
                return "Bom";
            case 5:
                return "Ótimo";
            default:
                return "Desconhecido";
        }
    }
}
