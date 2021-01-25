package com.furkannsahin.adison.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ad extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adsGenerator")
    @SequenceGenerator(name = "adsGenerator", sequenceName = "adsSequence")
    @Column(updatable = false, nullable = false)
    private Long id;

    private String requiredWord;
    private boolean expired;

    @ManyToOne(cascade = CascadeType.ALL,  targetEntity = Company.class)
    @JoinColumn
    private Company company;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, targetEntity = UserAdPost.class)
    private List<UserAdPost> posts = new ArrayList<>();
}
