package banking.controller;

import banking.entity.BankAccount;
import java.util.List;
import javax.persistence.*;

public class BankAccountController {
    
    private static EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myJPA");

    public BankAccountController() {
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BankAccount findByNumber(int accountNumber) {
    
        EntityManager em = null;
        BankAccount ba = null;
        try {
            em = getEntityManager();
            Query q = em.createQuery("select ba from BankAccount ba where ba.number = :num");
            q.setParameter("num", accountNumber);
            ba = (BankAccount) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Account Not Found!");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ba;

    }

    
    public void createAccount(BankAccount ba) {
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ba);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
    
    public List findAll() {
        
        EntityManager em = null;
        List list = null;
        em = getEntityManager();
        Query q = em.createQuery("select ba.number, ba.owner, ba.balance from BankAccount ba");
        list = q.getResultList();
  
        return list;
        
    }  
    
}
