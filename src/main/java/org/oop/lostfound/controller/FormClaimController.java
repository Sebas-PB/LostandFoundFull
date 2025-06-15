package org.oop.lostfound.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.oop.lostfound.dao.ClaimDAO;
import org.oop.lostfound.model.Claim;
import javafx.fxml.FXMLLoader;

public class FormClaimController implements Initializable
{

    @FXML private Button lostFoundButton;
    @FXML private Button lostItemButton;
    @FXML private Button foundItemButton;
    @FXML private Button reportButton;
    @FXML private Button claimButton;
    @FXML private Button logOutButton;

    @FXML private TableView<Claim> tableClaim;
    @FXML private TableColumn<Claim, Integer> columnClaimId;
    @FXML private TableColumn<Claim, String> columnClaimDate;
    @FXML private TableColumn<Claim, String> columnFoundBy;
    @FXML private TableColumn<Claim, String> columnClaimedBy;
    @FXML private TableColumn<Claim, String> columnItemName;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Setup cell value factories
        columnClaimId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getClaimId()).asObject());
        columnClaimDate.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getClaimDate() != null ?
                                cellData.getValue().getClaimDate().toString() : ""));
        columnFoundBy.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFoundBy() != null ?
                                cellData.getValue().getFoundBy() : ""));
        columnClaimedBy.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getClaimedBy() != null ?
                                cellData.getValue().getClaimedBy() : ""));
        columnItemName.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getItemName() != null ?
                                cellData.getValue().getItemName() : ""));

        refreshData();
    }

    public void refreshData()
    {
        tableClaim.setItems(javafx.collections.FXCollections.observableArrayList(ClaimDAO.getAllClaims()));
    }

    @FXML
    private void lostFoundButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void lostItemButtonOnAction(ActionEvent event) throws IOException
    {
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
    private void reportButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormReportUser.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void claimButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormClaim.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void logOutButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}