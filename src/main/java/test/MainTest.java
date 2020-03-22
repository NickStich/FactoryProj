package test;

import config.HibernateConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        MateriePrima x = new MateriePrima("X", 30);
        MateriePrima y = new MateriePrima("Y", 20);
        MateriePrima z = new MateriePrima("Z", 15);

        Map<MateriePrima, Integer> xyMap = new HashMap<MateriePrima, Integer>();
        xyMap.put(x, 3);
        xyMap.put(y, 2);
        Produse xy = new Produse("xy", 5, xyMap);

        Map<MateriePrima, Integer> xzMap = new HashMap<MateriePrima, Integer>();
        xzMap.put(x,3);
        xzMap.put(z,5);
        Produse xz = new Produse("xz", 8, xzMap);

        Map<MateriePrima,Integer> yzMap =new HashMap<MateriePrima, Integer>();
        yzMap.put(y,4);
        yzMap.put(z,6);
        Produse yz = new Produse("yz", 10, yzMap);

        Produse.createFinGood(xy);

        List<MateriePrima>materiePrimaList=new ArrayList<MateriePrima>();
        materiePrimaList.add(x);
        materiePrimaList.add(y);
        materiePrimaList.add(z);

        List<Produse>produseList =new ArrayList<Produse>();
        produseList.add(xy);
        produseList.add(xz);
        produseList.add(yz);

        // Methods

        //MateriePrima.getStock(materiePrimaList, produseList);

        HibernateConfig.shutdown();

    }
}
