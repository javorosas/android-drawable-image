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
    private Stroke currentStroke;
    private List<Stroke> strokeList;

    public DrawableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(touchListener);
        currentStroke = new Stroke();
        strokeList = new ArrayList<Stroke>();
    }

    public Bitmap getBitmap() {
        return ((BitmapDrawable)getDrawable()).getBitmap();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        currentStroke.Draw(canvas);
        for (Stroke stroke : strokeList) {
            stroke.Draw(canvas);
        }
    }

    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Path currentPath = currentStroke.getPath();
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                currentStroke = new Stroke();
                currentPath = currentStroke.getPath();
                currentPath.moveTo(motionEvent.getX(), motionEvent.getY());
                currentPath.lineTo(motionEvent.getX(), motionEvent.getY());
            }else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                currentPath.lineTo(motionEvent.getX(), motionEvent.getY());
            }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                currentPath.lineTo(motionEvent.getX(), motionEvent.getY());
                strokeList.add(currentStroke);
            }
            invalidate();
            return true;
        }
    };



}
