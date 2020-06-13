package com.lodenou.mylittlegame.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread mThread;
    private Boolean isPlaying;
    public int screenX, screenY;
    private Paint paint;
    private Background mBackground1, mBackground2;
    private Float screenRatioX, screenRatioY;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        mBackground1 = new Background(screenX, screenY, getResources());
        mBackground2 = new Background(screenX, screenY, getResources());
        mBackground2.x =screenX;
        screenRatioX = 1920f / screenX;
        screenRatioY =  1080f / screenY;
        paint = new Paint();
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
        mBackground1.x -= 10*screenRatioX;
        mBackground2.x -= 10*screenRatioX;

        if (mBackground1.x + mBackground1.background.getWidth() < 0) {
            mBackground1.x = screenX;
        }
        if (mBackground2.x + mBackground2.background.getWidth() < 0) {
            mBackground2.x = screenX;
        }
    }

    private void draw(){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(mBackground1.background, mBackground1.x, mBackground1.y, paint);
            canvas.drawBitmap(mBackground2.background, mBackground2.x, mBackground1.y, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
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
//        try {
            isPlaying = false;
//            mThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
