package com.donation.repository;

import com.donation.domain.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type,Integer> {
    List<Type> findByTypeName(String name);
    boolean existsByTypeName(String typeName);
}
