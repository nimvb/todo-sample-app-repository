package com.nimvb.app.repository;


import com.nimvb.app.database.exception.KeyAlreadyExistsException;
import com.nimvb.app.database.exception.KeyNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface SimpleRepository<TType,TId> {

    /**
     * Persist the {@code type} in the storage
     *
     * @param type the entity to be stored
     * @return the wrapped persisted entity
     * @throws KeyAlreadyExistsException if the provided <code>type</code> has the same id of another already persisted entity
     * @throws KeyNotFoundException if the id of the provided <code>type</code> is set, thrown when the persisted entity with the specified id can not be found
     * @throws IllegalArgumentException if the <code>type</code> is null
     */
    TType save(TType type) throws KeyNotFoundException, KeyAlreadyExistsException,IllegalArgumentException;

    /**
     * Persist the {@code type} in the storage
     *
     * @param type the entity to be stored
     * @return the persisted entity
     * @throws KeyAlreadyExistsException if the provided <code>type</code> has the same id of another already persisted entity
     * @throws KeyNotFoundException if the id of the provided <code>type</code> is set, thrown when the persisted entity with the specified id can not be found
     * @throws IllegalArgumentException if the <code>type</code> is null
     */
    TType persist(TType type) throws KeyNotFoundException, KeyAlreadyExistsException, IllegalArgumentException;

    /**
     * Find the entity by its id
     *
     * @param id the id of the target entity
     * @return the wrapped entity if found otherwise the null value wrapped inside the Optional object
     * @throws IllegalArgumentException if the <code>id</code> is null
     */
    Optional<TType> findById(TId id) throws IllegalArgumentException;

    /**
     * Find the entity by its id
     *
     * @param id the id of the target entity
     * @return the entity if found otherwise the null value wrapped inside the Optional object
     * @throws IllegalArgumentException if the <code>id</code> is null
     */
    Optional<TType> fetchById(TId id) throws IllegalArgumentException;

    /**
     * Find all the entities
     *
     * @return The collection contains the all existed entities which are wrapped or cloned
     */
    Collection<TType> findAll();

    /**
     * Delete the entity
     * @param id the id of the target entity
     * @throws IllegalArgumentException if th <code>id</code> is null
     */
    void deleteById(TId id) throws IllegalArgumentException;

    Integer count();
}
