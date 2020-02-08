package com.project.ateam.a_team321.daftarAgendesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.ateam.a_team321.LoginAndFriend.User;
import com.project.ateam.a_team321.R;

import java.util.HashMap;
import java.util.Map;

public class TambahTuanRumahActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_namaKepala, et_deskripsiKeluarga, et_noKTPKepala;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;

    Button btntambahKepala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agendesa_tuan_rumah);

        et_namaKepala = findViewById(R.id.edt_kepala_keluarga);
        et_deskripsiKeluarga = findViewById(R.id.edt_deskripsi_keluarga);
        et_noKTPKepala = findViewById(R.id.edt_nomer_ktp);
        btntambahKepala = findViewById(R.id.btn_tambah_kepala);
        btntambahKepala.setOnClickListener(this);
    }

    private void writeKepalaKeluarga(){

        String kpalaKluarga = et_namaKepala.getText().toString();
        String deskripsiKeluarga = et_deskripsiKeluarga.getText().toString();
        String noKTPKepala = et_noKTPKepala.getText().toString();

        KepalaKeluarga user = new KepalaKeluarga(kpalaKluarga, deskripsiKeluarga,noKTPKepala,"https://firebasestorage.googleapis.com/v0/b/belajar-12041.appspot.com/o/photos%2Fimage%3A128757?alt=media&token=84e17c6b-a96b-4b03-8d6c-bdcc60417cb3");

        final String uid =   FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("users").document(uid).collection("rumahDesa")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

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
            case R.id.btn_tambah_kepala:
                writeKepalaKeluarga();
                Bundle extras = getIntent().getExtras();
                Intent mainIntent = new Intent(TambahTuanRumahActivity.this, DaftarAgenDesaActivity4.class);
                mainIntent.putExtras(extras);
                startActivity(mainIntent);
                break;


        }
    }
}
