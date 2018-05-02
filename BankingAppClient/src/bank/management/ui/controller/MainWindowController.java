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
import bank.management.ui.model.TransactionType;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author jon
 */
public class MainWindowController extends GenericController {
    
    @FXML
    private MenuItem mbiSignOut;
    @FXML
    private MenuItem mbiClose;
    @FXML
    private MenuItem mbiHelp;
    @FXML
    private MenuItem mbiReport;
    @FXML
    private MenuItem mbiAbout;
    @FXML
    private VBox vbTable;
    @FXML
    private Label lblTransactionDate;
    @FXML
    private Label lblTransactionDescription;
    @FXML
    private Label lblTransactionBalance;
    @FXML
    private Label lblTransactionType;
    @FXML
    private Label lblTransactionAmount;
    @FXML
    private Label lblFrom;
    @FXML
    private Label lblTo;
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
    private RadioButton rbAny;
    @FXML
    private RadioButton rbToday;
    @FXML
    private RadioButton rbThisWeek;
    @FXML
    private RadioButton rbThisMonth;
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
    private ObservableList<AccountBean> accountsData;
    private FilteredList<TransactionBean> transactionsData;
    
    private static final int ITEMS_PER_PAGE = 13;
    private static final int MAX_PAGE_INDICATOR = 5;
    
