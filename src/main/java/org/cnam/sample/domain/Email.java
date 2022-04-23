package org.cnam.sample.domain;

import java.util.HashMap;

public class Email {

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    // Personne à qui l'on envoie l'email
    private String recipient;

    // HashMap clé/valeur : pour remplacer les valeurs dans le template choisi
    private HashMap<String,String> values;

    public Email() {
    }

    public Email(String recipient, HashMap<String, String> values) {
        this.recipient = recipient;
        this.values = values;
    }
}