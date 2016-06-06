package com.monster.massivexov10;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;

import android.content.Intent;

import android.os.Bundle;



import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends Activity implements OnClickListener {

    BluetoothAdapter BA;
    private ViewGroup hiddenPanel;
    private ViewGroup splashview;
    private boolean isPanelShown;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hiddenPanel = (ViewGroup)findViewById(R.id.hiddenpanel);
        hiddenPanel.setVisibility(View.INVISIBLE);
        splashview = (ViewGroup) findViewById(R.id.splashid);
        splashview.setVisibility(View.VISIBLE);
        isPanelShown = false;

        // Set up Click listeners for all the buttons

        TextView newButton = (TextView) findViewById(R.id.btn_newgame);
        newButton.setOnClickListener(this);
        TextView howToPlayButton = (TextView) findViewById(R.id.btn_htp);
        howToPlayButton.setOnClickListener(this);
        TextView aboutButton = (TextView) findViewById(R.id.btn_abtus);
        aboutButton.setOnClickListener(this);
        TextView exitButton = (TextView) findViewById(R.id.btn_exit);
        exitButton.setOnClickListener(this);
        BA = BluetoothAdapter.getDefaultAdapter();



        final Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.bottom_up);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2250);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            splashview.setVisibility(View.INVISIBLE);
                            hiddenPanel.startAnimation(bottomUp);
                            hiddenPanel.setVisibility(View.VISIBLE);
                            isPanelShown = true;
                        }
                    });

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        timer.start();
    }

    /** Start a new game with the given difficulty level */
    private void startGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.btn_newgame:
                startGame();
                break;
            case R.id.btn_htp:
                Intent i = new Intent(this, Howtoplayview.class);
                startActivity(i);
                break;
            case R.id.btn_abtus:
                Intent j = new Intent(this, About.class);
                startActivity(j);
                break;
            case R.id.btn_exit:
                this.finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
