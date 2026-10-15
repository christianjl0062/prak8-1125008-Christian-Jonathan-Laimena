package prak8.christian.jonathan.laimena.models;

public class Mahasiswa {
    public String nim;
    public String nama;
    public int idJurusan;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public Mahasiswa(String nim, String nama, int idJurusan) {
        this.nim = nim;
        this.nama = nama;
        this.idJurusan = idJurusan;
    }
}

