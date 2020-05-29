package com.lodenou.mylittlegame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;


public class StartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean mIsBound = false;
    private MusicService mServ;
    ImageButton mImageButton;
    Button new_game_button;
    private String[] mClasses = {"Guerrier","Magicien","Moine", "Ninja", "Samouraï","Paysan"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        musicOn();
        muteSound();
        showDialog();

    }
    public void muteSound() {
        mImageButton = findViewById(R.id.mute_sound_button);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(mServ.mPlayer.isPlaying()) {
                        mServ.mPlayer.pause();
                        mImageButton.setImageResource(R.drawable.ic_volume_off_black_24dp);
                    }
                    else {
                        mServ.mPlayer.start();
                        mImageButton.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    }
            }
        });
    }

    public void startNewGame() {

    }

    //BIND Music Service
    public void musicOn() {
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
    }

    //Bind/Unbind music service
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Detect idle screen
        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isInteractive();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //UNBIND music service
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }

    public void initiateSpinner() {
        //SPINNER SETTINGS

        //Getting the instance of Spinner and applying OnItemSelectedListener on it

        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the rooms' list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mClasses);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner and nothingselected
        spin.setAdapter(new NothingSelectedSpinnerAdapter(
                aa,
                R.layout.contact_spinner_row_nothing_selected,
                this));
        spin.setPrompt("Select your class");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showDialog(){
        new_game_button = findViewById(R.id.first_button);
        new_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(StartActivity.this,"");
            }
        });
    }
}
