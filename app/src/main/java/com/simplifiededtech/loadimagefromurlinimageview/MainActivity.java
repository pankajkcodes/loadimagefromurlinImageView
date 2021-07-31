package com.simplifiededtech.loadimagefromurlinimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);


        //-<<<<<<<<<<<<<<<- For Set Image On Image View Call Method->>>>>>>>>>>-//
        new ImageLoadTask("https://cdn.pixabay.com/photo/2021/07/12/05/02/mazarine-blue-6405362__480.jpg", imageView).execute();
        new ImageLoadTask("https://cdn.pixabay.com/photo/2021/07/12/05/02/mazarine-blue-6405362__480.jpg", imageView2).execute();
        new ImageLoadTask("https://cdn.pixabay.com/photo/2021/07/12/05/02/mazarine-blue-6405362__480.jpg", imageView3).execute();
    }


    //<-------------- This Method Load Images From URL In Background------------------>//
    public static class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;


        //<---------Constructor For Take Url and Set image-------->//
        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //<------Set result of image on ImagesView---->//
            imageView.setImageBitmap(result);
        }

    }
}