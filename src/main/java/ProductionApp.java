import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import production.RawMaterial;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductionApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(800);
        primaryStage.setHeight(900);

        primaryStage.setTitle("Production App");

        //_____________________________ TOP BUTTONS _________________________________________
        int fgNumber = Queries.countFG(); //NUMBER OF PRODUCTS
        String fgNum = Integer.toString(fgNumber);
        Button textProds = new Button("NUMBER OF PRODUCTS \n" + fgNum);
        textProds.setTextAlignment(TextAlignment.CENTER);
        //textProds.setFill(Color.rgb(223, 93, 87));
        textProds.setFont(Font.font("Times New Roman", 20));
        textProds.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

        int rmNumber = Queries.countMat(); // NUMBER OF MATERIALS
        String rmNum = Integer.toString(rmNumber);
        Button textMat = new Button("NUMBER OF MATERIALS \n" + rmNum);
        textMat.setTextAlignment(TextAlignment.CENTER);
        //textMat.setFill(Color.rgb(70, 3, 87));
        textMat.setFont(Font.font("Times New Roman", 20));
        textMat.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        //___________________________ PRODUCTS LIST _________________________________________
        final ListView<String> prodsList = new ListView<String>();
        // Queries.getProductName()
        List<String> productName = Queries.getProductName();
        for (int i = 0; i < productName.size(); i++) {
            prodsList.getItems().add(productName.get(i));
        }
        prodsList.setMaxHeight(200);

        //___________________________ FINAL PRICE____________________________________________________
        Button sellPrice=new Button("SELL PRICE");

        final Button priceOfSelectedProduct = new Button();

        //____________________________ MATERIALS LIST ________________________________________
        ListView<Object>space=new ListView<Object>();
        space.setMaxWidth(100);
        space.setMaxHeight(200);
        final ListView<String>containsMaterials =new ListView<String>();
        containsMaterials.setMaxHeight(200);
        containsMaterials.setMaxWidth(150);
        final ListView<Double>materialPrice = new ListView<Double>();
        materialPrice.setMaxHeight(200);
        materialPrice.setMaxWidth(150);

        prodsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                containsMaterials.getItems().clear();
                materialPrice.getItems().clear();
                priceOfSelectedProduct.setText("");
                double productPrice = 0.0;
                try {
                    List<RawMaterial> materialsOfSelectedProduct = Queries.getProductMaterials(prodsList.getSelectionModel().getSelectedItem());
                    for (int i = 0; i < materialsOfSelectedProduct.size(); i++) {
                        containsMaterials.getItems().add(materialsOfSelectedProduct.get(i).getNume());

                        materialPrice.getItems().add(materialsOfSelectedProduct.get(i).getPrice());

                        productPrice = Queries.getProductPrice(prodsList.getSelectionModel().getSelectedItem());
                        String prodPrice=Double.toString(productPrice);
                        priceOfSelectedProduct.setText(prodPrice);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //___________________________ H BOXES ______________________________________________________
        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(textProds, textMat);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setPadding(new Insets(50,0,0,0));

        HBox midHBox = new HBox();
        midHBox.getChildren().addAll(prodsList, space, containsMaterials,materialPrice);
        midHBox.setPadding(new Insets(80,0,0,0));
        midHBox.setAlignment(Pos.CENTER);

        HBox bottomHBox = new HBox();
        bottomHBox.getChildren().addAll(sellPrice,priceOfSelectedProduct);
        bottomHBox.setPadding(new Insets(130,0,0,0));
        bottomHBox.setAlignment(Pos.CENTER);


        // primaryStage.getIcons().add(new Image("src/main/java/app/icons/icon.png"));

        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(topHBox, midHBox,bottomHBox);
        Scene scene = new Scene(mainVBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Application stopped!");
    }
}
