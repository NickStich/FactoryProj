import config.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import production.FinishGood;
import production.RawMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    public static List<String> getProductName() throws SQLException {
        List<String> result = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory?serverTimezone=UTC", "root", "Colea4895");
        Statement stm = null;
        String query = "select name from products";
        try {
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String nume = rs.getString("name");
                result.add(nume);
            }
        } finally {
            stm.close();
        }
        return result;

    }

    public static List<RawMaterial> getProductMaterials(String nume) throws SQLException {
        List<RawMaterial> result = new ArrayList<RawMaterial>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory?serverTimezone=UTC", "root", "Colea4895");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT material.nume, material.quantity, material.price " +
                "FROM material inner join production using(mat_id) inner join products using(prod_id)" +
                "where name = ?");
        preparedStatement.setString(1, nume);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RawMaterial rawMaterial = new RawMaterial();
                String name = rs.getString("nume");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                rawMaterial.setNume(name);
                rawMaterial.setQuantity(quantity);
                rawMaterial.setPrice(price);
                result.add(rawMaterial);
            }
        } finally {
            statement.close();
        }
        return result;
    }

    public static double getProductPrice(String nume) throws SQLException {
        double x = 0;
        List<RawMaterial> result = new ArrayList<RawMaterial>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory?serverTimezone=UTC", "root", "Colea4895");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT material.nume, material.quantity, material.price " +
                "FROM material inner join production using(mat_id) inner join products using(prod_id)" +
                "where name = ?");
        preparedStatement.setString(1, nume);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RawMaterial rawMaterial = new RawMaterial();
                String name = rs.getString("nume");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                rawMaterial.setNume(name);
                rawMaterial.setQuantity(quantity);
                rawMaterial.setPrice(price);
                result.add(rawMaterial);
            }
        } finally {
            statement.close();
        }
        for (RawMaterial z : result) {
            x += z.getPrice();
        }
        return x + (x * 0.05);
    }

    public static int countFG() throws SQLException {
        int count = 0;
        Statement statement = null;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory?serverTimezone=UTC", "root", "Colea4895");
        try {
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT COUNT(*) AS ROWCOUNT FROM PRODUCTS");
            resultset.next();
            count = resultset.getInt("ROWCOUNT");
            resultset.close();
            return count;

        } catch (Exception e) {
            System.out.println("Error getting row count");
            e.printStackTrace();
        }
        return count;
    }

    public static int countMat() throws SQLException {
        int count = 0;
        Statement statement = null;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory?serverTimezone=UTC", "root", "Colea4895");
        try {
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT COUNT(*) AS ROWCOUNT FROM MATERIAL");
            resultset.next();
            count = resultset.getInt("ROWCOUNT");
            resultset.close();
            return count;

        } catch (Exception e) {
            System.out.println("Error getting row count");
            e.printStackTrace();
        }
        return count;
    }

}


