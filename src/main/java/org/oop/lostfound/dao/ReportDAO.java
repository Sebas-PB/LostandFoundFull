package org.oop.lostfound.dao;

import org.oop.lostfound.model.Report;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    //Mengambil semua data laporan dari tabel lost_item dan found_item
    public static List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        String query = """
            SELECT 
                li.id_item AS report_id,
                a.username AS user,
                li.item_name AS item_name,
                'LOST' AS type,
                li.location_item AS location,
                li.date_lost AS date,
                li.contact AS contact
            FROM lost_item li
            JOIN account a ON li.id_account = a.id_account

            UNION

            SELECT 
                fi.id_item AS report_id,
                a.username AS user,
                fi.item_name AS item_name,
                'FOUND' AS type,
                fi.location_item AS location,
                fi.date_found AS date,
                fi.contact AS contact
            FROM found_item fi
            JOIN account a ON fi.id_account = a.id_account

            ORDER BY date DESC
            """;

        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next())
            {
                Report report = new Report(
                    rs.getInt("report_id"),
                    rs.getString("user"),
                    rs.getString("item_name"),
                    rs.getString("type"),
                    rs.getString("location"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("contact")
                );

                reports.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
}
