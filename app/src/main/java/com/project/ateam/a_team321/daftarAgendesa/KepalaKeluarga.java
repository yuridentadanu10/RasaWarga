package com.project.ateam.a_team321.daftarAgendesa;

public class KepalaKeluarga {
    public String nama_warga;
    public String deskripsi_warga;
    public String noKTP;
    public  String img_url;




    public KepalaKeluarga(){

    }

    public KepalaKeluarga(String nama_warga, String deskripsi_keluarga, String noKTP, String img_url) {
        this.nama_warga = nama_warga;
        this.deskripsi_warga = deskripsi_keluarga;
        this.noKTP = noKTP;
        this.img_url = img_url;
    }

    public String getNama_warga() {
        return nama_warga;
    }

    public void setNama_warga(String nama_warga) {
        this.nama_warga = nama_warga;
    }

    public String getDeskripsi_warga() {
        return deskripsi_warga;
    }

    public void setDeskripsi_warga(String deskripsi_warga) {
        this.deskripsi_warga = deskripsi_warga;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
