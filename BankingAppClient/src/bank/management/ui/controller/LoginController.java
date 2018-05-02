/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.controller;

import bank.management.exception.ManagerException;
import bank.management.ui.model.AccountBean;
import bank.management.ui.model.CustomerBean;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class LoginController extends GenericController {

    private static final String TITLE = "Log in";
    //private static final Logger LOGGER = Logger.getLogger("gestion_bancaria.UI.controller");
    
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSignIn;
    @FXML
    private TextField tfUserId;
    @FXML
    private PasswordField tfPassw;
    
    public void initStage(Parent root) {
        LOGGER.info("Initializing Login Window");
        
        // Set scene
        Scene scene = new Scene(root);
        if (stage == null) stage = new Stage();
        stage.setScene(scene);
        
        // Set stage properties
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        
        // On showing listener
        stage.setOnShowing(this::handleWindowShowing);
        
        // Graphical node listeners
        btnCancel.setOnAction(this::handleCancelBtnAction);
        tfUserId.textProperty().addListener(this::handleTextChange);
        tfPassw.textProperty().addListener(this::handleTextChange);
        btnSignIn.setOnAction(this::handleSignInAction);
       
        // Show stage
        stage.show();
    }
    
    private void handleWindowShowing(WindowEvent event) {
        btnSignIn.setDisable(true);
        tfUserId.requestFocus();
        tfUserId.setText("jzaballa");
        tfPassw.setText("jzaballa");
        btnSignIn.requestFocus();
    }
    
    private void handleCancelBtnAction(ActionEvent event) {
        Platform.exit();
    }
    
    private void handleTextChange(
            ObservableValue observable,
            String oldValue,
            String newValue) {
        if (tfUserId.getText().trim().length() > 0 && tfPassw.getText().trim().length() > 0)
            btnSignIn.setDisable(false);
        else btnSignIn.setDisable(true);
    }
    
    private void handleSignInAction(ActionEvent event) {
        try {
            CustomerBean customer = manager.getCustomer(tfUserId.getText()).get(0);
            customer = manager.authenticate(customer.getId().toString(), tfPassw.getText());
            List<AccountBean> accounts = manager.getCustomerAccounts(customer.getId().toString());
            session.put("activeUser", customer);
            session.put("customerAccounts", accounts);
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/bank/management/ui/view/main_window.fxml")
            );
            Parent root = (Parent) loader.load();
            MainWindowController controller = (MainWindowController)loader.getController();
            controller.setUsersManager(manager);
            controller.setSession(session);
            controller.initStage(root);
            stage.hide();
        } catch (ManagerException ex) {
            showErrorAlert(ex.getMessage());
        } catch (IOException ex) {
            showErrorAlert("Error loading window");
        }
    }
}
