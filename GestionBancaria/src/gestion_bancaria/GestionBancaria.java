/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gestion_bancaria.UI.controller.Log_inController;

/**
 *
 * @author ubuntu
 */
public class GestionBancaria extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/view/log_in.fxml"));
        Parent root = (Parent) loader.load();

        Log_inController controller = (Log_inController)loader.getController();
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
