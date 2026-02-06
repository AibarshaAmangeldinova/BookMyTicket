package repositories;

import data.Db;
import models.StaffAccount;
import security.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthRepository {

    public StaffAccount findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id, username, role FROM staff_accounts WHERE username=? AND password=?";

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return null;

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                StaffAccount a = new StaffAccount();
                a.id = rs.getInt("id");
                a.username = rs.getString("username");
                a.role = Role.valueOf(rs.getString("role"));
                return a;
            }

        } catch (Exception e) {
            System.out.println(" Auth error: " + e.getMessage());
            return null;
        }
    }
}
