/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management;

import bank.management.logic.Manager;
import bank.management.logic.ManagerFactory;
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
    public void start(Stage primaryStage) throws Exception {
        //Create Bussines Logic Controller to be passed to UI controllers
        Manager bussinessLogicController= ManagerFactory.getManager();
        //Load node graph from fxml file
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("ui/view/log_in.fxml")
        );
        Parent root = (Parent)loader.load();
        //Get controller for graph 
        LoginController primaryStageController=
                ((LoginController)loader.getController());
        //Set a reference in UI controller para Bussiness Logic Controllesr
        primaryStageController.setUsersManager(bussinessLogicController);
        //Set a reference for Stage
        primaryStageController.setSession(bussinessLogicController.getSession());
        primaryStageController.setStage(primaryStage);
        //Initializes primary stage
        primaryStageController.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
