package com.example.a0805;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class MyView4 extends View{
    public MyView4(Context context){
        super(context);
        setBackgroundColor(Color.YELLOW);
    }
    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(100);

        Typeface t;
        t=Typeface.create(Typeface.DEFAULT,Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("DEFAULT",10,200,paint);

        t=Typeface.create(Typeface.SERIF,Typeface.ITALIC);
        paint.setTypeface(t);
        canvas.drawText("SERIF",10,300,paint);

        t=Typeface.create(Typeface.SANS_SERIF,Typeface.ITALIC);
        paint.setTypeface(t);
        canvas.drawText("SANS_SERIF",10,400,paint);
    }
}

public class GraphicText extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView4 w = new MyView4(this);
        setContentView(w);

    }
}
