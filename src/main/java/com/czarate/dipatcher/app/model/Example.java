package com.czarate.dipatcher.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import com.czarate.dipatcher.app.date.CustomDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document()
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)//SE COLOCA YA QUE NO ME QUIERE RECONOCER LA PROPIEDAD ISDELETED
public class Example {
	@Id
	private String _id;
    private String subscriptionId;
    private String frameworkAgreementId;
    private String paymentMethodType;
    private List<Product> products;
    private String traceId;
    private String alias;
    private String emailAddress;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date transactionDateTime;//
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date creationDate;
    private String subscriptionStatus;
    private boolean isDeleted;//NO RECONOCIDO BUG
    private boolean activation;
    private String _class;
}
