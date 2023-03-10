package com.example.csv.services.implementation;

import com.example.csv.domain.Contrat;
import com.example.csv.domain.Dossier;
import com.example.csv.repositories.ContratRepository;
import com.example.csv.repositories.DossierRepository;
import com.example.csv.services.ContratService;
import com.example.csv.services.DossierService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest

@Slf4j

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DossierServiceImplTest {



    @Autowired
    private DossierService dossierService ;
    @Order(1)
    @Test
    void save() {
        int size = dossierService.getAllDossiers().size();
        int expected = size+1;
        //given

        Dossier dossier = new Dossier(
                null,
                "hbvba",
                "1",
                "ikhog",
                "ihynr",
                "jxudn");

        //then
        dossierService.save(dossier);



        assertEquals(expected,dossierService.getAllDossiers().size());

        long id = dossierService.getDossier(dossier.getId()).getId();
        log.info("id:"+id);
        dossierService.delete(id);

    }
    @Test
    void getDossier() {
        Dossier dossier = new Dossier(
                null,
                "hbvba",
                "1",
                "ikhog",
                "ihynr",
                "jxudn");

        //then
        Dossier dossier1=dossierService.save(dossier);

        Long id_expected = dossier1.getId();

        Long actual= dossierService.getDossier(id_expected).getId();


        assertEquals(id_expected, actual);
        log.info(" id contrat"+ id_expected);

        dossierService.delete(id_expected);

    }

    @Test
    void update() {


        Dossier dossier = new Dossier(
                null,
                "hbvba",
                "1",
                "ikhog",
                "ihynr",
                "jxudn");

        //then
        Dossier dossier1= dossierService.save(dossier);

        Long id = dossier1.getId();
        Dossier updatedDossier= new Dossier(
                id,
                "hbvba",
                "5",
                "ikhog",
                "ihynr",
                "jxudn");




        log.info("id object1 : "+id);

        //   log.info("id object2 : "+updatedContrat.getId());

        // log.info("numCpobjet 2"+ updatedContrat.getNum_CP() );
        // log.info("numCpobjet 2"+ contrat.getNum_CP() );


        dossierService.update(updatedDossier);

        assertEquals("5",dossierService.getDossier(id).getNumero());
        // log.info("hhhhhhhhhhhhhhh"+ updatedContrat.getNum_CP() );


        dossierService.delete(id);



    }
    @Test
    void delete() {
        int size = dossierService.getAllDossiers().size();
        log.info("avantsupp"+size);
        Dossier dossier = new Dossier(
                null,
                "hbvba",
                "1",
                "ikhog",
                "ihynr",
                "jxudn");

        //then
        Dossier dossier1= dossierService.save(dossier);

        Long id = dossier1.getId();

        log.info("id object1 : "+id);


        dossierService.delete(id);


        log.info("contrat size : "+dossierService.getAllDossiers().size());

        assertEquals(size,dossierService.getAllDossiers().size());


    }



    @Test
    void saveFile() {
        int size = dossierService.getAllDossiers().size();
        int expected= size+1;

        String csvContent = "dossier_DC,Numero,ListSDC,N_DPS,Montant_du_pres\n"+
                "hbvba,1,ikhog,ihynr,jxudn\n";

        MultipartFile csvFile = new MockMultipartFile("test.csv", csvContent.getBytes(StandardCharsets.UTF_8));
        List<Dossier> dossiers= new ArrayList<>();
        dossiers.add(new Dossier(
                null,
                "hbvba",
                "1",
                "ikhog",
                "ihynr",
                "jxudn"

        ));
        dossierService.saveFile(csvFile);

        assertEquals(expected,dossierService.getAllDossiers().size());






    }

    @Test
    void testSaveFileWithIOException() throws Exception {
        // create a mock MultipartFile that throws an IOException when its input stream is accessed
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getInputStream()).thenThrow(new IOException("File could not be read"));

        // verify that a RuntimeException is thrown when the file is saved
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            dossierService.saveFile(mockFile);
        });

        String expectedMessage = "File could not be read";
        String actualMessage = exception.getCause().getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
        @Test
    void getAllDossiers() {

            // create some test data
            Dossier dossier1 = new Dossier(31l, "hbvba", "1", "ikhog", "ihynr", "jxudn");
            Dossier dossier2= new Dossier(32l, "hbvba", "1", "ikhog", "ihynr", "jxudn");
            List<Dossier> expectedDossier = new ArrayList<>();
            expectedDossier.add(dossier1);
            expectedDossier.add(dossier2);
            // create a mock repository and configure it to return the test data
            DossierRepository mockRepo = mock(DossierRepository.class);
            when(mockRepo.findAll()).thenReturn(expectedDossier);

            // create a service instance with the mock repository
            DossierServiceImpl dossierService = new DossierServiceImpl(mockRepo);

            // call the method and verify the results
            List<Dossier> actualDossier = dossierService.getAllDossiers();
            assertEquals(expectedDossier, actualDossier);
            verify(mockRepo).findAll();



    }



}