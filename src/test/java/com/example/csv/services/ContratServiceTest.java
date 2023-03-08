package com.example.csv.services;

import antlr.ASTNULLType;
import com.example.csv.domain.Contrat;
import com.example.csv.repositories.ContratRepository;
import com.example.csv.services.implementation.ContratServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContratServiceTest {



    @Autowired
    private ContratService contratService ;
    @Test
    @Order(1)
    void save() {
        int size = contratService.getAllContrat().size();
        int expected = size+1;
        //given

        Contrat contrat = new Contrat(
                null,
                "num_dossier",
                "num_cp",
                "raison_Social",
                "id_Tiers",
                "num_dc",
                "num_sdc",
                "num_cir",
                "num_siren",
                "ref_coll",
                "code_produit",
                "id_de_offre_comm",
                "chef_de_file",
                "num_ovi",
                "num_rum",
                "typeenregie",
                "produit_comm",
                "produit",
                "phase",
                "montant_pret");

        //then
        contratService.save(contrat);



        assertEquals(expected,contratService.getAllContrat().size());

    }


    @Test
    void testSaveFile()  {
        // Arrange

        int size = contratService.getAllContrat().size();
        int expected= size+1;

        String csvContent = "Num_dossierKPS,Num_CP,Raison_Social,Id_Tiers,Num_DC,Num_SDC,Num_CIR,Num_SIREN,Ref_Collaborative,Code_Produit,Identifiant_de_offre_comm,Chef_de_File,Num_OVI,Num_RUM,TypeEnergie,Produit_Comm,Produit,Phase,Montant_pret\n" +
                "dfydn,1,ztfop,amgqv,fmkzu,zqmyl,bfixm,yyvwp,vegzu,ixrrl,wmeoi,dcosp,wpinz,nliuy,impvq,uljpk,blcbp,poocm,yobnt\n";
        MultipartFile csvFile = new MockMultipartFile("test.csv", csvContent.getBytes(StandardCharsets.UTF_8));
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(  new Contrat(
                null,
                "dfydn",
                "1",
                "ztfop",
                "amgqv",
                "fmkzu",
                "zqmyl",
                "bfixm",
                "yyvwp",
                "vegzu",
                "ixrrl",
                "wmeoi",
                "dcosp",
                "wpinz",
                "nliuy",
                "impvq",
                "uljpk",
                "blcbp",
                "poocm",
                "yobnt"));




        // Act

        contratService.saveFile(csvFile);



        // Contrat c = contratService.getContrat(0L);


        // Assert
        // assertEquals(contrats.get(0),c);

        assertEquals(expected,contratService.getAllContrat().size());
        //log

        log.info("expected   "+ contrats.get(0).getNum_CIR());
        log.info("actual   "+ contratService.getAllContrat().get(0).getNum_CIR());





    }

    @Test
    void testSaveFileWithIOException() throws Exception {
        // create a mock MultipartFile that throws an IOException when its input stream is accessed
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getInputStream()).thenThrow(new IOException("File could not be read"));

        // verify that a RuntimeException is thrown when the file is saved
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            contratService.saveFile(mockFile);
        });

        // verify that the exception message is correct
        String expectedMessage = "File could not be read";
        String actualMessage = exception.getCause().getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void testGetAllContrat() {
        // create some test data
        Contrat contrat1 = new Contrat(31L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");
        Contrat contrat2= new Contrat(32L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");

        List<Contrat> expectedContrats = new ArrayList<>();
        expectedContrats.add(contrat1);
        expectedContrats.add(contrat2);
        // create a mock repository and configure it to return the test data
        ContratRepository mockRepo = mock(ContratRepository.class);
        when(mockRepo.findAll()).thenReturn(expectedContrats);

        // create a service instance with the mock repository
        ContratServiceImpl contratService = new ContratServiceImpl(mockRepo);

        // call the method and verify the results
        List<Contrat> actualContrats = contratService.getAllContrat();
        assertEquals(expectedContrats, actualContrats);
        verify(mockRepo).findAll();
    }
    @Test
    void testGetContrat() {
        Contrat contrat = new Contrat(
                null,
                "num_dossier",
                "num_cp",
                "raison_Social",
                "id_Tiers",
                "num_dc",
                "num_sdc",
                "num_cir",
                "num_siren",
                "ref_coll",
                "code_produit",
                "id_de_offre_comm",
                "chef_de_file",
                "num_ovi",
                "num_rum",
                "typeenregie",
                "produit_comm",
                "produit",
                "phase",
                "montant_pret");

        //then
        Contrat c= contratService.save(contrat);

        Long id_expected = c.getId();

        Long actual= contratService.getContrat(c.getId()).getId();


        assertEquals(id_expected, actual);
        log.info(" id contrat"+ c.getId());

    }

    @Test
    @Order(2)
    void delete() {


        List<Contrat> contrats = contratService.getAllContrat();
        int size = contrats.size();
        log.info("contrat size : "+size);
        Contrat contrat = contrats.get(0);


        Long id= contrat.getId();

        contratService.delete(id);
        log.info("contrat size : "+contratService.getAllContrat().size());
        assertEquals(0,contratService.getAllContrat().size());


    }
}