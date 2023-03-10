package com.example.csv.services.implementation;

import com.example.csv.domain.Dossier;
import com.example.csv.helper.CSVHelper;
import com.example.csv.repositories.DossierRepository;
import com.example.csv.services.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DossierServiceImpl implements DossierService {

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
    public Dossier addNewDossier(Dossier dossier) {
        return dosRepo.save(dossier);
    }


    @Override
    public boolean update(Dossier updatedDossier) {

        // save the updated version
        dosRepo.save(updatedDossier);
        return true;
    }

    @Override
    public List<Dossier> findDossierWithSorting(String field) {
        return dosRepo.findAll(Sort.by(Sort.Direction.ASC,field));
    }


}
