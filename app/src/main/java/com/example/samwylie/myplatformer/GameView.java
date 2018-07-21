package com.example.samwylie.myplatformer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private List<CharacterSprite> characters;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int hight) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        characters = new ArrayList<>();

        characters.add(new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.fourball), 100, 100, 5, 11));
        characters.add(new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.germanflag), 500, 0, 10, 7));
        characters.add(new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.firefox), 0, 500, 12, 4));
        characters.add(new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.amazonstich), 300, 150, 14, 9));


        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch (InterruptedException ex) {

            }

            retry = false;
        }
    }

    public void update() {

        for (CharacterSprite c : characters) {
            c.update(characters);;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas != null) {

            for (CharacterSprite c : characters) {
                c.draw(canvas);
            }
        }

    }
}

