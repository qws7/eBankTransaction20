package org.cnam.sample.domain;

public class EmailTemplate {

    private long id;
    private String subject;
    private String body;
    private String description;

    public EmailTemplate(long id, String title, String body, String description) {
        this.id = id;
        this.subject = title;
        this.body = body;
        this.description = description;
    }
}