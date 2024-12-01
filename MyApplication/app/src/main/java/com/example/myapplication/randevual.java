package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class randevual extends AppCompatActivity {

    Button sac1Button, sac2Button, sac3Button, sac4Button, onaylaButton;
    String selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevual);

        sac1Button = findViewById(R.id.sac1);
        sac2Button = findViewById(R.id.sac2);
        sac3Button = findViewById(R.id.sac3);
        sac4Button = findViewById(R.id.sac4);
        onaylaButton = findViewById(R.id.obtn);

        selectedDateTime = "";

        sac1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(sac1Button);
            }
        });

        sac2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(sac2Button);
            }
        });

        sac3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(sac3Button);
            }
        });

        sac4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(sac4Button);
            }
        });

        onaylaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedDateTime.isEmpty()) {
                    boolean inserted = insertData(selectedDateTime);
                    if (inserted) {
                        Toast.makeText(randevual.this, "Randevunuz onaylandı. Randevu Zamanı " + selectedDateTime, Toast.LENGTH_SHORT).show();


                        disableOtherButtons();

                        Intent intent = new Intent(randevual.this, anaekran.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(randevual.this, "Randevu Alma İşleminiz Başarısız", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(randevual.this, "Randevu Saati Seçiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDateTimePicker(final Button button) {
       
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(randevual.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDateTime = dayOfMonth + "/" + (month + 1) + "/" + year;
                        showTimePicker(button, hour, minute);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void showTimePicker(final Button button, int hour, int minute) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(randevual.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime += " " + hourOfDay + ":00"; 
                        button.setText(selectedDateTime);

                       
                        if (button != sac1Button) {
                            sac1Button.setText("");
                        }
                        if (button != sac2Button) {
                            sac2Button.setText("");
                        }
                        if (button != sac3Button) {
                            sac3Button.setText("");
                        }
                        if (button != sac4Button) {
                            sac4Button.setText("");
                        }
                    }
                }, hour, minute, true); 
        timePickerDialog.show();
    }





    private void disableOtherButtons() {
        sac1Button.setEnabled(false);
        sac2Button.setEnabled(false);
        sac3Button.setEnabled(false);
        sac4Button.setEnabled(false);
    }


    public boolean insertData(String selectedDateTime) {
        veri databaseHelper = new veri(randevual.this);
        if (databaseHelper.checkIfDateExists(selectedDateTime)) {

            Toast.makeText(randevual.this, "Bu tarihte ve saatte randevu alınmıştır.Lütfen başka tarih ve saat seçiniz.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return databaseHelper.insertData(selectedDateTime, selectedDateTime, selectedDateTime, selectedDateTime); 
        }
    }
}
