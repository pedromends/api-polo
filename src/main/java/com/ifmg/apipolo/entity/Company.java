package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "modal_name")
    private String modalName;

    @Column(name = "accordion_id")
    private String accordionId;

    @Column(name = "header_name")
    private String headerName;

    @Column(name = "header_body")
    private String headerBody;

    @Column(name = "resume")
    private String resume;

    @Column(name = "coor_name")
    private String coorName;

    @Column(name = "situation")
    private String situation;

    @Column(name = "proj_name")
    private String projName;

    @Column(name = "value")
    private String value;

    @Column(name = "link_to")
    private String linkTo;

    @Column(name = "attr_1")
    private String attr1;
}
