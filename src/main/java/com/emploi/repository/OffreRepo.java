package com.emploi.repository;

import com.emploi.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepo extends JpaRepository<Offre, Long> {
    void deleteOffreById(Long id);

    Offre findByTitle(String title);
}
