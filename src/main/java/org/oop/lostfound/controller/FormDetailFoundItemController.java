package org.oop.lostfound.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.oop.lostfound.dao.FoundItemDAO;
import org.oop.lostfound.dao.ClaimDAO;
import org.oop.lostfound.model.Claim;
import org.oop.lostfound.model.FoundItem;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormDetailFoundItemController implements Initializable
{
    @FXML private Label itemNameLabel;
    @FXML private Label finderNameLabel;
    @FXML private Label dateLabel;
    @FXML private Label descriptionLabel;
    @FXML private ImageView itemImageView;
    @FXML private Button claimButton;
    @FXML private FoundItem foundItem;

    public void setFoundItem(FoundItem foundItem)
    {
        this.foundItem = foundItem;
        updateDetail();
    }

    private void updateDetail()
    {
        if (foundItem != null)
        {
            itemNameLabel.setText(foundItem.getName());
            finderNameLabel.setText(foundItem.getContact());
            dateLabel.setText(foundItem.getDate() != null ? foundItem.getDate().toString() : "-");
            descriptionLabel.setText(foundItem.getDescription());
            if (foundItem.getImageUrl() != null && !foundItem.getImageUrl().isEmpty())
            {
                itemImageView.setImage(new Image(foundItem.getImageUrl()));
            } else
            {
                itemImageView.setImage(null);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        itemNameLabel.setText("");
        finderNameLabel.setText("");
        dateLabel.setText("");
        descriptionLabel.setText("");
        itemImageView.setImage(null);

        if (claimButton != null) {
            claimButton.setOnAction(event -> handleClaim());
        }
    }

    public static void showDetail(FoundItem foundItem) {
        try {
            FXMLLoader loader = new FXMLLoader(FormDetailFoundItemController.class.getResource("/org/oop/lostfound/FormDetailFoundItem.fxml"));
            Parent root = loader.load();
            FormDetailFoundItemController controller = loader.getController();
            controller.setFoundItem(foundItem);
            Stage stage = new Stage();
            stage.setTitle("Detail Barang Ditemukan");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClaim()
    {
        // Input nama
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Klaim Barang Ditemukan");
        nameDialog.setHeaderText("Masukkan Nama Anda untuk Klaim");
        nameDialog.setContentText("Nama Anda:");
        Optional<String> nameResult = nameDialog.showAndWait();
        if (!nameResult.isPresent() || nameResult.get().trim().isEmpty())
        {
            showAlert(AlertType.WARNING, "Klaim Gagal", "Nama tidak boleh kosong!");
            return;
        }

        // Input nomor telepon
        TextInputDialog phoneDialog = new TextInputDialog();
        phoneDialog.setTitle("Klaim Barang");
        phoneDialog.setHeaderText("Masukkan Nomor Telepon Anda");
        phoneDialog.setContentText("Nomor Telepon:");
        Optional<String> phoneResult = phoneDialog.showAndWait();
        if (!phoneResult.isPresent() || phoneResult.get().trim().isEmpty())
        {
            showAlert(AlertType.WARNING, "Klaim Gagal", "Nomor telepon tidak boleh kosong!");
            return;
        }

        // Simpan ke database
        Claim claimItem = new Claim();
        claimItem.setItemName(foundItem.getName());
        claimItem.setClaimDate(foundItem.getDate());
        claimItem.setFoundBy(foundItem.getContact());
        claimItem.setClaimedBy(nameResult.get());
        claimItem.setDescription(foundItem.getDescription());
        claimItem.setClaimantPhone(phoneResult.get());
        claimItem.setImageUrl(foundItem.getImageUrl());

        ClaimDAO claimDAO = new ClaimDAO();
        FoundItemDAO foundItemDAO = new FoundItemDAO();

        claimDAO.insertClaimItem(claimItem);
        foundItemDAO.deleteFoundItemById(foundItem.getId());

        showAlert(AlertType.INFORMATION, "Klaim Berhasil", "Barang berhasil diklaim!");

        try {
            // Tutup window detail
            Stage currentStage = (Stage) claimButton.getScene().getWindow();
            currentStage.close();

            // Buka halaman klaim
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormClaim.fxml"));
            Parent claimRoot = loader.load();
            FormClaimController controller = loader.getController();
            controller.refreshData(); // agar data langsung update

            Stage claimStage = new Stage();
            claimStage.setTitle("Claimed Items");
            claimStage.setScene(new Scene(claimRoot));
            claimStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
