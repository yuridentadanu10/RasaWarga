package com.project.ateam.a_team321.daftarAgendesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.project.ateam.a_team321.AgendesaTuanRumahFragment;
import com.project.ateam.a_team321.MainActivity;
import com.project.ateam.a_team321.R;

import java.util.HashMap;
import java.util.Map;

public class DaftarAgenDesaActivity4 extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    Button btn_TambahTuanRUmah,btnDaftar,btn_kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_agendesa_tuan_rumah);

        btn_TambahTuanRUmah = findViewById(R.id.btn_tambah_tuan_rumah);
        btn_TambahTuanRUmah.setOnClickListener(this);

        btnDaftar=findViewById(R.id.btn_simpan);
        btnDaftar.setOnClickListener(this);
    }

    private void writeKepalaKeluarga(){
        final String uid =   FirebaseAuth.getInstance().getCurrentUser().getUid();
         FirebaseFirestore dx = FirebaseFirestore.getInstance();
        Map<String, Object> data = new HashMap<>();
        data.put("jenisAkun", "agendesa");
        dx.collection("users").document(uid)
                .set(data, SetOptions.merge());


        Bundle bundleFinal = getIntent().getExtras();
        String namaDesa=bundleFinal.getString("namaDesa");
        String harga=bundleFinal.getString("harga");
        int harga21 = Integer.parseInt(harga);
        String  provinsi=bundleFinal.getString("provinsi");
        String kabupaten=bundleFinal.getString("kabupaten");
        String kecamatan=bundleFinal.getString("kecamatan");
        String jenisWisata=bundleFinal.getString("jenisWisata");
        String fasilitas=bundleFinal.getString("fasilitas");
        String durasi = bundleFinal.getString("durasi");
        String durasi2= Character.toString(durasi.charAt(0));
        int durasiInt = Integer.parseInt(durasi2);

        Log.d("tesFASILITAS", "writeKepalaKeluarga: "+fasilitas);

        String deskripsiDesa =bundleFinal.getString("deskripsiDesa");
        String deskripsiKegiatan=bundleFinal.getString("deskripsiKegiatan");
        String img_Desa_URL=bundleFinal.getString("url");

        String alamat = kecamatan+", "+kabupaten+", "+provinsi;
ModelPaketWisata model = new ModelPaketWisata(namaDesa,alamat,jenisWisata,fasilitas,durasiInt,deskripsiDesa,deskripsiKegiatan,img_Desa_URL,harga21);


        db.collection("paket_wisata")
                .add(model)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DaftarAgenDesaActivity4.this);
                        builder.setMessage("Selamat Data anda Sudah ditambahkan")
                                .setCancelable(false)
                                .setPositiveButton("Kembali ke Menu", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(DaftarAgenDesaActivity4.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_tuan_rumah:

                Bundle extras = getIntent().getExtras();
                Intent mainIntent = new Intent(DaftarAgenDesaActivity4.this, TambahTuanRumahActivity.class);
                mainIntent.putExtras(extras);
                startActivity(mainIntent);

                break;
            case R.id.btn_simpan:

         writeKepalaKeluarga();

                break;
        }


    }
}
