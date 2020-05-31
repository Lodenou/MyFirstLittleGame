package com.lodenou.mylittlegame.game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread mThread;
    private Boolean isPlaying;
    private Background mBackground1, mBackground2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        mBackground1 = new Background(screenX, screenY, getResources());
        mBackground2 = new Background(screenX, screenY, getResources());
        mBackground2.x =screenX;

    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }
    private void update(){

    }

    private void draw(){

    }

    private void sleep(){
        try {
            Thread.sleep(27);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void resume(){
        isPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
