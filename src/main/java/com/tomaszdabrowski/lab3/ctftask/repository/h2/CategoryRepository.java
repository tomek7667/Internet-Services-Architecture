package com.tomaszdabrowski.lab3.ctftask.repository.h2;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {}
