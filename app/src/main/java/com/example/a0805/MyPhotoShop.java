package com.example.a0805;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyPhotoShop extends AppCompatActivity {
    float scaleX = 1.0f , scaleY = 1.0f,rotateAngle;
    MyView10 displayView;

    public class MyView10 extends View{
        public MyView10(MyPhotoShop context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint = new Paint();

            int centerX = getWidth()/2;
            int centerY = getHeight()/2;
            canvas.scale(scaleX,scaleY,centerX,centerY);
            canvas.rotate(rotateAngle,centerX,centerY);

            Bitmap picutre= BitmapFactory.decodeResource(getResources(),R.drawable.lena);
            canvas.drawBitmap(picutre,0,0,paint);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myphotoshop);
        LinearLayout layout = (LinearLayout) findViewById(R.id.imageDisplay);
        displayView = new MyView10(this);
        layout.addView(displayView);
    }
    public void expand(View view) {
        scaleX += 0.3;
        scaleY += 0.3;
        displayView.invalidate();
    }
    public void shrink(View view) {
        scaleX -= 0.3;
        scaleY -= 0.3;
        displayView.invalidate();
    }
    public void rotateLeft(View view) {
        rotateAngle += 30;
        displayView.invalidate();
    }
    public void rotateRight(View view) {
        rotateAngle -= 30;
        displayView.invalidate();
    }
}
