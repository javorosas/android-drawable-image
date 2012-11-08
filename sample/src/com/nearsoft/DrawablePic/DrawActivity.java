package com.nearsoft.DrawablePic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.nearsoft.TapPhoto.R;

/**
 * Created with IntelliJ IDEA.
 * User: jrosas
 * Date: 11/6/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawActivity extends Activity {

    DrawableImageView drawableView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);

        this.drawableView = (DrawableImageView) this.findViewById(R.id.drawableView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);    //To change body of overridden methods use File | Settings | File Templates.
        Intent intent = this.getIntent();
        String path = intent.getStringExtra(MainActivity.DRAW_INTENT);
        Bitmap bitmap = loadPic(path);
        drawableView.setImageBitmap(bitmap);
    }

    private Bitmap loadPic(String path) {
        // Get the dimensions of the View
        int targetW = drawableView.getWidth();
        int targetH = drawableView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = (int)Math.min((float)photoW / (float)targetW, (float)photoH / (float)targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        return bitmap;
    }
}