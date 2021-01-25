package com.furkannsahin.adison.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserAdPost extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyGenerator")
    @SequenceGenerator(name = "companyGenerator", sequenceName = "companySequence")
    @Column(updatable = false, nullable = false)
    private Long id;

    private boolean accepted;
    private boolean active;

    @ManyToOne(targetEntity = Ad.class, cascade = CascadeType.ALL)
    @JoinColumn
    private Ad ad;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn
    private User user;
}
