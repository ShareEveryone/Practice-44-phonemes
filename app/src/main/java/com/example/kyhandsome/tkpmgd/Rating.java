package com.example.kyhandsome.tkpmgd;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyhandsome.tkpmgd.Database.Database;
import com.example.kyhandsome.tkpmgd.Database.DatabaseHelper;

public class Rating extends AppCompatActivity{
    private static final String TAG = "Rating";
    SharedPreferences sp;
    Button btnconinue;
    DatabaseHelper myDb;
    ImageView imvdiem;
    TextView textrating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_screen);
        sp = Rating.this.getSharedPreferences("saveSound", Context.MODE_PRIVATE);


        myDb = new DatabaseHelper(this);
        btnconinue = findViewById(R.id.btncontinue);
        imvdiem = findViewById(R.id.diem);
        textrating = findViewById(R.id.totlam);
        int wrong = sp.getInt("wrong", 1);
        int correct = sp.getInt("correct", 1);

        if (wrong > correct){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.star1);
            imvdiem.setImageBitmap(bitmap);
            textrating.setText("Cố gắng");
            AddData(1);
        } else {
            if (wrong <= correct && wrong != 0){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.star2);
                imvdiem.setImageBitmap(bitmap);
                textrating.setText("Tốt lắm");
                AddData(2);
            }else {
                if (wrong == 0) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star3);
                    imvdiem.setImageBitmap(bitmap);
                    textrating.setText("Tuyệt vời");
                    AddData(3);
                }
            }
        }
    }
    public  void AddData(final int star) {
        final int idSound = sp.getInt("id_sound", 1);
        final String nameSound = sp.getString("name_sound", "");
        Log.d(TAG, "lenghtttttttttt " + idSound);
        Log.d(TAG, "lenghtttttttttt " + nameSound);

        btnconinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (myDb.getIdSound(idSound) == 0) {
                            boolean isInserted = myDb.insertData(nameSound, String.valueOf(idSound), String.valueOf(star));
                            if (isInserted == true) {
                                Toast.makeText(Rating.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(Rating.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            boolean isUpdate = myDb.updateData(String.valueOf(myDb.getId(idSound)), nameSound, String.valueOf(idSound), String.valueOf(star));
                            if (isUpdate == true) {
                                Toast.makeText(Rating.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(Rating.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                        Intent intent = new Intent(Rating.this, MainActivity.class);
                       startActivity(intent);
                   }
                }
        );
    }
}
