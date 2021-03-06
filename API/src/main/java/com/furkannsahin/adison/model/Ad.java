package com.furkannsahin.adison.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ad extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adsGenerator")
    @SequenceGenerator(name = "adsGenerator", sequenceName = "adsSequence", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String requiredWord;
    private boolean expired;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ad_company"))
    private Company company;

    @JsonIgnore
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<UserAdPost> posts = new ArrayList<>();
}
