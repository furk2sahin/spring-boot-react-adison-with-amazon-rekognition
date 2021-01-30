package com.furkannsahin.adison.repository;

import com.furkannsahin.adison.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
    Optional<List<UserRole>> findAllByUserId(Long id);
}
