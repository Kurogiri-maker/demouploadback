package com.example.csv.services.implementation;

import com.example.csv.domain.Dossier;
import com.example.csv.helper.CSVHelper;
import com.example.csv.repositories.DossierRepository;
import com.example.csv.services.DossierService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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


    @Override
    public boolean update(Dossier updatedDossier) {
        Dossier toUpdate = dosRepo.findById(updatedDossier.getId()).orElse(null);
        if (toUpdate == null) { return false; }

        // save the updated version
        dosRepo.save(updatedDossier);
        return true;
    }

    @Override
    public List<Dossier> findDossierWithSorting(String field) {
        return dosRepo.findAll(Sort.by(Sort.Direction.ASC,field));
    }


}
