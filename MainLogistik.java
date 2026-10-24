package responsipbo1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Class Utama (Modul 2, 4, & 5)
public class MainLogistik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Collections ArrayList (Modul 4)
        ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();
        
        boolean running = true;
        System.out.println(" SISTEM LOGISTIK BANTUAN DAERAH 3T ");

        // Loop Menu Interaktif (Modul 2)
        while (running) {
            System.out.println("\n MENU OPERASIONAL ");
            System.out.println("1. Tambah Barang Bantuan");
            System.out.println("2. Selesai dan Cetak Manifes");
            System.out.print("Pilih menu (1/2): ");
            String pilihan = scanner.nextLine().trim();

            if (pilihan.equals("1")) {
                // Exception Handling & Validasi (Modul 3)
                try {
                    System.out.print("Masukkan Kode Barang (Harus diawali 'EDX-'): ");
                    String kode = scanner.nextLine().trim();
                    if (!kode.startsWith("EDX-")) {
                        throw new IllegalArgumentException("Kode barang harus diawali EDX-");
                    }

                    System.out.print("Masukkan Nama Barang: ");
                    String nama = scanner.nextLine().trim();

                    System.out.print("Masukkan Kategori (misal: Elektronik/Buku): ");
                    String kategori = scanner.nextLine().trim();

                    System.out.print("Masukkan Berat Barang (Kg): ");
                    double berat = Double.parseDouble(scanner.nextLine().trim());
                    if (berat <= 0) {
                        throw new BeratInvalidException("Berat barang harus lebih besar dari 0 Kg!");
                    }

                    // Tambah ke ArrayList
                    daftarBarang.add(new BarangBantuan(kode, nama, kategori, berat));
                    System.out.println("-> Berhasil menambahkan barang!");

                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Input berat harus berupa angka/desimal!");
                } catch (IllegalArgumentException | BeratInvalidException e) {
                    System.out.println("ERROR Validasi: " + e.getMessage());
                } finally {
                    System.out.println("(Pemrosesan input selesai)");
                }

            } else if (pilihan.equals("2")) {
                if (daftarBarang.isEmpty()) {
                    System.out.println("Peringatan: Belum ada barang yang didata!");
                    continue;
                }

                // Hitung total akumulasi berat
                double totalBerat = 0;
                for (BarangBantuan b : daftarBarang) {
                    totalBerat += b.getBeratKg();
                }

                // Percabangan penentuan armada (Modul 2)
                String labelArmada = (totalBerat <= 50) ? "Mobil Pickup" : "Truk Ekspedisi";

                // Export ke File I/O (Modul 5)
                String namaFile = "manifest_pengiriman.txt";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(namaFile))) {
                    bw.write("       MANIFES PENGIRIMAN LOGISTIK DAERAH 3T      ");

                    for (BarangBantuan b : daftarBarang) {
                        bw.write(String.format("%-12s | %-18s | %-12s | %.2f Kg", 
                                b.getKodeBarang(), b.getNamaBarang(), b.getKategori(), b.getBeratKg()));
                        bw.newLine();
                    }

                    bw.write("Total Jenis Barang : " + daftarBarang.size() + " item");
                    bw.newLine();
                    bw.write(String.format("Total Berat Muatan : %.2f Kg", totalBerat));
                    bw.newLine();
                    bw.write("Rekomendasi Armada : " + labelArmada);
                    bw.newLine();

                    System.out.println("\nData manifes berhasil diekspor ke " + namaFile);
                    running = false;

                } catch (IOException e) {
                    System.out.println("ERROR File I/O: " + e.getMessage());
                }

            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        scanner.close();
        System.out.println("Program selesai.");
    }
}
