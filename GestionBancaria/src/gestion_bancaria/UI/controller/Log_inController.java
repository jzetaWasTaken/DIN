/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria.UI.controller;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class Log_inController {

    private static final String TITLE = "Log in";
    private static final Logger LOGGER = Logger.getLogger("gestion_bancaria.UI.controller");
    private Stage stage;  
    
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSignIn;
    @FXML
    private TextField tfUserId;
    @FXML
    private PasswordField tfPassw;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public void initStage(Parent root) {
        LOGGER.info("Initializing Login Window");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOnShowing(this::handleWindowShowing);
        btnCancel.setOnAction(this::handleCancelBtnAction);
        tfUserId.textProperty().addListener(this::handleTextChange);
        tfPassw.textProperty().addListener(this::handleTextChange);
        stage.show();
    }
    
    private void handleWindowShowing(WindowEvent event) {
        btnSignIn.setDisable(true);
        tfUserId.requestFocus();
    }
    
    private void handleCancelBtnAction(ActionEvent event) {
        Platform.exit();
    }
    
    private void handleTextChange(ObservableValue observable,
            String oldValue,
            String newValue) {
        if (tfUserId.getText().trim().length() > 0 &&
                tfPassw.getText().trim().length() > 0)
            btnSignIn.setDisable(false);
        else btnSignIn.setDisable(true);
    }
}
