/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import bank.management.ui.controller.LoginController;
import java.io.IOException;

/**
 *
 * @author ubuntu
 */
public class BankingApplication extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/view/log_in.fxml"));
        Parent root = (Parent) loader.load();

        LoginController controller = (LoginController)loader.getController();
        controller.setStage(stage);
        
        controller.initStage(root);
        
        //Parent root = FXMLLoader.load(getClass().getResource());
        
        //Scene scene = new Scene(root);
        
        //stage.setScene(scene);
        //stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
