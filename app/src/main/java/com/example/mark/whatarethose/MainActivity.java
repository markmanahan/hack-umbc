package com.example.mark.whatarethose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int TAKE_PHOTO_PERMISSION = 1;
    static final int REQUEST_TAKE_PHOTO = 2;

    ImageView shoe_image_view;
    Button take_photo_button;

    static String photo_path = "";

    Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Context lookup
        shoe_image_view = findViewById(R.id.shoe_image_view);
        take_photo_button = findViewById(R.id.take_photo_button);

        // Photo permission lookup
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            take_photo_button.setEnabled(false);
            ActivityCompat.requestPermissions(this,
                    new String[] {  android.Manifest.permission.CAMERA,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    TAKE_PHOTO_PERMISSION);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        // Grant photo permission
        if (requestCode == TAKE_PHOTO_PERMISSION) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                take_photo_button.setEnabled(true);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Set the photo preview to the photo that was just taken
        if (requestCode == REQUEST_TAKE_PHOTO) shoe_image_view.setImageURI(file);

    }

    public void takePhoto(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, REQUEST_TAKE_PHOTO);

    }

    private static File getOutputMediaFile(){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists())
            if (!mediaStorageDir.mkdirs())
                return null;

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        photo_path = mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg";

        return new File(photo_path);

    }

    public void toSearchActivity(View view) {

        Intent intent = new Intent(this, SearchActivity.class);

        intent.putExtra("photo_path", photo_path);

        startActivity(intent);

    }

}
