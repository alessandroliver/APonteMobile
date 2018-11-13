package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MembroActivity extends AppCompatActivity {

    private Button maisMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membro);

        maisMembro = findViewById(R.id.add_membro);

        maisMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembroActivity.this, MaisMembroActivity.class);
                startActivity(intent);
            }
        });

    }
}
