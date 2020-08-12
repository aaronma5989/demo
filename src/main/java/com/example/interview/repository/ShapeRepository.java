package com.example.interview.repository;

import com.example.interview.entity.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, UUID> {

    Shape getByUuid(UUID id);

    List<Shape> findAll();

}
