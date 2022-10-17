package com.tomaszdabrowski.lab1.ctftask.repository.h2;

import com.tomaszdabrowski.lab1.ctftask.model.Category;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
  List<Category> findByName(String name);
}
