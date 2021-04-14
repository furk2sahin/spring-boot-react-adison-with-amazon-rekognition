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
@JsonIgnoreProperties({"company", "posts", "roles"})
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator", sequenceName = "userSequence", allocationSize = 1)
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

    private String userType;
    private boolean active;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAdPost> posts = new ArrayList<>();
}
