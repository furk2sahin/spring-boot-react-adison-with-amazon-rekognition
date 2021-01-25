package com.furkannsahin.adison.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyGenerator")
    @SequenceGenerator(name = "companyGenerator", sequenceName = "companySequence")
    @Column(updatable = false, nullable = false)
    private Long id;

    private String name;
    private String address;
    private String website;
    private String phone;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, targetEntity = Ad.class)
    private List<Ad> ads = new ArrayList<>();

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn
    private User user;
}
