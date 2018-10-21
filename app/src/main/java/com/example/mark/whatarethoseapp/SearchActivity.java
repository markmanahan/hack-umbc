package com.example.mark.whatarethoseapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<RetailerInfo> list;
    private RetailListAdapter adapter;
    TextView nameTxtView, brandTxtView, priceTxtView;
    ImageView ogshoeImgView, shoeImgView;
    RecyclerView rV_retailer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle extras = getIntent().getExtras();
        String ogImgPath = "";

        if (extras != null) {
            ogImgPath = extras.getString("photo_path");
            // and get whatever type user account id is
        }

        intializeViews();

        File imgFile = new  File(ogImgPath);

        if(imgFile.exists()){
            final Bitmap bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ogshoeImgView.post(new Runnable() {
                @Override
                public void run() {
                    int maxWidth = ogshoeImgView.getWidth();
                    int maxHeight = ogshoeImgView.getHeight();
                    ogshoeImgView.setImageBitmap(scaleBitmap(bm, maxWidth, maxHeight));
                }
            });
        }

        shoeImgView.post(new Runnable() {
            @Override
            public void run() {
                int maxWidth = shoeImgView.getWidth();
                int maxHeight = shoeImgView.getHeight();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                shoeImgView.setImageBitmap(scaleBitmap(bitmap, maxWidth, maxHeight));
            }
        });

        list = new ArrayList<>();
        adapter = new RetailListAdapter(getApplicationContext(), list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rV_retailer.setLayoutManager(mLayoutManager);
        rV_retailer.setItemAnimator(new DefaultItemAnimator());
        rV_retailer.setAdapter(adapter);

        prepareList();
    }

    private Bitmap scaleBitmap(Bitmap bm, int maxWidth, int maxHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Log.v("Pictures", "Width and height are " + width + "--" + height);

        if (width > maxWidth || height > maxHeight) {
            if (width > height) {
                // landscape
                float ratio = (float) width / maxWidth;
                width = maxWidth;
                height = (int) (height / ratio);
            } else if (height > width) {
                // portrait
                float ratio = (float) height / maxHeight;
                height = maxHeight;
                width = (int) (width / ratio);
            } else if (height == width) {
                // square
                height = maxHeight;
                width = maxWidth;
            }
        } else {
            return bm;
        }

        Log.v("Pictures", "after scaling Width and height are " + width + "--" + height);

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }

    private void intializeViews() {
        nameTxtView = findViewById(R.id.txtView_ShoeName);
        brandTxtView = findViewById(R.id.txtView_Brand);
        priceTxtView = findViewById(R.id.txtView_price);
        ogshoeImgView = findViewById(R.id.img_OriginalImage);
        shoeImgView = findViewById(R.id.img_NewImage);
        rV_retailer = findViewById(R.id.rV_retailers);

    }
    

    private void prepareList() {
        RetailerInfo a = new RetailerInfo("Foot Locker", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        list.add(a);
        a = new RetailerInfo("Adidas", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        list.add(a);
        a = new RetailerInfo("Nike", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        list.add(a);

        adapter.notifyDataSetChanged();
    }

}
