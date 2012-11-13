package com.nearsoft.DrawablePic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.nearsoft.TapPhoto.R;

import java.io.File;


public class MainActivity extends Activity {

    private static final int PICTURE_TAKEN = 31;
    public static final String PIC_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
    public static final String PIC_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/tmp.jpg";
    public static final String DRAW_INTENT = "DrawActivity";
    private Button btnPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.btnPhoto = (Button) this.findViewById(R.id.btnPhoto);

        this.btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dispatchTakePictureIntent(PICTURE_TAKEN);
            }
        });
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(PIC_PATH);
        photo.getParentFile().mkdirs();
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        startActivityForResult(takePictureIntent, actionCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);    //To change body of overridden methods use File | Settings | File Templates.

        if(requestCode == PICTURE_TAKEN && resultCode == RESULT_OK) {
                handleSmallCameraPhoto(data);
        }
    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeFile(PIC_PATH);
            Intent drawIntent = new Intent(this, DrawActivity.class);
            drawIntent.putExtra(DRAW_INTENT, PIC_PATH);
            startActivity(drawIntent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
        }

    }


}
