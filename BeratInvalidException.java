package responsipbo1;

// Custom Exception khusus untuk validasi berat barang (Modul 3)
public class BeratInvalidException extends Exception {
    public BeratInvalidException(String pesan) {
        super(pesan);
    }
}