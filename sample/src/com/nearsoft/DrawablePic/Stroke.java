package com.nearsoft.DrawablePic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created with IntelliJ IDEA.
 * User: jrosas
 * Date: 11/13/12
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class Stroke {
    private Path path;
    private Paint paint;

    public Stroke() {
        this.path = new Path();
        this.paint = new Paint();

        this.paint.setDither(true);
        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(20);
    }

    public Path getPath() {
        return path;

    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void Draw (Canvas canvas) {
        canvas.drawPath(this.path, this.paint);
    }
}
