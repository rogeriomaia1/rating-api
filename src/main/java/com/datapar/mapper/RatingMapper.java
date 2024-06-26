package com.datapar.mapper;

import org.springframework.stereotype.Component;

import com.datapar.dto.RatingDTO;
import com.datapar.model.Rating;

@Component
public class RatingMapper {
	
	public Rating toEntity(RatingDTO dto) {
        return new Rating(
        		null,
        		dto.getEmail(),
        		dto.getScore(),
        		dto.getComments(),
        		dto.getContactNumber(),
        		dto.getContactTime(),
        		dto.getContactRequest()
        );
    }
	
	public RatingDTO toDTO(Rating entity) {
        return new RatingDTO(
        		entity.getEmail(),
        		entity.getScore(),
        		entity.getComments(),
        		entity.getContactNumber(),
        		entity.getContactTime(),
        		entity.getContactRequest()
        );
    }

}
