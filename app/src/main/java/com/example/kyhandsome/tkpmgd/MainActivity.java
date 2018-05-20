package com.example.kyhandsome.tkpmgd;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{
    public TabHost mtabhost;
    public TextView textview;
    android.support.v7.widget.Toolbar toolbar;
    private static final String TAG = "RecyclerViewAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phát âm");

        mtabhost = findViewById(android.R.id.tabhost);
        LocalActivityManager localActivityManager =new LocalActivityManager(this,false);
        localActivityManager.dispatchCreate(savedInstanceState);
        mtabhost.setup(localActivityManager);

        handle();

    }
    public void handle() {
        //Tạo các tab
        TabHost.TabSpec tab1 = mtabhost.newTabSpec("Tab 1");
        tab1.setIndicator("Nguyên âm");
        tab1.setContent(new Intent(this, Vowel.class));
        mtabhost.addTab(tab1);

        TabHost.TabSpec tab2 = mtabhost.newTabSpec("Tab 2");
        tab2.setIndicator("Phụ âm");
        tab2.setContent(new Intent(this, Consonant.class));
        mtabhost.addTab(tab2);

        mtabhost.setOnTabChangedListener(this);                        //xác định tab hiện tại
        mtabhost.getTabWidget().setDividerDrawable(null);
        if (Integer.parseInt(Build.VERSION.SDK) >= Build.VERSION_CODES.HONEYCOMB) {
            mtabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        }
        final TabWidget tw = (TabWidget) mtabhost.findViewById(android.R.id.tabs);

        for (int i = 0; i < mtabhost.getTabWidget().getChildCount(); i++) {                //thiết lập chiều rọng cho tabhost
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = (TextView) tabView.findViewById(android.R.id.title);             //thiết lập size text

            //tắt in hoa
            textview = (TextView) mtabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            textview.setTextColor(getResources().getColor(R.color.white));                          //thây đổi màu

            mtabhost.getTabWidget().getChildAt(mtabhost.getCurrentTab()).setBackgroundResource(R.color.white);
            textview = (TextView) mtabhost.getCurrentTabView().findViewById(android.R.id.title);
            textview.setTextColor(getResources().getColor(R.color.black));

        }
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
                Intent intent = new Intent(MainActivity.this, History.class);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        int pos = this.mtabhost.getCurrentTab();         //gán vị trí tab
        //vòng for chạy vs các tab k phải là hiện ại
        for (int i = 0; i < mtabhost.getTabWidget().getChildCount(); i++) {
            mtabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.red);        //đổi màu nền
            textview = (TextView) mtabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            textview.setTextColor(getResources().getColor(R.color.white));                  //đổi màu text
        }
        mtabhost.getTabWidget().getChildAt(mtabhost.getCurrentTab()).setBackgroundResource(R.color.white); //đổi màu nền
        textview = (TextView) mtabhost.getCurrentTabView().findViewById(android.R.id.title);
        textview.setTextColor(getResources().getColor(R.color.black));                                     //đổi màu text
    }
}
