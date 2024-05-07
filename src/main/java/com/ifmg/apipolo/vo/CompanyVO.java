package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Company;
import com.ifmg.apipolo.entity.Image;
import lombok.*;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyVO {

    private Long id;
    private Image image;
    private String name;
//    private String modalName;
//    private String accordionId;
//    private String headerBody;
//    private String headerName;
    private String classification;
    private String cnpj;

    public CompanyVO(Company company) {
        this.id = company.getId();
        this.image = company.getImg();
        this.name = company.getName();
        this.classification = classification;
        this.cnpj = cnpj;
//        this.modalName = company.getModalName();
//        this.accordionId = company.getAccordionId();
//        this.headerBody = company.getHeaderBody();
//        this.headerName = company.getHeaderName();
    }
}
