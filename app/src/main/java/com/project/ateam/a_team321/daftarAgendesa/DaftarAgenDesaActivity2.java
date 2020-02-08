package com.project.ateam.a_team321.daftarAgendesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.ateam.a_team321.R;

public class DaftarAgenDesaActivity2 extends AppCompatActivity implements View.OnClickListener  {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    CheckBox cb_1,cb_2,cb_3,cb_4,cb_5,cb_6;
    Button btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agendesa_desa);
        radioGroup =  findViewById(R.id.radioGroup);
        cb_1 = findViewById(R.id.cb_organisasi);
        cb_2 = findViewById(R.id.cb_pokdarwis);
        cb_3 = findViewById(R.id.cb_koperasi);
        cb_4 = findViewById(R.id.cb_tempat_ibadah);
        cb_5 = findViewById(R.id.cb_balai_desa);
        cb_6 = findViewById(R.id.cb_lapangan);


        btnLanjut = findViewById(R.id.btn_lanjut);


        btnLanjut.setOnClickListener(this);


    }


    public String addListenerOnButton() {

        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
            }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lanjut:
                String jenisWisata = addListenerOnButton();
                Bundle extras = getIntent().getExtras();
                extras.putString("jenisWisata",jenisWisata);

                Intent mainIntent = new Intent(DaftarAgenDesaActivity2.this,DaftarAgenDesaActivity3.class);
                mainIntent.putExtras(extras);
                startActivity(mainIntent);


                break;
            case R.id.btn_unggah_ktp:

                //unggah
                break;
        }


    }
}
