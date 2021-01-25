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
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator", sequenceName = "userSequence")
    @Column(updatable = false, nullable = false)
    private Long id;

    private String fullName;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    private String roles;
    private String userType;
    private boolean active;

    @JsonIgnoreProperties
    @OneToOne(mappedBy = "user", targetEntity = Company.class, cascade = CascadeType.ALL)
    private Company company;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = UserAdPost.class)
    private List<UserAdPost> posts = new ArrayList<>();
}
