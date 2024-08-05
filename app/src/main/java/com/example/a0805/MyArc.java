package com.example.a0805;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class MyView2 extends View{
    private Paint mPaints,mFramePaint;
    private RectF mBigOval;
    private float mStart,mSweep;

    private static final float SWEEP_INC=2;
    private static final float START_INC=15;

    public MyView2(Context context){
        super(context);

        mPaints=new Paint();
        mPaints.setAntiAlias(true);
        mPaints.setStyle(Paint.Style.FILL);
        mPaints.setColor(0x88FF0000);

        mFramePaint = new Paint();
        mFramePaint.setAntiAlias(true);
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setStrokeWidth(3);
        mFramePaint.setColor(0x8800FF00);
        mBigOval=new RectF(40,10,900,1000);
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.YELLOW);
        canvas.drawRect(mBigOval,mFramePaint);
        canvas.drawArc(mBigOval,mStart,mSweep,false,mPaints);
        mSweep+=SWEEP_INC;
        if(mSweep>=360){
            mSweep -=360;
            mStart+=START_INC;
            if(mStart>=360){
                mStart-=360;
            }
        }
        invalidate();
    }
}

public class MyArc extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView2(this));
    }
}
