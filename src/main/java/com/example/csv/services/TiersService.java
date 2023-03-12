package com.example.csv.services;

import com.example.csv.domain.Tiers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TiersService {
//    public List<Tiers> searchTiers(String numero, String nom, String siren, String refMandat);
//
    Tiers save(Tiers tiers);

    void saveFile(MultipartFile file);

    List<Tiers> getAllTiers();

    Tiers getTiers(Long id);

    void delete(Long id);



    boolean update(Tiers tiers);
    List<Tiers> getAllTiers(Integer pageNo, Integer pageSize, String sortBy);

}
