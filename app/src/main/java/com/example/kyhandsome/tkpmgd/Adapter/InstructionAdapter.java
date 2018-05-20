package com.example.kyhandsome.tkpmgd.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.kyhandsome.tkpmgd.Model.Sound;
import com.example.kyhandsome.tkpmgd.R;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;

public class InstructionAdapter extends BaseAdapter {
    private static final String TAG = "InstructionAdapter";
    private YouTubePlayerSupportFragment youTubePlayerFragment;

    SharedPreferences sp;

    private MediaPlayer mediaPlayer;
    private Activity context;
    private ArrayList<Sound> list;

    // truy xuất đến các biến thành viên
    public InstructionAdapter(Activity context, ArrayList<Sound> list) {
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
        TextView textVowel;
        TextView textInstruction;
        VideoView videoView;

        ImageView imageView;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.textview_instruction, null);

        final Holder holder = new Holder();

        holder.videoView = row.findViewById(R.id.video);
        holder.textVowel = row.findViewById(R.id.textnguyenam);
        holder.textInstruction = row.findViewById(R.id.textinstruction);

        holder.imageView = row.findViewById(R.id.playaudio);

        final Sound sound = list.get(position);

        holder.textVowel.setText(sound.name);
        holder.textInstruction.setText(sound.textinstruction);

        holder.textInstruction.setMovementMethod(new ScrollingMovementMethod());

        sp = context.getSharedPreferences("saveSound", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id_sound", sound.id);
        editor.putString("name_sound", sound.name);
        editor.apply();

        String url = sound.videourl;
        //String url = ("android.resource://" + context.getPackageName() + "/" + R.raw.u2);
        if (url == null){
            url = "https://mp4.tienganh123.com/baihoc/pronunciation/bai2-short-i_noneVIP.mp4";
        }

        Uri videoUri = Uri.parse(url);
        holder.videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(context);
        holder.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(holder.videoView);


        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                //holder.videoView.start();
            }
        });
        String audio = sound.audiourl; // your URL here
        if (audio == null) audio = "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/t/thi/thing/thing__us_1.mp3";
        Uri aUri = Uri.parse(audio);
        mediaPlayer = MediaPlayer.create(context, aUri);

        holder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    holder.videoView.start();

            }

        });



       /* holder.btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btnplay.getText()=="play"){

                    String url = sound.videourl;
                    //String url = ("android.resource://" + context.getPackageName() + "/" + R.raw.u2);
                    if (url == null){
                        url = "https://mp4.tienganh123.com/baihoc/pronunciation/bai2-short-i_noneVIP.mp4";
                    }

                    Uri videoUri = Uri.parse(url);
                    holder.videoView.setVideoURI(videoUri);

                    MediaController mediaController = new MediaController(context);
                    holder.videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(holder.videoView);
                    holder.btnplay.setText("pause");

                    holder.videoView.start();
                    holder.btnplay.setBackground(context.getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                } else {
                    holder.videoView.pause();
                    holder.btnplay.setText("play");
                    holder.btnplay.setBackground(context.getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                }

            }
        });*/

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                new CountDownTimer(500, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        holder.imageView.setImageResource(R.drawable.ic_volume_up_black_24dp_red);
                    }

                    @Override
                    public void onFinish() {
                        holder.imageView.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    }
                }.start();

            }
        });


        return row;
    }
}