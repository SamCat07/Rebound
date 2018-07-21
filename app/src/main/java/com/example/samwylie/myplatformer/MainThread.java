package com.example.samwylie.myplatformer;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    @Override
    public void run() {

        while(running) {
            canvas = null;


            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
            } finally {

                if (canvas != null) {

                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }

    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

