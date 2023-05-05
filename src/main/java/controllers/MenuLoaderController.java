/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import utils.DataSource;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MenuLoaderController implements Initializable {
    /*
    Image haveNot = new Image(getClass().getResourceAsStream("../Ressources/images/notificationred.png"));
    Image donthaveNot = new Image(getClass().getResourceAsStream("../Ressources/images/notification.png"));
    Image traynot = new Image(getClass().getResourceAsStream("../Ressources/images/notificationg.png"));*/
    boolean test = false;
    Blend b = new Blend();
    InnerShadow sh = new InnerShadow();
    BoxBlur bb = new BoxBlur();
    boolean notclick = false;
    boolean dashclick = false;
    boolean medclick = false;
    boolean paraclick = false;
    boolean setclick = false;
    URL fileurl;
    private Pane view;
    private Label userid;
    private ImageView NotificationIcon;
    @FXML
    private Label mp;
    @FXML
    private Label lset;
    @FXML
    private AnchorPane anchset;
    @FXML
    private AnchorPane anchex;

    @FXML
    private AnchorPane mainanch;
    @FXML
    private AnchorPane anchmed;
    @FXML
    private Label lmed;
    @FXML
    private AnchorPane anchdash;
    @FXML
    private Label lnot;
    @FXML
    private AnchorPane anchside;
    @FXML
    private AnchorPane anchnot;
    @FXML
    private Label ldash;
    @FXML
    private Label lex;
    @FXML
    private BorderPane mainpan;
    @FXML
    private Pane pnot;
    @FXML
    private Pane pdash;
    @FXML
    private Pane pmed;
    @FXML
    private Pane pset;
    private Label time;
    private Label sec;
    private double xOffset = 0;
    private double yOffset = 0;

    public MenuLoaderController() {

    }

    static void player(String file) {

        File f = new File(file);
        Media media = new Media(f.toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(media);
        mediaplayer.play();
    }

    /*public void openPage(String path) throws IOException {
        Platform.runLater(() -> mainpan.getChildren().clear());
        Platform.runLater(() -> {
                    fileurl = PreLoader.class.getResource(path);
                    FXMLLoader f = new FXMLLoader();
                    try {
                        view = FXMLLoader.load(fileurl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainpan.setCenter(view);
                }
        );

    }*/
    
    @FXML
    void openProduct(MouseEvent event) throws IOException {

        notclick = true;
        dashclick = false;
        medclick = false;
        paraclick = false;
        setclick = false;
        selected(anchnot, lnot, pnot, "Produits");
        notselected(anchdash, ldash, pdash, "Tableau de bord");
        notselected(anchmed, lmed, pmed, "Fournisseurs");
        notselected(anchset, lset, pset, "Point de ventes");
        //openPage("../GUI/ProductUI.fxml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProductUI.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();


        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Manage Products");
        stage.setScene(scene);
        stage.show();

                /*Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/

        //mainanch.getChildren().clear();
        //mainanch.getScene().getWindow().hide();


    }

    @FXML
    void openFournisseur(MouseEvent event) throws IOException {


        notclick = false;
        dashclick = false;
        medclick = true;
        paraclick = false;
        setclick = false;
        selected(anchmed, lmed, pmed, "Fournisseur");
        notselected(anchnot, lnot, pnot, "Produits");
        notselected(anchdash, ldash, pdash, "Tableau de bord");
        notselected(anchset, lset, pset, "Point de ventes");
        //openPage("../GUI/Fournisseur.fxml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fournisseur.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();


        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Manage Fournisseur");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void openPointDeVente(MouseEvent event) throws IOException {
        notclick = false;
        dashclick = false;
        medclick = false;
        paraclick = false;
        setclick = true;

        selected(anchset, lset, pset, "Point de ventes");
        notselected(anchnot, lnot, pnot, "Produits");
        notselected(anchdash, ldash, pdash, "Tableau de bord");
        notselected(anchmed, lmed, pmed, "Fournisseurs");
        //openPage("../GUI/PointDeVente.fxml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PointDeVente.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();


        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Manage Pt de vente");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void openlogout(MouseEvent event) {
        try {
            Platform.runLater(() -> mainpan.getChildren().clear());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();



            Scene scene = new Scene(root, 320, 240);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            /*
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            Platform.setImplicitExit(true);
            stage.show();
            javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);*/
            Stage s = (Stage) mainanch.getScene().getWindow();
            s.close();

        } catch (IOException ex) {
        }

    }

    @FXML
    void openTableauDeBord(MouseEvent event) throws IOException {
        dashclick = true;
        notclick = false;
        medclick = false;
        paraclick = false;
        setclick = false;
        selected(anchdash, ldash, pdash, "Tableau de bord");
        notselected(anchnot, lnot, pnot, "Produits");
        notselected(anchmed, lmed, pmed, "Fournisseurs");
        notselected(anchset, lset, pset, "Point de ventes");
        //openPage("../GUI/DashBord.fxml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashBord.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();


        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Manage Products");
        stage.setScene(scene);
        stage.show();
    }

    public void selected(AnchorPane anch, Label lbl, Pane pane, String txt) {
        anch.setStyle("-fx-background-color :  #0a4128");
        anch.setEffect(sh);
        lbl.setText("  " + txt);
        lbl.setStyle("-fx-font-weight: bold");
        pane.setVisible(true);

    }

    public void notselected(AnchorPane anch, Label lbl, Pane pane, String txt) {
        anch.setEffect(b);
        anch.setStyle("-fx-background-color :  transparent");
        lbl.setText(" " + txt);
        pane.setVisible(false);


        lbl.setStyle(null);

    }

    void pressed(MouseEvent event) {
        Stage stage = (Stage) mainanch.getScene().getWindow();
        Parent root = mainanch.getScene().getRoot();

        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    void dragged(MouseEvent event) {
        Stage stage = (Stage) mainanch.getScene().getWindow();
        Parent root = mainanch.getScene().getRoot();


        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = DataSource.getInstance().getCnx();
             try {
            String t = conn.createStatement().executeQuery("Select strftime('%d-%m-%Y','now')").getString(1);
            time.setText(t);
        } catch (Exception e) {
        }
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            try {
                String s = conn.createStatement().executeQuery("SELECT substr(datetime(CURRENT_TIMESTAMP, 'localtime'),12,8)").getString(1);
                sec.setText(s);
            } catch (Exception e) {
            }
        }));
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();


        try {
            slideanimation();
        } catch (IOException ex) {
            Logger.getLogger(MenuLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        anchnot.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                selected(anchnot, lnot, pnot, "Produits");
            } else if ((oldValue) && (!notclick)) {

                notselected(anchnot, lnot, pnot, "Produits");

            }
        });
        anchdash.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                selected(anchdash, ldash, pdash, "Tableau de bord");
            } else if ((oldValue) && (!dashclick)) {

                notselected(anchdash, ldash, pdash, "Tableau de bord");

            }
        });


        anchmed.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                selected(anchmed, lmed, pmed, "Fournisseurs");
            } else if ((oldValue) && (!medclick)) {

                notselected(anchmed, lmed, pmed, "Fournisseurs");

            }
        });

        anchset.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selected(anchset, lset, pset, "Point de ventes");
            } else if ((oldValue) && (!setclick)) {
                notselected(anchset, lset, pset, "Point de ventes");
            }
        });
}
    
    public void slideanimation() throws IOException {
        mainanch.setVisible(true);
        pnot.setVisible(false);
        pmed.setVisible(false);
        pset.setVisible(false);
        if (notclick) {
            pnot.setVisible(true);
        }
        if (dashclick) {
            pdash.setVisible(true);
        }
        if (medclick) {
            pmed.setVisible(true);
        }
        if (setclick) {
            pset.setVisible(true);
        }

        TranslateTransition slidem = new TranslateTransition();
        slidem.setDuration(Duration.seconds(0.25));
        slidem.setNode(anchside);
        slidem.setToX(-312);
        slidem.play();
        slidem.setOnFinished((e -> {
        }));
        mp.setText("Main Menu");
        ldash.setText(" Tableau de bord");

        anchside.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.25));
                slide.setNode(anchside);
                slide.setToX(0.5);
                slide.play();
                slide.setOnFinished((e -> {
                }));
                mp.setText(" Main Menu");
                mainpan.setEffect(bb);
            } else {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.25));
                slide.setNode(anchside);
                slide.setToX(-312);
                slide.play();
                slide.setOnFinished((e -> {
                }));
                mp.setText("Main Menu");
                mainpan.setEffect(b);
                if ((!notclick) && (!dashclick) && (!setclick) && (!medclick) && (!paraclick)) {
                    anchdash.setStyle("-fx-background-color :  #0a4128");
                    anchdash.setEffect(sh);
                    ldash.setStyle("-fx-font-weight: bold");
                    ldash.setText(" Tableau de bord");
                    pdash.setVisible(true);
                }

            }
        });
        anchex.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                anchex.setStyle("-fx-background-color :  #606060");
                anchex.setEffect(sh);
                lex.setStyle("-fx-font-weight: bold");
                lex.setText("Se déconnecter");

            } else {
                anchex.setEffect(b);
                anchex.setStyle("-fx-background-color :  transparent");
                lex.setText("Se déconnecter");
                lex.setStyle(null);

            }
        });
        dashclick = true;
        //openPage("../GUI/DashBord.fxml");


    }
}   
    

