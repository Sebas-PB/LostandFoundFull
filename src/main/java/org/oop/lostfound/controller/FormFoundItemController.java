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
import org.oop.lostfound.config.Session;
import org.oop.lostfound.enums.Category;
import org.oop.lostfound.service.ImageKitService;
import org.oop.lostfound.dao.FoundItemDAO;

public class FormFoundItemController {
    // Navigasi antar halaman
    @FXML private Button lostFoundButton;
    @FXML private Button lostItemButton;
    @FXML private Button foundItemButton;
    @FXML private Button reportButton;
    @FXML private Button claimButton;
    @FXML private Button logOutButton; 

    //Input data barang ditemukan
    @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private Button selectImageButton;
    @FXML private Label selectedImageLabel;
    @FXML private DatePicker dateFoundDatePicker;
    @FXML private ComboBox<Category> categoryComboBox;
    @FXML private TextField contactTextField;
    @FXML private Button submitButton;

    //gambar dan preview
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
    private void selectImageButtonOnAction(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            selectedImageLabel.setText(selectedFile.getName());

            try {
                Image image = new Image(selectedFile.toURI().toString());
                imagePreview.setImage(image);  // inilah yang error sebelumnya kalau imagePreview null
                selectedImageFile = selectedFile;
            } catch (Exception e) {
                System.err.println("Error loading image for lost item: " + e.getMessage());
            }
        } else {
            selectedImageLabel.setText("Belum ada foto dipilih");
        }
    }

    @FXML
    private void dateFoundDatePickerOnAction(ActionEvent event) {
        LocalDate selectDate = dateFoundDatePicker.getValue();
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
    private void contactTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) contactTextField.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void submitButtonOnAction(ActionEvent event) throws IOException {
        String itemName = itemNameTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        LocalDate dateFound = dateFoundDatePicker.getValue();
        Category category = categoryComboBox.getValue();
        String contact = contactTextField.getText();

        if (itemName.isEmpty() || description.isEmpty() || location.isEmpty() || dateFound == null || category == null || contact.isEmpty())  {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("PESAN PERINGATAN");
            alert.setHeaderText(null);
            alert.setContentText("TIDAK BOLEH ADA YANG KOSONG WAJIB DIISI SEMUA!");
            alert.showAndWait();
            return;
        }
        
        //upload gambar
        uploadedImageUrl = null;
        if (selectedImageFile != null) {
            try {
                // tampilan loading 
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

        FoundItemDAO founditemDAO = new FoundItemDAO();
        int idAccount = Session.getId();
        boolean success = founditemDAO.insertFoundItem(itemName, description, location, uploadedImageUrl, dateFound, category, contact, idAccount);

        submitButton.setText("Submit");
        submitButton.setDisable(false);

        
        if (success) {
            showAlert(AlertType.INFORMATION, "BERHASIL", 
                    "DATA BARANG HILANG BERHASIL DISIMPAN!");
            
            // Kembali ke halaman menu utama
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