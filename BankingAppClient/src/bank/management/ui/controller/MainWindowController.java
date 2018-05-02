/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.controller;

import bank.management.exception.ManagerException;
import static bank.management.ui.controller.GenericController.LOGGER;
import bank.management.ui.model.AccountBean;
import bank.management.ui.model.CustomerBean;
import bank.management.ui.model.TransactionBean;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.swing.event.ChangeEvent;

/**
 * FXML Controller class
 *
 * @author jon
 */
public class MainWindowController extends GenericController {

    @FXML
    private TabPane tabs;
    @FXML
    private ComboBox cbAccountSelection;
    @FXML
    private Text lblAccountBalance;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem miSignOut;
    @FXML
    private Tab tabPayments;
    @FXML
    private TextField tfPaymentAmount;
    @FXML
    private TextArea tfPaymentDescription;
    @FXML
    private Button btnPaymentSubmit;
    @FXML
    private Button btnPaymentClear;
    @FXML
    private Tab tabDeposits;
    @FXML
    private Tab tabTransactions;
    @FXML
    private TextField tfDepositAmount;
    @FXML
    private TextArea tfDepositDescription;
    @FXML
    private Button btnDepositSubmit;
    @FXML
    private Button btnDepositClear;
    @FXML
    private Tab tabTransfers;
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
    private VBox vbTransactionDetails;
    @FXML
    private Button btnBack;
    @FXML
    private TableView tvTransactions;
    @FXML
    private TableColumn tcDate;
    @FXML
    private TableColumn tcDescription;
    @FXML
    private TableColumn tcAmount;
    @FXML
    private TableColumn tcBalance;
    @FXML
    private Pagination pagTransactions;
    @FXML
    private VBox vbTransactionFilters;
    @FXML
    private RadioButton rbAll;
    @FXML
    private RadioButton rbPayments;
    @FXML
    private RadioButton rbDeposits;
    @FXML
    private RadioButton rbTransfers;
    @FXML
    private RadioButton rbRecent;
    @FXML
    private RadioButton rbToday;
    @FXML
    private RadioButton rbLastWeek;
    @FXML
    private RadioButton rbLastMonth;
    @FXML
    private RadioButton rbBetweenDates;
    @FXML
    private DatePicker dpDateFrom;
    @FXML
    private DatePicker dpDateTo;
    @FXML
    private Button btnExit;
    
    private ToggleGroup toggleTransactionType;
    private ToggleGroup toggleTransactionDate;
    private static final String BTN_LABEL_BACK = "Back";
    private static final String BTN_LABEL_DETAIL = "Detail";
    private ObservableList<AccountBean> accounts;
    private ObservableList<TransactionBean> transactions;
    
    public void initStage(Parent root) {
        try {
            LOGGER.info("Initializing Login Window");

            // Set scene
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);

            // Set stage properties
            stage.setTitle("Main Window");
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);

            // On showing listener
            stage.setOnShowing(this::handleWindowShowing);
            
            // Initialize toggleGroups
            toggleTransactionType = new ToggleGroup();
            rbAll.setToggleGroup(toggleTransactionType);
            rbTransfers.setToggleGroup(toggleTransactionType);
            rbDeposits.setToggleGroup(toggleTransactionType);
            rbPayments.setToggleGroup(toggleTransactionType);
            
            toggleTransactionDate = new ToggleGroup();
            rbRecent.setToggleGroup(toggleTransactionDate);
            rbLastMonth.setToggleGroup(toggleTransactionDate);
            rbLastWeek.setToggleGroup(toggleTransactionDate);
            rbToday.setToggleGroup(toggleTransactionDate);
            rbBetweenDates.setToggleGroup(toggleTransactionDate);
            
            // Tabs
            tabDeposits.setOnSelectionChanged(this::handleDepositTab);
            tabDeposits.setClosable(true);
            tabPayments.setOnSelectionChanged(this::handlePaymentTab);
            tabPayments.setClosable(true);
            tabTransfers.setOnSelectionChanged(this::handleTransferTab);
            tabTransfers.setClosable(true);
            tabTransactions.setOnSelectionChanged(this::handleTransactionsTab);
            tabTransactions.setClosable(true);
            
