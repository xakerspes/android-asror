package aad.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileOutputStream;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView = (ImageView) this.findViewById(R.id.imageView);

        this.button1 = (Button) this.findViewById(R.id.button1);
        this.button2 = (Button) this.findViewById(R.id.button2);
        this.button3 = (Button) this.findViewById(R.id.button3);
        this.button4 = (Button) this.findViewById(R.id.button4);
        this.button5 = (Button) this.findViewById(R.id.button5);
        this.button6 = (Button) this.findViewById(R.id.button6);
        this.button7 = (Button) this.findViewById(R.id.button7);

        this.button1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage1();

            }
        });
        this.button2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage2();

            }
        });
        this.button3.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage3();

            }
        });
        this.button4.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage4();

            }
        });
        this.button5.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage5();

            }
        });
        this.button6.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage6();

            }
        });
//        this.button7.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                showImage7();
//
//            }
//        });
    }

    private void showImage1() {

        this.imageView.setImageResource(R.drawable.asd);
    }

    private void showImage2() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
//
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);

//
                int alpha1 = 255;
                int redValue1 = Color.red(p);
                int greenValue1 = Color.green(p);
                int blueValue1 = Color.blue(p);
                int blur = (int) ((0.299 * redValue1) + (0.587 * greenValue1) + (0.114 * blueValue1));


                bitmap.setPixel(i, j, Color.argb(alpha1, blur, blur, blur));


            }
        }


        this.imageView.setImageBitmap(bitmap);


    }

    private void showImage3() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
//
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);

//                int alpha1 = Color.alpha(p);
                int alpha1 = 150;
                int redValue1 = Color.red(p);
                int greenValue1 = Color.green(p);
                int blueValue1 = Color.blue(p);
                int blur = (redValue1 + greenValue1 + blueValue1) / 3;
                bitmap.setPixel(i, j, Color.argb(alpha1, blur, blur, blur));
            }
        }


        this.imageView.setImageBitmap(bitmap);
    }

    private void showImage4() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);


                int redValue = (int) (Color.red(p) * 0.2126);
                int greenValue = (int) (Color.green(p) * 0.7152);
                int blueValue = (int) (Color.blue(p) * 0.0722);
                int gray = (redValue + greenValue + blueValue);
                bitmap.setPixel(i, j, Color.argb(alpha1, gray, gray, gray));
            }
        }


        this.imageView.setImageBitmap(bitmap);
    }


    private void showImage5() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);
                int redValue = (Color.red(p));
                int greenValue = (Color.green(p));
                int blueValue = (Color.blue(p));


                if ((redValue + greenValue + blueValue) > 600) {
                    bitmap.setPixel(i, j, BLACK);
                }
                if (((redValue + greenValue + blueValue) < 10) && (redValue + greenValue + blueValue) >= 0) {
                    bitmap.setPixel(i, j, WHITE);
                }
            }
        }

        this.imageView.setImageBitmap(bitmap);
    }


    private void showImage6() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);
                int redValue = (int) (Color.red(p) * 1.5);
                int greenValue = (int) (Color.green(p) * 1.5);
                int blueValue = (int) (Color.blue(p) * 1.5);


                if (redValue > 255)
                    redValue = 255;
                if (greenValue > 255)
                    greenValue = 255;
                if (blueValue > 255)
                    blueValue = 255;

//                if ((redValue+greenValue+blueValue)>600) {
                bitmap.setPixel(i, j, Color.rgb(redValue, greenValue, blueValue));
//                }
//                if (((redValue+greenValue+blueValue)<10)  && (redValue+greenValue+blueValue)>=0) {
//                    bitmap.setPixel(i, j, WHITE);
//                }
            }
        }

        this.imageView.setImageBitmap(bitmap);
    }
}
//
//    Bitmap bitmap2;
//    try {
//        FileOutputStream fos = new FileOutputStream(  "bitmap.jpg");
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
//        fos.flush();
//        fos.close();
//    } catch (Exception e) {
//        Log.e("MyLog", e.toString());
//    }
//    }

