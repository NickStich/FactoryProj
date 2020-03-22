package test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(schema = "testdb", name = "materials")
public class MateriePrima {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int quantity;

    private List<Produse>produses=new ArrayList<Produse>();

    public MateriePrima() {
    }

    public MateriePrima(String name, int quantity, List<Produse> produses) {
        this.name = name;
        this.quantity = quantity;
        this.produses = produses;
    }

    public MateriePrima(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produse> getProduses() {
        return produses;
    }

    public void setProduses(List<Produse> produses) {
        this.produses = produses;
    }

    public static void getStock(List<MateriePrima> materiePrimaList, List<Produse> produseList) {
        Map<MateriePrima, Integer> stockMap = new HashMap<MateriePrima, Integer>();

        for (int i = 0; i < materiePrimaList.size(); i++) {
            int quant = materiePrimaList.get(i).getQuantity();
            for (int j = 0; j < produseList.size(); j++) {
                for (Map.Entry<MateriePrima, Integer> k : produseList.get(j).getReteta().entrySet()) {
                    if (k.getKey().equals(materiePrimaList.get(i))) {
                        quant-=k.getValue();

                    }
                    stockMap.put(materiePrimaList.get(i),quant);
                }
            }
        }
        for (Map.Entry<MateriePrima, Integer> stock : stockMap.entrySet()) {
            System.out.println("Produsul: "+stock.getKey().getName()+" are stoc: "+stock.getValue());
        }
    }

}
