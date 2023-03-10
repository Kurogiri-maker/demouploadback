package com.example.csv.services;

import antlr.ASTNULLType;
import com.example.csv.domain.Contrat;
import com.example.csv.helper.CSVHelper;
import com.example.csv.repositories.ContratRepository;
import com.example.csv.services.implementation.ContratServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DataJpaTest

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ContratServiceTest {

    @Mock
    private ContratRepository contratRepository;
    private ContratService contratService ;

    @BeforeEach
    void setUp() {
        contratService = new ContratServiceImpl(contratRepository);
    }

    @Test
    @Order(1)
    void save() {

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
        contratService.save(contrat);

        ArgumentCaptor<Contrat> contratArgumentCaptor = ArgumentCaptor.forClass(Contrat.class);
        verify(contratRepository).save(contratArgumentCaptor.capture());

        Contrat capturedContrat = contratArgumentCaptor.getValue();
        //then
        assertEquals(capturedContrat,contrat);

        log.info(""+capturedContrat);
        log.info(""+contrat);


    }

    @Test
    void testGetContrat() {
        Contrat expectedContrat = new Contrat(31L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");



        when(contratRepository.findById(31L)).thenReturn(Optional.of(expectedContrat));
        Contrat actualContrat = contratService.getContrat(31l);
        assertEquals(expectedContrat, actualContrat);
        verify(contratRepository).findById(31L);



    }



    @Order(2)
    @Test
    void updateContrat(){
        Contrat contrat = new Contrat(
                31L,
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
       boolean result = contratService.update(contrat);
        Mockito.verify(contratRepository).save(contrat);
        Assert.assertTrue(result);
    }

    @Test
    @Order(3)
    void delete() {

        Contrat contrat = new Contrat(
                31l,
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
        contratService.delete(contrat.getId());

        Mockito.verify(contratRepository).deleteById(contrat.getId());

    }



    @Test
    public void saveFile_shouldSaveContrats()  {
        // create a mock for the ContratRepository

             // create some test data
        byte[] fileContent = "Num_dossierKPS,Num_CP,Raison_Social,Id_Tiers,Num_DC,Num_SDC,Num_CIR,Num_SIREN,Ref_Collaborative,Code_Produit,Identifiant_de_offre_comm,Chef_de_File,Num_OVI,Num_RUM,TypeEnergie,Produit_Comm,Produit,Phase,Montant_pret\ndfydn,1,ztfop,amgqv,fmkzu,zqmyl,bfixm,yyvwp,vegzu,ixrrl,wmeoi,dcosp,wpinz,nliuy,impvq,uljpk,blcbp,poocm,yobnt\ndfydn,1,ztfop,amgqv,fmkzu,zqmyl,bfixm,yyvwp,vegzu,ixrrl,wmeoi,dcosp,wpinz,nliuy,impvq,uljpk,blcbp,poocm,yobnt\n".getBytes();
        MockMultipartFile file = new MockMultipartFile("file.csv", "file.csv", "text/csv", fileContent);

        // create a list of expected Contrat objects
        List<Contrat> expectedContrats = new ArrayList<>();
        expectedContrats.add( new Contrat(
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
        expectedContrats.add( new Contrat(
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

        // create a mock CSVHelper that returns the expected Contrat objects
        CSVHelper csvHelper = Mockito.mock(CSVHelper.class);


        // create an instance of the class under test


        // call the method under test
        contratService.saveFile(file);

        // verify that the ContratRepository.saveAll method was called with the expected Contrat objects
        Mockito.verify(contratRepository).saveAll(expectedContrats);
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
        List<Contrat> expectedContrats = new ArrayList<>();
        // create some test data
        Contrat contrat1 = new Contrat(31L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");
        Contrat contrat2= new Contrat(32L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");

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

}