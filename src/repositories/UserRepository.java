package repositories;

import data.PostgresDB;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public int saveAndReturnId(User u) {
        String sql = """
            INSERT INTO users (first_name, last_name, phone, document_type, document_number)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.firstName);
            ps.setString(2, u.lastName);
            ps.setString(3, u.phone);
            ps.setString(4, u.documentType);
            ps.setString(5, u.documentNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(" Error saving user: " + e.getMessage());
        }
        return -1;
    }
}

