package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class kullanici extends AppCompatActivity {
private EditText editText;
EditText sifre,kullanici,ad;
Button gbtn;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kullanici);
        kullanici =findViewById(R.id.ktxt);
        sifre= findViewById(R.id.stxt);
        gbtn=findViewById(R.id.gbtn);
        ad=findViewById(R.id.etxt);

DB = new DBHelper(this);
        sifre.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

gbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String  user = kullanici.getText().toString();
        String pass = sifre.getText().toString();
        String adi = ad.getText().toString();
        if (user.equals("")||pass.equals("")||adi.equals(("")))
            Toast.makeText(kullanici.this, "TÜM GEREKLİ YERLERİ DOLDURUN ", Toast.LENGTH_SHORT).show();
        else {
            Boolean checkuserpass = DB.checkusernamepassword(user, pass,adi);

            if (checkuserpass == true) {

                Toast.makeText(kullanici.this,"Hoşgeldin "+" " + adi ,Toast.LENGTH_SHORT).show();
                Intent k = new Intent(kullanici.this, anaekran.class);
                startActivity(k);


            } else {
                Toast.makeText(kullanici.this, "BİLGİLERİN DOĞRU OLDUĞUNU KONTROL ET", Toast.LENGTH_SHORT).show();
            }
        }
    }
});


         }
         }








