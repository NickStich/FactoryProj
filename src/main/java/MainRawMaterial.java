import config.HibernateConfig;
import daos.FinishGoodDAO;
import daos.RawMaterialDAOS;
import production.FinishGood;
import production.RawMaterial;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainRawMaterial {
    public static void main(String[] args) throws SQLException {
        RawMaterialDAOS rawMaterialDAOS = new RawMaterialDAOS();

        RawMaterial metal = new RawMaterial("Metal", 5, 25);
        //rawMaterialDAOS.createRawMat(metal);

        RawMaterial wood = new RawMaterial("Wood", 7, 15.25);
        //rawMaterialDAOS.createRawMat(wood);

        RawMaterial plastic = new RawMaterial("Plastic", 3, 12.75);
       // rawMaterialDAOS.createRawMat(plastic);

        RawMaterial paint = new RawMaterial("Paint", 2, 24.35);
        //rawMaterialDAOS.createRawMat(paint);

        FinishGoodDAO finishGoodDAO = new FinishGoodDAO();


        List<RawMaterial>materialTable=new ArrayList<RawMaterial>();
        materialTable.add(metal);
        materialTable.add(paint);
        materialTable.add(wood);
        FinishGood table = new FinishGood("Table", 8, "black", materialTable);
        finishGoodDAO.createFinGood(table);

        List<RawMaterial>materialToy=new ArrayList<RawMaterial>();
        materialToy.add(plastic);
        materialToy.add(paint);
        FinishGood toy = new FinishGood("Toy", 1, "yellow", materialToy);
        finishGoodDAO.createFinGood(toy);

        List<RawMaterial>materialChair=new ArrayList<RawMaterial>();
        materialChair.add(metal);
        materialChair.add(plastic);
        FinishGood chair = new FinishGood("Chair", 2, "white", materialChair);
        finishGoodDAO.createFinGood(chair);

        List<RawMaterial>materialDoor = new ArrayList<RawMaterial>();
        materialDoor.add(wood);
        materialDoor.add(paint);
        materialDoor.add(metal);
        FinishGood door = new FinishGood("Door", 9, "green", materialDoor);
        finishGoodDAO.createFinGood(door);


        // METHODS

        System.out.println("________________");

        List<String> productName = Queries.getProductName();// drop box
        for (int i = 0; i < productName.size(); i++) {
            System.out.println(productName.get(i));
        }

        System.out.println("________________");


        List<RawMaterial> toy1 = Queries.getProductMaterials("Toy");
        for (RawMaterial rawMaterial : toy1) {
            System.out.println(rawMaterial.getNume()+" "+rawMaterial.getQuantity()+" "+rawMaterial.getPrice());
        }

        System.out.println(Queries.getProductPrice("Toy"));

        System.out.println(Queries.countFG());

        System.out.println(Queries.countMat());

        HibernateConfig.shutdown();
    }

}
