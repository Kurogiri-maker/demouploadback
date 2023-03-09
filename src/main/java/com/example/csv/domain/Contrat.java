package com.example.csv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Num_dossierKPS;
    private String Num_CP;
    private String Raison_Social;
    private String Id_Tiers;
    private String Num_DC;
    private String Num_SDC;
    private String Num_CIR;
    private String Num_SIREN;
    private String Ref_Collaborative;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrat contrat = (Contrat) o;
        return Objects.equals(id, contrat.id) && Objects.equals(Num_dossierKPS, contrat.Num_dossierKPS) && Objects.equals(Num_CP, contrat.Num_CP) && Objects.equals(Raison_Social, contrat.Raison_Social) && Objects.equals(Id_Tiers, contrat.Id_Tiers) && Objects.equals(Num_DC, contrat.Num_DC) && Objects.equals(Num_SDC, contrat.Num_SDC) && Objects.equals(Num_CIR, contrat.Num_CIR) && Objects.equals(Num_SIREN, contrat.Num_SIREN) && Objects.equals(Ref_Collaborative, contrat.Ref_Collaborative) && Objects.equals(Code_Produit, contrat.Code_Produit) && Objects.equals(Identifiant_de_offre_comm, contrat.Identifiant_de_offre_comm) && Objects.equals(Chef_de_File, contrat.Chef_de_File) && Objects.equals(Num_OVI, contrat.Num_OVI) && Objects.equals(Num_RUM, contrat.Num_RUM) && Objects.equals(TypeEnergie, contrat.TypeEnergie) && Objects.equals(Produit_Comm, contrat.Produit_Comm) && Objects.equals(Produit, contrat.Produit) && Objects.equals(Phase, contrat.Phase) && Objects.equals(Montant_pret, contrat.Montant_pret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Num_dossierKPS, Num_CP, Raison_Social, Id_Tiers, Num_DC, Num_SDC, Num_CIR, Num_SIREN, Ref_Collaborative, Code_Produit, Identifiant_de_offre_comm, Chef_de_File, Num_OVI, Num_RUM, TypeEnergie, Produit_Comm, Produit, Phase, Montant_pret);
    }
}