    public void initStage(Parent root) {
        try {
            LOGGER.info("Initializing Main Window");
            
            // Set scene
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            
            // Set stage properties
            stage.setTitle("TrutxaBank");
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);
            
            // On showing listener
            stage.setOnShowing(this::handleWindowShowing);
            
            // Initialize toggleGroups
            toggleTransactionType = new ToggleGroup();
            toggleTransactionType.selectedToggleProperty()
                    .addListener(this::handleTransactionFilters);
            rbAll.setToggleGroup(toggleTransactionType);
            rbTransfers.setToggleGroup(toggleTransactionType);
            rbDeposits.setToggleGroup(toggleTransactionType);
            rbPayments.setToggleGroup(toggleTransactionType);
            
            toggleTransactionDate = new ToggleGroup();
            toggleTransactionDate.selectedToggleProperty()
                    .addListener(this::handleTransactionFilters);
            rbAny.setToggleGroup(toggleTransactionDate);
            rbThisMonth.setToggleGroup(toggleTransactionDate);
            rbThisWeek.setToggleGroup(toggleTransactionDate);
            rbToday.setToggleGroup(toggleTransactionDate);
            rbBetweenDates.setToggleGroup(toggleTransactionDate);
            
            // DatePickers
            dpDateFrom.valueProperty().addListener(this::handleDatePickers);
            dpDateTo.valueProperty().addListener(this::handleDatePickers);
            
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
            accountsData =
                    FXCollections.observableArrayList(
                            manager.getCustomerAccounts(activeUser.getId().toString())
                    );
            cbAccountSelection.setItems(accountsData);
            cbAccountSelection.valueProperty().addListener(this::handleAccountChange);
            
            // Graphical node listeners
            btnExit.setOnAction(this::handleExitBtn);
            btnExit.setMnemonicParsing(true);
            btnExit.setText("_Exit");
            btnExit.setTooltip(new Tooltip("Press to exit the application"));
            
            btnBack.setOnAction(this::handleBackBtn);
            btnBack.setMnemonicParsing(true);
            btnBack.setText("_Back");
            btnBack.setTooltip(new Tooltip("Press to hide transaction details"));
            
            btnDepositClear.setOnAction(this::handleDepositClearBtn);
            btnDepositClear.setMnemonicParsing(true);
            btnDepositClear.setText("_Clear");
            btnDepositClear.setTooltip(new Tooltip("Press to clear the fields"));
            
            btnDepositSubmit.setOnAction(this::handleDepositSubmitBtn);
            btnDepositSubmit.setMnemonicParsing(true);
            btnDepositSubmit.setText("_Submit");
            btnDepositSubmit.setTooltip(new Tooltip("Press to make a deposit"));
            
            btnPaymentClear.setOnAction(this::handlePaymentClearBtn);
            btnPaymentClear.setMnemonicParsing(true);
            btnPaymentClear.setText("_Clear");
            btnPaymentClear.setTooltip(new Tooltip("Press to clear the fields"));
            
            btnPaymentSubmit.setOnAction(this::handlePaymentSubmitBtn);
            btnPaymentSubmit.setMnemonicParsing(true);
            btnPaymentSubmit.setText("_Submit");
            btnPaymentSubmit.setTooltip(new Tooltip("Press to make a payment"));
            
            btnTransferClear.setOnAction(this::handleTransferClearBtn);
            btnTransferClear.setMnemonicParsing(true);
            btnTransferClear.setText("_Clear");
            btnTransferClear.setTooltip(new Tooltip("Press to clear the fields"));
            
            btnTransferSubmit.setOnAction(this::handleTransferSubmitBtn);
            btnTransferSubmit.setMnemonicParsing(true);
            btnTransferSubmit.setText("_Submit");
            btnTransferSubmit.setTooltip(new Tooltip("Press to make a transfer"));
            
            miSignOut.setOnAction(this::handleSignOut);
            mbiSignOut.setOnAction(this::handleSignOut);
            mbiClose.setOnAction(this::handleExitBtn);
            mbiHelp.setOnAction(this::handleHelp);
            mbiAbout.setOnAction(this::handleAbout);
            mbiReport.setOnAction(this::handleReport);
            
            tfDepositAmount.textProperty().addListener(this::handleDepositFields);
            tfDepositAmount.textProperty().addListener(this::checkAmountNumeric);
            tfDepositDescription.textProperty().addListener(this::handleDepositFields);
            tfPaymentAmount.textProperty().addListener(this::handlePaymentFields);
            tfPaymentAmount.textProperty().addListener(this::checkAmountNumeric);
            tfPaymentDescription.textProperty().addListener(this::handlePaymentFields);
            tfTransferAmount.textProperty().addListener(this::handleTransferFields);
            tfTransferAmount.textProperty().addListener(this::checkAmountNumeric);
            tfTransferDescriptionPayer.textProperty().addListener(this::handleTransferFields);
            tfTransferDescriptionRecipient.textProperty().addListener(this::handleTransferFields);
            tfTransferIban.textProperty().addListener(this::handleTransferFields);
            tfTransferPayerName.textProperty().addListener(this::handleTransferFields);
            tfTransferRecipientName.textProperty().addListener(this::handleTransferFields);
            
            // Transaction table
            tvTransactions.getSelectionModel().selectedItemProperty().addListener(this::handleTableSelection);
            tvTransactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tvTransactions.setPlaceholder(new Label("No transactions to show"));
            tcAmount.setCellValueFactory(
                    new Callback<CellDataFeatures<TransactionBean, String>, ObservableValue<String>>(){
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<TransactionBean, String> param) {
                            return param.getValue().getSignedAmount();
                        }
                        
                    });
            tcBalance.setCellValueFactory(new Callback<CellDataFeatures<TransactionBean, String>, ObservableValue<String>>(){
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<TransactionBean, String> param) {
                            return param.getValue().getFormattedBalance();
                        }
                    });
            tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tcDate.setCellValueFactory(
                    new Callback<CellDataFeatures<TransactionBean, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<TransactionBean, String> param) {
                            return param.getValue().getFormattedDate();
                        }
                    });
            pagTransactions.setPageFactory(this::createPage);
            
            // Show stage
            stage.show();
        } catch (Exception e) {
            showErrorAlert("Error loading window");
        }
    }
    
    public void handleWindowShowing(WindowEvent event) {
        // Default radios
        rbAll.setSelected(true);
        rbAny.setSelected(true);
        // Tab by default
        tabs.getSelectionModel().select(tabPayments);
        // First account by default
        cbAccountSelection.getSelectionModel().selectFirst();
        updateBalance(new BigDecimal(0));
        // Disable buttons
        btnPaymentClear.setDisable(true);
        btnPaymentSubmit.setDisable(true);
        btnTransferClear.setDisable(true);
        btnTransferSubmit.setDisable(true);
        btnDepositClear.setDisable(true);
        btnDepositSubmit.setDisable(true);
        // MenuButton
        menuButton.setText(((CustomerBean)session.get("activeUser")).getFullName());
        // Details/Back button
        btnBack.setVisible(false);
        // Disable unimplemented fields
        tfTransferDescriptionRecipient.setDisable(true);
        tfTransferPayerName.setDisable(true);
        tfTransferRecipientName.setDisable(true);
        mbiAbout.setDisable(true);
        // Detalle de página de transacciones
        vbTransactionDetails.setVisible(false);
        // DatePicker
        makeDatepickersVisible(false);
        // Pagination max page indicator
        pagTransactions.setMaxPageIndicatorCount(MAX_PAGE_INDICATOR);
        // Set focus
        Platform.runLater(()->tfPaymentAmount.requestFocus());
    }
    
    public void handleAbout(ActionEvent event) {
        
    }
    
    public void handleReport(ActionEvent event) {
        try {
            JasperReport report =
                    JasperCompileManager.compileReport(
                            getClass().getResourceAsStream("/bank/management/ui/report/transactionsreport.jrxml")
                    );
            AccountBean currentAccount =
                        (AccountBean) cbAccountSelection.getSelectionModel().getSelectedItem();
            JRBeanCollectionDataSource dataItems=
                    new JRBeanCollectionDataSource((List<TransactionBean>)manager.getAccountTransactions(currentAccount.getId().toString()));
            //Map of parameter to be passed to the report
            Map<String,Object> parameters=new HashMap<>();
            //Fill report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,dataItems);
            //Create and show the report window. The second parameter false value makes 
            //report window not to close app.
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            jasperViewer.setVisible(true);
            // jasperViewer.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        } catch (JRException e) {
            showErrorAlert("Error printing report");
        } catch (ManagerException e) {
            showWarningAlert(e.getMessage());
        }
    } 
    
    public void handleHelp(ActionEvent event) {
        try {
        FXMLLoader loader=
                new FXMLLoader(getClass().getResource("/bank/management/ui/view/help.fxml"));
        Parent root = (Parent)loader.load();
        HelpController helpController=
                ((HelpController)loader.getController());
        //Initializes and shows help stage
        helpController.initAndShowStage(root);
        } catch (Exception e) {
            showErrorAlert("Error loading help window");
        }
    }
    
    // TABLE AND PAGINATION
    public void handleTableSelection(ObservableValue observable, Object oldValue, Object newValue) {
        vbTransactionFilters.setVisible(false);
        vbTransactionDetails.setVisible(true);
        if (newValue != null) {
            vbTransactionFilters.setVisible(false);
            vbTransactionDetails.setVisible(true);
            TransactionBean transaction = (TransactionBean) newValue;
            System.out.println(transaction);
            lblTransactionBalance.setText(transaction.getFormattedBalance().get());
            lblTransactionAmount.setText(transaction.getSignedAmount().get());
            lblTransactionDate.setText(transaction.getFormattedDate().get());
            lblTransactionDescription.setText(transaction.getDescription());
            lblTransactionType.setText(transaction.getType().name());
            btnBack.setVisible(true);
        }
    }
    
    public Node createPage(int pageIndex) {
        System.out.println("Now");
        if (transactionsData != null) {
            int fromIndex = pageIndex * ITEMS_PER_PAGE;
            int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, transactionsData.size());
            tvTransactions.setItems(FXCollections.observableArrayList(transactionsData.subList(fromIndex, toIndex)));
            return new BorderPane(tvTransactions);
        }
        return new BorderPane();
    }

    // DATEPICKERS
    public void handleDatePickers(ObservableValue observable, Object oldValue, Object newValue) {
        if (rbBetweenDates.isSelected() && dpDateFrom.getValue() != null && dpDateTo.getValue() != null) {
            if (transactionsData != null) {
                transactionsData.setPredicate(
                    getPredicate(getTransactionType(), getTransactionDate(true))
                );
                setPagination();
                pagTransactions.setPageFactory(this::createPage);
            }
        }
    }
    
    // TRANSACTIONS FILTERS
    public void handleTransactionFilters(ObservableValue observable, Object oldValue, Object newValue) {
        if (rbBetweenDates.isSelected())
            makeDatepickersVisible(true);
        else {
            makeDatepickersVisible(false);
            if (transactionsData != null) {
                transactionsData.setPredicate(
                        getPredicate(getTransactionType(), getTransactionDate(false))
                );
                setPagination();
                pagTransactions.setPageFactory(this::createPage);
            }
        }
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
        if (tabTransactions.isSelected()) {
            try {
                System.out.println("Transaction Tab selected");
                AccountBean currentAccount =
                        (AccountBean) cbAccountSelection.getSelectionModel().getSelectedItem();
                transactionsData = new FilteredList<> (
                        FXCollections.observableArrayList(manager.getAccountTransactions(currentAccount.getId().toString())), predicate->true
                );
                setPagination();
                if (!rbAll.isSelected())
                    rbAll.setSelected(true);
                if (!rbAny.isSelected())
                    rbAny.setSelected(true);
            } catch (ManagerException e) {
                showWarningAlert(e.getMessage());
            }
        }
    }

    private void setPagination() {
        System.out.println("setPagination");
        pagTransactions.setPageCount(transactionsData.size()/ITEMS_PER_PAGE+1);
        pagTransactions.setCurrentPageIndex(0);
        vbTable.getChildren().add(new BorderPane(pagTransactions));
    }
    
    // BUTTON HANDLERS
    public void handleExitBtn(ActionEvent event) {
        Optional<ButtonType> result = showConfirmationAlert("Do you really want to exit?");
        if (result.get() == ButtonType.YES) Platform.exit();
    }
    public void handleBackBtn(ActionEvent event) {
        tvTransactions.getSelectionModel().clearSelection();
        btnBack.setVisible(false);
        vbTransactionDetails.setVisible(false);
        vbTransactionFilters.setVisible(true);
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
            showInfoAlert("Deposit made");
            updateBalance(deposit.getAmount());
            clearDepositFields();
            tfDepositAmount.requestFocus();
        } catch (ManagerException e) {
            showWarningAlert(e.getMessage());
            clearDepositFields();
        } catch (Exception e) {
            showErrorAlert("Unexpected error");
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
            showInfoAlert("Payment made");
            updateBalance(payment.getAmount().negate());
            clearPaymentFields();
        } catch (ManagerException e) {
            showWarningAlert(e.getMessage());
            clearPaymentFields();
        } catch (Exception e) {
            showErrorAlert("Unexpected error");
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
            showInfoAlert("Transfer made");
            updateBalance(transfer.getAmount().negate());
            clearTransferFields();
        } catch (ManagerException e) {
            showWarningAlert(e.getMessage());
            clearTransferFields();
        } catch (Exception e) {
            showErrorAlert("Unexpected error");
        }
    }
    public void handleSignOut(ActionEvent event) {
        session.clear();
        try {
            Optional<ButtonType> result = showConfirmationAlert("Do you really want to sign out?");
            if (result.get() == ButtonType.YES) {
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
            }
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
        if (tfDepositAmount.getText().trim().length() > 0 || tfDepositDescription.getText().trim().length() > 0) {
            if (tfDepositAmount.getText().trim().length() > 0) {
                btnDepositClear.setDisable(false);
                btnDepositSubmit.setDisable(false);
            } else {
                btnDepositClear.setDisable(false);
                btnDepositSubmit.setDisable(true);
            }
        }
        else {
            btnDepositClear.setDisable(true);
            btnDepositSubmit.setDisable(true);
        }
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
    
    private Predicate<TransactionBean> getPredicate(TransactionType type, Map dates) {
        return transaction ->
                (type == null || transaction.getType().name().equals(type.name()))
                        && (dates == null
                                || (transaction.getTimeStamp().toInstant().isAfter(((LocalDate)dates.get("dateFrom")).atStartOfDay().toInstant(OffsetDateTime.now().getOffset()))
                                        && transaction.getTimeStamp().toInstant().isBefore(((LocalDate)dates.get("dateTo")).atStartOfDay().toInstant(OffsetDateTime.now().getOffset()))));
    }
    
    private TransactionType getTransactionType() {
        RadioButton selectedRb = (RadioButton) toggleTransactionType.getSelectedToggle();
        TransactionType type = null;
        if (selectedRb == rbDeposits)
            type = TransactionType.DEPOSIT;
        else if (selectedRb == rbTransfers)
            type = TransactionType.TRANSFER;
        else if (selectedRb == rbPayments)
            type = TransactionType.PAYMENT;
        return type;
    }
    
    private Map getTransactionDate(boolean fromDatePicker) {
        Map dates = null;
        ZoneId zone = ZoneId.systemDefault();
        if (rbThisWeek.isSelected()) {
            DayOfWeek first = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
            DayOfWeek last = DayOfWeek.of(((first.getValue() + 5) % DayOfWeek.values().length) + 1);
            LocalDate firstThisWeek = 
                    LocalDate.now(zone).with(TemporalAdjusters.previousOrSame(first)).minusDays(1);
            LocalDate lastThisWeek = 
                    LocalDate.now(zone).with(TemporalAdjusters.nextOrSame(last)).plusDays(1);
            System.out.println(firstThisWeek);
            System.out.println(lastThisWeek);
            dates = fillMap(dates, firstThisWeek, lastThisWeek);
        } else if (rbThisMonth.isSelected()) {
            LocalDate firstThisMonth = 
                    LocalDate.now(zone).with(TemporalAdjusters.firstDayOfMonth()).minusDays(1);
            LocalDate lastThisMonth = 
                    LocalDate.now(zone).with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);
            dates = fillMap(dates, firstThisMonth, lastThisMonth);
        } else if (rbToday.isSelected()) {
            LocalDate todayFrom = LocalDate.now(zone).minusDays(1);
            LocalDate todayTo = LocalDate.now(zone).plusDays(1);
            dates = fillMap(dates, todayFrom, todayTo);
        } else if (rbBetweenDates.isSelected() && fromDatePicker) {
            LocalDate dateFrom = dpDateFrom.getValue().minusDays(1);
            LocalDate dateTo = dpDateTo.getValue().plusDays(1);
            dates = fillMap(dates, dateFrom, dateTo);
        }
        return dates;
    }

    private Map fillMap(Map dates, LocalDate todayFrom, LocalDate todayTo) {
        dates = new HashMap();
        dates.put("dateFrom", todayFrom);
        dates.put("dateTo", todayTo);
        return dates;
    }
    
    private void makeDatepickersVisible(boolean visible) {
        dpDateFrom.setVisible(visible);
        dpDateTo.setVisible(visible);
        lblFrom.setVisible(visible);
        lblTo.setVisible(visible);
        if (!visible) {
            dpDateFrom.setValue(null);
            dpDateTo.setValue(null);
        }
    }
}
