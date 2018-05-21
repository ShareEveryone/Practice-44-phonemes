package com.example.kyhandsome.tkpmgd;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.kyhandsome.tkpmgd.Adapter.RecyclerViewAdapter;
import com.example.kyhandsome.tkpmgd.Model.Sound;

import java.util.ArrayList;

public class Vowel extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Khởi tạo các biến và database
    final String DATABASE_NAME = "DBtkpm.sqlite";
    SQLiteDatabase database;
    ArrayList<Sound> list = new ArrayList<Sound>();

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<String> mName1s = new ArrayList<>();
    private ArrayList<String> mImageUrl1s = new ArrayList<>();

    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vowel_screen);

        getImages();
    }
    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("/ i /");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("/ e /");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("/ a /");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("/ ʊ /");

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("/ ʌ /");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("/ ə /");

        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("/ i: /");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("/ æ /");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("/ ɔ: /");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("/ u: /");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("/ a: /");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("/ ɜ: /");

        //nguyen am doi
        mImageUrl1s.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mName1s.add("/ ei /");

        mImageUrl1s.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mName1s.add("/ ɔu /");

        mImageUrl1s.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mName1s.add("/ eə /");

        mImageUrl1s.add("https://i.redd.it/j6myfqglup501.jpg");
        mName1s.add("/ ʊə /");

        mImageUrl1s.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mName1s.add("/ ɑi /");

        mImageUrl1s.add("https://i.redd.it/k98uzl68eh501.jpg");
        mName1s.add("/ ɑu /");

        mImageUrl1s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName1s.add("/ iə /");

        mImageUrl1s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName1s.add("/ ɔi /");

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(layoutManager2);
        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(this, mName1s, mImageUrl1s);
        recyclerView2.setAdapter(adapter2);
    }
}
