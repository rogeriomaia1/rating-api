package com.datapar.service;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapar.dto.RatingDTO;
import com.datapar.enums.ErrorCodeEnum;
import com.datapar.exception.RatingException;
import com.datapar.mapper.RatingMapper;
import com.datapar.model.Rating;
import com.datapar.repository.IRatingRepository;
import static com.datapar.util.Utilities.log;

@Service
public class RatingService {

    @Autowired
    private IRatingRepository ratingRepository;

    @Autowired
    private RatingMapper ratingMapper;

       
    public RatingDTO saveRating(RatingDTO ratingDTO) throws RatingException {
       
    	log.info("[RatingService.saveRating()] - Inicio da tratamento para persistencia da votação");
    	
        this.validations(ratingDTO);
        
        Rating rating = ratingMapper.toEntity(ratingDTO);
        
        Rating ratingSaved = null;
        try {
        	
        	log.info("[RatingService.saveRating()] - Chamdada do repository");
        	
        	ratingSaved = ratingRepository.save(rating);
        	return  ratingMapper.toDTO(ratingSaved);
        	
        } catch (Exception e) {
        	log.error("[RatingService.saveRating()] - Erro ao tentar persistir a votação.");
        	throw new RatingException(ErrorCodeEnum.ER0001.getMessage(), ErrorCodeEnum.ER0001.getCode());
        }
       
    }

	private void validations(RatingDTO ratingDTO) {
		
		log.info("[RatingService.validations()] - Realizando as validações");
		
		if (ratingDTO.getScore() <= 2) {
        	ratingDTO.setContactRequest(true);
        } else {
        	ratingDTO.setContactRequest(false);
        }
		
	}

    public List<RatingDTO> getAllRatings() throws RatingException {
        
    	log.info("[RatingService.getAllRatings()] - Obtendo as votações");
    	
    	var list = ratingRepository.findAll();
    	
		if (list.isEmpty()) {
			log.error("[RatingService.getAllRatings()] - A lista de votações está vazia");
			
			throw new RatingException(ErrorCodeEnum.ER0002.getMessage(), ErrorCodeEnum.ER0002.getCode());
		}
		
		List<RatingDTO> listDto = new ArrayList<>();
		
		list.forEach(tf -> {
			listDto.add(ratingMapper.toDTO(tf)); 
		});
        
    	return listDto;
    }

    public RatingDTO getRatingByEmail(String email) {
    	
      	log.info("[RatingService.getRatingById()] - Obtendo votação por email");
    	
      	Optional<Rating> op = ratingRepository.findByEmail(email);
    	
    	if (op.isPresent()) {
    		return ratingMapper.toDTO(op.get());
    	} else {
    		return null;
    	}
	    	
    }
}
