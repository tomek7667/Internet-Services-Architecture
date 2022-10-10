package com.tomaszdabrowski.lab1.ctftask.repository.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.datastore.DataStore;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CategoryRepository implements CategoryRepositoryInterface {
    private DataStore store;

    @Autowired
    public CategoryRepository(DataStore store) {
        this.store = store;
    }

    public Optional<Category> findById(Long id) {
        return store.findOneCategoryById(id);
    }

    public List<Category> findMany() {
        return store.findManyCategories();
    }

    public Category save(Category category) {
        return store.saveCategory(category);
    }

    public void deleteById(Long id) {
        store.deleteCategoryById(id);
    }
}