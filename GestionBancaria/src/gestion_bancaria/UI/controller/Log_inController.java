/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria.UI.controller;

import java.awt.Button;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private static final Logger logger = Logger.getLogger("gestion_bancaria.UI.controller");
    private Stage stage;  
    
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSignIn;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public void initStage(Parent root) {
        logger.info("Initializing");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOnShowing(this::handleWindowShowing);
    }
    
    private void handleWindowShowing(WindowEvent event) {
        btnSignIn.setEnabled(false);
        stage.show();
    }
}
