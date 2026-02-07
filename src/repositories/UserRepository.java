package repositories;

import data.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public Integer createUser(String firstName, String lastName, String phone, String docType, String docNumber) {
        String sql =
                "INSERT INTO users(first_name, last_name, phone, document_type, document_number) " +
                        "VALUES (?,?,?,?,?) RETURNING id";

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return null;

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setString(4, docType.trim().toUpperCase());
            ps.setString(5, docNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }

        } catch (Exception e) {
            System.out.println("User save error: " + e.getMessage());
            return null;
        }
        return null;
    }
}
