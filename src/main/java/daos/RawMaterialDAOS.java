package daos;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import production.FinishGood;
import production.RawMaterial;

import java.util.ArrayList;
import java.util.List;

public class RawMaterialDAOS {
    public void createRawMat(RawMaterial material) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(material);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void removeRawMat(RawMaterial material) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(material);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void updateRawMat(RawMaterial material) {
        Transaction transaction = null;

        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(material);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public RawMaterial getById(long id) {
        RawMaterial person = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            person = session.find(RawMaterial.class, id);
            session.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;
    }

    public List<RawMaterial> getAll() {
        List<RawMaterial>materials=new ArrayList<RawMaterial>();
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            materials= session.createQuery("from Product", RawMaterial.class).list();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return materials;
    }


}
