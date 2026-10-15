package prak8.christian.jonathan.laimena.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prak8.christian.jonathan.laimena.database.Database;
import prak8.christian.jonathan.laimena.models.MataKuliah;

public class MataKuliahRepository {

    private static final Connection conn = Database.connect();

    public static void addMataKuliah(MataKuliah matkul) {
        String sql = "INSERT INTO matakuliah (kode, nama, sks) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, matkul.kode);
            stmt.setString(2, matkul.nama);
            stmt.setInt(3, matkul.sks);
            stmt.executeUpdate();
            System.out.println("✅ Mata Kuliah berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<MataKuliah> getAllMataKuliah() {
        List<MataKuliah> result = new ArrayList<>();
        String sql = "SELECT kode, nama, sks FROM matakuliah";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new MataKuliah(rs.getString("kode"), rs.getString("nama"), rs.getInt("sks")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void updateMataKuliah(MataKuliah matkul) {
        String sql = "UPDATE matakuliah SET nama = ?, sks = ? WHERE kode = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, matkul.nama);
            stmt.setInt(2, matkul.sks);
            stmt.setString(3, matkul.kode);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Mata Kuliah berhasil diperbarui!");
            } else {
                System.out.println("Kode Mata Kuliah tidak ditemukan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMataKuliah(String kode) {
        String sql = "DELETE FROM matakuliah WHERE kode = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kode);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Mata Kuliah telah berhasil dihapus!");
            } else {
                System.out.println("Maaf, kode Mata Kuliah tsb tidak ditemukan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
