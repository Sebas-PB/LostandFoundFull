package org.oop.lostfound.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.oop.lostfound.model.LostItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class FormDetailLostItemController implements Initializable {
    @FXML private Label itemNameLabel;
    @FXML private Label finderNameLabel;
    @FXML private Label dateLabel;
    @FXML private Label descriptionLabel;
    @FXML private ImageView itemImageView;

    private LostItem lostItem;
    public void setLostItem(LostItem lostItem) {
        this.lostItem = lostItem;
        updateDetail();
    }
    private void updateDetail() {
        if (lostItem != null) {
            itemNameLabel.setText(lostItem.getName());
            finderNameLabel.setText(lostItem.getContact());
            dateLabel.setText(lostItem.getDate() != null ? lostItem.getDate().toString() : "-");
            descriptionLabel.setText(lostItem.getDescription());
            if (lostItem.getImageUrl() != null && !lostItem.getImageUrl().isEmpty()) {
                itemImageView.setImage(new Image(lostItem.getImageUrl()));
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemNameLabel.setText("");
        finderNameLabel.setText("");
        dateLabel.setText("");
        descriptionLabel.setText("");
        itemImageView.setImage(null);
    }
    public static void showDetail(LostItem lostItem) {
        try {
            FXMLLoader loader = new FXMLLoader(FormDetailLostItemController.class.getResource("/org/oop/lostfound/FormDetailLostItem.fxml"));
            Parent root = loader.load();
            FormDetailLostItemController controller = loader.getController();
            controller.setLostItem(lostItem);
            Stage stage = new Stage();
            stage.setTitle("Detail Lost Item");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
