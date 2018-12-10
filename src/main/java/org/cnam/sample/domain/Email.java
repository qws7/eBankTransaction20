package org.cnam.sample.domain;

import java.util.HashMap;

public class Email {
    // Selection du template associé au service
    private EmailTemplate emailTemplate;

    // Personne à qui l'on envoie l'email
    private String recipient;

    // HashMap clé/valeur : pour remplacer les valeurs dans le template choisi
    private HashMap<String,String> values;

    public Email(EmailTemplate emailTemplate, String recipient, HashMap<String, String> values) {
        this.emailTemplate = emailTemplate;
        this.recipient = recipient;
        this.values = values;
    }
}