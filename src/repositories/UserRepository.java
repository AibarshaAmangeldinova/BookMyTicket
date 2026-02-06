package repositories;

import data.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    public Integer createUser(String firstName, String lastName, String phone, String docType, String docNumber) {
        String sql = """
            INSERT INTO users(first_name,last_name,phone,document_type,document_number)
            VALUES (?,?,?,?,?) RETURNING id
        """;
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return null;

            ps.setString(1, firstName.trim());
            ps.setString(2, lastName.trim());
            ps.setString(3, phone.trim());
            ps.setString(4, docType.trim().toUpperCase());
            ps.setString(5, docNumber.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
