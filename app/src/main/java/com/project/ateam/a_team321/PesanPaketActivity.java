package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PesanPaketActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    ImageButton btnDurasi;
    TextView tvDurasi, tvDurasiBerakhir;
    RecyclerView rvTuanRumah;
    String jumlah_orang_pilih;
    int  totalHarga;
    Calendar calStart;
    Button btnLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_paket);

        btnLanjut = findViewById(R.id.btn_lanjut);
        btnDurasi = findViewById(R.id.btn_durasi);
        tvDurasi = findViewById(R.id.tv_durasi);
        tvDurasiBerakhir = findViewById(R.id.tv_durasi_akhir);

        calStart = Calendar.getInstance();

        btnDurasi.setOnClickListener(this);


        Spinner spinner = (Spinner) findViewById(R.id.sp_jumlah_peserta);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.orang_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_durasi: {
                final Calendar startDate = Calendar.getInstance();
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calStart.set(year, month, day);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        tvDurasi.setText(dateFormat.format(calStart.getTime()));
                        // TODO: set durasi akhir
                    }
                }, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE)).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        jumlah_orang_pilih = adapterView.getItemAtPosition(i).toString();
        Intent hargaIntent =getIntent();
        String harga=hargaIntent.getExtras().getString("harga");
        int harga21 = Integer.parseInt(harga);
        char s=jumlah_orang_pilih.charAt(0);
        String jumlah212 =Character.toString(s);
        int jumlah_orang = Integer.parseInt(jumlah212);
        totalHarga = jumlah_orang* harga21;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
