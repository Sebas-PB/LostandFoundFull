package org.oop.lostfound.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.oop.lostfound.enums.Category;
import org.oop.lostfound.service.ImageKitService;
import org.oop.lostfound.config.Session;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LostItemDAO;

public class FormLostItemController {
    // Tombol navigasi antar halaman
    @FXML private Button lostFoundButton;
    @FXML private Button lostItemButton;
    @FXML private Button foundItemButton;
    @FXML private Button reportButton;
    @FXML private Button claimButton;
    @FXML private Button logOutButton; 

    // Field input form barang hilang
    @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private DatePicker dateLostDatePicker;
    @FXML private ComboBox<Category> categoryComboBox;
    @FXML private Button selectImageButton;
    @FXML private Label selectedImageLabel;
    @FXML private TextField contactTextField;
    @FXML private Button submitButton;

    //preview gambar
    @FXML private ImageView imagePreview;
    @FXML private File selectedImageFile;
    @FXML private String uploadedImageUrl;

    @FXML
    private void initialize() {
        categoryComboBox.getItems().setAll(Category.values());
    }

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
    private void foundItemButtonOnAction(ActionEvent event) throws IOException {
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

    @FXML
    private void itemNameTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) itemNameTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void descriptionTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) descriptionTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void locationTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) locationTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dateLostDatePickerOnAction(ActionEvent event) {
        LocalDate selectDate = dateLostDatePicker.getValue();
        System.out.println("Tanggal yang dipilih: " + selectDate);
    }

    @FXML
    private void categoryComboBoxOnAction(ActionEvent event) {
        Category selectedCategory = categoryComboBox.getValue();
        if (selectedCategory != null) {
            System.out.println("Kategori dipilih: " + selectedCategory.name());
        }
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void selectImageButtonOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Foto Barang");
        
        // Set extension filter (file yang boleh dipilih)
        FileChooser.ExtensionFilter extFilter = 
            new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg)", "*.PNG", "*.png", "*.JPG", "*.jpg", "*.JPEG", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        
        Stage stage = (Stage) selectImageButton.getScene().getWindow();
        selectedImageFile = fileChooser.showOpenDialog(stage);
        
        if (selectedImageFile != null) {
            selectedImageLabel.setText(selectedImageFile.getName());
            
            // Show preview
            try {
                Image image = new Image(selectedImageFile.toURI().toString());
                imagePreview.setImage(image);
                imagePreview.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void contactTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) contactTextField.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void submitButtonOnAction(ActionEvent event) throws IOException {
        String itemName = itemNameTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        LocalDate dateLost = dateLostDatePicker.getValue();
        Category category = categoryComboBox.getValue();
        String contact = contactTextField.getText();

        if (itemName.isEmpty() || description.isEmpty() || location.isEmpty() || dateLost == null || category == null || contact.isEmpty())  {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("PESAN PERINGATAN");
            alert.setHeaderText(null);
            alert.setContentText("TIDAK BOLEH ADA YANG KOSONG WAJIB DIISI SEMUA!");
            alert.showAndWait();
            return;
        }
            // Upload image if selected
        uploadedImageUrl = null;
        if (selectedImageFile != null) {
            try {
                // Show loading indicator
                submitButton.setText("Uploading...");
                submitButton.setDisable(true);
                
                // Upload to ImageKit
                String fileName = System.currentTimeMillis() + "_" + selectedImageFile.getName();
                uploadedImageUrl = ImageKitService.uploadImage(selectedImageFile, fileName);
                
            } catch (Exception e) {
                submitButton.setText("Submit");
                submitButton.setDisable(false);
                showAlert(AlertType.ERROR, "GAGAL", 
                        "Gagal mengupload gambar: " + e.getMessage());
                return;
            }
        }

        LostItemDAO lostitemDAO = new LostItemDAO(Connector.getConnection());
        boolean success = lostitemDAO.insertLostItem(itemName, description, location, dateLost, category, uploadedImageUrl, contact, Session.getId());

        submitButton.setText("Submit");
        submitButton.setDisable(false);

        
        if (success) {
            showAlert(AlertType.INFORMATION, "BERHASIL", 
                    "DATA BARANG HILANG BERHASIL DISIMPAN!");
            
            // Kembali ke menu utama
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        } else { 
            showAlert(AlertType.ERROR, "GAGAL", "GAGAL MENYIMPAN DATA BARANG HILANG");
        }
    }
}