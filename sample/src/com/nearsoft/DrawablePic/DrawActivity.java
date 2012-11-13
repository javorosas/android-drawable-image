package com.nearsoft.DrawablePic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.nearsoft.TapPhoto.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.MalformedInputException;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save_pic:
                savePic();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void savePic() {
        File file = new File(MainActivity.PIC_DIR + "/app" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        Bitmap bitmap = drawableView.getBitmap();

        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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