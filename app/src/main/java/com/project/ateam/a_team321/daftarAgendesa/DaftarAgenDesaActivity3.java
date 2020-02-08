package com.project.ateam.a_team321.daftarAgendesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.project.ateam.a_team321.R;

import java.util.ArrayList;

public class DaftarAgenDesaActivity3 extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {

    CheckBox cb_1,cb_2,cb_3,cb_4,cb_5;
    Button btn_pilihLokasi,btn_tambahgambar,btn_lanjut;
    String durasi_spinner_pilih;
    EditText et_deskripsiDesa,et_deskripsiKegiatan,etHarga;

    ArrayList<String> ar = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agendesa_paket);

        et_deskripsiDesa = findViewById(R.id.edt_deskripsi_desa);
        et_deskripsiKegiatan = findViewById(R.id.edt_deskripsi_kegiatan);
        etHarga = findViewById(R.id.edt_harga);
        cb_1 = findViewById(R.id.cb_makan);
        cb_2 = findViewById(R.id.cb_penginapan);
        cb_3 = findViewById(R.id.cb_guide);
        cb_4 = findViewById(R.id.cb_mck);
        cb_5 = findViewById(R.id.cb_transportasi);
        btn_pilihLokasi = findViewById(R.id.btn_pilih_lokasi);
        btn_tambahgambar = findViewById(R.id.btn_tambah_gambar);
        btn_lanjut = findViewById(R.id.btn_lanjut);

        btn_lanjut.setOnClickListener(this);

        //spinner

        Spinner spinner = (Spinner) findViewById(R.id.sp_durasi);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hari_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

    }

    private void getCheckbox() {
        if (cb_1.isChecked()) {
            ar.add(cb_1.getText().toString());
        }
        if (cb_2.isChecked()) {
            ar.add(cb_2.getText().toString());
        }
        if (cb_3.isChecked()) {
            ar.add(cb_3.getText().toString());
        }
        if (cb_4.isChecked()) {
            ar.add(cb_4.getText().toString());
        }
        if (cb_5.isChecked()) {
            ar.add(cb_5.getText().toString());
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lanjut:
                getCheckbox();
                String arrayFasilitas = ar.toString();
                Bundle extras = getIntent().getExtras();

                extras.putString("harga",etHarga.getText().toString());
                extras.putString("fasilitas",arrayFasilitas);
                extras.putString("deskripsiDesa",et_deskripsiDesa.getText().toString());
                extras.putString("deskripsiKegiatan",et_deskripsiKegiatan.getText().toString());
                extras.putString("durasi",durasi_spinner_pilih);

                Intent mainIntent = new Intent(DaftarAgenDesaActivity3.this,DaftarAgenDesaActivity4.class);
                mainIntent.putExtras(extras);
                startActivity(mainIntent);


                break;
            case R.id.btn_tambah_gambar:
                //unggah
                break;

            case R.id.btn_pilih_lokasi:
               //
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       durasi_spinner_pilih = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

