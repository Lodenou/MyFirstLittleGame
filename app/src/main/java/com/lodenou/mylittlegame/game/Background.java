package com.lodenou.mylittlegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lodenou.mylittlegame.R;

public class Background {

    int x, y;
    Bitmap background;

    Background (int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.clover);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }
}
