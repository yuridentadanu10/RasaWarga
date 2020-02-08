package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailPaketActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgPhotoPaket, imgAgendesa;
    TextView tvJenis, tvDurasi, tvNamaDesa, tvAlamat, tvDeskripsiDesa, tvDeskripsiKegiatan,
            tvAgenDesa, tvHarga;
    RecyclerView rvFasilitas;
    Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);

        imgPhotoPaket = findViewById(R.id.img_list_photo);
        tvJenis = findViewById(R.id.tv_jenis);
        tvDurasi = findViewById(R.id.tv_durasi);
        tvNamaDesa = findViewById(R.id.tv_desa);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvDeskripsiDesa = findViewById(R.id.tv_deskripsi_desa);
        tvDeskripsiKegiatan = findViewById(R.id.tv_yang_akan_dilakukan);

        btnPesan = findViewById(R.id.btn_pesan);
        btnPesan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pesan: {
                //TODO: isi fungsi
            }
        }
    }
}
