package com.example.kyhandsome.tkpmgd;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyhandsome.tkpmgd.Database.Database;
import com.example.kyhandsome.tkpmgd.Adapter.InstructionAdapter;
import com.example.kyhandsome.tkpmgd.Model.Sound;

import java.util.ArrayList;

public class Instruction extends AppCompatActivity {

    private static final String TAG = "Instruction";
    // Khởi tạo các biến và database
    final String DATABASE_NAME = "DBtkpm.sqlite";
    SQLiteDatabase database;
    ArrayList<Sound> list = new ArrayList<Sound>();
    InstructionAdapter adapter;
    public Context context;
    Toolbar toolbar;

    ListView listView;
    public String textNguyenam = "";
    public String txtSound = "";
    public String textinstruction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);

        toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phát âm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handle();

        addControls();
        readData();
        doClickStart();
    }
    public void handle(){

        textNguyenam = getIntent().getStringExtra("name_url");
        getIntent().putExtra("name_url", "");

    }

    //Hàm thêm điều khiển
    private void addControls() {
        listView = (ListView) findViewById(R.id.listViewIns);
        list = new ArrayList<>();
        adapter = new InstructionAdapter(this, list);
        listView.setAdapter(adapter);
    }
    // Hàm đọc dữ liệu
    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from sound where sound.name = '" + textNguyenam +"' limit 1" , null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String textinstruction = cursor.getString(2);
            String videourl = cursor.getString(3);
            String imageurl = cursor.getString(4);
            String audiourl = cursor.getString(5);
            list.add(new Sound(id, name, textinstruction, videourl, imageurl, audiourl));
        }
        textNguyenam = "";
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
                Intent intent = new Intent(Instruction.this, History.class);
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
    //hàm xử lí khi click nút Xong
    public void doClickStart() {
        Button btnXong = (Button) findViewById(R.id.btnstart);
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //chuyển hoạt động sang class khác.
                Intent intent = new Intent(Instruction.this, DoWord.class);
                startActivity(intent);
            }
        });
    }


}
