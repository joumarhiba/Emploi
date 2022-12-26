package com.emploi.service;

import com.emploi.exception.UserNotFoundException;
import com.emploi.model.Offre;
import com.emploi.repository.OffreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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
    public void deleteOffre(Long id){
        offreRepo.deleteOffreById(id);
        return;
    }
    public Offre updateOffre(Offre offre){
        return offreRepo.save(offre);
    }

}
