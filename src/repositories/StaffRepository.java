package repositories;

import data.Db;
import security.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffRepository {
    public Role findRole(String username, String password) {
        String sql = "SELECT role FROM staff_accounts WHERE username=? AND password=? LIMIT 1";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return null;
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return Role.valueOf(rs.getString("role").toUpperCase());
            }
        } catch (Exception e) {
            return null;
        }
    }
}