            // Set accounts combo data model
            CustomerBean activeUser = (CustomerBean)session.get("activeUser");
            accounts = 
                    FXCollections.observableArrayList(manager.getCustomerAccounts(activeUser.getId().toString()));
            cbAccountSelection.setItems(accounts);
            cbAccountSelection.valueProperty().addListener(this::handleAccountChange);

            // Graphical node listeners
            btnExit.setOnAction(this::handleExitBtn);
            btnBack.setOnAction(this::handleBackBtn);
            btnDepositClear.setOnAction(this::handleDepositClearBtn);
            btnDepositSubmit.setOnAction(this::handleDepositSubmitBtn);
            btnPaymentClear.setOnAction(this::handlePaymentClearBtn);
            btnPaymentSubmit.setOnAction(this::handlePaymentSubmitBtn);
            btnTransferClear.setOnAction(this::handleTransferClearBtn);
            btnTransferSubmit.setOnAction(this::handleTransferSubmitBtn);
            miSignOut.setOnAction(this::handleSignOut);

            tfDepositAmount.textProperty().addListener(this::handleDepositFields);
            tfDepositAmount.textProperty().addListener(this::checkAmountNumeric);
            tfDepositDescription.textProperty().addListener(this::handleDepositFields);
            tfPaymentAmount.textProperty().addListener(this::handlePaymentFields);
            tfPaymentAmount.textProperty().addListener(this::checkAmountNumeric);
            //tfPaymentAmount.focusedProperty().addListener(this::handlePaymentAmountFocus);
            tfPaymentDescription.textProperty().addListener(this::handlePaymentFields);
            tfTransferAmount.textProperty().addListener(this::handleTransferFields);
            tfTransferAmount.textProperty().addListener(this::checkAmountNumeric);
            tfTransferDescriptionPayer.textProperty().addListener(this::handleTransferFields);
            tfTransferDescriptionRecipient.textProperty().addListener(this::handleTransferFields);
            tfTransferIban.textProperty().addListener(this::handleTransferFields);
            tfTransferPayerName.textProperty().addListener(this::handleTransferFields);
            tfTransferRecipientName.textProperty().addListener(this::handleTransferFields);
            
