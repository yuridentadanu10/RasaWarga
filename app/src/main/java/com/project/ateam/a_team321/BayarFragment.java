package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BayarFragment extends AppCompatActivity implements View.OnClickListener {

    TextView tvTotalBayar;
    Button btnTransfer, btnGopay, btnIndomaret, btnBayar;
    TextView tvKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_fragment);

        tvTotalBayar = findViewById(R.id.tv_total_bayar);
        tvKembali = findViewById(R.id.tv_kembali);

        btnTransfer = findViewById(R.id.btn_transfer);
        btnGopay = findViewById(R.id.btn_gopay);
        btnIndomaret = findViewById(R.id.btn_indomaret);
        btnBayar = findViewById(R.id.bayar);

        btnTransfer.setOnClickListener(this);
        btnGopay.setOnClickListener(this);
        btnIndomaret.setOnClickListener(this);
        btnBayar.setOnClickListener(this);
        tvKembali.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_kembali: {
                finish();
                break;
            }

            case  R.id.btn_transfer: {
                //TODO: isi fungsi
                break;
            }

            case R.id.btn_gopay: {
                //TODO
                break;
            }
            case R.id.btn_indomaret: {
                //TODO
                break;
            }
            case R.id.bayar: {
                //TODO
            }


        }

    }
}
