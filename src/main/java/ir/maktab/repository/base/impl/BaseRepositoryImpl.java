package ir.maktab.repository.base.impl;

import ir.maktab.model.base.BaseEntity;
import ir.maktab.repository.base.BaseRepository;
import ir.maktab.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic Repository implementation for CRUD operations using JPA
 *
 * @param <T> Entity type
 * @param <ID> Id type of the entity
 */
public class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<T, ID> {

    protected final Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T add(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            JpaUtil.beginTransaction(em);
            em.persist(entity);
            JpaUtil.commitTransaction(em);
            return entity;
        } catch (RuntimeException e) {
            JpaUtil.rollbackTransaction(em);
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(ID id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            JpaUtil.beginTransaction(em);
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            JpaUtil.commitTransaction(em);
        }catch (RuntimeException e){
            JpaUtil.rollbackTransaction(em);
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public Optional<T> update(ID id, T updatedEntity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            JpaUtil.beginTransaction(em);
            T existingEntity = em.find(entityClass, id);
            if (existingEntity == null) {
                return Optional.empty();
            }
            updatedEntity.setId(id);
            em.merge(updatedEntity);
            JpaUtil.commitTransaction(em);
        }catch (RuntimeException e){
            JpaUtil.rollbackTransaction(em);
            throw e;
        }finally {
            em.close();
        }
        return Optional.of(updatedEntity);
    }

    @Override
    public List<T> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        List<T> result;
        try {
            result = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                    .getResultList();
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public Optional<T> findById(ID id) {
        System.out.println("findbyid");
        EntityManager em = JpaUtil.getEntityManager();
        try {
            System.out.println("try");
            T entity = em.find(entityClass, id);
            System.out.println("entity" + entity);
            return Optional.ofNullable(entity);
        } finally {
            em.close();
        }
    }
}