package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Company;
import lombok.*;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyVO {

    private Long id;
    private String image;
    private String companyName;
    private String modalName;
    private String accordionId;
    private String headerBody;
    private String resume;
    private String coorName;
    private String situation;
    private String projName;
    private String value;
    private String linkTo;
    private String attr1;

    public CompanyVO(Company company) {
        this.id = company.getId();
        this.image = company.getImage();
        this.companyName = company.getCompanyName();
        this.modalName = company.getModalName();
        this.accordionId = company.getAccordionId();
        this.headerBody = company.getHeaderBody();
        this.resume = company.getResume();
        this.coorName = company.getCoorName();
        this.situation = company.getSituation();
        this.projName = company.getProjName();
        this.value = company.getValue();
        this.linkTo = company.getLinkTo();
        this.attr1 = company.getAttr1();
    }
}
