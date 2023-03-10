package com.example.csv.services.implementation;

import com.example.csv.domain.Tiers;
import com.example.csv.repositories.DossierRepository;
import com.example.csv.repositories.TiersRepository;
import com.example.csv.services.DossierService;
import com.example.csv.services.TiersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class TiersServiceImplTest {
    @Mock
    private TiersRepository tiersRepository;
    private TiersService tiersService;


    @BeforeEach
    void setUp() {
        tiersService= new TiersServiceImpl(tiersRepository);
    }

    @Test
    void searchTiers() {
    }

    @Test
    void saveTiers(){
        Tiers t = new Tiers(1L,"1","Iheb","iheb.cherif99@gmail.com","cherif");
        List<Tiers> list = new ArrayList<>();
//        list.add(t);
//        when(tiersRepo.save(t)).thenReturn(t);
        Tiers t1 = tiersService.save(t);

        //when(tiersRepo.findAll()).thenReturn(list);
        List<Tiers> result = tiersService.getAllTiers();

        assertNotNull(t1);


        assertEquals(1,result.size());


    }
    @Test
    void saveFile() {
    }

    @Test
    void getAllTiers() {
    }

    @Test
    void getTiers() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void testGetAllTiers() {
    }
}