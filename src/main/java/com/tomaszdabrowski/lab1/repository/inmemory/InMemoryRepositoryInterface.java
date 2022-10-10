package com.tomaszdabrowski.lab1.repository.inmemory;

import java.util.List;
import java.util.Optional;

public interface InMemoryRepositoryInterface<E, K> {
    Optional<E> findById(K id);
    List<E> findMany();
    E save(E entity);
    void deleteById(K id);
}
