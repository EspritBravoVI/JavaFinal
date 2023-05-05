package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.UserService;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class SignUpController  {

    private UserService userService = new UserService();
    public static String username;

    Connection cnx;
    Blend b = new Blend();
    BoxBlur bb = new BoxBlur();
    /*
    Image back = new Image(getClass().getResourceAsStream("../Ressources/images/back.png"));
    Image backfliped = new Image(getClass().getResourceAsStream("../Ressources/images/backfliped.png"));
    Image backblur = new Image(getClass().getResourceAsStream("../Ressources/images/backblur.png"));
    Image backblurfliped = new Image(getClass().getResourceAsStream("../Ressources/images/backblurfliped.png"));
    Image backpan = new Image(getClass().getResourceAsStream("../Ressources/images/backpan.png"));
    Image backpanfliped = new Image(getClass().getResourceAsStream("../Ressources/images/backpanfliped.png"));
    Image backpanblur = new Image(getClass().getResourceAsStream("../Ressources/images/backpanblur.png"));
    Image backpanblurfliped = new Image(getClass().getResourceAsStream("../Ressources/images/backpanblurfliped.png"));
    Image showpass = new Image(getClass().getResourceAsStream("../Ressources/images/showpass.png"));
    Image hidepass = new Image(getClass().getResourceAsStream("../Ressources/images/hidepass.png"));*/
    boolean status = false;
    @FXML
    private ImageView eye;
    @FXML
    private Label bienvenue;
    @FXML
    private AnchorPane anchgreet;
    @FXML
    private AnchorPane loginanc;

    @FXML
    private PasswordField passzone;
    @FXML
    private TextField idzone1;
    @FXML
    private Label bienvenue1;
    @FXML
    private AnchorPane anchlog1;
    @FXML
    private TextField idzone11;
    @FXML
    private Label con1;
    @FXML
    private ImageView eye1;
    @FXML
    private Label id1;
    @FXML
    private Label pw1;
    @FXML
    private PasswordField passzone1;
    @FXML
    private Label pw11;
    @FXML
    private PasswordField passzone11;
    @FXML
    private Button addAcount;

    @FXML
    private Label errorlbl;

    @FXML
    private Label donelbl;

    @FXML
    void ShowHidePassClicked(MouseEvent event) {

        if (!status) {
            //eye.setImage(hidepass);
            status = true;
            //hide pass
            passzone.setPromptText(passzone.getText());
            passzone.setText("");
            passzone.setDisable(true);
        } else {
            //eye.setImage(showpass);
            status = false;
            //show pass
            passzone.setText(passzone.getPromptText());
            passzone.setDisable(false);
        }
    }

    @FXML
    private void addAcount(MouseEvent event) {
        String p1 = passzone1.getText();
        String p2 = passzone11.getText();
        if ((idzone1.getText().isEmpty()) || (passzone1.getText().isEmpty()) || (passzone11.getText().isEmpty())) {
            idzone11.setText("Fill up \nthe form !");

        } else if (!(p1.equals(p2))) {
            //idzone11.setText("");
            errorlbl.setText("Both passwords arent identical !");
            passzone11.setStyle("-fx-border-color:red");
            passzone1.setStyle("-fx-border-color:red");
        } else {
            try {
                userService.addUser(idzone1.getText(),passzone1.getText());

                idzone1.setText("");
                passzone1.setText("");
                passzone11.setText("");
                passzone11.setStyle("-fx-border-color:black");
                passzone1.setStyle("-fx-border-color:black");
                errorlbl.setText("");
                donelbl.setText("Account created Please close this window and login with your new credentials !");



            } catch (Exception ex) {
                ex.printStackTrace();
                errorlbl.setText("Username already taken !");
                passzone11.setText("");
                passzone1.setText("");
            }

        }

    }

}
