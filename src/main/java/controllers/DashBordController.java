/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import Service.ServiceStock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.ProduitService;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {
    @FXML
    private Label nb14;
    @FXML
    private Label nb13;
    @FXML
    private Label nb12;
    @FXML
    private Label nb11;
    @FXML
    private Label nb10;



    public DashBordController() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ServiceStock serviceStock = new ServiceStock();
        ProduitService produitService = new ProduitService();
        try {

            //produit
            nb10.setText(String.valueOf(produitService.getProducts().stream().count()));

            //products in stock
            Integer productsInStock = Math.toIntExact(produitService.getProductsDisponible().stream().count());
            nb11.setText(productsInStock.toString());

            //hors stock
            Integer countHorsStock = Math.toIntExact(produitService.getProductsEnRuptureDeStock().stream().count());
            nb12.setText(countHorsStock.toString());


        } catch (Exception e) {}

    }

}
