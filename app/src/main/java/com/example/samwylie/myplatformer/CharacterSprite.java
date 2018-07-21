package com.example.samwylie.myplatformer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

//This is where we do the calculations for a single Character
public class CharacterSprite {

    private Bitmap image;
    private int imageWidth;
    private int imageHeight;

    private int x,y;
    private int xVelocity;
    private int yVelocity;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public CharacterSprite(Bitmap bmp, int initialX, int initialY, int xSpeed, int ySpeed ) {
        image = bmp;
        x = initialX;
        y = initialY;

        imageWidth = bmp.getWidth();
        imageHeight = bmp.getHeight();

        xVelocity = xSpeed;
        yVelocity = ySpeed;

    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(List<CharacterSprite> otherCharacters) {

        if (isTouchingEdgeOfScreen()) {
            reverseXDirection();
        }

        if (isTouchingTopOrBottomOfScreen()) {
           reverseYDirection();
        }

        for (CharacterSprite otherCharacter : otherCharacters) {

            if (otherCharacter != this) {

                if (this.isTouching(otherCharacter)) {
                    bounce();
                    otherCharacter.bounce();
                }

            }
        }

        x = x + xVelocity;
        y = y + yVelocity;
    }

    private boolean isTouchingEdgeOfScreen() {
        return (x + imageWidth > screenWidth || x< 0);
    }

    private boolean isTouchingTopOrBottomOfScreen() {
        return (y + imageHeight > screenHeight || y < 0);
    }

    private void reverseXDirection() {
        xVelocity *= -1;
    }

    private void reverseYDirection() {
        yVelocity *= -1;
    }

    public boolean isTouching(CharacterSprite otherCharacter) {

        return (getRight() >= otherCharacter.getLeft()
                && getRight() <= otherCharacter.getRight()
                && getBottom() >= otherCharacter.getTop()
                && getBottom() <= otherCharacter.getBottom());
    }

    public void bounce() {
        reverseXDirection();
        reverseYDirection();
    }

    public int getLeft() {
        return x;
    }

    public int getTop() {
        return y;
    }

    public int getRight() {
        return x+imageWidth;
    }

    public int getBottom() {
        return y+imageHeight;
    }
}
