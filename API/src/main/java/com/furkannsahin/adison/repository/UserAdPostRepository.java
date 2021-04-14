package com.furkannsahin.adison.repository;

import com.furkannsahin.adison.model.UserAdPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdPostRepository extends JpaRepository<UserAdPost, Long> {
}
