package controllers;

import service.UserService;
import javafx.animation.TranslateTransition;
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
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    private UserService userService;
    public static String username;
    Connection cnx;
    Blend b = new Blend();
    BoxBlur bb = new BoxBlur();
/*
        Image back = new Image(getClass().getResourceAsStream("back.png"));
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
    private Label con;
    @FXML
    private Label id;
    @FXML
    private Label pw;
    @FXML
    private Label errorlbl;
    @FXML
    private AnchorPane anchlog;
    @FXML
    private AnchorPane anchgreet;
    @FXML
    private TextField idzone;
    @FXML
    private AnchorPane loginanc;
    @FXML
    private Button connect;
    @FXML
    private PasswordField passzone;
    @FXML
    private TextField idzone1;
    @FXML
    private Label bienvenue1;
    @FXML
    private Label signUp;
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
    void EnterKeyConnect(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            connect();
        }
    }

    @FXML
    void Seconnecter(MouseEvent event) {
        if (event.getClickCount() == 1)
            connect();
    }

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

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        this.userService = new UserService();

        anchlog.setVisible(true);
        //anchlog1.setVisible(false);
        /*eye.setImage(showpass);
        eye1.setImage(showpass); */
           }

    public void connect() {
        try {


            if (userService.userLogin(idzone.getText(), passzone.getText())) {
                username = idzone.getText();
                System.out.println(username);

                //open munulaoder

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuLoader.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                //stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                //scene.setFill(Color.TRANSPARENT);
                stage.show();
                /* javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2); */

                loginanc.getChildren().clear();
                loginanc.getScene().getWindow().hide();


            } else {

                errorlbl.setText("Username or password is incorrect !");
                idzone.setStyle("-fx-border-color:red");
                passzone.setStyle("-fx-border-color:red");

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @FXML
    private void signUp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();


        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Create an account");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addAcount(MouseEvent event) {
        String p1 = passzone1.getText();
        String p2 = passzone11.getText();
        if ((idzone1.getText().isEmpty()) || (passzone1.getText().isEmpty()) || (passzone11.getText().isEmpty())) {
            idzone11.setText("Fill up \nthe form !");

        } else if (!(p1.equals(p2))) {
            idzone11.setText("Both passwords arent identical !");

        } else {
            try {
                userService.addUser(idzone1.getText(),passzone1.getText());

                idzone1.setText("");
                passzone1.setText("");
                passzone11.setText("");
                idzone11.setText("Account created !");

            } catch (Exception ex) {
                ex.printStackTrace();
                idzone11.setText("Invalid data!");
                passzone11.setText("");
            }


        }


    }
    
}
