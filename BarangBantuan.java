package responsipbo1;

// Class Entitas Barang (Modul 1)
public class BarangBantuan {
    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private double beratKg;

    // Konstruktor inisialisasi seluruh atribut
    public BarangBantuan(String kodeBarang, String namaBarang, String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    }

    // Getter untuk mengambil data saat pembuatan manifes
    public String getKodeBarang() { return kodeBarang; }
    public String getNamaBarang() { return namaBarang; }
    public String getKategori() { return kategori; }
    public double getBeratKg() { return beratKg; }
}