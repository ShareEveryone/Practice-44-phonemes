package com.example.kyhandsome.tkpmgd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kyhandsome.tkpmgd.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class Consonant extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mName1s = new ArrayList<>();
    private ArrayList<String> mImageUrl1s = new ArrayList<>();

    private ArrayList<String> mName2s = new ArrayList<>();
    private ArrayList<String> mImageUrl2s = new ArrayList<>();

    private ArrayList<String> mName3s = new ArrayList<>();
    private ArrayList<String> mImageUrl3s = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consonant_screen);

        getImages();
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrl1s.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mName1s.add("/ b /");

        mImageUrl1s.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mName1s.add("/ g /");

        mImageUrl1s.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mName1s.add("/ v /");

        mImageUrl1s.add("https://i.redd.it/j6myfqglup501.jpg");
        mName1s.add("/ z /");

        mImageUrl1s.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mName1s.add("/ d /");

        mImageUrl1s.add("https://i.redd.it/k98uzl68eh501.jpg");
        mName1s.add("/ dʒ /");

        mImageUrl1s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName1s.add("/ ð /");

        mImageUrl1s.add("https://i.redd.it/obx4zydshg601.jpg");
        mName1s.add("/ ʒ /");


        //phu am vo thanh
        mImageUrl2s.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mName2s.add("/ p /");

        mImageUrl2s.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mName2s.add("/ f /");

        mImageUrl2s.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mName2s.add("/ s /");

        mImageUrl2s.add("https://i.redd.it/j6myfqglup501.jpg");
        mName2s.add("/ ʃ /");

        mImageUrl2s.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mName2s.add("/ k /");

        mImageUrl2s.add("https://i.redd.it/k98uzl68eh501.jpg");
        mName2s.add("/ t /");

        mImageUrl2s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName2s.add("/ θ /");

        mImageUrl2s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName2s.add("/ tʃ /");

        //phu am con lai
        mImageUrl3s.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mName3s.add("/ m /");

        mImageUrl3s.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mName3s.add("/ η /");

        mImageUrl3s.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mName3s.add("/ l /");

        mImageUrl3s.add("https://i.redd.it/j6myfqglup501.jpg");
        mName3s.add("/ j /");

        mImageUrl3s.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mName3s.add("/ n /");

        mImageUrl3s.add("https://i.redd.it/k98uzl68eh501.jpg");
        mName3s.add("/ r /");

        mImageUrl3s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName3s.add("/ w /");

        mImageUrl3s.add("https://i.redd.it/glin0nwndo501.jpg");
        mName3s.add("/ tʃ /");

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.consonantView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mName1s, mImageUrl1s);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView2 = findViewById(R.id.consonantView2);
        recyclerView2.setLayoutManager(layoutManager2);
        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(this, mName2s, mImageUrl2s);
        recyclerView2.setAdapter(adapter2);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView3 = findViewById(R.id.consonantView3);
        recyclerView3.setLayoutManager(layoutManager3);
        RecyclerViewAdapter adapter3 = new RecyclerViewAdapter(this, mName3s, mImageUrl3s);
        recyclerView3.setAdapter(adapter3);
    }
}
