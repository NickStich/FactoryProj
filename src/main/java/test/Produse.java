package test;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import production.FinishGood;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(schema = "testdb", name = "produses")
public class Produse {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int weight;

    private Map<MateriePrima,Integer> reteta =new HashMap<MateriePrima, Integer>();

    public Produse() {
    }

    public Produse(String name, int weight, Map<MateriePrima, Integer> reteta) {
        this.name = name;
        this.weight = weight;
        this.reteta = reteta;
    }

    public static void createFinGood(Produse product) {
        Transaction transaction = null;

        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Map<MateriePrima, Integer> getReteta() {
        return reteta;
    }

    public void setReteta(Map<MateriePrima, Integer> reteta) {
        this.reteta = reteta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
