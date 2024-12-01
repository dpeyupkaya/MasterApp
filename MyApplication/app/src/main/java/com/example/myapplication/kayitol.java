package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class kayitol extends AppCompatActivity {

    private EditText kadi, sif, ad, eposta;
    private CheckBox checkBox;

    private Button kyt ;
    RadioGroup rb;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);

        eposta = findViewById(R.id.eptxt);
        ad = findViewById(R.id.txt);
        kadi = findViewById(R.id.ktxt);
        sif = findViewById(R.id.stxt);
        kyt = findViewById(R.id.kyt2);
        checkBox = findViewById(R.id.checkBox);
        DB = new DBHelper(this);
        rb = findViewById(R.id.rgr);
        sif.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                 
                    AlertDialog.Builder builder = new AlertDialog.Builder(kayitol.this);
                    builder.setTitle("Hizmet Koşulları");
                    builder.setMessage("Kullanıcılarımızdan topladığımız veriler, hizmetlerimizi geliştirmek, özelleştirmek ve kullanıcı deneyimini iyileştirmek amacıyla işlenmektedir. Toplanan veriler, yürürlükteki yasal düzenlemelere uygun olarak güvenli bir şekilde saklanmakta ve yalnızca belirtilen amaçlar doğrultusunda kullanılmaktadır. Kişisel verileriniz, sizin izniniz olmadan üçüncü şahıslarla paylaşılmayacak olup, sadece hizmet sunumunu gerçekleştirebilmek ve müşteri memnuniyetini artırmak amacıyla iş ortaklarımızla sınırlı bir şekilde paylaşılabilir. Verilerinizin gizliliğini ve güvenliğini sağlamak için gerekli tüm teknik ve idari tedbirler alınmıştır. Hizmetlerimizi kullanarak, verilerinizin bu koşullar altında işlenmesini kabul etmiş olursunuz.");
                    builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          
                            checkBox.setChecked(true);
                            dialog.dismiss();
                        }
                    });
                    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                      
                            checkBox.setChecked(false);
                        }
                    });
                    builder.show();
                } else {
                 
                }
            }
        });

        kyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = rb.getCheckedRadioButtonId();

              
                if (selectedRadioButtonId == -1) {
                    Toast.makeText(kayitol.this, "Cinsiyet seçimi yapın", Toast.LENGTH_SHORT).show();
                    return; 
                }
                String userr = kadi.getText().toString();
                String passs = sif.getText().toString();
                String adi = ad.getText().toString();
                String epost = eposta.getText().toString();
                boolean isChecked = checkBox.isChecked();

                if (userr.isEmpty() || passs.isEmpty() || adi.isEmpty() || epost.isEmpty()) {
                    Toast.makeText(kayitol.this, "LÜTFEN TÜM BİLGİLERİ GİRİNİZ", Toast.LENGTH_LONG).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(epost).matches()) {
                    Toast.makeText(kayitol.this, "Geçerli bir e-posta adresi girin", Toast.LENGTH_LONG).show();
                } else if (!isChecked) {
                    Toast.makeText(kayitol.this, "HİZMET KOŞULLARINI ONAYLAYIN", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Boolean checkuser = DB.checkusername(userr);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(userr, passs, adi);
                            if (insert) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(kayitol.this);
                                builder.setTitle("BAŞARDIN");
                                builder.setMessage("HELAL OLSUN");
                                builder.setPositiveButton("Eyvallah", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent j = new Intent(kayitol.this, kullanici.class);
                                        startActivity(j);
                                        finish();
                                    }
                                });
                                builder.show();
                            } else {
                                Toast.makeText(kayitol.this, "KAYIT BAŞARISIZ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(kayitol.this, "KULLANICI ADI ZATEN MEVCUT", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("kayitol", "Error occurred: " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(kayitol.this, "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
