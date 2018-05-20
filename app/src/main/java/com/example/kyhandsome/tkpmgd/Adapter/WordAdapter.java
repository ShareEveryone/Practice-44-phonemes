package com.example.kyhandsome.tkpmgd.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyhandsome.tkpmgd.DoWord;
import com.example.kyhandsome.tkpmgd.History;
import com.example.kyhandsome.tkpmgd.Instruction;
import com.example.kyhandsome.tkpmgd.Model.Word;
import com.example.kyhandsome.tkpmgd.R;
import com.example.kyhandsome.tkpmgd.Rating;

import java.util.ArrayList;
import java.util.Locale;

public class WordAdapter extends BaseAdapter{
    private static final String TAG = "InstructionAdapter";

    SharedPreferences sp;
    private Activity context;
    private ArrayList<Word> list;
    private TextToSpeech toSpeech;
    private int result;

    // truy xuất đến các biến thành viên
    public WordAdapter(Activity context, ArrayList<Word> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return 1;
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
        TextView txtnotification;
        TextView txtdecription;
        TextView txtmean;
        ImageView nextview;
        ImageView speaker;
        TextView speaktext;

    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.textviewdoword, null);

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
        final WordAdapter.Holder holder = new WordAdapter.Holder();
        holder.txtword = row.findViewById(R.id.word);
        holder.txtdecription = row.findViewById(R.id.decription);
        holder.txtmean = row.findViewById(R.id.mean);
        holder.nextview = row.findViewById(R.id.nextword);
        holder.speaker = row.findViewById(R.id.voice_work);
        holder.speaktext = row.findViewById(R.id.star);
        holder.txtnotification = row.findViewById(R.id.textnotification);

        final Word word = list.get(position);

        holder.txtword.setText(word.name);
        holder.txtdecription.setText(word.decription);
        holder.txtmean.setText(word.mean);

        sp = context.getSharedPreferences("saveSound", Context.MODE_PRIVATE);
        String speaktxt = sp.getString("speaktext", "");
        holder.speaktext.setText(speaktxt);
      //  holder.nextview.setVisibility(View.GONE);

        final int[] b = {1};
        for (int i = position; i <= list.size(); i++)
            holder.nextview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CountDownTimer(500, 100) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            holder.nextview.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp_red);
                        }

                        @Override
                        public void onFinish() {
                            holder.nextview.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                        }
                    }.start();

                    if ((position + b[0]) < list.size()) {
                        holder.speaktext.setText("");
                        holder.txtword.setText(list.get(position + b[0]).name);
                        holder.txtdecription.setText(list.get(position + b[0]).decription);
                        holder.txtnotification.setText("Chạm vào micro để nói và nhận phản hồi về âm dược gạch dưới.");
                        holder.txtnotification.setTextColor(context.getResources().getColor(R.color.black));
                        holder.txtnotification.setTextSize(14);
                        b[0] = b[0] + 1;
                    } else {
                        Intent intent = new Intent(context, Rating.class);
                        context.startActivity(intent);
                    }
               //     holder.nextview.setVisibility(View.GONE);
                }
            });

        holder.speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(context, "Feature not supported", Toast.LENGTH_SHORT).show();
                } else {
                    String text = holder.txtword.getText().toString();
                    toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
                new CountDownTimer(500, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        holder.speaker.setImageResource(R.drawable.ic_volume_up_black_24dp_red);
                    }

                    @Override
                    public void onFinish() {
                        holder.speaker.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    }
                }.start();
            }
        });
        return row;
    }


}
