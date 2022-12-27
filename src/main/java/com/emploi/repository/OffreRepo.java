package com.emploi.repository;

import com.emploi.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface OffreRepo extends JpaRepository<Offre, Long> {

    Offre findByProfil(String profil);
}
