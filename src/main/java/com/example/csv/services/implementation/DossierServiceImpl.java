package com.example.csv.services.implementation;

import com.example.csv.domain.Dossier;
import com.example.csv.helper.CSVHelper;
import com.example.csv.repositories.DossierRepository;
import com.example.csv.services.DossierService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DossierServiceImpl implements DossierService {
    @Autowired
    private final DossierRepository dosRepo;

    @Override
    public void saveFile(MultipartFile file) {
        try {
            List<Dossier> dossiers = CSVHelper.csvToDossiers(file.getInputStream());
            dosRepo.saveAll(dossiers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Dossier> getAllDossiers() {
        return dosRepo.findAll();
    }

    @Override
    public Dossier getDossier(Long id) {
        return dosRepo.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        dosRepo.deleteById(id);
    }

    @Override
    public Dossier save(Dossier dossier) {
        Dossier dossier1 = dosRepo.save(dossier);
        return dossier1;
    }



}
