package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
