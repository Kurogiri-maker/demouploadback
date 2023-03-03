package com.example.csv.services.implementation;

import com.example.csv.domain.Tiers;
import com.example.csv.helper.CSVHelper;
import com.example.csv.repositories.TiersRepository;
import com.example.csv.services.TiersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class TiersServiceImpl implements TiersService {

    @Autowired
    private final TiersRepository tiersRepo;

@Override
    public List<Tiers> searchTiers(String numero, String nom, String siren, String refMandat) {
        Specification<Tiers> specification = Specification.where(null);

        if (numero != null && !numero.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("numero"), "%" + numero + "%"));
        }

        if (nom != null && !nom.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("nom"),  nom ));
        }

        if (siren != null && !siren.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("siren"), "%" + siren + "%"));
        }

        if (refMandat != null && !refMandat.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("refMandat"), "%" + refMandat + "%"));
        }

        return tiersRepo.findAll(specification);
    }



@Override
    public Tiers save(Tiers tiers) {

        return tiersRepo.save(tiers);
    }

    @Override
    public void saveFile(MultipartFile file) {

        try {
            List<Tiers> tiers = CSVHelper.csvToTiers(file.getInputStream());
            tiersRepo.saveAll(tiers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tiers> getAllTiers() {
        return tiersRepo.findAll();
    }

    @Override
    public Tiers getTiers(Long id) {
        return tiersRepo.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        tiersRepo.deleteById(id);
    }




}
