package com.esprit.microservice.etudiant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String from;

    private String mailTo;

    private String body;

    private String subject;

    private List<Object> attachments;

    private Map<String, Object> props;

}