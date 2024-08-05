package com.example.a0805;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class MyView7 extends View{
    public MyView7(Context context){
        super(context);
        setBackgroundColor(Color.YELLOW);
    }
    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        Matrix m = new Matrix();
        m.preScale(1,-1);
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.house);
        Bitmap mb=Bitmap.createBitmap(b,0,0,b.getWidth(),b.getHeight(),m,false);
        Bitmap sb = Bitmap.createScaledBitmap(b,600,600,false);
        canvas.drawBitmap(b,0,0,null);
        canvas.drawBitmap(mb,100,100,null);
        canvas.drawBitmap(sb,200,200,null);

    }
}

public class ImageDsip extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView7 w = new MyView7(this);
        setContentView(w);
    }
}
