package com.example.kyhandsome.tkpmgd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyhandsome.tkpmgd.Adapter.WordAdapter;
import com.example.kyhandsome.tkpmgd.Database.Database;
import com.example.kyhandsome.tkpmgd.Model.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class DoWord extends AppCompatActivity{
    private static final String TAG = "DoWord";
    SharedPreferences sp;
    final String DATABASE_NAME = "DBtkpm.sqlite";
    SQLiteDatabase database;
    ArrayList<Word> list = new ArrayList<Word>();
    WordAdapter adapter;
    android.support.v7.widget.Toolbar toolbar;
    TextView textView;
    TextToSpeech toSpeech;
    int result;
    ListView listView;
    ImageButton micro;
    TextView tv1, tv2, tv3;
    ImageView nextword, btnheer;
    int correct = 0;
    int wrong = 0;

    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_screen);
        sp = DoWord.this.getSharedPreferences("saveSound", Context.MODE_PRIVATE);

        random = new Random();
        addControls();
        readData();
        getSpeechInput();
        ClickHeer();

        textView = findViewById(R.id.word);
        toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phát âm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.US);
                } else {
                    Toast.makeText(getApplicationContext(), "Feature not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public  void ClickHeer() {
        btnheer = findViewById(R.id.btnheer);
        btnheer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
            }
        });
    }
    public void getSpeechInput() {
        micro = findViewById(R.id.micro);
        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()) {

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    CreateRandomAudioFileName(5) + "AudioRecording.3gp";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    requestPermission();
                }

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(DoWord.this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
	}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv1 = findViewById(R.id.word);
        tv2 = findViewById(R.id.star);
        tv3 = findViewById(R.id.textnotification);
        nextword = findViewById(R.id.nextword);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String res = result.get(0);
                   // nextword.setVisibility(View.VISIBLE);

                    if (res.length() != tv1.getText().toString().length()) {
                        tv3.setText("TRY AGAIN");
                        tv3.setTextSize(30);
                        tv3.setTextColor(getResources().getColor(R.color.red));
                        tv2.setText(result.get(0));
                        tv2.setTextColor(getResources().getColor(R.color.red));
                        wrong++;
                    } else {
                        char[] rescharArray = res.toCharArray();
                        char[] wordcharArray = tv1.getText().toString().toCharArray();

                        int temp = 0;
                        for (int i = 0; i < rescharArray.length; i++) {
                            if (wordcharArray[i] == rescharArray[i]) {
                                temp++;
                            }
                        }
                        if (temp == rescharArray.length) {
                            tv2.setText(result.get(0));
                            tv2.setTextColor(getResources().getColor(R.color.green));
                            tv3.setText("EXCELLENT");
                            tv3.setTextSize(30);
                            tv3.setTextColor(getResources().getColor(R.color.green));
                            correct++;

                        } else {
                            tv2.setText(result.get(0));
                            tv2.setTextColor(getResources().getColor(R.color.red));
                            tv3.setText("TRY AGAIN");
                            tv3.setTextSize(30);
                            tv3.setTextColor(getResources().getColor(R.color.red));
                            wrong++;
                        }

                    }

                    mediaRecorder.stop();
                }
                break;

        }

        Log.d(TAG, "wrong " + wrong);
        Log.d(TAG, "correct " + correct);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("correct", correct);
        editor.putInt("wrong", wrong);
        editor.apply();
    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(DoWord.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(DoWord.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DoWord.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
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
                Intent intent = new Intent(DoWord.this, History.class);
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

    private void addControls() {
        listView = (ListView) findViewById(R.id.listdoword);
        listView.getCount();
        list = new ArrayList<>();
        adapter = new WordAdapter(DoWord.this, list);
        listView.setAdapter(adapter);
    }
    // Hàm đọc dữ liệu
    private void readData() {
        int idSound = sp.getInt("id_sound", 1);
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from word where word.id_sound = '" + idSound +"'" , null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int id_sound = cursor.getInt(2);
            String mean = cursor.getString(3);
            String decription = cursor.getString(4);
            int complete = cursor.getInt(5);

            list.add(new Word(id, name, id_sound, mean, decription, complete));

        }

//        adapter.notifyDataSetChanged();
        cursor.close();
        database.close();
    }
}
