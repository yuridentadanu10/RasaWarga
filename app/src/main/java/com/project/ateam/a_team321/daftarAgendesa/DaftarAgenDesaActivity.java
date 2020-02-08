package com.project.ateam.a_team321.daftarAgendesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ateam.a_team321.R;

public class DaftarAgenDesaActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_KTP, et_namaDesa,et_Provinsi, et_kabupatenKota, et_kecamatan, et_NamaPokdarwis;
    Button btn_UnggahKTP, btnLanjut;
    TextView tvBatal;
    Bundle dataBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agendesa_daftar);

        et_KTP = findViewById(R.id.edt_no_ktp);
        et_namaDesa = findViewById(R.id.edt_nama_desa);
        et_Provinsi = findViewById(R.id.edt_provinsi);
        et_kabupatenKota = findViewById(R.id.edt_kabupaten);
        et_kecamatan = findViewById(R.id.edt_kecamatan);
        et_NamaPokdarwis = findViewById(R.id.edt_nama_pokdarwis);
        btn_UnggahKTP = findViewById(R.id.btn_unggah_ktp);
        btnLanjut = findViewById(R.id.btn_lanjut);
        tvBatal = findViewById(R.id.tv_batal);

        btnLanjut.setOnClickListener(this);
        btn_UnggahKTP.setOnClickListener(this);
        tvBatal.setOnClickListener(this);
    }

    public void sendBundle(){

        dataBundle = new Bundle();
        dataBundle.putString("noKTP",et_KTP.getText().toString());
        dataBundle.putString("namaDesa",et_namaDesa.getText().toString());
        dataBundle.putString("provinsi",et_Provinsi.getText().toString());
        dataBundle.putString("kabupaten",et_kabupatenKota.getText().toString());
        dataBundle.putString("kecamatan",et_kecamatan.getText().toString());
        dataBundle.putString("provinsi",et_Provinsi.getText().toString());

        Intent mainIntent = new Intent(DaftarAgenDesaActivity.this,DaftarAgenDesaActivity2.class);
        mainIntent.putExtras(dataBundle);
        startActivity(mainIntent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lanjut:
               sendBundle();
                break;
            case R.id.btn_unggah_ktp:
                //unggah
                break;
            case R.id.tv_batal:
                finish();
                break;

        }


    }
}
