package com.example.a0805;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    private int paintColor=0xFF00000;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public SingleTouchView(Context context, AttributeSet attrs){
        super(context,attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(paintColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }
    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        canvasBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        canvasPaint=new Paint(Paint.DITHER_FLAG);
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX,touchY);
                break;
                case MotionEvent.ACTION_UP:
                    drawCanvas.drawPath(path,paint);
                    path.reset();
                    break;
                    default:
                        return false;
        }
        invalidate();
        return true;
    }
    public void setColor(String newColor){
        invalidate();
        paintColor= Color.parseColor(newColor);
        paint.setColor(paintColor);
    }
    public void clearCanvas() {
        path.reset();
        canvasBitmap.eraseColor(Color.TRANSPARENT);
        invalidate();
    }

    public Bitmap getBitmap() {
        return canvasBitmap;
    }
}
public class MainActivity extends AppCompatActivity {
    private SingleTouchView drawView;
    private ImageButton currPaint;
    private static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = findViewById(R.id.drawing);
        LinearLayout paintLayout = findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);

        // 권한 요청
        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        }
    }

    public void clicked(View view) {
        if (view != currPaint) {
            String color = view.getTag().toString();
            drawView.setColor(color);
            currPaint = (ImageButton) view;
        }
    }

    public void saveImage(View view) {
        Bitmap bitmap = drawView.getBitmap();
        String fileName = "drawing_" + System.currentTimeMillis() + ".png";
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);

        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Toast.makeText(this, "Image saved: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearCanvas(View view) {
        drawView.clearCanvas();
    }

    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setType("image/*");
        startActivity(intent);
    }
}