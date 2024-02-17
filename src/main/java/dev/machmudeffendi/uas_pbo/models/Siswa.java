package dev.machmudeffendi.uas_pbo.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Siswa {
    final private StringProperty idSiswa;
    final private StringProperty nama;
    final private StringProperty kelas;
    final private StringProperty jurusan;
    final private StringProperty pembayaran;
    final private IntegerProperty jumlah;

    public Siswa() {
        this.idSiswa = new SimpleStringProperty(this, "idSiswa");
        this.nama = new SimpleStringProperty(this, "nama");
        this.kelas = new SimpleStringProperty(this, "kelas");
        this.jurusan = new SimpleStringProperty(this, "jurusan");
        this.pembayaran = new SimpleStringProperty(this, "pembayaran");
        this.jumlah = new SimpleIntegerProperty(this, "jumlah");
    }

    public Siswa(StringProperty idSiswa, StringProperty nama, StringProperty kelas, StringProperty jurusan, StringProperty pembayaran, IntegerProperty jumlah) {
        this.idSiswa = idSiswa;
        this.nama = nama;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.pembayaran = pembayaran;
        this.jumlah = jumlah;
    }

    public String getIdSiswa() {
        return idSiswa.get();
    }

    public StringProperty idSiswaProperty() {
        return idSiswa;
    }

    public void setIdSiswa(String idSiswa) {
        this.idSiswa.set(idSiswa);
    }

    public String getNama() {
        return nama.get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getKelas() {
        return kelas.get();
    }

    public StringProperty kelasProperty() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas.set(kelas);
    }

    public String getJurusan() {
        return jurusan.get();
    }

    public StringProperty jurusanProperty() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan.set(jurusan);
    }

    public String getPembayaran() {
        return pembayaran.get();
    }

    public StringProperty pembayaranProperty() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran.set(pembayaran);
    }

    public Integer getJumlah() {
        return jumlah.get();
    }

    public IntegerProperty jumlahProperty() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah.set(jumlah);
    }
}
