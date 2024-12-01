package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class anaekran extends AppCompatActivity {

    ListView listView;
    EditText searchView;
    String[] arr = {"Eyüp Usta" "Sadık Usta", "Bigboss"};

    List<String> itemList;

    private AlertDialog ratingDialog;
    private boolean ratingDialogShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anaekran);

        listView = findViewById(R.id.lv);
        searchView = findViewById(R.id.search_view);

        itemList = Arrays.asList(arr);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);

       
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showRatingDialogOnce(); 
            }
        }, 20000);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Seçim Yapınız");
        menu.add(0, v.getId(), 0, "Randevu Al");
        menu.add(0, v.getId(), 0, "Ara");
        menu.add(0, v.getId(), 0, "Konumu Gör");
        menu.add(0, v.getId(), 0, "Hakkımızda Daha Fazla Bilgi");
        menu.add(0, v.getId(), 0, "Çıkış Yap");


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String selectedPerson = itemList.get(info.position);

        if (item.getTitle().equals("Randevu Al")) {
            Intent m = new Intent(anaekran.this, randevual.class);
            startActivity(m);
            Toast.makeText(this, "RANDEVU EKRANINA YÖNLENDİRİLİYORSUNUZ", Toast.LENGTH_SHORT).show();
            return true;

        } else if (item.getTitle().equals("Hakkımızda Daha Fazla Bilgi")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=H08aYNTfHzU"));
            startActivity(intent);
            Toast.makeText(this, "WEB SİTESİNE YÖNLENDİRİLİYORSUNUZ", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getTitle().equals("Konumu Gör")) {
            String location = getLocation(selectedPerson);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
            startActivity(intent);
            Toast.makeText(this, "HARİTA EKRANINA YÖNLENDİRİLİYORSUNUZ", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getTitle().equals("Ara")) {
            String phoneNumber = getPhoneNumber(selectedPerson);
            Intent c = new Intent(Intent.ACTION_DIAL);
            c.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(c);
            Toast.makeText(this, "ARAMA EKRANINA YÖNLENDİRİLİYORSUNUZ", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getTitle().equals("Çıkış Yap")) {
            Intent o = new Intent(anaekran.this,giris.class);
            startActivity(o);

            Toast.makeText(this, "ÇIKIŞ EKRANINA YÖNLENDİRİLİYORSUNUZ", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private String getLocation(String person) {
        switch (person) {
         
            case "Eyüp Usta":
        

            case "Sadık Usta":
                return "geo:41.008238,28.978359";
            case "Bigboss":
                return "geo:41.008238,28.978359";
              
        }
    }
    private String getPhoneNumber(String person) {
        switch (person) {
        
            case "Eyüp Usta":
          
            case "Sadık Usta":
                return "5551112233";
            case "Bigboss":
                return "5551234567";
           
        }
    }

    private void showRatingDialogOnce() {
        if (!ratingDialogShown) { 
            showRatingDialog(); 
            ratingDialogShown = true; 
        }
    }

    private void showRatingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lütfen Değerlendirin");

        
        final RatingBar ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setMax(5);
        ratingBar.setStepSize(1);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button positiveButton = new Button(this);
        positiveButton.setText("TABİ Kİ");
        positiveButton.setOnClickListener(v -> {
            Toast.makeText(this, "TEŞEKKÜR EDERİZ", Toast.LENGTH_SHORT).show();
            ratingDialog.dismiss(); 
        });

       
        Button negativeButton = new Button(this);
        negativeButton.setText("İptal");
        negativeButton.setOnClickListener(v -> {
            Toast.makeText(this, "ÜZÜLDÜK :(", Toast.LENGTH_SHORT).show();
            ratingDialog.dismiss();
        });

      
        buttonLayout.addView(positiveButton);
        buttonLayout.addView(negativeButton);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(ratingBar);
        layout.addView(buttonLayout);
        layout.setPadding(50, 40, 50, 10);

        builder.setView(layout);

        ratingDialog = builder.create();
        ratingDialog.show();
    }
}
