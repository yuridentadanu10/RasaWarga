package com.project.ateam.a_team321.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.project.ateam.a_team321.R;
import com.project.ateam.a_team321.daftarAgendesa.DaftarAgenDesaActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment implements View.OnClickListener {

    private String status;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;

    private ImageView imgPhoto;
    private TextView tvNama, tvEditProfil, tvStatus, tvHistori, tvJadiAgendesa, tvEditDesa,
            tvEditTuanRumah, tvPesanan, tvKeluar;

    private LinearLayout llJadiAgenDesa, llEditDesa, llEditTuanRumah, llPesanan,llHistory;


    public AkunFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llJadiAgenDesa = view.findViewById(R.id.ll_jadi_agen_desa);
        llEditDesa =view.findViewById(R.id.ll_edit_desa);
        llEditTuanRumah = view.findViewById(R.id.ll_edit_tuan_rumah);
        llPesanan = view.findViewById(R.id.ll_pesanan);

        imgPhoto = view.findViewById(R.id.img_photo);
        tvNama = view.findViewById(R.id.tv_agendesa);
        tvEditProfil = view.findViewById(R.id.tv_edit_profil);
        tvStatus = view.findViewById(R.id.tv_status_agendesa);
        tvHistori = view.findViewById(R.id.tv_histori);
        tvJadiAgendesa = view.findViewById(R.id.tv_jadi_agendesa);
        tvEditDesa = view.findViewById(R.id.tv_edit_desa);
        tvEditTuanRumah = view.findViewById(R.id.tv_edit_tuan_rumah);
        tvPesanan = view.findViewById(R.id.tv_pesanan);
        tvKeluar = view.findViewById(R.id.tv_keluar);
        llHistory=view.findViewById(R.id.ll_history);

getStatus();



        tvHistori.setOnClickListener(this);
        tvJadiAgendesa.setOnClickListener(this);
        tvEditDesa.setOnClickListener(this);
        tvEditTuanRumah.setOnClickListener(this);
        tvPesanan.setOnClickListener(this);
        tvKeluar.setOnClickListener(this);
    }

    private void getStatus(){
        String sts;
        final String uid =   FirebaseAuth.getInstance().getCurrentUser().getUid();


        final DocumentReference userRef = db.collection("users").document(uid);
        userRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                  //  Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    tvNama.setText(snapshot.getString("name"));
                    String status = snapshot.getString("jenisAkun");
                    if (status.equals("biasa")){
                        llJadiAgenDesa.setVisibility(View.VISIBLE);
                        llHistory.setVisibility(View.VISIBLE);
                        llEditDesa.setVisibility(View.GONE);
                        llEditTuanRumah.setVisibility(View.GONE);
                        llPesanan.setVisibility(View.GONE);
                    } else if (status.equalsIgnoreCase("Agendesa")) {
                        llEditDesa.setVisibility(View.VISIBLE);
                        llHistory.setVisibility(View.VISIBLE);
                        llEditTuanRumah.setVisibility(View.VISIBLE);
                        llPesanan.setVisibility(View.VISIBLE);
                        llJadiAgenDesa.setVisibility(View.GONE);
                    }

                } else {
                   // Log.d(TAG, "Current data: null");
                }
            }
        });
    }


    private void createNestedCollection(){
        Map<String, Object> data = new HashMap<>();
        data.put("null", "null");

        final String uid =   FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("users").document(uid).collection("rumahDesa")
                .add(data)
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
        switch (view.getId()){
            case  R.id.tv_histori : {
                break;
            }
            case R.id.tv_jadi_agendesa: {
                createNestedCollection();
                Intent intent = new Intent(this.getContext(), DaftarAgenDesaActivity.class);
                startActivity(intent);
                break;
            }
            case  R.id.tv_edit_desa: {
                // TODO: Activity edit desa
            }
            case R.id.tv_edit_tuan_rumah: {
                // TODO: Activity edit tuan rumah
            }
            case R.id.tv_pesanan: {
                // TODO: Activity pesanan
            }
            case R.id.tv_keluar: {
                // TODO: Logout
            }
        }
    }
}
