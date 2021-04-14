package com.furkannsahin.adison.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserAdPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userAdPostGenerator")
    @SequenceGenerator(name = "userAdPostGenerator", sequenceName = "userAdPostSequence", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private Long id;

    private boolean accepted;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Ad ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Users user;
}
