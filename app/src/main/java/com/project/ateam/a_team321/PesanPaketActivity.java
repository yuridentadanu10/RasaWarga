package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PesanPaketActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spJumlahPeserta;
    ImageButton btnDurasi;
    TextView tvDurasi, tvDurasiBerakhir;
    RecyclerView rvTuanRumah;

    Calendar calStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_paket);

        spJumlahPeserta = findViewById(R.id.sp_jumlah_peserta);
        btnDurasi = findViewById(R.id.btn_durasi);
        tvDurasi = findViewById(R.id.tv_durasi);
        tvDurasiBerakhir = findViewById(R.id.tv_durasi_akhir);

        calStart = Calendar.getInstance();

        btnDurasi.setOnClickListener(this);

        String jumlahPeserta = spJumlahPeserta.getSelectedItem().toString();
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
}
