package com.project.ateam.a_team321.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.ateam.a_team321.R;
import com.project.ateam.a_team321.daftarAgendesa.DaftarAgenDesaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment implements View.OnClickListener {

    private String status;

    private ImageView imgPhoto;
    private TextView tvNama, tvEditProfil, tvStatus, tvHistori, tvJadiAgendesa, tvEditDesa,
            tvEditTuanRumah, tvPesanan, tvKeluar;


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

       String status1= getStatus(status);

        if (status1.equals("biasa")){
            tvEditDesa.setVisibility(View.GONE);
            tvEditTuanRumah.setVisibility(View.GONE);
            tvPesanan.setVisibility(View.GONE);
        } else if (status1.equals("Agendesa")) {
            tvJadiAgendesa.setVisibility(View.GONE);
        }

        tvHistori.setOnClickListener(this);
        tvJadiAgendesa.setOnClickListener(this);
        tvEditDesa.setOnClickListener(this);
        tvEditTuanRumah.setOnClickListener(this);
        tvPesanan.setOnClickListener(this);
        tvKeluar.setOnClickListener(this);
    }

    private String getStatus(String status){
        status = "biasa";

        return status;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_histori : {
                break;
            }
            case R.id.tv_jadi_agendesa: {
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
