package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class giris extends AppCompatActivity {
    public Button dvm;
    public Button kyt , srg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giris);
        dvm = findViewById(R.id.dvm);
        srg=findViewById(R.id.sbtn);
        dvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent der = new Intent(giris.this, kullanici.class);
                startActivity(der);
            }
        });
        kyt = findViewById(R.id.kyt);

        srg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ı = new Intent(giris.this,uye.class);
                startActivity(ı);
            }
        });

        kyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kayit = new Intent(giris.this, kayitol.class);
                startActivity(kayit);
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
            }
        });
    }
}
