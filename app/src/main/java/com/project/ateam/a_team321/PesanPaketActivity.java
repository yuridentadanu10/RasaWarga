package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.project.ateam.a_team321.adapterPilihKeluarga.adapterPilihKeluarga;
import com.project.ateam.a_team321.daftarAgendesa.KepalaKeluarga;


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

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    private adapterPilihKeluarga adapter;
    Bundle bundle = getIntent().getExtras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_paket);

        btnLanjut = findViewById(R.id.btn_lanjut);
        btnDurasi = findViewById(R.id.btn_durasi);
        tvDurasi = findViewById(R.id.tv_durasi);
        tvDurasiBerakhir = findViewById(R.id.tv_durasi_akhir);
        setUpRecyclerView();

        calStart = Calendar.getInstance();


btnLanjut.setOnClickListener(this);
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
    
    private void setUpRecyclerView() {
        Query query = db.collection("users").document("FDmbN96I4SgsGUs41wPv0GGa1T93").collection("rumahDesa").orderBy("nama_warga").limit(10);

        FirestoreRecyclerOptions<KepalaKeluarga> options = new FirestoreRecyclerOptions.Builder<KepalaKeluarga>()
                .setQuery(query, KepalaKeluarga.class)
                .build();

        adapter = new adapterPilihKeluarga(options);

        RecyclerView recyclerView = findViewById(R.id.rv_tuan_rumah);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new adapterPilihKeluarga.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String namaDesa = documentSnapshot.getId();
                Log.d("SSSSSSSSSSSSSS", "onItemClick: "+namaDesa);
                bundle.putString("nama_warga",namaDesa);
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
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
                        Intent hargaIntent =getIntent();
                        int durasi=Integer.parseInt(hargaIntent.getExtras().getString("durasi"));


                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        tvDurasi.setText(dateFormat.format(calStart.getTime()));

                        calStart.add(Calendar.DAY_OF_MONTH,durasi );
                        tvDurasiBerakhir.setText(dateFormat.format(calStart.getTime()));

                        // TODO: set durasi akhir
                    }
                }, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE)).show();

                break;
            }
            case R.id.btn_lanjut: {
                    bundle.putInt("totalHarga",totalHarga);
                    Intent intent = new Intent(PesanPaketActivity.this,BayarFragment.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                break;
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        jumlah_orang_pilih = adapterView.getItemAtPosition(i).toString();

        String harga=bundle.getString("harga");
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
