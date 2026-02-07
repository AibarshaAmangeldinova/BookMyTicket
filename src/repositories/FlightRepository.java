package repositories;

import data.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    public boolean exists(int flightId) {
        String sql = "SELECT 1 FROM flights WHERE id=?";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (con == null) return false;
            ps.setInt(1, flightId);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getPrice(int flightId) {
        String sql = "SELECT price FROM flights WHERE id=?";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (con == null) return null;
            ps.setInt(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("price");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public List<String[]> getAllForTable() {
        String sql =
                "SELECT f.id, f.origin, f.destination, COALESCE(c.name,'-') AS category, f.price " +
                        "FROM flights f " +
                        "LEFT JOIN flight_categories c ON c.id = f.category_id " +
                        "ORDER BY f.id";

        List<String[]> rows = new ArrayList<>();
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (con == null) return rows;
            while (rs.next()) {
                rows.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("category"),
                        String.valueOf(rs.getInt("price"))
                });
            }
        } catch (Exception e) {
            System.out.println("Error loading flights: " + e.getMessage());
        }
        return rows;
    }

    public boolean addFlight(String origin, String destination, int price, Integer categoryId) {
        String sql = "INSERT INTO flights(origin, destination, price, category_id) VALUES (?,?,?,?)";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (con == null) return false;
            ps.setString(1, origin);
            ps.setString(2, destination);
            ps.setInt(3, price);
            if (categoryId == null) ps.setNull(4, java.sql.Types.INTEGER);
            else ps.setInt(4, categoryId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Add flight error: " + e.getMessage());
            return false;
        }
    }
}
