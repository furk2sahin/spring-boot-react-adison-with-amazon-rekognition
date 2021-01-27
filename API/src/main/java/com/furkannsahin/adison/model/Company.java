package com.furkannsahin.adison.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyGenerator")
    @SequenceGenerator(name = "companyGenerator", sequenceName = "companySequence", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private Long id;

    private String name;
    private String address;
    private String website;
    private String phone;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Ad> ads = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
}
