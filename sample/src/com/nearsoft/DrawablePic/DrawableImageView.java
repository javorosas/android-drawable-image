package com.nearsoft.DrawablePic;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jrosas
 * Date: 11/5/12
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableImageView extends ImageView {

    //private Bitmap image;
    private Path currentDrawingPath;
    private Paint currentPaint;
    private List<Path> pathList;

    public DrawableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.image = Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
        this.setOnTouchListener(touchListener);
        currentPaint = new Paint();
        currentPaint.setDither(true);
        currentPaint.setColor(Color.BLACK);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPaint.setStrokeWidth(20);

        pathList = new ArrayList<Path>();
    }

    public Bitmap getBitmap() {
        return ((BitmapDrawable)getDrawable()).getBitmap();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Path path : pathList) {
            canvas.drawPath(path, currentPaint);
            canvas.drawPath(currentDrawingPath, currentPaint);
        }
    }

    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                currentDrawingPath = new Path();
                currentDrawingPath.moveTo(motionEvent.getX(), motionEvent.getY());
                currentDrawingPath.lineTo(motionEvent.getX(), motionEvent.getY());
            }else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                currentDrawingPath.lineTo(motionEvent.getX(), motionEvent.getY());
            }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                currentDrawingPath.lineTo(motionEvent.getX(), motionEvent.getY());
                pathList.add(currentDrawingPath);
            }
            invalidate();
            return true;
        }
    };



}
