package dev.machmudeffendi.uas_pbo.controllers;

import dev.machmudeffendi.uas_pbo.models.Siswa;
import dev.machmudeffendi.uas_pbo.utils.CrudHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SiswaController implements Initializable {
    @FXML
    private TextField fieldIDSiswa;
    @FXML
    private TextField fieldNama;
    @FXML
    private TextField fieldKelas;
    @FXML
    private ChoiceBox<Object> fieldJurusan;
    @FXML
    private ChoiceBox<Object> fieldPembayaran;
    @FXML
    private TextField fieldJumlah;
    @FXML
    private TableView<Siswa> tblSiswa;
    @FXML
    private TableColumn<Siswa, String> colIDSiswa;
    @FXML
    private TableColumn<Siswa, String> colNama;
    @FXML
    private TableColumn<Siswa, String> colKelas;
    @FXML
    private TableColumn<Siswa, String> colJurusan;
    @FXML
    private TableColumn<Siswa, String> colPembayaran;
    @FXML
    private TableColumn<Siswa, String> colJumlah;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showSiswa();
    }

    @FXML
    protected void onBtnSimpan() {
        if(fieldIDSiswa.getText().isEmpty()) return;
        String query = "INSERT INTO siswa (id,nama,kelas,jurusan,pembayaran,jumlah) VALUES ('"
                +fieldIDSiswa.getText()+"','"+fieldNama.getText()+"','"+fieldKelas.getText()+"','"
                +fieldJurusan.getSelectionModel().getSelectedItem().toString()+"','"+fieldPembayaran.getSelectionModel().getSelectedItem().toString()+"',"+fieldJumlah.getText()+")";
        if(CrudHelper.executeUpdate(query)) {
            showSiswa();
            clearField();
        }
    }

    @FXML
    private void onBtnUbah(){
        if(fieldIDSiswa.getText().isEmpty()) return;
        String query = "UPDATE siswa SET nama='" + fieldNama.getText()+ "', kelas='" +fieldKelas.getText()+ "', " +
                "jurusan='"+fieldJurusan.getSelectionModel().getSelectedItem().toString()+"', " +
                "pembayaran='"+fieldPembayaran.getSelectionModel().getSelectedItem().toString()+"', " +
                "jumlah="+fieldJumlah.getText()+" WHERE id='"+fieldIDSiswa.getText()+"'";
        if(CrudHelper.executeUpdate(query)) {
            showSiswa();
            clearField();
        }
    }

    @FXML
    protected void onBtnHapus(){
        if(fieldIDSiswa.getText().isEmpty()) return;
        String query = "DELETE FROM siswa WHERE id='"+fieldIDSiswa.getText()+"'";
        if(CrudHelper.executeUpdate(query)) {
            showSiswa();
            clearField();
        }
    }

    @FXML
    protected void onBtnReset() {
        clearField();
    }

    @FXML
    protected void onSelectRowSiswa(){
        Siswa siswa = tblSiswa.getSelectionModel().getSelectedItem();
        if (siswa != null){
            fieldIDSiswa.setText(siswa.getIdSiswa());
            fieldNama.setText(siswa.getNama());
            fieldKelas.setText(siswa.getKelas());
            fieldJurusan.setValue(siswa.getJurusan());
            fieldPembayaran.setValue(siswa.getPembayaran());
            fieldJumlah.setText(siswa.getJumlah().toString());
        }
    }

    private void clearField() {
        fieldIDSiswa.setText("");
        fieldNama.setText("");
        fieldKelas.setText("");
        fieldJurusan.getSelectionModel().clearSelection();
        fieldPembayaran.getSelectionModel().clearSelection();
        fieldJumlah.setText("");
    }

    private ObservableList<Siswa> getSiswaList(){
        ObservableList<Siswa> siswaList = FXCollections.observableArrayList();
        String query = "SELECT * FROM siswa ";

        try {
            ResultSet rs = CrudHelper.executeQuery(query);
            while(rs.next()) {
                Siswa siswa = new Siswa();
                siswa.setIdSiswa(rs.getString("id"));
                siswa.setNama(rs.getString("nama"));
                siswa.setKelas(rs.getString("kelas"));
                siswa.setJurusan(rs.getString("jurusan"));
                siswa.setPembayaran(rs.getString("pembayaran"));
                siswa.setJumlah(rs.getInt("jumlah"));
                siswaList.add(siswa);
            }
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Unable to load siswa");
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": error msg " + e.getMessage());
        }
        return siswaList;
    }

    private void showSiswa() {
        ObservableList<Siswa> list = getSiswaList();

        colIDSiswa.setCellValueFactory(new PropertyValueFactory<Siswa, String>("idSiswa"));
        colNama.setCellValueFactory(new PropertyValueFactory<Siswa, String>("nama"));
        colKelas.setCellValueFactory(new PropertyValueFactory<Siswa, String>("kelas"));
        colJurusan.setCellValueFactory(new PropertyValueFactory<Siswa, String>("jurusan"));
        colPembayaran.setCellValueFactory(new PropertyValueFactory<Siswa, String>("pembayaran"));
        colJumlah.setCellValueFactory(new PropertyValueFactory<Siswa, String>("jumlah"));

        tblSiswa.setItems(list);
    }
}