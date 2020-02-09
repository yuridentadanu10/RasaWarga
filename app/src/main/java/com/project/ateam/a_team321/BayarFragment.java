package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BayarFragment extends AppCompatActivity implements View.OnClickListener {

    TextView tvTotalBayar;
    Button btnTransfer, btnGopay, btnIndomaret, btnBayar;
    TextView tvKembali;
    ImageView check1, check2, check3;

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

        check1 = findViewById(R.id.img_check);
        check2 = findViewById(R.id.img_check2);
        check3 = findViewById(R.id.img_check3);


        Bundle bundleFinal = getIntent().getExtras();
        String namaWarga=bundleFinal.getString("nama_warga");
        int harga=bundleFinal.getInt("totalHarga");
        String namaDesa= bundleFinal.getString("nama_warga");

        DecimalFormat kursIdr = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIdr.setDecimalFormatSymbols(formatRp);

        String hargaIndo = kursIdr.format(Long.valueOf(harga));

        tvTotalBayar.setText(hargaIndo);


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
                check1.setVisibility(View.VISIBLE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                break;
            }

            case R.id.btn_gopay: {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.VISIBLE);
                check3.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_indomaret: {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.bayar: {
                if (check1.getVisibility() == View.VISIBLE || check2.getVisibility() == View.VISIBLE || check3.getVisibility() == View.VISIBLE){
                    Intent intent = new Intent(BayarFragment.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Kamu belum memilih metode pembayaran", Toast.LENGTH_SHORT).show();
                }
            }


        }

    }
}
