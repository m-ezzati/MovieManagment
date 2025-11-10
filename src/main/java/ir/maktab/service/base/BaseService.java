package ir.maktab.service.base;

import ir.maktab.model.base.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic service interface for basic CRUD operations
 *
 * @param <T> The entity Type
 * @param <ID> The type of entity id
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {
    T create(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    void deleteById(ID id);
    Optional<T> update(ID id, T t);
}
