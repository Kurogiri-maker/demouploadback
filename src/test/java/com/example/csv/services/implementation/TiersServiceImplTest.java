package com.example.csv.services.implementation;

import com.example.csv.repositories.DossierRepository;
import com.example.csv.repositories.TiersRepository;
import com.example.csv.services.DossierService;
import com.example.csv.services.TiersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

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
    void save() {
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