package com.example.csv.services;

import com.example.csv.domain.Dossier;
import com.example.csv.domain.Tiers;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DossierService {
    void saveFile(MultipartFile file);

    List<Dossier> getAllDossiers();

    Dossier getDossier(Long id);

    void delete(Long id);

    Dossier addNewDossier(Dossier dossier);

    boolean update(Dossier dossier);

    public  List<Dossier> findDossierWithSorting(String field);


}
