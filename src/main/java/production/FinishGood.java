package production;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(schema = "factory", name = "products")
public class FinishGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private int prod_id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private double weight;

    @Column(name = "color")
    private String color;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "production",
            joinColumns = {@JoinColumn(name = "prod_id")},
            inverseJoinColumns = {@JoinColumn(name = "mat_id")})
    private List<RawMaterial> materials;

    public FinishGood() {
    }

    public FinishGood(int id, String name, double weight, String color, List<RawMaterial> materials) {
        this.prod_id = id;
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.materials = materials;
    }


    public FinishGood(String name, double weight, String color, List<RawMaterial> materials) {
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.materials = materials;
    }

    public FinishGood(String name, double weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<RawMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<RawMaterial> materials) {
        this.materials = materials;
    }
}
