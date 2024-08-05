
package com.example.a0805;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MyView5 extends View{
    private List<Circle> circles=new ArrayList<>();
    private Paint paint;

    public MyView5(Context context){
        super(context);
        paint=new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for (Circle circle:circles){
            paint.setColor(circle.color);
            canvas.drawCircle(circle.x,circle.y,circle.radius,paint);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            Random random=new Random();
            float radius = random.nextInt(300);
            int color = Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            float x=event.getX();
            float y=event.getY();

            circles.add(new Circle(x,y,radius,color));
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
    private class Circle{
        float x,y,radius;
        int color;

        Circle(float x,float y,float radius,int color){
            this.x=x;
            this.y=y;
            this.radius=radius;
            this.color=color;
        }
    }
}


public class TouchEvent extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView5 w = new MyView5(this);
        setContentView(w);
    }
}
