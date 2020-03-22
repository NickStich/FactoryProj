package daos;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import production.FinishGood;

import java.util.ArrayList;
import java.util.List;

public class FinishGoodDAO {

    public void createFinGood(FinishGood product) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void removeFinGood(FinishGood product) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void updateFinGood(FinishGood product) {
        Transaction transaction = null;

        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public FinishGood getById(long id) {
        FinishGood person = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            person = session.find(FinishGood.class, id);
            session.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;
    }

    public List<FinishGood> getAll() {
        List<FinishGood>products=new ArrayList<FinishGood>();
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            products= session.createQuery("from Product", FinishGood.class).list();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }



    public int priceOfFG(FinishGood finishGood){
        int result=0;
        for (int i = 0; i < finishGood.getMaterials().size(); i++) {
            result+=finishGood.getMaterials().get(i).getPrice();
        }
        return result;
    }
}