            // Transaction table
            tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tcBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
            tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tcDate.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TransactionBean, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<TransactionBean, String> param) {
                    return param.getValue().getFormattedDate();
                }
            });
            
            // Show stage
            stage.show();
        } catch (Exception e) {
            showErrorAlert("Error");
        }
    }
    public void handleWindowShowing(WindowEvent event) {
        // Tab by default
        tabs.getSelectionModel().select(tabPayments);
        // First account by default
        cbAccountSelection.getSelectionModel().selectFirst();
        updateBalance(new BigDecimal(0));
        // Disable payment buttons
        btnPaymentClear.setDisable(true);
        btnPaymentSubmit.setDisable(true);
        // MenuButton
        menuButton.setText(((CustomerBean)session.get("activeUser")).getFullName());
        // Details/Back button
        btnBack.setText(BTN_LABEL_DETAIL);
        btnBack.setDisable(true);
        // Disable unimplemented fields
        tfTransferDescriptionRecipient.setDisable(true);
        tfTransferPayerName.setDisable(true);
        tfTransferRecipientName.setDisable(true);
        // Detalle de página de transacciones
        vbTransactionDetails.setVisible(false);
        // Set focus
        Platform.runLater(()->tfPaymentAmount.requestFocus());
    }
    
    // TAB SELECTION
    public void handleDepositTab(Event event) {
        if (!tabDeposits.isSelected())
            clearDepositFields();
        else 
            Platform.runLater(()->tfDepositAmount.requestFocus());
    }
    public void handlePaymentTab(Event event) {
        if (!tabPayments.isSelected())
            clearPaymentFields();
        else 
            Platform.runLater(()->tfPaymentAmount.requestFocus());
    }
    public void handleTransferTab(Event event) {
        if (!tabTransfers.isSelected())
            clearTransferFields();
        else 
            Platform.runLater(()->tfTransferAmount.requestFocus());
    }
    
    public void handleTransactionsTab(Event event) {
        if (!tabTransactions.isSelected()) {
            if (transactions != null) transactions.clear();
        }
        else {
            try {
                System.out.println("Transaction Tab selected");
                AccountBean currentAccount = 
                        (AccountBean) cbAccountSelection.getSelectionModel().getSelectedItem();
                transactions = 
                        FXCollections.observableArrayList(manager.getAccountTransactions(currentAccount.getId().toString()));
                tvTransactions.setItems(transactions);
            } catch (ManagerException e) {
                showErrorAlert(e.getMessage());
            }
        }
    }
    
    // BUTTON HANDLERS
    public void handleExitBtn(ActionEvent event) {
        Platform.exit();
    }
    public void handleBackBtn(ActionEvent event) {
        
    }
    public void handleDepositClearBtn(ActionEvent event) {
        clearDepositFields();
    }
    public void handleDepositSubmitBtn(ActionEvent event) {
        try {
            TransactionBean deposit = new TransactionBean();
            double amount = Double.parseDouble(tfDepositAmount.getText());
            deposit.setAccount((AccountBean)cbAccountSelection.getSelectionModel().getSelectedItem());
            deposit.setAmount(new BigDecimal(amount));
            deposit.setDescription(tfDepositDescription.getText());
            manager.makeDeposit(deposit);
            showErrorAlert("Deposit made");
            updateBalance(deposit.getAmount());
            clearDepositFields();
            tfDepositAmount.requestFocus();
        } catch (ManagerException e) {
            showErrorAlert(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showErrorAlert("An error ocurred");
            e.printStackTrace();
        }
    }
    public void handlePaymentClearBtn(ActionEvent event) {
        clearPaymentFields();
    }
    public void handlePaymentSubmitBtn(ActionEvent event) {
        try {
            TransactionBean payment = new TransactionBean();
            double amount = Double.parseDouble(tfPaymentAmount.getText());
            payment.setAccount((AccountBean)cbAccountSelection.getSelectionModel().getSelectedItem());
            payment.setAmount(new BigDecimal(amount));
            payment.setDescription(tfPaymentDescription.getText());
            manager.makePayment(payment);
            showErrorAlert("Payment made");
            updateBalance(payment.getAmount().negate());
            clearPaymentFields();
        } catch (ManagerException e) {
            showErrorAlert(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showErrorAlert("An error ocurred");
            e.printStackTrace();
        }
    }
    public void handleTransferClearBtn(ActionEvent event) {
        clearTransferFields();
    }
    public void handleTransferSubmitBtn(ActionEvent event) {
        try {
            TransactionBean transfer = new TransactionBean();
            double amount = Double.parseDouble(tfTransferAmount.getText());
            transfer.setAccount((AccountBean)cbAccountSelection.getSelectionModel().getSelectedItem());
            transfer.setAmount(new BigDecimal(amount));
            transfer.setDescription(tfTransferDescriptionPayer.getText());
            String accountTo = tfTransferIban.getText();
            manager.makeTransfer(transfer, accountTo);
            showErrorAlert("Transfer made");
            updateBalance(transfer.getAmount().negate());
            clearTransferFields();
        } catch (ManagerException e) {
            showErrorAlert(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showErrorAlert("An error ocurred");
            e.printStackTrace();
        }
    }
    public void handleSignOut(ActionEvent event) {
        session.clear();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/bank/management/ui/view/log_in.fxml")
            );
            Parent root = (Parent)loader.load();
            //Get controller for graph 
            LoginController loginController=
                    ((LoginController)loader.getController());
            //Set a reference in UI controller para Bussiness Logic Controllesr
            loginController.setUsersManager(manager);
            //Set a reference for Stage
            loginController.setSession(manager.getSession());
            //Initializes primary stage
            loginController.initStage(root);
            stage.hide();
        } catch (IOException e) {
            showErrorAlert("Error");
        }
    }
    
    // ACCOUNT COMBO
    
    public void handleAccountChange(ObservableValue observable, Object oldValue, Object newValue) {
        updateBalance(new BigDecimal(0));
        if (!tabPayments.isSelected()) {
            tabs.getSelectionModel().select(tabPayments);
        }
        else clearPaymentFields();
        
    }
    
    // TEXT FIELD HANDLERS
    public void handleDepositFields(
            ObservableValue observable,
            String oldValue,
            String newValue) {
    }
    
    public void handlePaymentFields(
            ObservableValue observable,
            String oldValue,
            String newValue) {
        if (tfPaymentAmount.getText().trim().length() > 0 || tfPaymentDescription.getText().trim().length() > 0) {
            if (tfPaymentAmount.getText().trim().length() > 0) {
                btnPaymentClear.setDisable(false);
                btnPaymentSubmit.setDisable(false);
            } else {
                btnPaymentClear.setDisable(false);
                btnPaymentSubmit.setDisable(true);
            }
        }
        else {
            btnPaymentClear.setDisable(true);
                btnPaymentSubmit.setDisable(true);
        }
    }
    
    public void checkAmountNumeric(ObservableValue observable,String oldValue,String newValue) {
        if (newValue.matches("[^0-9]")) {
            if (tfPaymentAmount.isFocused())
                tfPaymentAmount.setText(newValue.replaceAll("\\D+", ""));
            else if (tfDepositAmount.isFocused())
                tfDepositAmount.setText(newValue.replaceAll("\\D+", ""));
            else if (tfTransferAmount.isFocused())
                tfTransferAmount.setText(newValue.replaceAll("\\D+", ""));
        }
    }
    public void handleTransferFields(
            ObservableValue observable,
            String oldValue,
            String newValue) {
        if (tfTransferAmount.getText().trim().length() > 0 || tfTransferDescriptionPayer.getText().trim().length() > 0) {
            if (tfTransferAmount.getText().trim().length() > 0) {
                btnTransferClear.setDisable(false);
                btnTransferSubmit.setDisable(false);
            } else {
                btnTransferClear.setDisable(false);
                btnTransferSubmit.setDisable(true);
            }
        }
        else {
            btnTransferClear.setDisable(true);
            btnTransferSubmit.setDisable(true);
        }
    }
    
    /*
    public void handlePaymentAmountFocus(
            ObservableValue observable,
            Boolean oldValue,
            Boolean newValue) {
        System.out.println("Focus listener");
        if (newValue.booleanValue()) {
            System.out.println("Focus is true");
            tfPaymentAmount.getStyleClass().clear();
            tfPaymentAmount.setStyle(null);
            tfPaymentAmount.getStyleClass().addAll("text-field", "text-input");
        }
    }
    */
    
    // PRIVATE METHODS
    public void clearPaymentFields() {
        tfPaymentAmount.setText("");
        tfPaymentDescription.setText("");
    }
    public void clearDepositFields() {
        tfDepositAmount.setText("");
        tfDepositDescription.setText("");
    }
    public void clearTransferFields() {
        tfTransferAmount.setText("");
        tfTransferDescriptionPayer.setText("");
        tfTransferDescriptionRecipient.setText("");
        tfTransferIban.setText("");
        tfTransferPayerName.setText("");
        tfTransferRecipientName.setText("");
    }

    private void updateBalance(BigDecimal amount) {
        AccountBean currentAccount = (AccountBean)cbAccountSelection
                .getSelectionModel()
                .getSelectedItem();
        currentAccount.setBalance(currentAccount.getBalance().add(amount));
        double balance = ((AccountBean)cbAccountSelection
                .getSelectionModel()
                .getSelectedItem())
                .getBalance().doubleValue();
        DecimalFormat df = new DecimalFormat("#.00");
        String balanceString = df.format(balance);
        lblAccountBalance.setText(String.format("%s €", balanceString));
    }
}
