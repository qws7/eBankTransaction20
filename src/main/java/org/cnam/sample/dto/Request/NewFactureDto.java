package org.cnam.sample.dto.Request;

import java.util.Date;
import java.util.UUID;

public class NewFactureDto implements RequestDto{
    public UUID getId_client() {
        return id_client;
    }

    public void setId_client(UUID id_client) {
        this.id_client = id_client;
    }

    public String getLibelle_frais() {
        return libelle_frais;
    }

    public void setLibelle_frais(String libelle_frais) {
        this.libelle_frais = libelle_frais;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private  UUID id_client;

        private String libelle_frais;

        private double montant;

        private Date date;

        public NewFactureDto(){

        }

        public NewFactureDto(UUID id_client, String libelle_frais, double montant, Date date){
            //this.id = id;
            this.id_client = id_client;
            this.libelle_frais = libelle_frais;
            this.montant = montant;
            this.date = date;
        }
}
