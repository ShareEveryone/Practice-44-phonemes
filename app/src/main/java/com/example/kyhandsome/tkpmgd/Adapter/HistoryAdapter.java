package com.example.kyhandsome.tkpmgd.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyhandsome.tkpmgd.DoWord;
import com.example.kyhandsome.tkpmgd.Instruction;
import com.example.kyhandsome.tkpmgd.Model.Word;
import com.example.kyhandsome.tkpmgd.Model.Worddo;
import com.example.kyhandsome.tkpmgd.R;
import com.example.kyhandsome.tkpmgd.Rating;

import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends BaseAdapter {
    private static final String TAG = "WorddoAdapter";

    SharedPreferences sp;
    private Activity context;
    private ArrayList<Worddo> list;
    private TextToSpeech toSpeech;
    private int result;

    // truy xuất đến các biến thành viên
    public HistoryAdapter(Activity context, ArrayList<Worddo> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder {
        TextView txtword;
        ImageView ivrating;
        Button btntry;


    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.textview_history, null);

        toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.US);
                } else {
                    Toast.makeText(context, "Feature not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final HistoryAdapter.Holder holder = new HistoryAdapter.Holder();
        holder.txtword = row.findViewById(R.id.word_word_history);
        holder.ivrating = row.findViewById(R.id.ivrating);
        holder.btntry = row.findViewById(R.id.tryagain);


        final Worddo worddo = list.get(position);

        holder.txtword.setText(worddo.name);

        if (worddo.star == 1){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.star1);
            holder.ivrating.setImageBitmap(bitmap);
        } else {
            if (worddo.star == 2){
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.star2);
                holder.ivrating.setImageBitmap(bitmap);
            } else {
                if (worddo.star == 3){
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.star3);
                    holder.ivrating.setImageBitmap(bitmap);
                }
            }
        }


        sp = context.getSharedPreferences("saveSound", Context.MODE_PRIVATE);

        holder.btntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //chuyển hoạt động sang class khác.
                Intent intent = new Intent(context, Instruction.class);
                intent.putExtra("name_url", worddo.name);
                context.startActivity(intent);
            }
        });

        return row;
    }


}
