package com.example.csv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue
    private Long id;
    private String Num_dossierKPS;
    private String Num_CP;
    private String Raison_Social;
    private String Id_Tiers;
    private String Num_DC;
    private String Num_SDC;
    private String Num_CIR;
    private String Num_SIRENRef_Collaborative;
    private String Code_Produit;
    private String Identifiant_de_offre_comm;
    private String Chef_de_File;
    private String Num_OVI;
    private String Num_RUM;
    private String TypeEnergie;
    private String Produit_Comm;
    private String Produit;
    private String Phase;
    private String Montant_pret;
}