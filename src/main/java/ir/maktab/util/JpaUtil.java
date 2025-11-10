package ir.maktab.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// Singleton used for Connection

public final class JpaUtil {
    private JpaUtil(){}

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("cinema_system");

    public static EntityManager getEntityManager(){
        System.out.println("getEntityManager");
        return emf.createEntityManager();
    }

    public static void beginTransaction(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
    }

    public static void commitTransaction(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.commit();
        }
    }

    public static void rollbackTransaction(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.rollback();
        }
    }

    public static void closeFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
