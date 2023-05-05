/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;


import entite.Fournisseur;
import entite.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import service.FournisseurService;
import service.ProduitService;
import service.UserService;
import utils.ModelTableFournisseur;
import utils.ModelTableProduct;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class FournisseurController  {
    boolean status = false;

    private final ObservableList<String> catinfo = FXCollections.observableArrayList("médicament");
    private final ObservableList<ModelTableFournisseur> oblist = FXCollections.observableArrayList();
    @FXML
    private Button connect;
    @FXML
    private PasswordField passzone;
    @FXML
    private TextField idzone;
    @FXML
    private AnchorPane superanch;
    @FXML
    private AnchorPane anchcnx;
    @FXML
    private AnchorPane anchset;
    @FXML
    private Label note;
    @FXML
    private Button btndelete;

    @FXML
    private Button btnEdit;
    @FXML
    private Label deleteerror;

    @FXML
    private Label editerror;
    @FXML
    private Label adderror;
    @FXML
    private Button btnadd;
    @FXML
    private ComboBox<String> catcombo;
    @FXML
    private TextField idcat;

    @FXML
    private TextField idcatEdit;
    @FXML
    private TextField designation;

    @FXML
    private TextField category;
    @FXML
    private TextField quantity;
    @FXML
    private TableColumn<ModelTableFournisseur, String> des;
    @FXML
    private TableView<ModelTableFournisseur> tab;
    @FXML
    private TableColumn<ModelTableFournisseur, String> cat;
    @FXML
    private TextField iddes;

    @FXML
    private TextField iddesEdit;

    @FXML
    private TextField idQteEdit;
    ProduitService produitService = new ProduitService();
    UserService userService = new UserService();
    @FXML
    private Label id;
    @FXML
    private Label pw;
    @FXML
    private TableColumn<?, ?> Qte;

    private String productName;

    FournisseurService fournisseurService = new FournisseurService();

    @FXML
    void tabclick(MouseEvent event) {
        if (tab.getSelectionModel().getSelectedItem() != null) {
            iddes.setText(tab.getSelectionModel().getSelectedItem().getNom());
            btndelete.setDisable(false);

            iddesEdit.setText(tab.getSelectionModel().getSelectedItem().getNom());
            idcatEdit.setText(tab.getSelectionModel().getSelectedItem().getCat());
            idQteEdit.setText(tab.getSelectionModel().getSelectedItem().getContact());
            productName = tab.getSelectionModel().getSelectedItem().getNom();
            btnEdit.setDisable(false);
        }
    }

    @FXML
    void add(MouseEvent event) {

        boolean test = true;
        //verifier s'il ya des champs vides
        if ((designation.getText().isEmpty()) || ((quantity.getText().isEmpty()))) {
            adderror.setText("Données manquantes !");
            // Mee  MenuLoaderController.player("src/Ressources/media/error.mp3");
            test = false;
        }
        if (test == true) {
            try {

                // inserer les donnees saisies dans la table usertbl en utilisant l'interface PreparedStatement

                fournisseurService.addFournisseur(designation.getText(),category.getText(),quantity.getText());

                //afficher tous les elements de la base  + vider les champs + afficher un message
                adderror.setText("Fournisseur Added ");

                //catcombo.getSelectionModel().clearSelection();
                designation.setText("");
                quantity.setText("");
                category.setText("");
                deleteerror.setText("");


            } catch (Exception e) {
                adderror.setText("2 Fournisseur cant have the same NAME");
                System.out.println(e.getMessage());
            }

        }
        refresh();


    }

    @FXML
    void delete(MouseEvent event) {
        boolean allow = true;


        if (allow == true) {
            Stage stage = (Stage) superanch.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("This Action is irreversable !");
            alert.getDialogPane().setContentText("Would you like to continue with this action ?");
            Optional<ButtonType> result = alert.showAndWait();
            boolean test = false;
            if (result.get() == ButtonType.OK) {
                if (test == false)
                    try {
                        // delete product


                        fournisseurService.deleteSupplier(iddes.getText());

                        deleteerror.setText("Fournisseur deleted ! ");
                        refresh();
                        iddes.setText("");
                        adderror.setText("");

                        btndelete.setDisable(true);

                    } catch (Exception e) {
                        deleteerror.setText("Error !");
                        refresh();
                        adderror.setText("");
                    }
            } else if (result.get() == ButtonType.CANCEL) {

                refresh();
                deleteerror.setText("Action Canceled!");
                adderror.setText("");

            }
        }
        refresh();


    }

    @FXML
    void edit(MouseEvent event) {
        boolean allow = true;


        if (allow == true) {
            Stage stage = (Stage) superanch.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Tou are about to edit this product");
            alert.getDialogPane().setContentText("Would you like to continue with this action ?");
            Optional<ButtonType> result = alert.showAndWait();
            boolean test = false;
            if (result.get() == ButtonType.OK) {
                if (test == false)
                    try {
                        // delete product


                        fournisseurService.updateFournisseur(iddesEdit.getText(),productName,idcatEdit.getText(),idQteEdit.getText());

                        editerror.setText("Fournisseur updated ! ");
                        refresh();
                        iddesEdit.setText("");
                        idcatEdit.setText("");
                        idQteEdit.setText("");
                        productName = "";
                        adderror.setText("");

                        btnEdit.setDisable(true);

                    } catch (Exception e) {
                        editerror.setText("Error !");
                        refresh();
                        adderror.setText("");
                    }
            } else if (result.get() == ButtonType.CANCEL) {

                refresh();
                editerror.setText("Action Canceled!");
                adderror.setText("");

            }
        }
        refresh();


    }


    public void connect() {
        try {
            boolean userExists = userService.userLogin(idzone.getText(), passzone.getText());
            if ((userExists && ((idzone.getText().equals("admin")) || (idzone.getText().equals("dev"))))) {
                anchcnx.setVisible(false);
                anchset.setVisible(true);

            } else {
                //Password incorrect message
                note.setText("Username or Password is incorrect !");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void EnterKeyConnect(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            connect();
        }
    }

    @FXML
    void Seconnecter(MouseEvent event) {
        connect();
    }

    private void refresh() {//refresh tab
        try {
            tab.getItems().clear();
            ArrayList<Fournisseur> allProducts = fournisseurService.getAllFournisseur();
            for (Fournisseur product: allProducts) {
                ModelTableFournisseur tableList = new ModelTableFournisseur(product.getNom(), product.getCategorie(),product.getContact());
                oblist.add(tableList);
            }
            System.out.println(oblist.get(0));
            des.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
            Qte.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tab.setItems(oblist);
        } catch (SQLException ex) {}
    }

    @FXML
    private void showAll() {//refresh tab
        try {
            tab.getItems().clear();
            ArrayList<Fournisseur> allProducts = fournisseurService.getAllFournisseur();
            for (Fournisseur product: allProducts) {
                ModelTableFournisseur tableList = new ModelTableFournisseur(product.getNom(), product.getCategorie(),product.getContact());
                oblist.add(tableList);
            }
            System.out.println(oblist.get(0));
            des.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
            Qte.setCellValueFactory(new PropertyValueFactory<>("contact"));
            tab.setItems(oblist);
        } catch (SQLException ex) {}
    }
}
