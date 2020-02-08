package com.project.ateam.a_team321;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.ateam.a_team321.LoginAndFriend.LoginActivity;
import com.project.ateam.a_team321.LoginAndFriend.REgisterActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailPaketActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        tvAgenDesa = findViewById(R.id.tv_agendesa);
        tvNamaDesa = findViewById(R.id.tv_desa);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvHarga = findViewById(R.id.tv_harga);
        tvDeskripsiDesa = findViewById(R.id.tv_deskripsi_desa);
        tvDeskripsiKegiatan = findViewById(R.id.tv_yang_akan_dilakukan);

        btnPesan = findViewById(R.id.btn_pesan);


        Intent i=getIntent();
        final String food=i.getExtras().getString("jangkrik");


        tvNamaDesa.setText(food);

        btnPesan.setOnClickListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent i=getIntent();
        final String wisata_paket=i.getExtras().getString("jangkrik");

        DocumentReference docRef = db.collection("paket_wisata").document(wisata_paket);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String alamat = document.getString("alamat");
                        String deskripsiDesa = document.getString("deskripsi_desa");
                        String deskripsiKegiatan = document.getString("deskripsi_kegiatan");
                        String fasilitas = document.getString("fasilitas");
                        String img_url = document.getString("img_url");
                        String jenis = document.getString("jenis");
                        String harga = document.getLong("harga").toString();

                        DecimalFormat kursIdr = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                        formatRp.setCurrencySymbol("Rp");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        kursIdr.setDecimalFormatSymbols(formatRp);

                        String hargaIndo = kursIdr.format(Long.valueOf(harga));

                        String durasi = document.getLong("durasi").toString();



                        tvAlamat.setText( alamat);
                        tvDeskripsiDesa.setText(deskripsiDesa);
                        tvDeskripsiKegiatan.setText(deskripsiKegiatan);
                        tvJenis.setText(jenis);
                        tvHarga.setText(hargaIndo);
                        tvDurasi.setText(durasi);
                       // tvfa.setText(fasilitas);
                        Picasso.get()
                                .load(img_url)
                                .placeholder(R.mipmap.ic_launcher)
                                .into(imgPhotoPaket);
                       // Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                       // Log.d(TAG, "No such document");
                    }
                } else {
                   // Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pesan: {
                switch (view.getId()) {
                    case R.id.btn_pesan:
                        Intent intent = new Intent(DetailPaketActivity.this,PesanPaketActivity.class);
                        intent.putExtra("namaDesa",tvNamaDesa.getText().toString());
                        intent.putExtra("harga",tvHarga.getText().toString());
                        intent.putExtra("durasi",tvDurasi.getText().toString());

                        break;
                }


            }
        }
    }
}
