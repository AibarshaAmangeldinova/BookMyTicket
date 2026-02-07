package repositories;

import data.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryRepository {

    public Integer getCategoryIdByName(String name) {
        String sql = "SELECT id FROM flight_categories WHERE LOWER(name)=LOWER(?)";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return null;
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
