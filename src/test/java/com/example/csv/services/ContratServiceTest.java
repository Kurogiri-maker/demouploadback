package com.example.csv.services;

import antlr.ASTNULLType;
import com.example.csv.domain.Contrat;
import com.example.csv.repositories.ContratRepository;
import com.example.csv.services.implementation.ContratServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
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
//        int size = contratService.getAllContrat().size();
//        int expected = size+1;
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


    }
//
//    @Test
//    void testGetContrat() {
//        Contrat contrat = new Contrat(
//                null,
//                "num_dossier",
//                "num_cp",
//                "raison_Social",
//                "id_Tiers",
//                "num_dc",
//                "num_sdc",
//                "num_cir",
//                "num_siren",
//                "ref_coll",
//                "code_produit",
//                "id_de_offre_comm",
//                "chef_de_file",
//                "num_ovi",
//                "num_rum",
//                "typeenregie",
//                "produit_comm",
//                "produit",
//                "phase",
//                "montant_pret");
//
//        //then
//        Contrat c= contratService.save(contrat);
//
//        Long id_expected = c.getId();
//
//        Long actual= contratService.getContrat(id_expected).getId();
//
//        assertEquals(contrat,c);
//      assertEquals(id_expected, actual);
//        log.info(" id contrat"+ c.getId());
//
//    }
//
//
//
//    @Order(2)
//    @Test
//    void updateContrat(){
//        Contrat contrat = new Contrat(
//                null,
//                "num_dossier",
//                "num_cp",
//                "raison_Social",
//                "id_Tiers",
//                "num_dc",
//                "num_sdc",
//                "num_cir",
//                "num_siren",
//                "ref_coll",
//                "code_produit",
//                "id_de_offre_comm",
//                "chef_de_file",
//                "num_ovi",
//                "num_rum",
//                "typeenregie",
//                "produit_comm",
//                "produit",
//                "phase",
//                "montant_pret");
//
//        //then
//        Contrat c= contratService.save(contrat);
//
//        Long id = c.getId();
//
//        log.info("id object1 : "+id);
//        Contrat updatedContrat = new Contrat(
//                id,
//                "num_dossier",
//                "imen",
//                "raison_Social",
//                "id_Tiers",
//                "num_dc",
//                "num_sdc",
//                "num_cir",
//                "num_siren",
//                "ref_coll",
//                "code_produit",
//                "id_de_offre_comm",
//                "chef_de_file",
//                "num_ovi",
//                "num_rum",
//                "typeenregie",
//                "produit_comm",
//                "produit",
//                "phase",
//                "montant_pret");
//
//
//
//     //   log.info("id object2 : "+updatedContrat.getId());
//
//       // log.info("numCpobjet 2"+ updatedContrat.getNum_CP() );
//       // log.info("numCpobjet 2"+ contrat.getNum_CP() );
//
//
//        contratService.update(updatedContrat);
//
//        assertEquals("imen",contratService.getContrat(id).getNum_CP());
//       // log.info("hhhhhhhhhhhhhhh"+ updatedContrat.getNum_CP() );
//
//
//
//
//
//
//    }
//
//    @Test
//    @Order(3)
//    void delete() {
//        int size = contratService.getAllContrat().size();
//        log.info("avantsupp"+size);
//        Contrat contrat = new Contrat(
//                null,
//                "num_dossier",
//                "num_cp",
//                "raison_Social",
//                "id_Tiers",
//                "num_dc",
//                "num_sdc",
//                "num_cir",
//                "num_siren",
//                "ref_coll",
//                "code_produit",
//                "id_de_offre_comm",
//                "chef_de_file",
//                "num_ovi",
//                "num_rum",
//                "typeenregie",
//                "produit_comm",
//                "produit",
//                "phase",
//                "montant_pret");
//
//        //then
//        Contrat c= contratService.save(contrat);
//
//        Long id = c.getId();
//
//        log.info("id object1 : "+id);
//
//
//        contratService.delete(id);
//
//
//        log.info("contrat size : "+contratService.getAllContrat().size());
//
//        assertEquals(size,contratService.getAllContrat().size());
//
//
//    }
//
//    @Test
//    void testSaveFile()  {
//        // Arrange
//
//        int size = contratService.getAllContrat().size();
//        int expected= size+1;
//
//        String csvContent = "Num_dossierKPS,Num_CP,Raison_Social,Id_Tiers,Num_DC,Num_SDC,Num_CIR,Num_SIREN,Ref_Collaborative,Code_Produit,Identifiant_de_offre_comm,Chef_de_File,Num_OVI,Num_RUM,TypeEnergie,Produit_Comm,Produit,Phase,Montant_pret\n" +
//                "dfydn,1,ztfop,amgqv,fmkzu,zqmyl,bfixm,yyvwp,vegzu,ixrrl,wmeoi,dcosp,wpinz,nliuy,impvq,uljpk,blcbp,poocm,yobnt\n";
//        MultipartFile csvFile = new MockMultipartFile("test.csv", csvContent.getBytes(StandardCharsets.UTF_8));
//        List<Contrat> contrats = new ArrayList<>();
//        contrats.add(  new Contrat(
//                null,
//                "dfydn",
//                "1",
//                "ztfop",
//                "amgqv",
//                "fmkzu",
//                "zqmyl",
//                "bfixm",
//                "yyvwp",
//                "vegzu",
//                "ixrrl",
//                "wmeoi",
//                "dcosp",
//                "wpinz",
//                "nliuy",
//                "impvq",
//                "uljpk",
//                "blcbp",
//                "poocm",
//                "yobnt"));
//        // Act
//
//        contratService.saveFile(csvFile);
//
//        assertEquals(expected,contratService.getAllContrat().size());
//    }
//
//    @Test
//    void testSaveFileWithIOException() throws Exception {
//        // create a mock MultipartFile that throws an IOException when its input stream is accessed
//        MultipartFile mockFile = mock(MultipartFile.class);
//        when(mockFile.getInputStream()).thenThrow(new IOException("File could not be read"));
//
//        // verify that a RuntimeException is thrown when the file is saved
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            contratService.saveFile(mockFile);
//        });
//
//        // verify that the exception message is correct
//        String expectedMessage = "File could not be read";
//        String actualMessage = exception.getCause().getMessage();
//        assertEquals(expectedMessage, actualMessage);
//    }
//
//
//    @Test
//    void testGetAllContrat() {
//        // create some test data
//        Contrat contrat1 = new Contrat(31L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");
//        Contrat contrat2= new Contrat(32L,"dfydn","1","ztfop","amgqv","fmkzu","zqmyl","bfixm","yyvwp","vegzu","ixrrl","wmeoi","dcosp","wpinz","nliuy","impvq","uljpk","blcbp","poocm","yobnt");
//
//        List<Contrat> expectedContrats = new ArrayList<>();
//        expectedContrats.add(contrat1);
//        expectedContrats.add(contrat2);
//        // create a mock repository and configure it to return the test data
//        ContratRepository mockRepo = mock(ContratRepository.class);
//        when(mockRepo.findAll()).thenReturn(expectedContrats);
//
//        // create a service instance with the mock repository
//        ContratServiceImpl contratService = new ContratServiceImpl(mockRepo);
//
//
//        // call the method and verify the results
//        List<Contrat> actualContrats = contratService.getAllContrat();
//        assertEquals(expectedContrats, actualContrats);
//        verify(mockRepo).findAll();
//    }

}