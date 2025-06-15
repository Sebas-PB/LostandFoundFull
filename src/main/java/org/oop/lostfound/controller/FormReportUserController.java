package org.oop.lostfound.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;
import org.oop.lostfound.dao.ReportDAO;
import org.oop.lostfound.model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FormReportUserController implements Initializable
{
    @FXML private Button lostFoundButton;
    @FXML private Button lostItemButton;
    @FXML private Button foundItemButton;
    @FXML private Button reportButton;
    @FXML private Button claimButton;
    @FXML private Button logOutButton; 

    @FXML private TableView<Report> tableReport;
    @FXML private TableColumn<Report, Integer> columnReportId;
    @FXML private TableColumn<Report, String> columnUser;
    @FXML private TableColumn<Report, String> columnItemName;
    @FXML private TableColumn<Report, String> columnType;
    @FXML private TableColumn<Report, String> columnLocation;
    @FXML private TableColumn<Report, LocalDate> columnDate;
    @FXML private TableColumn<Report, String> columnContact;


    @FXML
    private void lostFoundButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void lostItemButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLostItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void foundItemButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormFoundItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void reportButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormReportUser.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void claimButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormClaim.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void logOutButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        columnReportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        columnItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        // Styling untuk header kolom
        tableReport.setStyle("-fx-background-color: white;");
        
        // Styling individual untuk setiap header kolom
        columnReportId.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");
        columnUser.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");
        columnItemName.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");
        columnType.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");
        columnLocation.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");
        columnDate.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");
        columnContact.setStyle("-fx-background-color: #318991; -fx-text-fill: white;");

        loadAllReports();
    }

    private void loadAllReports() {
        List<Report> reports = ReportDAO.getAllReports();
        tableReport.getItems().setAll(reports);
    }


}
