package com.example.a0805;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class MyView6 extends View {
    private Drawable image;
    private ScaleGestureDetector gestureDetector;
    private float scale=1.0f;

    public MyView6(Context context) {
            super(context);
            image = context.getResources().getDrawable(R.drawable.ic_launcher_foreground);
            setFocusable(true);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicWidth());
            gestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //canvas.save();
        canvas.scale(scale,scale);
        image.draw(canvas);
        //canvas.restore();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector){
            scale *= detector.getScaleFactor();
            if(scale<0.1f){
                scale=0.1f;
            }else if(scale>10.0f){
                scale=10.0f;
            }
            return true;

            }
        }
    }

public class ImageScale extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView6(this));
    }
}
