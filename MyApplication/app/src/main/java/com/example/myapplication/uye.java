package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class uye extends AppCompatActivity {

    SearchView searchView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye);

        searchView = findViewById(R.id.sc);
        listView = findViewById(R.id.listView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String searchText = s.trim();
                if (!searchText.isEmpty()) {
                    ArrayList<String> txtList = getTxtList(searchText);
                    if (txtList.size() > 0) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(uye.this, android.R.layout.simple_list_item_1, txtList);
                        listView.setAdapter(adapter);
                    } else {
                     
                        Toast.makeText(uye.this, "Arama sonucu bulunamadı", Toast.LENGTH_SHORT).show();
                    }
                } else {
                
                    listView.setAdapter(null);
                }
                return false;
            }
        });
    }

    private ArrayList<String> getTxtList(String searchText) {
        ArrayList<String> txtList = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(uye.this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT txt FROM users WHERE txt LIKE ?", new String[]{"%" + searchText + "%"});
        if (cursor.moveToFirst()) {
            do {
                String txt = cursor.getString(cursor.getColumnIndex("txt"));
                txtList.add(txt);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return txtList;
    }
}
