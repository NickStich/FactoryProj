package production;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "factory", name = "material")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mat_id")
    private int mat_id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price ;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "materials")
    private List<FinishGood>products;

    public RawMaterial(){}

    public RawMaterial(int id, String nume, int quantity, double price) {
        this.mat_id = id;
        this.nume = nume;
        this.quantity = quantity;
        this.price = price;
    }

    public RawMaterial(String nume, int quantity, double price) {
        this.nume = nume;
        this.quantity = quantity;
        this.price = price;
    }


    public int getMat_id() {
        return mat_id;
    }

    public void setMat_id(int mat_id) {
        this.mat_id = mat_id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<FinishGood> getProducts() {
        return products;
    }

    public void setProducts(List<FinishGood> products) {
        this.products = products;
    }
}
