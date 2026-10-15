package prak8.christian.jonathan.laimena.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prak8.christian.jonathan.laimena.database.Database;
import prak8.christian.jonathan.laimena.models.Jurusan;

public class JurusanRepository {

    private static final Connection conn = Database.connect();

    public static void addJurusan(Jurusan jurusan) {
        String sql = "INSERT INTO jurusan (kode_jurusan, nama) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jurusan.kodeJurusan);
            stmt.setString(2, jurusan.nama);
            stmt.executeUpdate();
            System.out.println("Jurusan berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Jurusan> getAllJurusan() {
        List<Jurusan> result = new ArrayList<>();
        String sql = "SELECT kode_jurusan, nama FROM jurusan";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Jurusan(
                    rs.getString("kode_jurusan"),
                    rs.getString("nama")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void updateJurusan(Jurusan jurusan) {
        String sql = "UPDATE jurusan SET nama = ? WHERE kode_jurusan = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jurusan.nama);
            stmt.setString(2, jurusan.kodeJurusan);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Jurusan berhasil diperbarui!");
            } else {
                System.out.println("Kode Jurusan tidak ditemukan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJurusan(String kodeJurusan) {
        String sql = "DELETE FROM jurusan WHERE kode_jurusan = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kodeJurusan);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Jurusan berhasil dihapus!");
            } else {
                System.out.println("❌ Kode Jurusan tidak ditemukan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}