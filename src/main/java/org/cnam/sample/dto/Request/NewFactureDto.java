package org.cnam.sample.dto.Request;

import java.util.Date;

public class NewFactureDto {
    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
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

    private  Long id_client;

        private String libelle_frais;

        private double montant;

        private Date date;

        public NewFactureDto(){

        }

        public NewFactureDto(long id_client, String libelle_frais, double montant, Date date){
            //this.id = id;
            this.id_client = id_client;
            this.libelle_frais = libelle_frais;
            this.montant = montant;
            this.date = date;
        }
}
