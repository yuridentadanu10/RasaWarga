package com.project.ateam.a_team321;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PostAnalisisActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnConfirm;
    TextView foodName,calorieInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_analisis);



        //RECEIVE OUR DATA
        Intent i=getIntent();
        final String food=i.getExtras().getString("wisata");
        final Double calorie = i.getExtras().getDouble("deskripsi");
        foodName = findViewById(R.id.tv_jenis);
        foodName.setText(food);
        calorieInfo = findViewById(R.id.tv_deskripsi_jenis);
        calorieInfo.setText(String.valueOf(calorie)+" Calories");

        btnConfirm = findViewById(R.id.btn_ok);

        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                startActivity(new Intent(PostAnalisisActivity.this, MainActivity.class));
                break;

        }
    }
}
