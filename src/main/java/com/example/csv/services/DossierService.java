package com.example.csv.services;

import com.example.csv.DTO.DossierDTO;
import com.example.csv.domain.Dossier;
import com.example.csv.domain.Tiers;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DossierService {
    void saveFile(MultipartFile file);

    List<Dossier> getAllDossiers();

    Dossier getDossier(Long id);

    void delete(Long id);

    Dossier save(Dossier dossier);
    void update(Long id, DossierDTO dto);

}
