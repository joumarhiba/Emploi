package com.emploi.service;

import com.emploi.exception.UserNotFoundException;
import com.emploi.model.Offre;
import com.emploi.repository.OffreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffreService {
    private final OffreRepo offreRepo;

    public Offre addOffre(Offre offre){
       return offreRepo.save(offre);
    }

    public Offre findOffreById(Long id) {
        return offreRepo.findById(id).orElseThrow(()-> new UserNotFoundException("this offre not found"));
    }

    public List<Offre> findAllOffres(){
        return offreRepo.findAll();
    }
    public String deleteOffre(Long id){
        offreRepo.deleteOffreById(id);
        return "L'offre "+id+ " est supprimée ...";
    }
    public Offre updateOffre(Offre offre){
        return offreRepo.save(offre);
    }

    public Offre findByTitle(String title){
        return offreRepo.findByTitle(title);
    }

}
