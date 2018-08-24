package com;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TungBT
 */
public class ProductDao {

    public List<Product> findAll() {
        Session session = session = HibernateUtil.getSessionAndBeginTransaction();
        try {
            Query query = session.createQuery("SELECT p FROM Product p");
            return query.list();
        } catch (Exception e) {
        }
        return null;
    }

    public boolean insert(Product p) {
        Session session = session = HibernateUtil.getSessionAndBeginTransaction();
        try {
            Product p1 = new Product();
            p1.setId(p.getId());
            p1.setName(p.getName());
            p1.setPrice(p.getPrice());
            p1.setQuantity(p.getQuantity());
            session.persist(p1);
            return true;
        } catch (Exception e) {
            HibernateUtil.rollBackSessions(session);
        } finally {
            try {
                HibernateUtil.commitCurrentSessions();
            } catch (Exception ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean update(String id, int quantity) {
        Session session = session = HibernateUtil.getSessionAndBeginTransaction();
        try {
            String hql = "UPDATE Product SET quantity = :quantity WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("quantity", quantity);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            HibernateUtil.rollBackSessions(session);
        }
        return false;
    }
}
