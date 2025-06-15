package org.oop.lostfound.dao;

import org.oop.lostfound.model.Claim;
import org.oop.lostfound.enums.ClaimStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {

    // Mengambil semua data klaim dari database
    public static List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claim_item";

        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {

            while (rs.next())
            {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("id"));
                claim.setItemName(rs.getString("item_name"));
                Date foundDate = rs.getDate("found_date");
                if (foundDate != null)
                {
                    claim.setClaimDate(foundDate.toLocalDate());
                }
                claim.setFoundBy(rs.getString("finder_name"));
                claim.setClaimedBy(rs.getString("claimant_name"));
                claim.setDescription(rs.getString("description"));
                claim.setClaimantPhone(rs.getString("claimant_phone"));
                claim.setImageUrl(rs.getString("image_url"));

                claims.add(claim);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return claims;
    }

    // Simpan data klaim ke database
    public void insertClaimItem(Claim claimItem)
    {
        String sql = "INSERT INTO claim_item (id, item_name, finder_name, found_date, description, image_url, claimant_name, claimant_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("Insert Claim");

        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, claimItem.getClaimId());
            stmt.setString(2, claimItem.getItemName());
            stmt.setString(3, claimItem.getFoundBy());
            stmt.setDate(4, claimItem.getClaimDate() != null ? Date.valueOf(claimItem.getClaimDate()) : null);
            stmt.setString(5, claimItem.getDescription());
            stmt.setString(6, claimItem.getImageUrl());
            stmt.setString(7, claimItem.getClaimedBy());
            stmt.setString(8, claimItem.getClaimantPhone());

            stmt.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Mengubah status klaim berdasarkan ID klaim
    public static void updateClaimStatus(Integer claimId, ClaimStatus status) throws SQLException {
        String query = "UPDATE claims SET status = ? WHERE id_claim = ?";
        System.out.println("Update claim");
        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, status.name());
            stmt.setInt(2, claimId);
            stmt.executeUpdate();
        }
    }
}