package com.example.csv.services;

import com.example.csv.domain.Contrat;
import com.example.csv.domain.Dossier;
import com.example.csv.domain.Tiers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContratService {
    Contrat save(Contrat contrat);

    void saveFile(MultipartFile file);

    public List<Contrat> getAllContrat();

    Contrat getContrat(Long id);

    void delete(Long id);
    boolean update(Contrat contrat);
}
