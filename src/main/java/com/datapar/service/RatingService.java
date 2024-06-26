package com.datapar.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapar.dto.RatingDTO;
import com.datapar.mapper.RatingMapper;
import com.datapar.model.Rating;
import com.datapar.repository.IRatingRepository;

@Service
public class RatingService {

    @Autowired
    private IRatingRepository ratingRepository;

    @Autowired
    private RatingMapper ratingMapper;

       
    public Rating saveRating(RatingDTO ratingDTO) {
       
        this.validations(ratingDTO);
        Rating rating = ratingMapper.toEntity(ratingDTO);
        
        return ratingRepository.save(rating);
    }

	private void validations(RatingDTO ratingDTO) {
		
		if (ratingDTO.getScore() <= 2) {
        	ratingDTO.setContactRequest(true);
        } else {
        	ratingDTO.setContactRequest(false);
        }
	}

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }
}
