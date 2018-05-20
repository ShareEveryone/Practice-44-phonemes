package com.example.kyhandsome.tkpmgd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyhandsome.tkpmgd.Adapter.HistoryAdapter;
import com.example.kyhandsome.tkpmgd.Adapter.WordAdapter;
import com.example.kyhandsome.tkpmgd.Database.Database;
import com.example.kyhandsome.tkpmgd.Database.DatabaseHelper;
import com.example.kyhandsome.tkpmgd.Model.Sound;
import com.example.kyhandsome.tkpmgd.Model.Worddo;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    DatabaseHelper db;
    final String DATABASE_NAME = "DB.sqlite";
    SQLiteDatabase database;
    ArrayList<Worddo> list = new ArrayList<Worddo>();
    HistoryAdapter adapter;
    ListView listViewHistory;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_screen);

        toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lịch sử");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addControls();
        readData();
    }

    private void addControls() {
        listViewHistory = (ListView) findViewById(R.id.listviewHistory);
        list = new ArrayList<>();
        adapter = new HistoryAdapter(History.this, list);
        listViewHistory.setAdapter(adapter);
    }
    // Hàm đọc dữ liệu
    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from worddo ", null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int soundid = cursor.getInt(2);
            int star = cursor.getInt(3);

            list.add(new Worddo(id, name, soundid, star));
        }
        adapter.notifyDataSetChanged();
        cursor.close();
        database.close();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ex, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.itemprofile:
                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemhistory:
                Intent intent = new Intent(History.this, History.class);
                startActivity(intent);
                return true;
            case R.id.itemlogout:
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemsearch:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemshare:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
