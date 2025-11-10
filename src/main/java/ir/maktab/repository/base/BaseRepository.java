package ir.maktab.repository.base;

import ir.maktab.model.base.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for basic CRUD operations
 *
 * @param <T> The entity type
 * @param <ID> The type of entity's id
 */
public interface BaseRepository <T extends BaseEntity<ID>, ID extends Serializable>{
    T add(T entity);
    void deleteById(ID id);
    Optional<T> update(ID id, T t);
    List<T> findAll();
    Optional<T> findById(ID id);

}
