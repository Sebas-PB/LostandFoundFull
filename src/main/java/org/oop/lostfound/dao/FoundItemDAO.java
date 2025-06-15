package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.oop.lostfound.enums.Category;
import org.oop.lostfound.model.FoundItem;


public class FoundItemDAO {
    private Connection connection;

    public FoundItemDAO() {
        this.connection = Connector.getConnection();
    }

    //Menyimpan data barang ditemukan ke database
    public boolean insertFoundItem(String itemName, String description, String location, String image_url, LocalDate dateFound, Category category, String contact, int idAccount) {
        String sql = "INSERT INTO found_item (item_name, description_item, location_item, image_url, date_found, category, contact, id_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setString(4, image_url);
            stmt.setDate(5, java.sql.Date.valueOf(dateFound));
            stmt.setString(6, category.name());
            stmt.setString(7, contact);
            stmt.setInt(8, idAccount); 
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mengambil jumlah total barang ditemukan dari found_item
    public int getJumlahFoundItems() {
    String sql = "SELECT COUNT(*) FROM found_item";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        var rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
    }

    // Mengambil semua data barang ditemukan dalam bentuk list
    public List<FoundItem> getAllFoundItems() throws Exception {
    List<FoundItem> foundItems = new ArrayList<>();
    String sql = "SELECT * FROM found_item"; 
    try (PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            FoundItem item = new FoundItem();

            // Set data menggunakan setter methods
            item.setId(rs.getInt("id_item"));
            item.setName(rs.getString("item_name"));
            item.setDescription(rs.getString("description_item"));
            item.setLocation(rs.getString("location_item"));
            item.setImageUrl(rs.getString("image_url"));
            item.setContact(rs.getString("contact"));
            
            // Set tanggal dengan null-check
            if (rs.getDate("date_found") != null) {
                item.setDate(rs.getDate("date_found").toLocalDate());
            }
            
            // Set kategori dengan null-check
            String categoryStr = rs.getString("category");
            if (categoryStr != null && !categoryStr.isEmpty()) {
                try {
                    item.setCategory(Category.valueOf(categoryStr));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid category: " + categoryStr);
                    // Set default category atau handle error sesuai kebutuhan
                }
            }
            foundItems.add(item);
            }
        } 
        return foundItems;
    }
    
    public void deleteFoundItemById(int id) {
        String sql = "DELETE FROM found_item WHERE id_item = ?";
        try (Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
