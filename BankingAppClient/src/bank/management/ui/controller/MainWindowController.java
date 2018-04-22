/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class MainWindowController {

    @FXML
    private ComboBox<?> cbAccountSelection;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem miSignOut;
    @FXML
    private TextField tfPaymentAmount;
    @FXML
    private TextArea tfPaymentDescription;
    @FXML
    private TextField tfPaymentSuffix;
    @FXML
    private TextField tfPaymentRecipient;
    @FXML
    private TextField tfPaymentReference;
    @FXML
    private TextField tfPaymentId;
    @FXML
    private Button btnPaymentSubmit;
    @FXML
    private Button btnPaymentClear;
    @FXML
    private TextField tfDepositAmount;
    @FXML
    private TextArea tfDepositDescription;
    @FXML
    private Button btnDepositSubmit;
    @FXML
    private Button btnDepositClear;
    @FXML
    private TextField tfTransferAmount;
    @FXML
    private TextField tfTransferPayerName;
    @FXML
    private TextArea tfTransferDescriptionPayer;
    @FXML
    private Button btnTransferSubmit;
    @FXML
    private Button btnTransferClear;
    @FXML
    private TextField tfTransferRecipientName;
    @FXML
    private TextField tfTransferIban;
    @FXML
    private TextArea tfTransferDescriptionRecipient;
    @FXML
    private Button btnBack;
    @FXML
    private VBox vbFilters;
}
