package ir.maktab.service.base.impl;

import ir.maktab.exception.EntityNotFoundException;
import ir.maktab.model.base.BaseEntity;
import ir.maktab.repository.base.BaseRepository;
import ir.maktab.service.base.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic Service implementations for basic CRUD operations
 *
 * @param <T> type of entity
 * @param <ID> type of entity id
 */
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {
    protected final BaseRepository<T, ID> repository;

    public BaseServiceImpl(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.add(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<T> update(ID id, T entity) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        return repository.update(id, entity);
    }

}
