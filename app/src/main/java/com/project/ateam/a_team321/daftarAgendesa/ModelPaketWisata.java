package com.project.ateam.a_team321.daftarAgendesa;

public class ModelPaketWisata {

   public String nama_desa;
    public  String alamat;
    public String jenis;
    public String fasilitas;
    public int durasi;
    public String deskripsi_desa;
    public String deskripsi_kegiatan;
    public  String img_url;
    public int harga;

    public ModelPaketWisata(){

    }


    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama_desa() {
        return nama_desa;
    }

    public void setNama_desa(String nama_desa) {
        this.nama_desa = nama_desa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getDeskripsi_desa() {
        return deskripsi_desa;
    }

    public void setDeskripsi_desa(String deskripsi_desa) {
        this.deskripsi_desa = deskripsi_desa;
    }

    public String getDeskripsi_kegiatan() {
        return deskripsi_kegiatan;
    }

    public void setDeskripsi_kegiatan(String deskripsi_kegiatan) {
        this.deskripsi_kegiatan = deskripsi_kegiatan;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ModelPaketWisata(String nama_desa, String alamat, String jenis, String fasilitas, int durasi, String deskripsi_desa, String deskripsi_kegiatan, String img_url, int harga) {
        this.nama_desa = nama_desa;
        this.alamat = alamat;
        this.jenis = jenis;
        this.fasilitas = fasilitas;
        this.durasi = durasi;
        this.deskripsi_desa = deskripsi_desa;
        this.deskripsi_kegiatan = deskripsi_kegiatan;
        this.img_url = img_url;
        this.harga = harga;
    }
}
